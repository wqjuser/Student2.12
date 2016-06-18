package cn.hsd.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.StudentMain.StudentActivity;
import cn.hsd.student.activity.login.InfoActivity;
import cn.hsd.student.activity.model.Stu_Login_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class wqj_Stu_LoginActivity extends AppCompatActivity {
    private Button wqj_stu_bt;
    private TextView tv1;
    private TextView tv2;
    private String username;
    private String password;

    //SharedPreferences sp;
    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onFailture(String content) {
            super.onFailture(content);

            Toast.makeText(wqj_Stu_LoginActivity.this, "联网失败", Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            if ("1".equals(content)) {
                //Toast.makeText(wqj_Stu_LoginActivity.this, content, Toast.LENGTH_SHORT).show();
                File file = new File(getFilesDir() + "data.txt");
                if (file.exists() && file.length() > 0) {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
                        String info = bf.readLine();
                        fis.close();

                        if (info.length() > 0) {
                            //SpTools.getBooleans(getApplicationContext(), Mycontents.ISSETUP, false);
                            Toast.makeText(wqj_Stu_LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(wqj_Stu_LoginActivity.this, StudentActivity.class);
                            startActivity(intent);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //SpTools.setBooleans(getApplicationContext(), Mycontents.ISSETUP, true);
                    Toast.makeText(wqj_Stu_LoginActivity.this, "首次登陆，请填写信息", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(wqj_Stu_LoginActivity.this, InfoActivity.class);
                    startActivity(intent1);
                }
            } else {

                Toast.makeText(wqj_Stu_LoginActivity.this, "登陆失败，请检查你的用学号和密码并重新登录", Toast.LENGTH_LONG).show();
            }


        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
        setContentView(R.layout.activity_stu__login);
        //sp = this.getSharedPreferences("ifFirst", 1);
        tv1 = (TextView) findViewById(R.id.username_edit);
        tv2 = (TextView) findViewById(R.id.password_edit);
        wqj_stu_bt = (Button) findViewById(R.id.wqj_stu_log_button);
        wqj_stu_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv1.length() == 0 || tv2.length() == 0) {

                    Toast.makeText(wqj_Stu_LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_LONG).show();
                } else if (tv1.length() < 10) {
                    Toast.makeText(wqj_Stu_LoginActivity.this, "学号格式不正确", Toast.LENGTH_LONG).show();
                } else if (tv2.length() < 6) {
                    Toast.makeText(wqj_Stu_LoginActivity.this, "密码不正确或者格式错误", Toast.LENGTH_LONG).show();
                } else {

                    new Thread() {
                        public void run() {
                            String path = port.port + "/StuLoginServlet";
                            username = tv1.getText().toString().trim();
                            password = tv2.getText().toString().trim();
                            Stu_Login_model log_data = new Stu_Login_model();
                            log_data.setUsername(username);
                            log_data.setPassword(password);
                            json<Stu_Login_model> json = new json<Stu_Login_model>();
                            String infoResult = json.ObjectToJson1(log_data);
                            SDS_Httpclient conn = new SDS_Httpclient();
                            conn.Postclient(path, infoResult, handler);

                        }
                    }.start();

                }

            }
        });
    }
}









