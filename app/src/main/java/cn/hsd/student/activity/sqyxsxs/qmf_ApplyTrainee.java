package cn.hsd.student.activity.sqyxsxs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.apply_commit_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class qmf_ApplyTrainee extends AppCompatActivity implements View.OnTouchListener {

    private EditText student_name;//姓名
    private EditText apply_reason;//申请理由
    private EditText apply_achieve;//成就
    private EditText apply_time;//时间
    private SDS_Httpclient conn;
    public SDS_ZMHandler sds_zmhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            //苏东升版 另两处在qmf_ApplyCon中
            Intent intent=new Intent();
            intent.putExtra("qq",content);
            setResult(0,intent);
            super.onSuccess(content);
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmf__apply_trainee);
        student_name = (EditText) this.findViewById(R.id.qmf_applyname);
        apply_reason = (EditText) this.findViewById(R.id.qmf_applyreason);
        apply_achieve = (EditText) this.findViewById(R.id.qmf_applyachieve);
        apply_time = (EditText) this.findViewById(R.id.qmf_applytime);
        //点击文本框是提示语消失---申请理由
        final String qmfapply_string1 = apply_reason.getHint().toString();
        apply_reason.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            public void onFocusChange(View arg0, boolean hasFocus) {
                if (hasFocus) {
                    apply_reason.setHint(null);
                } else {
                    apply_reason.setHint(qmfapply_string1);
                }
            }

        });
        //点击文本框是提示语消失---申请成就
        final String qmfapply_string2 = apply_achieve.getHint().toString();
        apply_reason.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View arg0, boolean hasFocus) {
                if (hasFocus) {
                    apply_achieve.setHint(null);
                } else {
                    apply_achieve.setHint(qmfapply_string2);
                }
            }
        });
        //日历弹窗，选择日期
        apply_time.setOnTouchListener(this);
    }

    //日历弹窗，选择日期
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.qmf_time, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.MINUTE);

            if (v.getId() == R.id.qmf_applytime) {
                final int inType = apply_time.getInputType();
                apply_time.setInputType(InputType.TYPE_NULL);
                apply_time.onTouchEvent(event);
                apply_time.setInputType(inType);
                apply_time.setSelection(apply_time.getText().length());

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
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());

                        apply_time.setText(sb);

                    }
                });

            }

            Dialog dialog = builder.create();
            dialog.show();
        }

        return true;
    }

    //返回跳转
    public void qmf_ApplyReturnS(View v) {

//       Toast.makeText(qmf_ApplyTrainee.this, "未添加", Toast.LENGTH_LONG).show();

          Intent intent=new Intent();
          intent.setClass(qmf_ApplyTrainee.this,qmf_ApplyCon.class);
          startActivity(intent);
    }

    //提交跳转
    public void qmf_Applyrefer(View view) {
    //上传到服务器

             //   Toast.makeText(qmf_ApplyTrainee.this, "yyyyyyyy", Toast.LENGTH_LONG).show();
                new Thread(){

                    public void run(){
                        String path = port.port+"/xsxs";
                        conn=new SDS_Httpclient();
                        apply_commit_model date =new apply_commit_model();
                        date.setStudent_name(student_name.getText().toString().trim());
                        date.setTrainee_achieve(apply_achieve.getText().toString().trim());
                        date.setTrainee_reason(apply_reason.getText().toString().trim());
                        date.setTrainee_time(apply_time.getText().toString().trim());
                        json<apply_commit_model> dataVerture=new json<apply_commit_model>();
                        String returnJson=dataVerture.ObjectToJson1(date);
                        conn.Postclient(path,returnJson,sds_zmhandler);

                    }
                }.start();
                Intent intent=new Intent();
                intent.setClass(qmf_ApplyTrainee.this,qmf_ApplyCon.class);
                startActivity(intent);
                finish();




    }

}







