package cn.hsd.student.activity.ChangeInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.login.wqj_Stu_LoginActivity;
import cn.hsd.student.activity.model.ChangeInfo_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class ChangeInfoActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;
    private EditText et7;

    private String phonenumber;
    private String qqnumber;
    private String name;
    private String age;
    private String idnumber;
    private String homeaddress;
    private String password;
    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            Toast.makeText(ChangeInfoActivity.this, "提交成功", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(ChangeInfoActivity.this, wqj_Stu_LoginActivity.class);
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
        setContentView(R.layout.activity_change_info);
        et1 = (EditText) findViewById(R.id.chanet1);
        et2 = (EditText) findViewById(R.id.chanet2);
        et3 = (EditText) findViewById(R.id.chanet4);
        et4 = (EditText) findViewById(R.id.chanet5);
        et5 = (EditText) findViewById(R.id.chanet6);
        et6 = (EditText) findViewById(R.id.chanet7);
        et7 = (EditText) findViewById(R.id.chanet8);
    }

    public void EnsureChange(View v) {
        if (et1.length() <= 0 || et2.length() <= 0 || et3.length() <= 0 || et4.length() <= 0 || et5.length() <= 0 || et6.length() <= 0 || et7.length() <= 0) {
            Toast.makeText(ChangeInfoActivity.this, "请将内容填写完整", Toast.LENGTH_LONG).show();
        } else if (et1.length() < 11) {
            Toast.makeText(ChangeInfoActivity.this, "手机号码格式错误", Toast.LENGTH_LONG).show();
        } else if (et2.length() < 6) {
            Toast.makeText(ChangeInfoActivity.this, "QQ号码格式错误", Toast.LENGTH_LONG).show();
        } else if (et4.length() > 3 || et4.length() < 1) {
            Toast.makeText(ChangeInfoActivity.this, "请填写正确的年龄", Toast.LENGTH_LONG).show();
        } else if (et5.length() < 18) {
            Toast.makeText(ChangeInfoActivity.this, "身份证号格式错误", Toast.LENGTH_LONG).show();
        } else {
            new Thread() {
                public void run() {
                    String path = port.port+"/WQJServices/StuLoginServlet";
                    phonenumber = et1.getText().toString().trim();
                    qqnumber = et2.getText().toString().trim();
                    name = et3.getText().toString().trim();
                    age = et4.getText().toString().trim();
                    idnumber = et5.getText().toString().trim();
                    homeaddress = et6.getText().toString().trim();
                    password = et7.getText().toString().trim();
                    ChangeInfo_model data = new ChangeInfo_model();
                    data.setIdnumber(idnumber);
                    data.setName(name);
                    data.setAge(age);
                    data.setHomeaddress(homeaddress);
                    data.setPassword(password);
                    data.setPhonenumber(phonenumber);
                    data.setQqnumber(qqnumber);
                    json<ChangeInfo_model> json = new json<ChangeInfo_model>();
                    String chinfo = json.ObjectToJson1(data);
                    SDS_Httpclient conn = new SDS_Httpclient();
                    conn.Postclient(path, chinfo, handler);


                }

            }.start();
        }

    }
}
