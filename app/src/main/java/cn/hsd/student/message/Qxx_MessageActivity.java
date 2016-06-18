package cn.hsd.student.message;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_MessageActivity extends Activity {
    public String EXPANDED = "EXPANDED";
    public Qxx_MessageAdapter adapter;
    public ArrayList<Map<String, Object>> itemList;
    private ListView listView;
    private Button qxx_select_Bt1;
    private ImageButton qxx_img;
    private TextView qxx_tv_clr;
    private String item;
    private String[] s={""};
    private Builder builder;
    private List<Qxx_Message> list;
    private List<String> list1;

    SDS_ZMHandler handler = new SDS_ZMHandler() {
        public void onSuccess(String content) {


                json<String> returnjson = new json<String>();
                List<String> list1 = returnjson.jsonToList2(content);
                System.out.println("-----------------"+content);

                s = new String[list1.size()];
                s = list1.toArray(s);



            builder.setSingleChoiceItems(s, -1, new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int position) {

                    item = s[position];
                }

            });
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {

                    qxx_tv_clr.setText(item);
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {


                    dialog.dismiss();
                }
            });
            builder.show();

        }

        ;

        public void onFailture(String content) {


        }

        ;

    };
    SDS_ZMHandler handler2 = new SDS_ZMHandler() {
        public void onSuccess(String content) {
            try {
               JSONArray jsonArray = new  JSONArray(content);

                list = new ArrayList<Qxx_Message>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    String time = (String) jsonArray.getJSONObject(i).get("message_time");
                    String detail = (String) jsonArray.getJSONObject(i).get("message_detail");
                    String receive = (String) jsonArray.getJSONObject(i).get("message_receiveName");
                    String send = (String) jsonArray.getJSONObject(i).get("message_sendName");
                    Qxx_Message mge = new Qxx_Message();
                    mge.setMessage_time(time);
                    mge.setMessage_detail(detail);
                    mge.setMessage_receiveName(receive);
                    mge.setMessage_sendName(send);
                    list.add(mge);
                }
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
            adapter = new Qxx_MessageAdapter(Qxx_MessageActivity.this, itemList);
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
        setContentView(R.layout.activity_qxx_message);
        qxx_img = (ImageButton) findViewById(R.id.qxx_img);
        qxx_tv_clr = (TextView) findViewById(R.id.qxx_tv_clr);
        listView = (ListView) findViewById(R.id.listview);
        qxx_select_Bt1 = (Button) findViewById(R.id.qxx_select_Bt1);
        // this.listView.setDivider(getResources().getDrawable(android.R.color.white));
        listView.setDivider(null);
        listView.setOnItemClickListener(new ItemClick());
        qxx_img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String toname = qxx_tv_clr.getText().toString();
                if (!toname.equals("")) {
                    Toast.makeText(Qxx_MessageActivity.this, "写留言", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Qxx_MessageActivity.this,
                            Qxx_WriteActivity.class);
                    intent.putExtra("toname", toname);
                    startActivity(intent);
                } else {
                    Toast.makeText(Qxx_MessageActivity.this, "请选择辅导员", Toast.LENGTH_SHORT).show();
                }


            }
        });

        qxx_select_Bt1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

//				  View view = View.inflate(Qxx_MessageActivity.this, R.layout.qxx_dialog_list, null);
                builder = new Builder(Qxx_MessageActivity.this);
                builder.setTitle("选择辅导员");


                new Thread() {

                    public void run() {

                        String path = port.port+"/counsellor";


                        SDS_Httpclient conn = new SDS_Httpclient();
                        conn.Postclient(path, "", handler);


                    }


                }.start();


            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        showUpdate();
    }

    public void showUpdate() {

        new Thread() {

            public void run() {

                String path = port.port+"/qjqxsxs";

                SDS_Httpclient conn = new SDS_Httpclient();
                conn.Postclient(path, "", handler2);

            }
        }.start();
        itemList = new ArrayList<Map<String, Object>>();


    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> paramAdapterView,
                                View paramView, int paramInt, long paramLong) {

            System.out.println("item----------click");
            Map<String, Object> localMap = Qxx_MessageActivity.this.itemList
                    .get(paramInt);
            if (((Boolean) localMap.get("EXPANDED")).booleanValue()) {
                localMap.put("EXPANDED", Boolean.valueOf(false));
            } else {
                localMap.put("EXPANDED", Boolean.valueOf(true));
            }
            Qxx_MessageActivity.this.adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__message, menu);
        return true;
    }

}
