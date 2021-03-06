package cn.hsd.college.teacher.baseFragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class delect_teacher_contentbBaseTagPager extends BaseTagPager {
    private EditText et_teacher_tno;
    private Button bt_delect;

    private SDS_ZMHandler handler = new SDS_ZMHandler() {
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(context, "删除完成", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(context, "网络连接异常！请检查网络！", Toast.LENGTH_LONG).show();
        }
    };

    public delect_teacher_contentbBaseTagPager(Context context) {

        super(context);
    }

    @Override
    protected void initData() {
        tv_title.setText("删除指导教师");
        iv_menu.setVisibility(View.GONE);
        View view = View.inflate(context, R.layout.delect_teacher, null);
        et_teacher_tno = (EditText) view.findViewById(R.id.sds_delete_tno);
        bt_delect = (Button) view.findViewById(R.id.sds_delect_bt);
        bt_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread() {
                    public void run() {
                        String path = port.port + "/adviserdelect.action";
                        SDS_Httpclient conn = new SDS_Httpclient();
                        conn.Postclient(path, et_teacher_tno.getText().toString() + "", handler);
                    }
                }.start();
            }
        });
        fl_content.addView(view);
        super.initData();
    }
}
