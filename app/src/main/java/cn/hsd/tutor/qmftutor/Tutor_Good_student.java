package cn.hsd.tutor.qmftutor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;
import cn.hsd.tutor.model.Good_Student_Apply;


public class Tutor_Good_student extends AppCompatActivity {
    List<String> listgxq1 = new ArrayList<String>();

    //   public List<Gxq_apply_student> shishiList1  = new ArrayList<Gxq_apply_student>();
    public List<String> dataList1 = new ArrayList<String>();//返回的主要信息筛选过的
    public List<Good_Student_Apply> personmessage  = new ArrayList<Good_Student_Apply>();
    public List<Good_Student_Apply> personmessage2  = new ArrayList<Good_Student_Apply>();
    public List<Good_Student_Apply> personmessage3  = new ArrayList<Good_Student_Apply>();
    public List<Map> listmap =  new ArrayList<Map>();;

    public Spinner applyshow;
    public ListView applyshow_list;
    private ArrayAdapter<String> adapter1;
    public RadioGroup radioGroup;
    public RadioButton radioButton1;
    public RadioButton radioButton2;

    private Good_Student_Apply lastshow_gxqapply;
    public TextView starttime;
    public TextView applychieve;
    public Good_Student_Apply data;
    public TextView apply_reason;
    public Button click_result;
    public Double result_id;
    public String check_result;
    private SDS_Httpclient conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_good_student);
        applyshow = (Spinner) findViewById(R.id.spinner_gs);
        applyshow_list = (ListView)findViewById(R.id.lv_apply_time) ;
        radioGroup = (RadioGroup) findViewById(R.id.qmf_sex);
        radioButton1 =(RadioButton) findViewById(R.id.male);
        radioButton2 =(RadioButton) findViewById(R.id.qmf_female);
        starttime = (TextView) findViewById(R.id.tv_apply_time);
        applychieve = (TextView) findViewById(R.id.tv_apply_chieve);
        apply_reason = (TextView) findViewById(R.id.tv_apply_reason);
        click_result = (Button) findViewById(R.id.qmf_click_result);
        //-----------------------------------------------------------radioBUtton-----------------------------------------------------
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==radioButton1.getId()){
                    Toast.makeText(Tutor_Good_student.this, "选择了同意", Toast.LENGTH_SHORT).show();
                    check_result = "同意";
                }else if(checkedId==radioButton2.getId()){
                    Toast.makeText(Tutor_Good_student.this, "选择了拒绝", Toast.LENGTH_SHORT).show();
                    check_result = "拒绝";
                }
            }
        });

        adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, dataList1);
        applyshow_list.setAdapter(adapter1);
        applyshow_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastshow_gxqapply =  personmessage.get(position);
                starttime.setText(lastshow_gxqapply.getGs_apply_time());
                applychieve.setText(lastshow_gxqapply.getGs_apply_chieve());
                apply_reason.setText(lastshow_gxqapply.getGs_apply_reason());
                result_id = lastshow_gxqapply.getGoodStudent_id();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------


    }

    public List<String> diaoyong(List<Good_Student_Apply>  backdata ,String name) {

        if (backdata.size() > 0) {
            dataList1.clear();
            personmessage.clear();
            for (Good_Student_Apply provience : backdata) {
                //    Toast.makeText(Gxq_apply_main.this, provience.getApply_student_beginname(), Toast.LENGTH_SHORT).show();
                if((provience.getStudent_name()).equals(name)){
                    dataList1.add(provience.getCreate_time());
                    personmessage.add(provience);
                }
            }
        }
        return dataList1;
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.qmf_click_result:
                if ((starttime.getText()).equals("开始时间")){
                    Toast.makeText(Tutor_Good_student.this, "请选择处理的假条", Toast.LENGTH_SHORT).show();
                }else {
                    connect_tijiao();
                }

                break;
            default:
                break;
        }
    }
    //-------------------------------------提交教师返回的假条 处理数据-------------------------------------------------------------------------
    public SDS_ZMHandler gxq_teacherbackhandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            Toast.makeText(Tutor_Good_student.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            json<String> gxqreturn = new json<String>();
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Tutor_Good_student.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    public void connect_tijiao(){
        new Thread(){
            public void run(){
                String path = port.port+"/lwxsxs";
                conn = new SDS_Httpclient();
                data = new Good_Student_Apply();
                data.setGoodStudent_id(result_id);
                data.setApply_check(check_result);
                json<Good_Student_Apply> dataVerture = new json<Good_Student_Apply>();
                String returnJson = dataVerture.ObjectToJson1(data);
                conn.Postclient(path,returnJson,gxq_teacherbackhandler);
            }
        }.start();
    }
    //----------------------------------------------------获取学生姓名------------------------------------------------------------
    public void connect_studentname(){
        new Thread(){
            public void run(){
                String path = port.port+"/goodnamexsxs";
                conn = new SDS_Httpclient();

                conn.Postclient(path,"",gxq_studentnaehandler);
            }
        }.start();
    }
    public SDS_ZMHandler gxq_studentnaehandler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            //          Toast.makeText(gxq_teacher_Activity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            json<String> gxqreturn = new json<String>();

            listgxq1= gxqreturn.jsonToList2(content);
            ArrayAdapter<String> apply_adapter = new ArrayAdapter<String>(Tutor_Good_student.this, android.R.layout.simple_spinner_item, listgxq1);
            applyshow.setAdapter(apply_adapter);


        }
        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Tutor_Good_student.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    //---------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------获取信息List（Gxq_apply——student）--------------------------------------------------------------
    public void connect_apply(){
        new Thread(){
            public void run(){
                String path = port.port+"/gsxsxs";
                conn.Postclient(path,"",gxq_apply_handler);
            }
        }.start();
    }
    public SDS_ZMHandler gxq_apply_handler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            //    Toast.makeText(Apply_gxqshow_Activity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            personmessage2.clear();
            listmap.clear();
            personmessage3.clear();
            json<Good_Student_Apply> gxqreturn1 = new json<Good_Student_Apply>();
            personmessage2= gxqreturn1.jsonToList2(content);
            for(int i=0;i<personmessage2.size();i++){
                Map map = (Map) personmessage2.get(i);
                listmap.add(map);
            }
            for (Map ll : listmap){
                Good_Student_Apply rr = new Good_Student_Apply();
                Double qq = (Double) ll.get("leave_id");
                String ww = (String) ll.get("create_time");
                String ee = (String) ll.get("student_name") ;
                String tt = (String) ll.get("counserlor_name") ;
                String yy = (String) ll.get("leave_content") ;
                String uu = (String) ll.get("leave_begin") ;
                String ii = (String) ll.get("leave_end") ;
                String oo = (String) ll.get("leave_check") ;
                rr.setGoodStudent_id(qq);
                rr.setCreate_time(ww);
                rr.setStudent_name(ee);
                rr.setCounserlor_name(tt);
                rr.setGs_apply_chieve(yy);
                rr.setGs_apply_time(uu);
                rr.setGs_apply_reason(ii);
                rr.setLeave_check(oo);

                personmessage3.add(rr);
            }

            applyshow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //    Toast.makeText(Apply_gxqshow_Activity.this, "你点击的是:"+listgxq1.get(position), Toast.LENGTH_SHORT).show();
                    //spinner = apply_spinner[position];
                    String aa = listgxq1.get(position);
                    dataList1 = diaoyong(personmessage3,aa);
                    adapter1.notifyDataSetChanged();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Tutor_Good_student.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    //---------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        connect_studentname();
        connect_apply();
    }
}
