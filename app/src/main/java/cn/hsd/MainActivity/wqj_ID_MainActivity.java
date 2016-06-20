package cn.hsd.MainActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cn.hsd.login.wqj_FTea_LoginActivity;
import cn.hsd.login.wqj_Stu_LoginActivity;
import cn.hsd.login.wqj_XA_LoginActivity;
import cn.hsd.login.wqj_YA_LoginActivity;
import cn.hsd.login.wqj_ZTea_LoginActivity;
import cn.hsd.student.R;

public class wqj_ID_MainActivity extends Activity {
    private Spinner spinner;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wqj_main);
        spinner = (Spinner) findViewById(R.id.spinner);


    /*    List<String> list = new ArrayList<String>();
        list.add("请选择以下身份");
        list.add("学生");
        list.add("指导老师");
        list.add("辅导员");
        list.add("校级管理员");
        list.add("院级管理员");*/
        final String[] apply_spiner={"请选择以下身份","学生","指导老师","辅导员","校级管理员","院级管理员"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apply_spiner);

        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new SpinnerListener());
    }

    class SpinnerListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            String selected2 = adapterView.getItemAtPosition(position).toString();

            switch (selected2) {
                case "学生":
                    Toast.makeText(wqj_ID_MainActivity.this, "您选择了学生", Toast.LENGTH_SHORT).show();
                    spinner.setSelection(0, true);
                    Intent intent = new Intent(wqj_ID_MainActivity.this, wqj_Stu_LoginActivity.class);
                    startActivity(intent);
                    break;
                case "指导老师":
                    Toast.makeText(wqj_ID_MainActivity.this, "您选择了指导老师", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(wqj_ID_MainActivity.this, wqj_ZTea_LoginActivity.class);
                    startActivity(intent1);
                    break;
                case "辅导员":
                    Toast.makeText(wqj_ID_MainActivity.this, "您选择了辅导员", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(wqj_ID_MainActivity.this, wqj_FTea_LoginActivity.class);
                    startActivity(intent2);
                    break;
                case "校级管理员":
                    Toast.makeText(wqj_ID_MainActivity.this, "您选择了校级管理员", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(wqj_ID_MainActivity.this, wqj_XA_LoginActivity.class);
                    startActivity(intent3);
                    break;
                case "院级管理员":
                    Toast.makeText(wqj_ID_MainActivity.this, "您选择了院级管理员", Toast.LENGTH_SHORT).show();
                    Intent intent4 = new Intent(wqj_ID_MainActivity.this, wqj_YA_LoginActivity.class);
                    startActivity(intent4);
                    break;
            }

        }

        //当用户不做选择时调用的该方法
        public void onNothingSelected(AdapterView<?> arg0) {
            Toast.makeText(wqj_ID_MainActivity.this, "您没有选择任何选项", Toast.LENGTH_SHORT).show();

        }
    }

}

