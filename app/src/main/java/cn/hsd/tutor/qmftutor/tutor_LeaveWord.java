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
import cn.hsd.tutor.model.tutor_lw_refer;

public class tutor_LeaveWord extends AppCompatActivity {

    private SimpleAdapter simp_ada;
    public TextView noet;
    public TextView nameet;
    private EditText diago_lw;
    public SDS_Httpclient conn;
    private tutor_lw_refer date=null;
    private ArrayList<HashMap<String, Object>> leavelist;

    //--------------------------------
    private List<tutor_lw_refer> list1 ;
    private List<Map> list2 ;
    private ListView list_view ;
    private ArrayList<HashMap<String, Object>> listItem;

    List<String> listgxq1 = new ArrayList<String>();

    public SDS_ZMHandler sds_zmhandler = new SDS_ZMHandler() {
        @Override
           public void onSuccess(String content) {
            super.onSuccess(content);
            list2 = new ArrayList<Map>();
            list1 = new ArrayList<tutor_lw_refer>();
            System.out.println(content);
            nameet=(TextView)tutor_LeaveWord.this.findViewById(R.id.tutor_lv_studentname);
            noet=(TextView)tutor_LeaveWord.this.findViewById(R.id.tutor_lv_studentno);

            listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
            json<tutor_lw_refer> returnjson = new json<tutor_lw_refer>();
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
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(getApplicationContext(),listItem, R.layout.tutor_leavew_list ,new String[]{
                    "student_no","student_name"},
                    new int[] {R.id.tutor_lv_studentno,R.id.tutor_lv_studentname
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
        setContentView(R.layout.tutor_leave_word);
        list_view = (ListView) this.findViewById(R.id.tutor_lw_list);

        new Thread() {

            public void run() {

                String path = port.port+"/qmfxsxs";
                conn = new SDS_Httpclient();
                conn.Postclient(path, "123", sds_zmhandler);
            }
        }.start();



        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                date = new tutor_lw_refer();
                HashMap item = (HashMap) parent.getItemAtPosition(position);
                Dialog dialog = new Dialog(tutor_LeaveWord.this);
                dialog.setContentView(R.layout.leaveword_dialog);
                diago_lw=(EditText)dialog.findViewById(R.id.tutor_qmf_diago_lw) ;
                date.setStudent_name(item.get("student_name").toString());
                date.setStudent_no(item.get("student_no").toString());
                date.setTutor_leaveword(diago_lw.getText().toString().trim());
                dialog.setTitle("留言板");
                dialog.show();

            }
        });
    }
    //----------------------------------------------提交留言
    public SDS_ZMHandler qmfhandler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
        }

        public void onFailture(String content) {
            super.onFailture(content);
            //    Toast.makeText(tutor_LeaveWord.this,"failture",Toast.LENGTH_LONG).show();
        }
    };
    public void qmf_lwrefer(View view) {
        //上传到服务器


        new Thread() {

            public void run() {


//               Toast.makeText(tutor_LeaveWord.this,"连服务器",Toast.LENGTH_LONG).show();

                String path = port.port+"/lwxsxs";
                conn = new SDS_Httpclient();
                json<tutor_lw_refer> dataVerture = new json<tutor_lw_refer>();
                String returnJson = dataVerture.ObjectToJson1(date);

                conn.Postclient(path, returnJson, qmfhandler);
            }
        }.start();
        Intent s=new Intent();
        s.setClass(this,tutor_LeaveWord.class);
        startActivity(s);
        //finish();

    }


}
