package cn.hsd.student.activity.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import cn.hsd.student.R;
public class InfoActivity extends AppCompatActivity {
    //private ListView info_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
       // info_lv=(ListView)findViewById(R.id.wqj_lv);
        /*ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("text","姓名");
        map1.put("EditText","");
        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("text","年龄");
        map2.put("EditText","");
        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("text","学院");
        map3.put("EditText","");
        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("text","专业");
        map4.put("EditText","");
        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("text","姓名");
        map5.put("EditText","");
        listItem.add(map1);
        listItem.add(map2);
        listItem.add(map3);
        listItem.add(map4);
        listItem.add(map5);
        SimpleAdapter simp = new SimpleAdapter(getApplicationContext(),listItem,
                R.layout.list,new String[]{"text","EditText"},new int[]{R.id.info_tv,R.id.info_et});
        info_lv.setAdapter(simp);*/

    }
    public void commit(View v){

    }
}
