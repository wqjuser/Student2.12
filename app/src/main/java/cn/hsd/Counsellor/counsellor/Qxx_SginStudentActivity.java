package cn.hsd.Counsellor.counsellor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import PORT.port;
import cn.hsd.Counsellor.modle.Qxx_Sgin;
import cn.hsd.Counsellor.modle.Qxx_SginScore;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_SginStudentActivity extends Activity {

    private String name;
    private String no;
    private String num, s;
    private String et_score;
    private TextView qxx_sg_stName;
    private TextView qxx_sg_stNo;
    private TextView qxx_sg_stNum;
    private TextView qxx_sg_score;
    private ListView qxx_Lvsg_stu;
    private ArrayList<Qxx_Sgin> list;
    private ArrayList<HashMap<String, Object>> data;
    private Button qxx_sg_Button;

    SDS_ZMHandler handler = new SDS_ZMHandler() {


        public void onSuccess(String content) {


            try {
                JSONArray jsonArray = new JSONArray(content);
                list = new ArrayList<Qxx_Sgin>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    String sign_time = (String) jsonArray.getJSONObject(i).get("sign_time");
                    String sign_address = (String) jsonArray.getJSONObject(i).get("sign_address");
                    String student_no = (String) jsonArray.getJSONObject(i).get("student_no");
                    String counselor_name = (String) jsonArray.getJSONObject(i).get("counselor_name");

                    if (student_no.equals(no)) {

                        Qxx_Sgin sgin = new Qxx_Sgin();
                        sgin.setSign_time(sign_time);
                        sgin.setSign_address(sign_address);
                        sgin.setSign_address(sign_address);
                        sgin.setStudent_no(student_no);
                        sgin.setCounselor_name(counselor_name);
                        list.add(sgin);
                    }
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
            for (Qxx_Sgin sgin : list) {
                HashMap<String, Object> item = new HashMap<String, Object>();

                item.put("sign_time", sgin.getSign_time());
                item.put("sign_address", sgin.getSign_address());
                data.add(item);
            }
            String[] s = {"sign_time", "sign_address"};
            SimpleAdapter adapter = new SimpleAdapter(Qxx_SginStudentActivity.this, data,
                    R.layout.qxx_signstudent_item, s,
                    new int[]{R.id.qxx_sg_time, R.id.qxx_sg_address});
            qxx_Lvsg_stu.setAdapter(adapter);

        }

        ;

        public void onFailture(String content) {

            Toast.makeText(getApplicationContext(), "����δ���ӣ�", Toast.LENGTH_SHORT).show();
        }

        ;

    };

    SDS_ZMHandler handler2 = new SDS_ZMHandler() {

        public void onSuccess(String content) {

        }

        ;

        public void onFailture(String content) {

            Toast.makeText(getApplicationContext(), "����δ���ӣ�", Toast.LENGTH_SHORT).show();
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx__sgin_student);

        qxx_sg_stName = (TextView) findViewById(R.id.qxx_sg_stName);
        qxx_sg_stNo = (TextView) findViewById(R.id.qxx_sg_stNo);
        qxx_sg_stNum = (TextView) findViewById(R.id.qxx_sg_stNum);
        qxx_sg_score = (TextView) findViewById(R.id.qxx_sg_score);
        qxx_Lvsg_stu = (ListView) findViewById(R.id.qxx_Lvsg_stu);
        qxx_sg_Button = (Button) findViewById(R.id.qxx_sg_Button);


        @SuppressWarnings("unchecked")
        HashMap<String, Object> sg = (HashMap<String, Object>) getIntent().getExtras().get("sgin_student");
        name = sg.get("student_name").toString();
        no = sg.get("student_no").toString();
        num = sg.get("sgin_num").toString();

        qxx_sg_stName.setText(name);
        qxx_sg_stNo.setText(no);
        qxx_sg_stNum.setText(num);


        qxx_sg_Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO �Զ����ɵķ������
                if (qxx_sg_score.getText() == "") {
                    dialog();
                } else {
                    Builder builder = new Builder(Qxx_SginStudentActivity.this);
                    builder.setTitle("是否修改该学生的出勤成绩？");
//					builder.setIcon(R.drawable.ic_launcher);
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            dialog();
                        }
                    });
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {


                        }
                    });
                    builder.show();
                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    public void dialog() {
        final AlertDialog dialog = new Builder(Qxx_SginStudentActivity.this).create();
        dialog.setTitle("请为此学生评定出勤成绩");
        final EditText eText = new EditText(Qxx_SginStudentActivity.this);
//		dialog.setIcon(R.drawable.ic_launcher);
        dialog.setView(eText);
        dialog.setButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO �Զ����ɵķ������
                et_score = eText.getText().toString();

                if (et_score.matches("\\d+(.\\d+)?") && Float.parseFloat(et_score) <= 100) {
                    insert();
                } else {
                    Toast.makeText(getApplicationContext(), "请输入<=100的有效数值分数！", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.setButton2("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO �Զ����ɵķ������

            }
        });
        dialog.show();

    }

    public void insert() {
        qxx_sg_score.setText(et_score + "��");
        final Qxx_SginScore SginScore = new Qxx_SginScore();
        SginScore.setStudent_no(no);
        SginScore.setStudent_SginScore(et_score);

        new Thread() {//ע����û��װ���߳�

            public void run() {

                String path = port.port + "/sgin_score.action";
                SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                json<Qxx_SginScore> returnjson = new json<Qxx_SginScore>();//new һ��json����
                String data = returnjson.ObjectToJson1(SginScore);
                conn.Postclient(path, data, handler2); //��post�����ύ����
            }


        }.start();
        Toast.makeText(getApplicationContext(), "该学生的出勤成绩为：" + et_score + "��", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        // TODO �Զ����ɵķ������
        super.onResume();
        showUpdate();
    }

    public void showUpdate() {


        new Thread() {//ע����û��װ���߳�

            public void run() {

                String path = port.port + "/qxx_sgin.action";

                SDS_Httpclient conn = new SDS_Httpclient();//��ȡ����
                conn.Postclient(path, "", handler); //��post�����ύ����

            }
        }.start();
        data = new ArrayList<HashMap<String, Object>>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qxx__sgin_student, menu);
        return true;
    }

}
