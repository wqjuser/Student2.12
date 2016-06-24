package cn.hsd.Counsellor.counsellor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import PORT.port;
import cn.hsd.Counsellor.modle.Qxx_Sgin_student;
import cn.hsd.student.R;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_SginActivity extends Activity {


    private ListView qxx_lvSgin;
    private ArrayList<Qxx_Sgin_student> list;
    private ArrayList<HashMap<String, Object>> data;


    SDS_ZMHandler handler = new SDS_ZMHandler() {


        public void onSuccess(String content) {

            try {
                JSONArray jsonArray = new JSONArray(content);
                list = new ArrayList<Qxx_Sgin_student>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    String student_name = (String) jsonArray.getJSONObject(i).get("student_name");
                    String student_no = (String) jsonArray.getJSONObject(i).get("student_no");
                    String sgin_num = (String) jsonArray.getJSONObject(i).get("sgin_num");
                    Qxx_Sgin_student sg = new Qxx_Sgin_student();
                    sg.setStudent_name(student_name);
                    sg.setStudent_no(student_no);
                    sg.setSgin_num(sgin_num);
                    list.add(sg);

                }
            } catch (JSONException e) {
                // TODO �Զ����ɵ� catch ��
                e.printStackTrace();
            }
            for (Qxx_Sgin_student sgin_student : list) {
                HashMap<String, Object> item = new HashMap<String, Object>();

                item.put("student_name", sgin_student.getStudent_name());
                item.put("student_no", sgin_student.getStudent_no());
                item.put("sgin_num", sgin_student.getSgin_num());
                data.add(item);
            }
            String[] s = {"student_name", "student_no", "sgin_num"};
            SimpleAdapter adapter = new SimpleAdapter(Qxx_SginActivity.this, data,
                    R.layout.qxx_sign_item, s,
                    new int[]{R.id.qxx_sg_name, R.id.qxx_sg_no, R.id.qxx_sg_num});
            qxx_lvSgin.setAdapter(adapter);
            qxx_lvSgin.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    // TODO �Զ����ɵķ������
                    Intent intent = new Intent();

                    intent.setClass(Qxx_SginActivity.this, Qxx_SginStudentActivity.class);

                    System.out.println(data.get(position));
                    intent.putExtra("sgin_student", data.get(position));

                    startActivity(intent);

                }
            });

        }

        ;

        public void onFailture(String content) {

            Toast.makeText(getApplicationContext(), "����δ���ӣ�", Toast.LENGTH_SHORT).show();
        }

        ;

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx__sgin);
        qxx_lvSgin = (ListView) findViewById(R.id.qxx_lvSgin);


    }

    @Override
    protected void onResume() {
        // TODO �Զ����ɵķ������
        super.onResume();
        showUpdate();
    }

    public void showUpdate() {


        new Thread() {//ע����û��װ���߳�

            public void run() {

                String path = port.port + "/sgin_student";

                SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                conn.Postclient(path, "", handler); //��post�����ύ����

            }
        }.start();
        data = new ArrayList<HashMap<String, Object>>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__sgin, menu);
        return true;
    }

}
