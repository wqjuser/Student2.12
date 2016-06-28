package cn.hsd.college.tcatch.basefragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.college.model.teacher_leader;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class c_add_teacher_contentbBaseTagPager extends BaseTagPager1 {


    private EditText et_name;
    private EditText et_sax;
    private EditText et_gonghao;
    private EditText et_nianji;
    private EditText et_zhuanye;
    private EditText et_pici;
    private teacher_leader teacher;
    private Button bt_add_teacher;

    private SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            Toast.makeText(context, "", Toast.LENGTH_LONG).show();
            super.onSuccess(content);
        }


        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(context, "网络连接异常！请检查网络！", Toast.LENGTH_LONG).show();
        }
    };

    public c_add_teacher_contentbBaseTagPager(Context context) {
        super(context);


    }


    protected void initData() {
        // TODO Auto-generated method stub
        tv_title.setText("添加辅导员");
        View view = View.inflate(context, R.layout.catch_add, null);
        et_name = (EditText) view.findViewById(R.id.sds_add_teacher_name1);
        et_zhuanye = (EditText) view.findViewById(R.id.sds_add_teacher_dailingzhuanye1);
        et_gonghao = (EditText) view.findViewById(R.id.sds_add_teacher_gonghao1);
        et_nianji = (EditText) view.findViewById(R.id.sds_add_teacher_nianji1);
        et_pici = (EditText) view.findViewById(R.id.sds_add_teacher_pici1);
        et_sax = (EditText) view.findViewById(R.id.sds_add_teacher_sax1);
        bt_add_teacher = (Button) view.findViewById(R.id.sds_add_teacher_leader1);


//		private String counselor_name = "";//姓名
//		private String counselor_password ="";
//		private String counselor_userName ="";//辅导员账号
//		private String counselor_major ="";//专业
//		private String counselor_college ="";//学院
//		private String counselor_email ="";//邮箱

        teacher = new teacher_leader();
        teacher.setCounselor_name(et_name.getText().toString().trim());
        teacher.setCounselor_major(et_zhuanye.getText().toString().trim());
        teacher.setCounselor_userName(et_gonghao.getText().toString().trim());
        teacher.setCounselor_college(et_nianji.getText().toString().trim());//学院
        teacher.setCounselor_email(et_pici.getText().toString().trim());//邮箱
        teacher.setCounselor_password(et_sax.getText().toString().trim());//密码

        bt_add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        String path = port.port + "/fudaoyuanadd.action";
                        SDS_Httpclient conn = new SDS_Httpclient();
                        json<teacher_leader> returnjson = new json<teacher_leader>();
                        String jsonstring = returnjson.ObjectToJson1(teacher);
                        conn.Postclient(path, jsonstring, handler);
                    }
                }.start();
            }
        });

        fl_content.addView(view);

        super.initData();
    }


}
