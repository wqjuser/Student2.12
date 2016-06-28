package cn.hsd.student.activity.StudentMain;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.college.model.gonge_gonggao;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.Settings.SettingsActivity;
import cn.hsd.student.activity.gxq_class.gxqStatic;
import cn.hsd.student.activity.leava.Gxq_apply_main;
import cn.hsd.student.activity.logcat.sds_Logcat_Activity;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.student.activity.sqyxsxs.qmf_ApplyCon;
import cn.hsd.student.message.Qxx_MessageMain;


public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tv1;
    private ImageView iv1;
    private Button bt1;

    private final int OPEN_RESULT = 1;
    private final int PICK_RESULT = 2;
    private ListView listview;
    private List<gonge_gonggao> list1;
    private List<Map> list2;

    private ArrayList<HashMap<String, Object>> listItem;
    private SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            listview = (ListView) StudentActivity.this.findViewById(R.id.stu_googe_fabiao);
            list2 = new ArrayList<Map>();

            listItem = new ArrayList<HashMap<String, Object>>();
            list1 = new ArrayList<gonge_gonggao>();
            json<gonge_gonggao> returnjson = new json<gonge_gonggao>();

            System.out.println(content);

            list1 = returnjson.jsonToList2(content);
            for (int i = 0; i < list1.size(); i++) {
                Map map = (Map) list1.get(i);
                list2.add(map);
            }


//            HashMap<String,Object> map = new HashMap<String,Object>();
//            map.put("title","公告标题");
//            map.put("content","中新网6月16日电 据北京市气象局官方微博消息，明天全市大部分地区将有阵雨或雷雨天气。" +
//                    "具体预报：今夜多云，北部有雷阵雨，最低气温22℃；明天白天多云转阴，西部北部有阵雨或雷阵雨");
//            list.add(map);
            for (int i = 0; i < list2.size(); i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("title", list2.get(i).get("title"));
                map.put("content", list2.get(i).get("content"));

                listItem.add(map);
            }


            SimpleAdapter mysimple = new SimpleAdapter(StudentActivity.this, listItem
                    , R.layout.auto_sds_googe, new String[]{"title", "content"}, new int[]{R.id.tv_sds_googe, R.id.tv_sds_content_googe});
            listview.setAdapter(mysimple);


        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(StudentActivity.this, "网络连接异常！请检查网络！", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Thread() {
            public void run() {
                String path = port.port + "/google.action";
                SDS_Httpclient conn = new SDS_Httpclient();
                conn.Postclient(path, "", handler);
            }

        }.start();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);
        tv1=(TextView)headerView.findViewById(R.id.hq_info_stu);
        tv1.setText(gxqStatic.info);
        iv1 = (ImageView) headerView.findViewById(R.id.stu_ce_iv);
    }

    public void camera(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
        builder.setTitle("选择拍照方式");
        builder.setPositiveButton("本地图片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_RESULT);

            }
        });
        builder.setNegativeButton("相机拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, OPEN_RESULT);
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OPEN_RESULT:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    iv1.setImageBitmap(bitmap);
                }
                break;
            case PICK_RESULT:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    iv1.setImageURI(uri);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student, menu);
        //tv1.setText("2222222");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.stugg) {
            new Thread() {
                public void run() {
                    String path = port.port + "/google.action";
                    SDS_Httpclient conn = new SDS_Httpclient();
                    conn.Postclient(path, "", handler);
                }

            }.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Stu_Holiday) {
            Intent intent1= new Intent(this, Gxq_apply_main.class);
            startActivity(intent1);

        } else if (id == R.id.Stu_apply_good_Student) {
            Intent intent2= new Intent(this, qmf_ApplyCon.class);
            startActivity(intent2);
        } else if (id == R.id.Stu_logCat) {
            Intent intent = new Intent(this,sds_Logcat_Activity.class);
            startActivity(intent);
        } else if (id == R.id.Stu_setting) {
            Intent intent3= new Intent(this,SettingsActivity.class);
            startActivity(intent3);

        } else if (id == R.id.nav_share) {
            Intent intent4 = new Intent(this, Qxx_MessageMain.class);
            startActivity(intent4);

        } else if (id == R.id.nav_send) {
            Intent intent4 = new Intent(this, Qxx_MessageMain.class);
            startActivity(intent4);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
