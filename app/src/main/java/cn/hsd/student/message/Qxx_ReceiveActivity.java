package cn.hsd.student.message;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_ReceiveActivity extends Activity {

    private List<Qxx_Message> list;
    private String student_no = "1234567890";
    public String EXPANDED = "EXPANDED";
    public Qxx_MessageAdapter adapter;
    public ArrayList<Map<String, Object>> itemList;
    private ListView listView2;
    private TextView qxx_lt_num;
    private int n = 0;

    SDS_ZMHandler handler2 = new SDS_ZMHandler() {
        public void onSuccess(String content) {
            try {
                JSONArray jsonArray = new JSONArray(content);
                list = new ArrayList<Qxx_Message>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    String time = (String) jsonArray.getJSONObject(i).get("message_time");
                    String detail = (String) jsonArray.getJSONObject(i).get("message_detail");
                    String receiveno = (String) jsonArray.getJSONObject(i).get("message_receiveNo");
                    String sendno = (String) jsonArray.getJSONObject(i).get("message_sendNo");
                    String receivename = (String) jsonArray.getJSONObject(i).get("message_receiveName");
                    String sendname = (String) jsonArray.getJSONObject(i).get("message_sendName");
                    if (student_no.equals(receiveno)) {
                        Qxx_Message mge = new Qxx_Message();
                        mge.setMessage_time(time);
                        mge.setMessage_detail(detail);
                        mge.setMessage_receiveNo(receiveno);
                        mge.setMessage_sendNo(sendno);
                        mge.setMessage_receiveName(receivename);
                        mge.setMessage_sendName(sendname);
                        list.add(mge);
                        n++;
                    }

                }
                qxx_lt_num.setText(Integer.toString(n));
                n = 0;
            } catch (JSONException e) {

                e.printStackTrace();
            }
            for (Qxx_Message message : list) {
                Map<String, Object> item = new HashMap<String, Object>();

                item.put("message_time", message.getMessage_time());
                item.put("message_detail", message.getMessage_detail());
                item.put("message_receiveName", message.getMessage_receiveName());
                item.put("message_sendName", message.getMessage_sendName());

                item.put("EXPANDED", Boolean.valueOf(false));
                itemList.add(item);
            }
            adapter = new Qxx_MessageAdapter(Qxx_ReceiveActivity.this, itemList);
            listView2.setAdapter(adapter);
        }

        ;

        public void onFailture(String content) {


        }

        ;

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx__receive);

        qxx_lt_num = (TextView) findViewById(R.id.qxx_lt_num);
        listView2 = (ListView) findViewById(R.id.qxx_listview2);
        listView2.setDivider(null);
        listView2.setOnItemClickListener(new ItemClick());

    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> paramAdapterView,
                                View paramView, int paramInt, long paramLong) {

            System.out.println("item----------click");
            Map<String, Object> localMap = Qxx_ReceiveActivity.this.itemList
                    .get(paramInt);
            if (((Boolean) localMap.get("EXPANDED")).booleanValue()) {
                localMap.put("EXPANDED", Boolean.valueOf(false));
            } else {
                localMap.put("EXPANDED", Boolean.valueOf(true));
            }
            Qxx_ReceiveActivity.this.adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {

        super.onResume();
        showUpdate();
    }

    public void showUpdate() {


        new Thread() {//ע����û��װ���߳�

            public void run() {

                String path = port.port + "/qjqxsxs";

                SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                conn.Postclient(path, "123", handler2); //��post�����ύ����

            }
        }.start();
        itemList = new ArrayList<Map<String, Object>>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__receive, menu);
        return true;
    }

}
