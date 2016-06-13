package cn.hsd.student.activity.logcat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.Logcat_commit_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class sds_Logcat_fabiaoLogcat extends AppCompatActivity implements View.OnTouchListener{
    private  EditText edittext_title;
    private  EditText sds_fabian_time1;
    private  EditText sds_fabian_time2;
    private  EditText sds_fabian_tihui;
    private  EditText sds_fabian_wenti;
    private  EditText sds_fabian_zhouci;
    private  EditText edittext_content;
    private SDS_Httpclient conn;
    private port port80;

    public SDS_ZMHandler sds_zmhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            Toast.makeText(sds_Logcat_fabiaoLogcat.this,""+content,Toast.LENGTH_LONG).show();
            super.onSuccess(content);
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sds__logcat_fabiaologcat);
        sds_fabian_time1 =(EditText) findViewById(R.id.sds_fabian_time1);
        sds_fabian_time2 =(EditText) findViewById(R.id.sds_fabian_time2);
        edittext_title = (EditText) findViewById(R.id.sds_et_logcat_title);
        edittext_content = (EditText) findViewById(R.id.sds_content_logcat);
        sds_fabian_tihui = (EditText) findViewById(R.id.sds_tihui_logcat);
        sds_fabian_wenti = (EditText) findViewById(R.id.sds_wenti_logcat);
        sds_fabian_zhouci = (EditText) findViewById(R.id.logcat_fabiao_zhouci);




        sds_fabian_time1.setOnTouchListener(this);
        sds_fabian_time2.setOnTouchListener(this);

    }

    public void sds_logcat_fabiaoTijiao(View view){
//        System.out.println("http://"+port80.toString()+":8080/MyHsd/webserver/xsxs");

        new Thread(){
            public void run(){
                String path = port.port+"/MyHsd/xsxs";


                conn = new SDS_Httpclient();
                Logcat_commit_model data = new Logcat_commit_model();
                data.setLog_title(edittext_title.getText().toString().trim());
                data.setLog_content(edittext_content.getText().toString().trim());
                data.setLog_week(sds_fabian_zhouci.getText().toString().trim());
                data.setLog_harvest(sds_fabian_tihui.getText().toString().trim());
                //data.setStudent_no(sds);学号
                data.setLog_createStart(sds_fabian_time1.getText().toString().trim());
                data.setLog_createEnd(sds_fabian_time2.getText().toString().trim());
                data.setLog_wenti(sds_fabian_wenti.getText().toString().trim());
                json<Logcat_commit_model> dataVerture = new json<Logcat_commit_model>();
                String returnJson = dataVerture.ObjectToJson1(data);
                conn.Postclient(path,returnJson,sds_zmhandler);
            }
        }.start();

    }



    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.qmf_time, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
           // final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

           // timePicker.setIs24HourView(true);
           // timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
           // timePicker.setCurrentMinute(Calendar.MINUTE);

            if (v.getId() == R.id.sds_fabian_time1) {
                //第一个文本框设置的时间
                final int inType = sds_fabian_time1.getInputType();
                sds_fabian_time1.setInputType(InputType.TYPE_NULL);
                sds_fabian_time1.onTouchEvent(event);
                sds_fabian_time1.setInputType(inType);
                sds_fabian_time1.setSelection(sds_fabian_time1.getText().length());

                builder.setTitle("选取时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                 //       sb.append(timePicker.getCurrentHour())
                 //               .append(":").append(timePicker.getCurrentMinute());

                        sds_fabian_time1.setText(sb);

                    }
                });

            }else if(v.getId() == R.id.sds_fabian_time2){
                //第二个文本框设置的时间
                final int inType = sds_fabian_time2.getInputType();
                sds_fabian_time2.setInputType(InputType.TYPE_NULL);
                sds_fabian_time2.onTouchEvent(event);
                sds_fabian_time2.setInputType(inType);
                sds_fabian_time2.setSelection(sds_fabian_time2.getText().length());

                builder.setTitle("选取时间");
                builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        sb.append("  ");
                        sds_fabian_time2.setText(sb);

                    }
                });


            }

            Dialog dialog = builder.create();
            dialog.show();
        }

        return true;
    }

}
