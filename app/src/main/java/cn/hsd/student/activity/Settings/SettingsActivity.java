package cn.hsd.student.activity.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hsd.student.R;
import cn.hsd.student.activity.AboutUs.AboutUsActivity;
import cn.hsd.student.activity.ChangeInfo.ChangeInfoActivity;

public class SettingsActivity extends AppCompatActivity {
    //private ListView listView;
    private TextView tv1;
    private TextView tv2;
    private LinearLayout LL1;
    private LinearLayout LL2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        tv1=(TextView)findViewById(R.id.xinxi_set);
        tv2=(TextView)findViewById(R.id.about_tv);
        LL1=(LinearLayout)findViewById(R.id.xinxi_on);
        LL2=(LinearLayout)findViewById(R.id.about_us);
        /*listView = (ListView) findViewById(R.id.set_list);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("text", "修改信息");
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("text", "关于我们");
        listItem.add(map1);
        listItem.add(map2);
        SimpleAdapter simp = new SimpleAdapter(getApplicationContext(), listItem,
                R.layout.set_list, new String[]{"text"}, new int[]{R.id.set_text
        });
        listView.setAdapter(simp);
        listView.setOnItemClickListener(new WqjListListener());

    }

    //设置列表项点击事件，实现activity的跳转
    class WqjListListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    Intent intent1 = new Intent();
                    intent1.setClass(SettingsActivity.this, ChangeInfoActivity.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent();
                    intent2.setClass(SettingsActivity.this, AboutUsActivity.class);
                    startActivity(intent2);
                    break;


            }
        }*/
    }

    public void xinxi(View v1) {
        Intent intent1 = new Intent(SettingsActivity.this, ChangeInfoActivity.class);
        startActivity(intent1);
    }

    public void about_on(View v2) {
        Intent intent2 = new Intent(SettingsActivity.this, AboutUsActivity.class);
        startActivity(intent2);
    }
}
