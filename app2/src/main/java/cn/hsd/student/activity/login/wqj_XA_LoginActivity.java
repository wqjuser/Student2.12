package cn.hsd.student.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.hsd.student.R;
import cn.hsd.student.activity.wqj_service.UserServices;

public class wqj_XA_LoginActivity extends AppCompatActivity {
    private Button wqj_xa_btn;
    private TextView tv1;
    private TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xa__login);
        wqj_xa_btn = (Button) findViewById(R.id.wqj_xa_log_button);

        tv1 = (TextView) findViewById(R.id.username_edit);
        tv2 = (TextView) findViewById(R.id.password_edit);
        wqj_xa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv1.length() == 0 && tv2.length() == 0) {
                    Toast.makeText(wqj_XA_LoginActivity.this, "身份证号或密码不能为空", Toast.LENGTH_LONG).show();

                } else if (tv1.length() < 13) {
                    Toast.makeText(wqj_XA_LoginActivity.this, "身份证号错误或者格式不正确", Toast.LENGTH_LONG).show();
                } else if (tv2.length() < 6) {
                    Toast.makeText(wqj_XA_LoginActivity.this, "密码错误", Toast.LENGTH_LONG).show();

                } else {

                    final Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == 0) {
                                Toast.makeText(wqj_XA_LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(wqj_XA_LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    new Thread() {
                        Message msg = new Message();

                        @Override
                        public void run() {
                            String name = tv1.getText().toString();
                            String pass = tv2.getText().toString();
                            boolean result = UserServices.check(name, pass);
                            if (result != true) {
                                msg.what = 1;//false
                            } else {
                                msg.what = 0;//true
                            }
                            handler.sendMessage(msg);
                        }
                    }.start();
                }
            }
        });

    }
}
    /*public class StuBtuOnClickListener implements View.OnClickListener{
        public void onClick(View v){
            if(tv2.length()==0||tv1.length()==0){
                Toast.makeText(wqj_XA_LoginActivity.this,"学号或密码不能为空",Toast.LENGTH_LONG).show();
            }
            else if(tv1.length()<10){
                Toast.makeText(wqj_XA_LoginActivity.this,"学号格式错误或者长度不够",Toast.LENGTH_LONG).show();
            }
            else if(tv2.length()<6){
                Toast.makeText(wqj_XA_LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
            }

        }
    }
*/

