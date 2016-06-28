package cn.hsd.Counsellor.counsellor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PORT.port;
import cn.hsd.Counsellor.modle.Qxx_Leave;
import cn.hsd.student.R;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_LeaveActivity extends Activity {

    private ListView lv1;
    private String item;
    private boolean check = false;
    private String state;
    private String CounselorNo = "123";
    private ArrayList<Map<String, Object>> data;
    private Map<String, Object> leaveMap;
    private ArrayList<Qxx_Leave> list;


    SDS_ZMHandler handler = new SDS_ZMHandler() {


        public void onSuccess(String content) {
            try {
                JSONArray jsonArray = new JSONArray(content);
                list = new ArrayList<Qxx_Leave>();
                for (int i = 0; i < jsonArray.length(); i++) {

                    String student_name = (String) jsonArray.getJSONObject(i).get("student_name");
                    String counselor_name = (String) jsonArray.getJSONObject(i).get("counselor_name");
                    String counselor_no = (String) jsonArray.getJSONObject(i).get("counselor_no");
                    String adviser_name = (String) jsonArray.getJSONObject(i).get("adviser_name");

                    String leave_daynum = (String) jsonArray.getJSONObject(i).get("leave_daynum");
                    String leave_check = (String) jsonArray.getJSONObject(i).get("leave_check");
                    String leave_type = (String) jsonArray.getJSONObject(i).get("leave_type");
                    String leave_content = (String) jsonArray.getJSONObject(i).get("leave_content");
                    String leave_begin = (String) jsonArray.getJSONObject(i).get("leave_begin");
                    String leave_end = (String) jsonArray.getJSONObject(i).get("leave_end");
                    String create_time = (String) jsonArray.getJSONObject(i).get("create_time");
                    if (CounselorNo.equals(counselor_no)) {

                        Qxx_Leave le = new Qxx_Leave();

                        le.setStudent_name(student_name);

                        le.setCounselor_name(counselor_name);
                        le.setCounselor_no(counselor_no);
                        le.setAdviser_name(adviser_name);

                        le.setLeave_daynum(leave_daynum);
                        le.setLeave_check(leave_check);

                        le.setLeave_type(leave_type);
                        le.setLeave_content(leave_content);
                        le.setLeave_begin(leave_begin);
                        le.setLeave_end(leave_end);
                        le.setCreate_time(create_time);
                        list.add(le);
                    }
                    for (Qxx_Leave leave : list) {
                        HashMap<String, Object> item = new HashMap<String, Object>();


                        item.put("student_name", leave.getStudent_name());

                        item.put("counselor_name", leave.getCounselor_name());
                        item.put("counselor_no", leave.getCounselor_no());
                        item.put("adviser_name", leave.getAdviser_name());

                        item.put("leave_daynum", leave.getLeave_daynum());
                        item.put("leave_check", leave.getLeave_check());

                        item.put("leave_type", leave.getLeave_type());
                        item.put("leave_content", leave.getLeave_content());
                        item.put("leave_begin", leave.getLeave_begin());
                        item.put("leave_end", leave.getLeave_end());
                        item.put("create_time", leave.getCreate_time());
                        data.add(item);
                    }

                }
            } catch (JSONException e) {
                // TODO �Զ����ɵ� catch ��
                e.printStackTrace();
            }
            String[] s = {"student_name", "adviser_name", "counselor_name", "create_time", "leave_begin", "leave_end", "leave_type", "leave_daynum", "leave_content", "leave_state"};
            SimpleAdapter adapter = new SimpleAdapter(Qxx_LeaveActivity.this, data,
                    R.layout.qxx_leave_item, s,
                    new int[]{R.id.qxx_name, R.id.qxx_teacher, R.id.qxx_counsellor, R.id.qxx_create_time,
                            R.id.qxx_leave_begin, R.id.qxx_leave_end, R.id.qxx_style, R.id.qxx_daynum, R.id.qxx_content, R.id.qxx_chuli});
            lv1.setAdapter(adapter);

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
        setContentView(R.layout.activity_qxx__leave);
        lv1 = (ListView) findViewById(R.id.lv1);

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

                String path = port.port + "/qxx_leave.action";

                SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                conn.Postclient(path, "", handler); //��post�����ύ����

            }
        }.start();
        data = new ArrayList<Map<String, Object>>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__leave, menu);
        return true;
    }

}
