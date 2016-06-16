package cn.hsd.tutor.gxq_teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.tutor.gxq_class.Signin_class;


public class gxq_teacher_Activity extends AppCompatActivity {
    public Spinner spinner_teacher;
    public TextView qiandaoshow_gxq;

    public List<Signin_class> backList;
    public List<Map> list;
    private SDS_Httpclient conn;
    List<String> listgxq = new ArrayList<String>();
    List<String> listgxq22222222 = new ArrayList<String>();
    public List<Signin_class> shishiList  = new ArrayList<Signin_class>();
    public List<Signin_class> shishiList2  = new ArrayList<Signin_class>();
    public List<Signin_class> shishiList3  = new ArrayList<Signin_class>();
    ArrayAdapter<String> apply_message_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gxq_teacher_);
        list = new ArrayList<Map>();
        spinner_teacher = (Spinner) findViewById(R.id.spinner_teacher);
        qiandaoshow_gxq = (TextView) findViewById(R.id.qidaoshow_gxq);
        backList = new ArrayList<Signin_class>();


    }

    public String  diaoyong(List<Signin_class>  shidata,String name) {
        String qiandao = "";
        if (shidata.size() > 0) {
            for (Signin_class provience : shidata) {

                if ((provience.getStudent_name()).equals(name)) {
                     qiandao = qiandao+ provience.getSign_time() +provience.getSign_address()+"\n";

                }
            }
        }
        return qiandao;
    }

    public SDS_ZMHandler gxq_zmhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
  //          Toast.makeText(gxq_teacher_Activity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
           json<String> gxqreturn = new json<String>();

           listgxq= gxqreturn.jsonToList2(content);
            apply_message_adapter = new ArrayAdapter<String>(gxq_teacher_Activity.this, android.R.layout.simple_spinner_item, listgxq);
            spinner_teacher.setAdapter(apply_message_adapter);


        }
        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(gxq_teacher_Activity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
public void connectserv(){
    new Thread(){
        public void run(){
            String path = port.port+"/GXQServices/xsxs";
            conn = new SDS_Httpclient();

             conn.Postclient(path,"123",gxq_zmhandler);
        }
    }.start();
}
    public void connect(){
        new Thread(){
            public void run(){
                String path = port.port+"/GXQServices/gxqxsxs";
                conn = new SDS_Httpclient();
                Signin_class data = new Signin_class();
                json<Signin_class> dataVerture = new json<Signin_class>();
                String returnJson = dataVerture.ObjectToJson1(data);

                conn.Postclient(path,returnJson,gxq_conhandler);
            }
        }.start();
    }
    public SDS_ZMHandler gxq_conhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(gxq_teacher_Activity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            shishiList2.clear();
            shishiList3.clear();
            list.clear();
           json<Signin_class> gxqreturn1 = new json<Signin_class>();
            shishiList2= gxqreturn1.jsonToList2(content);
            for(int i=0;i<shishiList2.size();i++){
                Map map = (Map) shishiList2.get(i);
                list.add(map);
            }
            for (Map ll : list){
                Signin_class rr = new Signin_class();
                String qq = (String) ll.get("sign_address");
                String ww = (String) ll.get("student_name");
                String ee = (String) ll.get("sign_time") ;
                rr.setStudent_name(ww);
                rr.setSign_address(qq);
                rr.setSign_time(ee);
                shishiList3.add(rr);
            }

            spinner_teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String aa = listgxq.get(position);
                    String bb;
                    bb =   diaoyong(shishiList3,aa);
                    qiandaoshow_gxq.setText(bb);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(gxq_teacher_Activity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    public void click(View view){
        switch (view.getId()){
            case R.id.shuxin:
                Intent intent1 = new Intent(gxq_teacher_Activity.this,Apply_gxqshow_Activity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        connectserv();
        connect();
    }
}
