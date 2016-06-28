package cn.hsd.Counsellor.counsellor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.Counselor;
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.student.message.Qxx_MessageAdapter;

public class Qxx_counselorMgeActivity extends Activity {
    public String EXPANDED = "EXPANDED";
    public Qxx_MessageAdapter adapter;
    public ArrayList<Map<String, Object>> itemList;
    private ListView listView;
    private Button qxx_select_Bt2;
    private ImageButton qxx_img;
    private TextView qxx_tv_clr;
    private HashMap<String, Object> item;
    private String[] s;
    private ArrayList<Counselor> listcr;
    private ArrayList<Qxx_Message> list;
    private String counselor_no = "123";
    private String counselor_name = "翟德斌";
    private ArrayList<HashMap<String, Object>> data;
    private AlertDialog alertDialog;
    protected View view;

    SDS_ZMHandler handler = new SDS_ZMHandler() {


        public void onSuccess(String content) {


            json<Counselor> returnJson = new json<Counselor>();
            List list = returnJson.jsonToList2(content);
            for (int i = 0; i < list.size(); i++) {
                HashMap map = (HashMap) list.get(i);
                data.add(map);
            }
            System.out.println(data);
            alertDialog.setTitle("请选择学生");
            alertDialog.setView(view);
            alertDialog.setCanceledOnTouchOutside(false);//除了dialog以外不能点
            alertDialog.show();

            ListView lv = (ListView) view.findViewById(R.id.qxx_lt_dialog);
            lv.setItemsCanFocus(false);
            lv.setItemsCanFocus(false);
            lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            String[] s = {"student_name", "student_no"};
            SimpleAdapter myadapter = new SimpleAdapter(Qxx_counselorMgeActivity.this, data,
                    R.layout.qxx_dialog_item2, s,
                    new int[]{R.id.qxx_dialog_name2, R.id.qxx_dialog_no2});
            lv.setAdapter(myadapter);
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    item = data.get(position);
                    qxx_tv_clr.setText(item.get("student_name").toString());
                    alertDialog.cancel();

                }
            });

        }

        ;

        public void onFailture(String content) {

            Toast.makeText(getApplicationContext(), "网络未连接！", Toast.LENGTH_SHORT).show();
        }

        ;

    };
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

                    if (counselor_no.equals(sendno)) {
                        Qxx_Message mge = new Qxx_Message();
                        mge.setMessage_time(time);
                        mge.setMessage_detail(detail);
                        mge.setMessage_receiveNo(receiveno);
                        mge.setMessage_sendNo(sendno);
                        mge.setMessage_receiveName(receivename);
                        mge.setMessage_sendName(sendname);
                        list.add(mge);
                    }

                }
            } catch (JSONException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            for (Qxx_Message message : list) {
                Map<String, Object> item = new HashMap<String, Object>();

                item.put("message_time", message.getMessage_time());
                item.put("message_detail", message.getMessage_detail());
                item.put("message_receiveName", message.getMessage_receiveName());
                item.put("message_sendName", message.getMessage_sendName());
                // 默认笔记是摊开还是折叠，true为摊开
                item.put("EXPANDED", Boolean.valueOf(false));
                itemList.add(item);
            }
            adapter = new Qxx_MessageAdapter(Qxx_counselorMgeActivity.this, itemList);
            listView.setAdapter(adapter);
        }

        ;

        public void onFailture(String content) {


        }

        ;

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx_counselor_mge);
        qxx_img = (ImageButton) findViewById(R.id.qxx_img2);
        qxx_tv_clr = (TextView) findViewById(R.id.qxx_tv_clr2);
        listView = (ListView) findViewById(R.id.qxx_listview2);
        qxx_select_Bt2 = (Button) findViewById(R.id.qxx_select_Bt2);
        // this.listView.setDivider(getResources().getDrawable(android.R.color.white));
        listView.setDivider(null);
        listView.setOnItemClickListener(new ItemClick());


        qxx_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                String toname = qxx_tv_clr.getText().toString();
                if (!toname.equals("")) {
                    Toast.makeText(Qxx_counselorMgeActivity.this, "写留言", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Qxx_counselorMgeActivity.this,
                            cn.hsd.Counsellor.counsellor.Qxx_counselorWriteActivity.class);
                    intent.putExtra("clr_no", counselor_no);
                    intent.putExtra("student", item);
                    startActivity(intent);
                } else {
                    Toast.makeText(Qxx_counselorMgeActivity.this, "请选择学生！", Toast.LENGTH_SHORT).show();
                }


            }
        });

        qxx_select_Bt2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO 自动生成的方法存根


                new Thread() {//注意我没封装子线程

                    public void run() {

                        String path = port.port + "/qxx_student.action";

                        SDS_Httpclient conn = new SDS_Httpclient();//获取连接
                        conn.Postclient(path, "123", handler); //以post方法提交数据

                    }
                }.start();

                view = View.inflate(Qxx_counselorMgeActivity.this, R.layout.qxx_dialog_list, null);

                alertDialog = new AlertDialog.Builder(Qxx_counselorMgeActivity.this).create();

                data = new ArrayList<HashMap<String, Object>>();


            }
        });
    }

    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
        showUpdate();
    }

    public void showUpdate() {


        new Thread() {//注意我没封装子线程

            public void run() {

                String path = port.port + "/qxx_message.action";

                SDS_Httpclient conn = new SDS_Httpclient();//获取连接
                conn.Postclient(path, "", handler2); //以post方法提交数据

            }
        }.start();
        itemList = new ArrayList<Map<String, Object>>();
        // TODO 自动生成的方法存根
    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> paramAdapterView,
                                View paramView, int paramInt, long paramLong) {
            // TODO 自动生成的方法存根
            System.out.println("item----------click");
            Map<String, Object> localMap = Qxx_counselorMgeActivity.this.itemList
                    .get(paramInt);
            if (((Boolean) localMap.get("EXPANDED")).booleanValue()) {
                localMap.put("EXPANDED", Boolean.valueOf(false));
            } else {
                localMap.put("EXPANDED", Boolean.valueOf(true));
            }
            Qxx_counselorMgeActivity.this.adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx_counselor_mge, menu);
        return true;
    }

}
