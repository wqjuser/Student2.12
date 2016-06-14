package cn.hsd.tutor.Stu_log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.tutor.model.stu_log;

public class VistlogActivity extends AppCompatActivity {
    private EditText et1;
    private ListView lv1;
    //private TextView tv1;
    private String studentnumber;
    private String logcat;
    private SimpleAdapter simp_ada;
    private ArrayList<HashMap<String, Object>> arr_list;

    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {

            super.onSuccess(content);//content return data(json);
            Toast.makeText(VistlogActivity.this, "查询成功", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailture(String content) {
            Toast.makeText(VistlogActivity.this, "查询失败", Toast.LENGTH_LONG).show();


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistlog);
        et1 = (EditText) findViewById(R.id.log_et1);
        lv1 = (ListView) findViewById(R.id.wqj_log_List);
        //tv1=(TextView)findViewById(R.id.log_List) ;
        show_List_Logcat();


    }

    public void show_List_Logcat() {

        arr_list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HashMap map = new HashMap<>();
            map.put("key", "实习生日志" + i);
            arr_list.add(map);
        }

        lv1 = (ListView) this.findViewById(R.id.wqj_log_List);
        simp_ada = new SimpleAdapter(this, arr_list, R.layout.list
                , new String[]{"key"}, new int[]{R.id.wqj_log_list});
        lv1.setAdapter(simp_ada);
    }

    public void ensure() {
        studentnumber = et1.getText().toString().trim();
        new Thread() {
            public void run() {
                String path = port.port + "/WQJServices/wqjservlet";
                studentnumber = et1.toString().trim();
                stu_log log = new stu_log();
                log.setStudent_no(studentnumber);
                json<stu_log> json = new json<>();
                String infoResult = json.ObjectToJson1(log);
                SDS_Httpclient conn = new SDS_Httpclient();
                conn.Postclient(path, infoResult, handler);//测试上传



            }

        }.start();
    }

}
