package cn.hsd.school;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.school.model.college_message;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class gxq_collegeActivity extends AppCompatActivity {

    public Button gxq_add;
    public EditText college_manager_name;
    public EditText college_manager_sex;
    public EditText colleger_manager_gonghao;
    public EditText college;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gxq_college);
      //  gxq_add = (Button)findViewById(R.id.)
        college_manager_name = (EditText)findViewById(R.id.gxq_add_teacher_name);
        college_manager_sex = (EditText)findViewById(R.id.gxq_add_teacher_gonghao);
        colleger_manager_gonghao =(EditText)findViewById(R.id.gxq_add_teacher_gonghao);
        college = (EditText)findViewById(R.id.gxq_add_teacher_xueyuan);

    }
    public void gxqButton_add_teacher_leader(View view){
    //    Toast.makeText(gxq_collegeActivity.this,college.getText().toString(),Toast.LENGTH_SHORT).show();
        final college_message college_message = new college_message();
        college_message.setC_admin_college(college.getText().toString());
        college_message.setC_admin_name(college_manager_name.getText().toString());
        college_message.setC_admin_onlynumber(colleger_manager_gonghao.getText().toString());
        college_message.setC_admin_password(college_manager_sex.getText().toString());

        new Thread(){
            public void run(){
                String path = port.port + "/newcollegemanager.action";
                SDS_Httpclient conn = new SDS_Httpclient();

                json<college_message> dataVerture = new json<college_message>();
                String returnJson = dataVerture.ObjectToJson1(college_message);
                conn.Postclient(path,returnJson,gxq_college);
            }
        }.start();

    }
    public SDS_ZMHandler gxq_college = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(gxq_collegeActivity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(gxq_collegeActivity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };

}
