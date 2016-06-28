package cn.hsd.college.teacher.baseFragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.college.model.teacher_zhidao;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class teacher_leader_contentbBaseTagPager extends BaseTagPager {
    private ListView list_view;
    private ArrayList<HashMap<String, Object>> listItem;
    private List<teacher_zhidao> list1;
    private List<Map> list2;

    private SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            list2 = new ArrayList<Map>();
            list1 = new ArrayList<teacher_zhidao>();
            System.out.println(content);
            View view = View.inflate(context, R.layout.teacher_list, null);
            list_view = (ListView) view.findViewById(R.id.sds_teacher_leader_list);
            listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
            json<teacher_zhidao> returnjson = new json<teacher_zhidao>();
            list1 = returnjson.jsonToList2(content);
            for (int i = 0; i < list1.size(); i++) {
                Map map = (Map) list1.get(i);
                list2.add(map);
            }

//			private String adviser_name = "";//姓名
//			private String adviser_password ="";
//			private String adviser_userName ="";//辅导员账号
//			private String adviser_major ="";//专业
//			private String adviser_college ="";//学院
//			private String adviser_email ="";//邮箱

            for (int i = 0; i < list2.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("teacher_name", list2.get(i).get("adviser_name"));
                map.put("teacher_sax", "*******");//list2.get(i).get("adviser_password")
                map.put("teacher_gonghao", list2.get(i).get("adviser_userName"));
                map.put("teacher_dailingzhuanye", list2.get(i).get("adviser_major"));
                map.put("teacher_nianji", list2.get(i).get("adviser_college"));
                map.put("teacher_pici", list2.get(i).get("adviser_email"));
                listItem.add(map);
            }
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, listItem, R.layout.auto_sds_teacher_list, new String[]{"teacher_name",
                    "teacher_sax", "teacher_gonghao", "teacher_dailingzhuanye", "teacher_nianji", "teacher_pici"},
                    new int[]{R.id.techer_list_name, R.id.techer_list_sax,
                            R.id.techer_list_tno, R.id.techer_list_leader_zhuanye,
                            R.id.techer_list_leader_nianji, R.id.techer_list_leader_pici}
            );

            list_view.setAdapter(mSimpleAdapter);
            fl_content.addView(view);


        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(context, "网络连接异常！请检查网络！", Toast.LENGTH_LONG).show();
        }
    };

    public teacher_leader_contentbBaseTagPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void initData() {
        // TODO Auto-generated method stub
        tv_title.setText("指导教师");
        new Thread() {
            boolean flag = true;

            public void run() {

                while (flag) {
                    if (null != handler) {
                        String path = port.port + "/adviserlist.action";
                        SDS_Httpclient conn = new SDS_Httpclient();
                        conn.Postclient(path, "true", handler);
                        flag = false;
                    }
                }
            }
        }.start();

        super.initData();
    }


    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
