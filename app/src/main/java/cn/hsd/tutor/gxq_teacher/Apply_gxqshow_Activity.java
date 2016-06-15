package cn.hsd.tutor.gxq_teacher;

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
import cn.hsd.tutor.gxq_class.Signin_class;
import cn.hsd.tutor.gxq_class.Gxq_apply_student;


public class Apply_gxqshow_Activity extends AppCompatActivity {

    /**
     * 放置String类型的list    存储学生的姓名。
     */
    List<String> listgxq1 = new ArrayList<String>();

 //   public List<Gxq_apply_student> shishiList1  = new ArrayList<Gxq_apply_student>();
    public List<String> dataList1 = new ArrayList<String>();//返回的主要信息筛选过的
    public List<Gxq_apply_student> personmessage  = new ArrayList<Gxq_apply_student>();
    public List<Gxq_apply_student> personmessage2  = new ArrayList<Gxq_apply_student>();
    public List<Gxq_apply_student> personmessage3  = new ArrayList<Gxq_apply_student>();
    public List<Map> listmap =  new ArrayList<Map>();;

    public Spinner applyshow;
    public ListView applyshow_list;
    private ArrayAdapter<String> adapter1;
    public RadioGroup radioGroup;
    public RadioButton radioButton1;
    public RadioButton radioButton2;

    private Gxq_apply_student lastshow_gxqapply;
    public TextView starttime;
    public TextView endtime;
    public TextView apply_reason;
    public Button click_result;
    public Double result_id;
    public String check_result;
    private SDS_Httpclient conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_gxqshow_);
        applyshow = (Spinner) findViewById(R.id.spinner_studengapply);
        applyshow_list = (ListView)findViewById(R.id.showapply_gxq) ;
        radioGroup = (RadioGroup) findViewById(R.id.sex);
        radioButton1 =(RadioButton) findViewById(R.id.male);
        radioButton2 =(RadioButton) findViewById(R.id.female);
        starttime = (TextView) findViewById(R.id.beginTime_gxq);
        endtime = (TextView) findViewById(R.id.endTime_gxq);
        apply_reason = (TextView) findViewById(R.id.apply_reason);
        click_result = (Button) findViewById(R.id.click_result);
        //-----------------------------------------------------------radioBUtton-----------------------------------------------------
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==radioButton1.getId()){
                    Toast.makeText(Apply_gxqshow_Activity.this, "选择了接收", Toast.LENGTH_SHORT).show();
                    check_result = "同意";
                }else if(checkedId==radioButton2.getId()){
                    Toast.makeText(Apply_gxqshow_Activity.this, "选择了拒绝", Toast.LENGTH_SHORT).show();
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
                starttime.setText(lastshow_gxqapply.getLeave_begin());
                endtime.setText(lastshow_gxqapply.getLeave_end());
                apply_reason.setText(lastshow_gxqapply.getLeave_content());
                result_id = lastshow_gxqapply.getLeave_id();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------------


    }

    public List<String> diaoyong(List<Gxq_apply_student>  backdata ,String name) {

        if (backdata.size() > 0) {
            dataList1.clear();
            personmessage.clear();
            for (Gxq_apply_student provience : backdata) {
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
      case R.id.click_result:
             if ((starttime.getText()).equals("开始时间")){
                 Toast.makeText(Apply_gxqshow_Activity.this, "请选择处理的假条", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Apply_gxqshow_Activity.this, "与服务器连接成功", Toast.LENGTH_SHORT).show();
            json<String> gxqreturn = new json<String>();
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Apply_gxqshow_Activity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    public void connect_tijiao(){
        new Thread(){
            public void run(){
                String path = port.port+"/GXQServices/submitxsxs";
                conn = new SDS_Httpclient();
               Gxq_apply_student data = new Gxq_apply_student();
                data.setLeave_id(result_id);
                data.setLeave_check(check_result);
                json<Signin_class> dataVerture = new json<Signin_class>();
                String returnJson = dataVerture.ObjectToJson1(data);
                conn.Postclient(path,returnJson,gxq_teacherbackhandler);
            }
        }.start();
    }
//----------------------------------------------------获取学生姓名------------------------------------------------------------
public void connect_studentname(){
    new Thread(){
        public void run(){
            String path = port.port+"/GXQServices/xsxs";
            conn = new SDS_Httpclient();
//            Signin_class data = new Signin_class();
//            json<Signin_class> dataVerture = new json<Signin_class>();
//            String returnJson = dataVerture.ObjectToJson1(data);
////            String returnJson ="";
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
            ArrayAdapter<String> apply_adapter = new ArrayAdapter<String>(Apply_gxqshow_Activity.this, android.R.layout.simple_spinner_item, listgxq1);
            applyshow.setAdapter(apply_adapter);


        }
        @Override
        public void onFailture(String content) {
            super.onFailture(content);
            Toast.makeText(Apply_gxqshow_Activity.this, "失败", Toast.LENGTH_SHORT).show();
        }
    };
    //---------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------获取信息List（Gxq_apply——student）--------------------------------------------------------------
    public void connect_apply(){
        new Thread(){
            public void run(){
                String path = port.port+"/GXQServices/apply";
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
            json<Gxq_apply_student> gxqreturn1 = new json<Gxq_apply_student>();
            personmessage2= gxqreturn1.jsonToList2(content);
            for(int i=0;i<personmessage2.size();i++){
                Map map = (Map) personmessage2.get(i);
                listmap.add(map);
            }
            for (Map ll : listmap){
                Gxq_apply_student rr = new Gxq_apply_student();
                Double qq = (Double) ll.get("leave_id");
                String ww = (String) ll.get("create_time");
                String ee = (String) ll.get("student_name") ;
                String tt = (String) ll.get("counserlor_name") ;
                String yy = (String) ll.get("leave_content") ;
                String uu = (String) ll.get("leave_begin") ;
                String ii = (String) ll.get("leave_end") ;
                String oo = (String) ll.get("leave_check") ;
                 rr.setLeave_id(qq);
                rr.setCreate_time(ww);
                rr.setStudent_name(ee);
                rr.setCounserlor_name(tt);
                rr.setLeave_content(yy);
                rr.setLeave_begin(uu);
                rr.setLeave_end(ii);
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
            Toast.makeText(Apply_gxqshow_Activity.this, "失败", Toast.LENGTH_SHORT).show();
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
