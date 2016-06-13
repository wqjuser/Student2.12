package cn.hsd.student.activity.logcat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cn.hsd.student.R;


public class sds_Logcat_Activity extends AppCompatActivity implements View.OnClickListener{
    private ListView logcatlist;
    private ImageButton shuaXin;
    private ImageButton fabiao;
    private ImageButton shanchu;
    private ImageButton shouye;

    private SimpleAdapter simp_ada;

    private ArrayList<HashMap<String,Object>> arr_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sds__logcat_);
        //显示发表的日志
        show_List_Logcat();
        //绑定控件
        content_service();




    }


    /**
     * 日志显示
     */


    public void show_List_Logcat(){
        arr_list = new ArrayList<HashMap<String,Object>>();
        for(int i = 0;i<20;i++){
            HashMap map = new HashMap<String,Object>();
            map.put("key","我是实习生"+i);
            arr_list.add(map);
        }

        logcatlist =(ListView) this.findViewById(R.id.logcat_list_xinde);
        simp_ada = new SimpleAdapter(this, arr_list, R.layout.auto_logcat_xml
                ,new String[]{"key"},new int[]{R.id.auto_xml_logcat});
        logcatlist.setAdapter(simp_ada);
    }

    /**
     * content_service
     */

    public void content_service(){
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
        switch (v.getId()){
            case R.id.image1:
                //刷新logcat
                break;
            case R.id.image2:
                //发表日志
                Intent intent = new Intent(this,sds_Logcat_fabiaoLogcat.class);
                startActivity(intent);
                break;
            case R.id.image3:
                //删除日志
                break;
            case R.id.image4:
                Intent intent1 = new Intent(this,StudentActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
