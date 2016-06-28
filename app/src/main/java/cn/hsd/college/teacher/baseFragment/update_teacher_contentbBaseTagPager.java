package cn.hsd.college.teacher.baseFragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.college.model.teacher_zhidao;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class update_teacher_contentbBaseTagPager extends BaseTagPager {


    private EditText et_name;
    private EditText et_sax;
    private EditText et_gonghao;
    private EditText et_nianji;
    private EditText et_zhuanye;
    private EditText et_pici;
    private Button bt_updata_teacher;
    private teacher_zhidao teacher;

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


    public update_teacher_contentbBaseTagPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    protected void initData() {
        // TODO Auto-generated method stub
        tv_title.setText("更新指导教师");
        View view = View.inflate(context, R.layout.update_teacher, null);

        bt_updata_teacher = (Button) view.findViewById(R.id.sds_update_teacher_leader);
        et_name = (EditText) view.findViewById(R.id.sds_update_teacher_name);
        et_zhuanye = (EditText) view.findViewById(R.id.sds_update_teacher_dailingzhuanye);
        et_gonghao = (EditText) view.findViewById(R.id.sds_update_teacher_gonghao);
        et_nianji = (EditText) view.findViewById(R.id.sds_update_teacher_nianji);
        et_pici = (EditText) view.findViewById(R.id.sds_update_teacher_pici);
        //et_sax = (EditText)view.findViewById(R.id.sds_update_teacher_sax);


//		public class teacher_zhidao {
//
//			private String adviser_name = "";//姓名
//			private String adviser_password ="";
//			private String adviser_userName ="";//辅导员账号
//			private String adviser_major ="";//专业
//			private String adviser_college ="";//学院
//			private String adviser_email ="";//邮箱

        teacher = new teacher_zhidao();
        teacher.setAdviser_name(et_name.getText().toString().trim());
        teacher.setAdviser_major(et_zhuanye.getText().toString().trim());
        teacher.setAdviser_userName(et_gonghao.getText().toString().trim());
        teacher.setAdviser_college(et_nianji.getText().toString().trim());
        teacher.setAdviser_email(et_pici.getText().toString().trim());
        //teacher.setAdviser_password(et_sax.getText().toString().trim());

        bt_updata_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        String path = port.port + "/adviserupdata.action";
                        SDS_Httpclient conn = new SDS_Httpclient();
                        json<teacher_zhidao> returnjson = new json<teacher_zhidao>();
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
