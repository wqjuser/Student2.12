package cn.hsd.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.college.model.gonge_gonggao;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class google_fabiao extends AppCompatActivity {

    private EditText editText_title;
    private EditText editText_content;
    private Button bt_google_fabiao;

    private SDS_ZMHandler handle = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            Toast.makeText(google_fabiao.this, "发表成功", Toast.LENGTH_LONG).show();
            super.onSuccess(content);
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(google_fabiao.this, "网络连接异常！请检查网络！", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_fabiao);
        initView();
    }


    public void initView() {
        editText_title = (EditText) this.findViewById(R.id.sds_google_fabiao_title);
        editText_content = (EditText) this.findViewById(R.id.sds_google_fabiao_content);
        bt_google_fabiao = (Button) this.findViewById(R.id.sds_google_fabiao_tijiao);
        bt_google_fabiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String path = port.port + "/google.action";
                        gonge_gonggao gongle = new gonge_gonggao();
                        gongle.setTitle(editText_title.getText().toString());
                        gongle.setContent(editText_content.getText().toString());
                        json<gonge_gonggao> returnjson = new json<gonge_gonggao>();
                        String data = returnjson.ObjectToJson1(gongle);

                        SDS_Httpclient conn = new SDS_Httpclient();
                        conn.Postclient(path, data, handle);
                    }
                }.start();
            }
        });


    }
}
