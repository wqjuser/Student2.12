package cn.hsd.school;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PORT.port;
import cn.hsd.school.model.college_message;
import cn.hsd.school.model.college_notes;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class gxq_college_notes extends AppCompatActivity {

    public Spinner notes_spinner;
    public TextView gxq_systemtime;
    public TextView notes_name;
    public EditText notes_detail;

    String  xueyuan;
    String str;

    List<String> listgxq1 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gxq_college_notes);
        notes_spinner = (Spinner)findViewById(R.id.gxq_notse_spinner);
        gxq_systemtime = (TextView)findViewById(R.id.note_message_systemtime);
        notes_name =(TextView)findViewById(R.id.gxq_notesname);
        notes_detail = (EditText)findViewById(R.id.note_message_because);
        //获取当前系统时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        str = formatter.format(curDate);
        gxq_systemtime.setText(str);
        //选择spinner获取点击事件，以及adapter
//        final String[] apply_spinner = {"计算机科学技术", "中文学院", "体育学院"};
//        ArrayAdapter<String> apply_message_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apply_spinner);
//        notes_spinner.setAdapter(apply_message_adapter);
        notes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // String[] languages = getResources().getStringArray(R.array.languages);
                // Toast.makeText(Gxq_apply_message.this, "你点击的是:"+apply_spinner[position], Toast.LENGTH_SHORT).show();
                xueyuan =listgxq1.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //从登陆那里获取到，校级管理员的姓名
        notes_name.setText("呵呵大");
       //获取到输入的留言信息
       //notes_detail.getText().toString();


    }
    public void notes_click(View view){
  //      Toast.makeText(gxq_college_notes.this,"hhhhh",Toast.LENGTH_SHORT).show();
        final college_notes collge_notes = new college_notes();
        collge_notes.setMessage_detail(notes_detail.getText().toString());
        collge_notes.setMessage_receiveName(xueyuan);//收件人
        collge_notes.setMessage_sendName("呵呵大");
        collge_notes.setMessage_time(str);
        new Thread(){
            public void run(){
                String path = port.port + "/collegenotes.action";
                SDS_Httpclient conn = new SDS_Httpclient();
                json<college_message> dataVerture = new json<college_message>();
                String returnJson = dataVerture.ObjectToJson1(collge_notes);
                conn.Postclient(path,returnJson,gxq_notes_college);
            }
        }.start();

    }
    public SDS_ZMHandler gxq_notes_college = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(gxq_college_notes.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(gxq_college_notes.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };

    //-------------------------------获取学院的名单----------------------------------------------------------------------
    public void connect_college(){
        new Thread(){
            public void run(){
                String path = port.port + "/collegelist.action";
                SDS_Httpclient conn1 = new SDS_Httpclient();

                conn1.Postclient(path,"13",gxq_studentnaehandler);
            }
        }.start();
    }
    public SDS_ZMHandler gxq_studentnaehandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(gxq_college_notes.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            json<String> gxqreturn = new json<String>();
            listgxq1= gxqreturn.jsonToList2(content);

            ArrayAdapter<String> apply_adapter = new ArrayAdapter<String>(gxq_college_notes.this, android.R.layout.simple_spinner_item, listgxq1);
            notes_spinner .setAdapter(apply_adapter);


        }
        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(gxq_college_notes.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        connect_college();
    }
}
