package cn.hsd.student.activity.logcat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cn.hsd.student.R;



public class sds_Logcat_fabiaoLogcat extends AppCompatActivity {
    private  EditText edittext_title;
    private  EditText edittext_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sds__logcat_fabiao_logcat);
    }

    public void sds_logcat_fabiaoTijiao(View view){
        edittext_title = (EditText) findViewById(R.id.sds_et_logcat_title);
        edittext_content = (EditText) findViewById(R.id.sds_content_logcat);
    }
}
