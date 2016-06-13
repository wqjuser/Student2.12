package cn.hsd.student.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import PORT.port;
import cn.hsd.login.wqj_Stu_LoginActivity;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.intership_info_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class InfoActivity extends AppCompatActivity {
    private EditText et1;//intership_name
    private EditText et2;//intership_major
    private EditText et3;//intership_train
    private EditText et4;//intership_end
    private EditText et5;//
    private EditText et6;
    private EditText et7;
    private EditText et8;
    private EditText et9;
    private EditText et10;
    private EditText et11;
    private String intership_name;
    private String intership_major;
    private String intership_train;
    private String intership_end;
    private String adviser_name;
    private String conn_num;
    private String qq;
    private String weixin;
    private String age;
    private String idnumber;
    private String homeAddress;
    //public  static String  log;


    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            //log=new String ("hello");
            try {
                File file = new File(getFilesDir()+"data.txt");
                FileOutputStream fos= new FileOutputStream(file);
                fos.write(("nihao").getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(InfoActivity.this, "提交成功", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(InfoActivity.this, wqj_Stu_LoginActivity.class);
            startActivity(intent1);
            super.onSuccess(content);//content return data(json);
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
        et1 = (EditText) findViewById(R.id.info_et1);
        et2 = (EditText) findViewById(R.id.info_et2);
        et3 = (EditText) findViewById(R.id.info_et3);
        et4 = (EditText) findViewById(R.id.info_et4);
        et5 = (EditText) findViewById(R.id.info_et5);
        et6 = (EditText) findViewById(R.id.info_et6);
        et7 = (EditText) findViewById(R.id.info_et7);
        et8 = (EditText) findViewById(R.id.info_et8);
        et9 = (EditText) findViewById(R.id.info_et9);
        et10 = (EditText) findViewById(R.id.info_et10);
        et11 = (EditText) findViewById(R.id.info_et11);

    }

    public void commit(View v) {
        if (et1.length() <= 0 || et2.length() <= 0 || et3.length() <= 0 || et4.length() <= 0 || et5.length() <= 0 ||
                et6.length() <= 0 || et7.length() <= 0 || et8.length() <= 0 || et9.length() <= 0
                || et10.length() <= 0 || et11.length() <= 0) {
            Toast.makeText(InfoActivity.this, "请将内容填写完整", Toast.LENGTH_LONG).show();
        } else if (et9.length() > 3 || et9.length() < 1) {
            Toast.makeText(InfoActivity.this, "请填写正确的年龄", Toast.LENGTH_LONG).show();
        } else if (et6.length() < 11) {
            Toast.makeText(InfoActivity.this, "手机号码格式错误", Toast.LENGTH_LONG).show();
        } else if (et7.length() < 6) {
            Toast.makeText(InfoActivity.this, "QQ号码格式错误", Toast.LENGTH_LONG).show();
        } else if (et10.length() < 18) {
            Toast.makeText(InfoActivity.this, "身份证号格式错误", Toast.LENGTH_LONG).show();
        }
        else {
            new Thread() {
                public void run() {
                    String path = port.port+"/WQJServices/StuLoginServlet";
                    intership_name = et1.getText().toString().trim();
                    intership_major = et2.getText().toString().trim();
                    intership_train = et3.getText().toString().trim();
                    intership_end = et4.getText().toString().trim();
                    adviser_name = et5.getText().toString().trim();
                    conn_num = et6.getText().toString().trim();
                    qq = et7.getText().toString().trim();
                    weixin = et8.getText().toString().trim();
                    age = et9.getText().toString().trim();
                    idnumber = et10.getText().toString().trim();
                    homeAddress = et11.getText().toString().trim();
                    intership_info_model data = new intership_info_model();
                    data.setIntership_name(intership_name);
                    data.setAdviser_name(adviser_name);
                    data.setConn_num(conn_num);
                    data.setIntership_major(intership_major);
                    data.setIntership_train(intership_train);
                    data.setIntership_end(intership_end);
                    data.setAge(age);
                    data.setHomeAddress(homeAddress);
                    data.setIdnumber(idnumber);
                    data.setQq(qq);
                    data.setWeixin(weixin);
                    json<intership_info_model> json1 = new json<intership_info_model>();
                    String infoResult = json1.ObjectToJson1(data);
                    SDS_Httpclient conn = new SDS_Httpclient();
                    conn.Postclient(path, infoResult, handler);
                }


            }.start();


        }
    }
}