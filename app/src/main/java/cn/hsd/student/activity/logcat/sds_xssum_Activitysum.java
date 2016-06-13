
package cn.hsd.student.activity.logcat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.sds_sum_model;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;

public class sds_xssum_Activitysum extends AppCompatActivity {
    private EditText sds_sum_sumreport_title;
    private EditText sds_sum_sumreport_detail;

    SDS_ZMHandler handler = new SDS_ZMHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
        }

        @Override
        public void onFailture(String content) {
            super.onFailture(content);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitysum);
        sds_sum_sumreport_title = (EditText) findViewById(R.id.sds_sum_sumreport_title);
        sds_sum_sumreport_detail = (EditText) findViewById(R.id.sds_sum_sumreport_detail);
    }


   public void sds_sum_fabiaoTijiao(View view){

       new Thread(){
           public void run(){

               String path = "http://";
               sds_sum_model model = new sds_sum_model();
               //model.setStudent_no();
               model.setSumreport_detail(sds_sum_sumreport_detail.getText().toString().trim());
               model.setSumreport_title(sds_sum_sumreport_title.getText().toString().trim());
               json<sds_sum_model> jsontoobject = new json<sds_sum_model>();
               String jsonreturn = jsontoobject.ObjectToJson1(model);
               SDS_Httpclient conn = new SDS_Httpclient();
               conn.Postclient(path,jsonreturn,handler);
           }

       }.start();

   }
}
