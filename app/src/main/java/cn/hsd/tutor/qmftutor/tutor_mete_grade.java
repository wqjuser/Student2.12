package cn.hsd.tutor.qmftutor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.tutor.model.tutor_mg_refer;


public class tutor_mete_grade extends AppCompatActivity {
    private ListView metegradelv;
    private SimpleAdapter simp_ada1;
    private TextView noet;
    private TextView nameet;
    private EditText dialog_mg;
    private SDS_Httpclient conn;
    private tutor_mg_refer date=null;
    private ArrayList<HashMap<String, Object>> gradelist;
    public List<String> dataList = new ArrayList<String>();
    private List<tutor_mg_refer> list1 ;
    private List<Map> list2 ;
    private ListView list_view ;
    private ArrayList<HashMap<String, Object>> listItem;
    public SDS_ZMHandler sds_zmhandler = new SDS_ZMHandler() {

        public void onSuccess(String content) {


            super.onSuccess(content);
            list2 = new ArrayList<Map>();
            list1 = new ArrayList<tutor_mg_refer>();
            System.out.println(content);
            nameet=(TextView)tutor_mete_grade.this.findViewById(R.id.tutor_mg_studentname);
            noet=(TextView)tutor_mete_grade.this.findViewById(R.id.tutor_mg_studentno);

            listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
            json<tutor_mg_refer> returnjson = new json<tutor_mg_refer>();
            list1 = returnjson.jsonToList2(content);
            for (int i=0;i<list1.size();i++){
                Map map =(Map) list1.get(i);
                list2.add(map);
            }

            for(int i =0 ; i<list2.size() ; i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("student_no", list2.get(i).get("student_no"));
                map.put("student_name", list2.get(i).get("student_name"));

                listItem.add(map);
            }
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(getApplicationContext(),listItem, R.layout.tutor_mateg_list ,new String[]{
                    "student_no","student_name"},
                    new int[] {R.id.tutor_mg_studentno,R.id.tutor_mg_studentname
                    }
            );

            list_view.setAdapter(mSimpleAdapter);
        }

        public void onFailture(String content) {
            super.onFailture(content);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_mete_grade);
        list_view = (ListView) this.findViewById(R.id.tutor_mg_list);

        new Thread() {

            public void run() {

                String path = port.port + "/getmgroll.action";
                conn = new SDS_Httpclient();
                conn.Postclient(path, "", sds_zmhandler);
            }
        }.start();
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                date = new tutor_mg_refer();
                HashMap item = (HashMap) parent.getItemAtPosition(position);
                Dialog dialog = new Dialog(tutor_mete_grade.this);
                dialog.setContentView(R.layout.metegrade_dialog);
                dialog_mg=(EditText)dialog.findViewById(R.id.tutor_qmf_diago_mg) ;
                date.setStudent_name(item.get("student_name").toString());
                date.setStudent_no(item.get("student_no").toString());
                date.setTutor_metegrade(dialog_mg.getText().toString().trim());
                dialog.setTitle("成绩");
                dialog.show();

            }
        });
    }


    public SDS_ZMHandler qmf_hhhandler = new SDS_ZMHandler() {

        public void onSuccess(String content) {


            super.onSuccess(content);}
        public void onFailture(String content) {
            super.onFailture(content);
        }
    };

    public void qmf_mgrefer(View view) {
        //上传到服务器
        date.setTutor_metegrade(dialog_mg.getText().toString().trim());
        new Thread() {

            public void run() {


                //  Toast.makeText(getApplicationContext(),nameet.getText().toString(),Toast.LENGTH_LONG).show();

                String path = port.port + "/getmg.action";
                conn = new SDS_Httpclient();
                json<tutor_mg_refer> dataVerture = new json<tutor_mg_refer>();
                String returnJson = dataVerture.ObjectToJson1(date);
                conn.Postclient(path, returnJson, qmf_hhhandler);
            }
        }.start();
        Intent b=new Intent();
        b.setClass(this,tutor_mete_grade.class);
        startActivity(b);
        //finish();

    }
}
