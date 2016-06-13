package cn.hsd.student.activity.logcat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.StudentMain.StudentActivity;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class sds_Logcat_Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView logcatlist;
    private ListView logcatlistsum;
    private ImageButton shuaXin;
    private ImageButton fabiao;
    private ImageButton shanchu;
    private ImageButton shouye;
    private SimpleAdapter simp_ada;
    private ArrayList<HashMap<String, Object>> arr_list;
    private Button btn;


    SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onFailture(String content) {
            super.onFailture(content);
//            List<> list = new ArrayList<>();
//            json jsonTools = new json();
//            list = jsonTools.jsonToList2(list);
        }

        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat);

        //显示发表的日志
        show_List_Logcat();
        //绑定控件
        content_service();
    }

    /**
     * 日志显示
     */
    public void show_List_Logcat() {

        arr_list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 20; i++) {
            HashMap map = new HashMap<String, Object>();
            map.put("key", "我是实习生" + i);
            arr_list.add(map);
        }
        //实习日志
        logcatlist = (ListView) this.findViewById(R.id.logcat_list_xinde);
        simp_ada = new SimpleAdapter(this, arr_list, R.layout.auto_logcat_xml
                , new String[]{"key"}, new int[]{R.id.auto_xml_logcat});
        logcatlist.setAdapter(simp_ada);


        logcatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap item = (HashMap) parent.getItemAtPosition(position);
                String data = item.get("key").toString();

                Dialog dialog = new Dialog(sds_Logcat_Activity.this);
                dialog.setContentView(R.layout.sds_auto_dialog);
                dialog.setTitle("我的日志");
                EditText sds_sum_xianshi = (EditText) dialog.findViewById(R.id.sds_sum_content_xianshi);
                sds_sum_xianshi.setText(data);
                dialog.show();
            }
        });
        //实习总结
        logcatlistsum = (ListView) this.findViewById(R.id.logcat_list_sum);
        simp_ada = new SimpleAdapter(this, arr_list, R.layout.auto_logcat_xml
                , new String[]{"key"}, new int[]{R.id.auto_xml_logcat});
        logcatlistsum.setAdapter(simp_ada);


        logcatlistsum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap item = (HashMap) parent.getItemAtPosition(position);
                String data = item.get("key").toString();

                Dialog dialog = new Dialog(sds_Logcat_Activity.this);
                btn = (Button) dialog.findViewById(R.id.sds_return);
                dialog.setTitle("我的总结");
                dialog.setContentView(R.layout.sds_auto_dialog);
                EditText sds_sum_xianshi = (EditText) dialog.findViewById(R.id.sds_sum_content_xianshi);
                sds_sum_xianshi.setText(data);
                dialog.show();
            }
        });
    }
        public void back(View v){
            Intent intent = new Intent(sds_Logcat_Activity.this,sds_Logcat_Activity.class);
            startActivity(intent);

    }




    /**
     * content_service
     */

    public void content_service() {
        shuaXin = (ImageButton) findViewById(R.id.image1);
        fabiao = (ImageButton) findViewById(R.id.image2);
        shanchu = (ImageButton) findViewById(R.id.image3);
        shouye = (ImageButton) findViewById(R.id.image4);
        //实现监听
        shuaXin.setOnClickListener(this);
        fabiao.setOnClickListener(this);
        shanchu.setOnClickListener(this);
        shouye.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                new Thread() {
                    public void run() {
                        String path = port.port + "/MyHsd/xsxs";
                        SDS_Httpclient conn = new SDS_Httpclient();
                        conn.Postclient(path, "", handler);
                    }

                }.start();
                break;
            case R.id.image2:
                //发表日志
                Intent intent = new Intent(this, sds_Logcat_fabiaoLogcat.class);
                startActivity(intent);
                break;
            case R.id.image3:
                Intent intent2 = new Intent(this, sds_xssum_Activitysum.class);
                startActivity(intent2);
                break;
            case R.id.image4:
                Intent intent1 = new Intent(this, StudentActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
