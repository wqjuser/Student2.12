package cn.hsd.student.activity.leava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.gxq_class.Gxq_apply_dateTimePickDialogUtil;
import cn.hsd.student.activity.gxq_class.gxqStatic;
import cn.hsd.student.activity.model.Gxq_apply_student;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Gxq_apply_message extends AppCompatActivity {
    public String begintime;
    public String overtime;
    public String because;
    String str;
    //Gxq_apply_db2 database;
    public String spinner;
    public String name = "小王";
    public Spinner apply_message_spinner;
    public TextView apply_message_systemtime;
    public String initStartDateTime;
    //= "2013年9月3日 14:44"; // 初始化开始时间
    public EditText apply_message_begintimeshow;
    public EditText apply_message_overtimeshow;
    public EditText apply_message_because;
    public Gxq_apply_student student1;

    private SDS_Httpclient conn;
    //private port port80;
    public EditText et1;
    public EditText et2;
    public EditText daynum;
    public TextView name1;


    public SDS_ZMHandler gxq_zmhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(Gxq_apply_message.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Gxq_apply_message.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gxq_apply_message);
        student1 = new Gxq_apply_student();
        name1 = (TextView) findViewById(R.id.gxq_aqqlyname);
        apply_message_spinner = (Spinner) findViewById(R.id.gxq_aqqly_spinner);
        apply_message_systemtime = (TextView) findViewById(R.id.apply_message_systemtime);
        apply_message_begintimeshow = (EditText) findViewById(R.id.apply_message_begintimeshow);
        apply_message_overtimeshow = (EditText) findViewById(R.id.apply_message_overtimeshow);
        apply_message_because = (EditText) findViewById(R.id.apply_message_because);
        et1 = (EditText) findViewById(R.id.esit1);
        et2 = (EditText) findViewById(R.id.esit2);
        daynum = (EditText) findViewById(R.id.daytime);
        //because = apply_message_because.getText().toString();

        name1.setText(name);
        apply_message_begintimeshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gxq_apply_dateTimePickDialogUtil dateTimePicKDialog2 = new Gxq_apply_dateTimePickDialogUtil(
                        Gxq_apply_message.this, initStartDateTime);
                dateTimePicKDialog2.dateTimePicKDialog(apply_message_begintimeshow);

            }
        });
        //Toast.makeText(Gxq_apply_message.this, "你点击的是:"+begintime, Toast.LENGTH_SHORT).show();
        apply_message_overtimeshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gxq_apply_dateTimePickDialogUtil dateTimePicKDialog1 = new Gxq_apply_dateTimePickDialogUtil(
                        Gxq_apply_message.this, initStartDateTime);
                dateTimePicKDialog1.dateTimePicKDialog(apply_message_overtimeshow);
            }
        });
        //获取当前系统的时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        str = formatter.format(curDate);
        apply_message_systemtime.setText(str);

        final String[] apply_spinner = {"事假", "病假"};
        ArrayAdapter<String> apply_message_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apply_spinner);
        apply_message_spinner.setAdapter(apply_message_adapter);
        apply_message_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // String[] languages = getResources().getStringArray(R.array.languages);
                // Toast.makeText(Gxq_apply_message.this, "你点击的是:"+apply_spinner[position], Toast.LENGTH_SHORT).show();
                spinner = apply_spinner[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void apply_message_click(View view) {
        switch (view.getId()) {
            case R.id.apply_message_tijiao:
                student1.setLeave_begin(apply_message_begintimeshow.getText().toString());
                student1.setLeave_end(apply_message_overtimeshow.getText().toString());
                student1.setStudent_name(name);
                student1.setLeave_content(apply_message_because.getText().toString());
                student1.setCounserlor_name(et2.getText().toString());
                student1.setAdviser_name(et1.getText().toString());//....................
                student1.setLeave_check("未处理");
                student1.setCreate_time(str);
                student1.setLeave_type(spinner);
                student1.setLeave_daynum(daynum.getText().toString());
                gxqStatic.data = student1;
                gxqStatic.apply_student_name = student1.getStudent_name();
                new Thread(){
                    public void run(){
                        String path = port.port+"/xsxs";
                        conn = new SDS_Httpclient();
                        Gxq_apply_student data = new Gxq_apply_student();
                        json<Gxq_apply_student> dataVerture = new json<Gxq_apply_student>();
                        String returnJson = dataVerture.ObjectToJson1(student1);
                        conn.Postclient(path,returnJson,gxq_zmhandler);
                    }
                }.start();
                Intent intent11 = new Intent(Gxq_apply_message.this,Gxq_apply_main.class);
                startActivity(intent11);
                finish();


                break;

            default:
                break;
        }
    }

}
