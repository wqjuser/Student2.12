package cn.hsd.student.message;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_WriteActivity extends Qxx_BaseActivity {
    private Button cancelButton;
    private Context context = this;
    private String date;
    private EditText editText;
    private Qxx_Date getDate;
    private Button sureButton;
    private TextView textView;
    private String top;
    private String toname;
    private Qxx_Message message;


    SDS_ZMHandler handler = new SDS_ZMHandler() {
        public void onSuccess(String content) {
//			json<Qxx_Message> returnjson = new json<Qxx_Message>();
//			List<Qxx_Message> list= returnjson.jsonToList2(content);
//			Qxx_Message data = list.get(0);

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
        toname = (String) getIntent().getExtras().getString("toname");
        getDate = new Qxx_Date();
        date = this.getDate.getDate();
        String top = "       To:    " + toname + "                   " + date;
        textView.setText(top);
        sureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//				SQLiteDatabase Database = new Qxx_SqliteHelper(Qxx_WriteActivity.this.context, null, null, 0).getWritableDatabase();
                message = new Qxx_Message();
//				Qxx_ChangeSqlite ChangeSqlite = new Qxx_ChangeSqlite();
                String strContent = Qxx_WriteActivity.this.editText.getText().toString();
                if (strContent.equals("")) {
                    Toast.makeText(Qxx_WriteActivity.this.context, "内容为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strContent.length() > 200) {
                    Toast.makeText(Qxx_WriteActivity.this.context, "不能超过200字", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strTitle = strContent.length() > 10 ? " " + strContent.substring(0, 8) : strContent;
                message.setMessage_detail(strContent);
                message.setMessage_time(date);
                message.setMessage_receiveName(toname);
                message.setMessage_sendName("����");
//				ChangeSqlite.add(Database, message);


//�ϴ���������
                new Thread() {//ע����û��װ���߳�

                    public void run() {

                        String path = port.port+"/MyHsd/xsxs";


                        SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                        json<Qxx_Message> returnjson = new json<Qxx_Message>();//new һ��json����
                        String data = returnjson.ObjectToJson1(message);
                        conn.Postclient(path, data, handler); //��post�����ύ����


                    }


                }.start();

                finish();
            }
        });
        this.cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__write, menu);
        return true;
    }

}
