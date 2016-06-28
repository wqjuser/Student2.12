package cn.hsd.sett;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.MainActivity.wqj_ID_MainActivity;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.gxq_class.gxqStatic;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class SchsetActivity extends AppCompatActivity {
    private EditText tv1;
    private EditText tv2;
    private EditText tv3;
    private String pass1 = "";
    private String pass2 = "";
    private String pass3 = "";
    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            if ("1".equals(content)) {
                Toast.makeText(SchsetActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SchsetActivity.this, wqj_ID_MainActivity.class);
                startActivity(intent);
            }

        }

        @Override
        public void onFailture(String content) {
            Toast.makeText(SchsetActivity.this, "与服务器连接失败", Toast.LENGTH_LONG).show();
            super.onFailture(content);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlayout);
        tv1 = (EditText) findViewById(R.id.ypass);
        tv2 = (EditText) findViewById(R.id.xpas);
        tv3 = (EditText) findViewById(R.id.xpas1);


    }

    public void commit(View v) {
        if (pass1.length() == 0 || pass2.length() == 0 || pass3.length() == 0) {
            Toast.makeText(SchsetActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
        } else if (!(pass1.equals(gxqStatic.pass))) {
            Toast.makeText(SchsetActivity.this, "原始密码不正确", Toast.LENGTH_SHORT).show();
        } else if (!(pass2.equals(pass3))) {
            Toast.makeText(SchsetActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();

        } else {

            new Thread() {
                public void run() {

                    String path = port.port + "/Schset.action";
                    pass1 = tv1.getText().toString().trim();
                    pass2 = tv2.getText().toString().trim();
                    pass3 = tv3.getText().toString().trim();
                    setmodel set = new setmodel();
                    set.setPass1(pass1);
                    set.setPass2(pass2);
                    set.setPass3(pass3);
                    json<setmodel> json = new json<setmodel>();
                    String infoResult = json.ObjectToJson1(set);
                    SDS_Httpclient conn = new SDS_Httpclient();
                    conn.Postclient(path, infoResult, handler);

                }
            }.start();
        }

    }
}
