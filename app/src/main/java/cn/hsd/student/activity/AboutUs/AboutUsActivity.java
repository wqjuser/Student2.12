package cn.hsd.student.activity.AboutUs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import cn.hsd.student.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_us);
    }
}
