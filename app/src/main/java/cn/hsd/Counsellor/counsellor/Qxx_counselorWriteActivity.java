package cn.hsd.Counsellor.counsellor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.student.message.Qxx_Date;
import cn.hsd.student.message.Qxx_DrawLine;


public class Qxx_counselorWriteActivity extends Activity {

    private Button cancelButton;
    private Context context = this;
    private String date;
    private EditText editText;
    private Qxx_Date getDate;
    private Button sureButton;
    private TextView textView;
    private String top;
    private String toName, toNo;
    private Qxx_Message message;
    private String clr_no;


    SDS_ZMHandler handler = new SDS_ZMHandler() {
        public void onSuccess(String content) {

        }

        ;

        public void onFailture(String content) {

        }

        ;

    };

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);


        setContentView(R.layout.activity_qxx_write);
        textView = (TextView) findViewById(R.id.qxx_writedate);
        editText = (Qxx_DrawLine) findViewById(R.id.qxx_edittext);
        cancelButton = (Button) findViewById(R.id.qxx_cancel);
        sureButton = (Button) findViewById(R.id.qxx_send);

        @SuppressWarnings("unchecked")
        HashMap<String, Object> st = (HashMap<String, Object>) getIntent().getExtras().get("student");

        toName = st.get("student_name").toString();
        toNo = st.get("student_no").toString();
        clr_no = (String) getIntent().getExtras().getString("clr_no");
        getDate = new Qxx_Date();
        date = this.getDate.getDate();
        String top = "To:    " + toName + "                   " + date;
        textView.setText(top);
        sureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
//				SQLiteDatabase Database = new Qxx_SqliteHelper(Qxx_WriteActivity.this.context, null, null, 0).getWritableDatabase();
                message = new Qxx_Message();
//				Qxx_ChangeSqlite ChangeSqlite = new Qxx_ChangeSqlite();
                String strContent = Qxx_counselorWriteActivity.this.editText.getText().toString();
                if (strContent.equals("")) {
                    Toast.makeText(Qxx_counselorWriteActivity.this.context, "内容为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strContent.length() > 200) {
                    Toast.makeText(Qxx_counselorWriteActivity.this.context, "不能超过200字！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strTitle = strContent.length() > 10 ? " " + strContent.substring(0, 8) : strContent;
                message.setMessage_detail(strContent);
                message.setMessage_time(date);
                message.setMessage_receiveNo(toNo);
                message.setMessage_sendNo(clr_no);


//上传服务器：
                new Thread() {//注意我没封装子线程

                    public void run() {

                        String path = port.port + "/qxx_message.action";


                        SDS_Httpclient conn = new SDS_Httpclient();//获取连接
                        json<Qxx_Message> returnjson = new json<Qxx_Message>();//new 一个json对象
                        String data = returnjson.ObjectToJson1(message);
                        conn.Postclient(path, data, handler); //以post方法提交数据


                    }


                }.start();

                finish();
            }
        });
        this.cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx_counselor_write, menu);
        return true;
    }

}
