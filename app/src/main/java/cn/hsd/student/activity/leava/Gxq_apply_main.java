package cn.hsd.student.activity.leava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.gxq_class.gxqStatic;
import cn.hsd.student.activity.model.Gxq_apply_student;


public class Gxq_apply_main extends AppCompatActivity {

    public ListView apply_main_list;
    public Button gxq_main_new;
    public List<Gxq_apply_student> backList;
    private ArrayAdapter<String> adapter;
    public List<String> dataList = new ArrayList<String>();
    public List<Gxq_apply_student> jasonback = new ArrayList<Gxq_apply_student>();
    public List<String> dataList1 = new ArrayList<String>();
    public Gxq_apply_student student1;
    public String a = "kk";

    public json<Gxq_apply_student> mainjason ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       requestWindowFeature(NO)
        setContentView(R.layout.activity_gxq_apply_main);

//        mainjason = new json<Gxq_apply_student>();
//        jasonback = mainjason.jsonToList2(String jasonList);//jasonlist  是从服务器返回的值
//        dataList1 = diaoyong(jasonback);

        apply_main_list = (ListView) findViewById(R.id.apply_main_list);
        gxq_main_new = (Button) findViewById(R.id.gxq_main_new);
        backList = new ArrayList<Gxq_apply_student>();
        dataList1 = shishi(gxqStatic.data);
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, dataList);
        apply_main_list.setAdapter(adapter);

        gxq_main_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Gxq_apply_main.this, Gxq_apply_message.class);
                startActivity(intent1);
                finish();
            }
        });

    }

//    public void loaddate() {
//
//        backList = database2.loadProviences();
//        if (backList.size() > 0) {
//            dataList.clear();
//            for (Gxq_apply_student provience : backList) {
//                dataList.add(provience.getApply_student_beginname());
//            }
//            adapter.notifyDataSetChanged();
//            apply_main_list.setSelection(0);
//        }
//
//    }

    public List<String> shishi(Gxq_apply_student data1) {

        if (gxqStatic.apply_student_name.equals(""))
        {
            dataList.add("fffff");
        }
        else {
            backList.add(data1);
            if (backList.size() > 0) {
                //          dataList.clear();
                for (Gxq_apply_student provience : backList) {
           //         Toast.makeText(Gxq_apply_main.this, provience.getApply_student_beginname(), Toast.LENGTH_SHORT).show();
                    dataList.add(provience.getLeave_begin());
//                    adapter.notifyDataSetChanged();
                }

            }
        }
        return dataList;
    }
//    public List<String> shishi(Gxq_apply_student data1) {
//
//        if (gxqStatic.apply_student_name.equals(""))
//        {
//            dataList.add("fffff");
//        }
////        else {
////            Gxq_apply_student gas = new Gxq_apply_student();
////            gas.setApply_student_beginname("aaa");
////            gas.setApply_student_overname("aaas");
////            gas.setApply_student_name("aadsa");
////            gas.setApply_student_reason("aaa");
////            gas.setApply_student_applyname("aaa");
////            gas.setApply_student_result("未处理");
//        else {
//            backList.add(data1);
//            if (backList.size() > 0) {
//                dataList.clear();
//                for (Gxq_apply_student provience : backList) {
//                    Toast.makeText(Gxq_apply_main.this, provience.getApply_student_beginname(), Toast.LENGTH_SHORT).show();
//                    dataList.add(provience.getApply_student_beginname());
//                }
//
//            }
//        }
//        return dataList;
//    }

    public List<String> diaoyong(List<Gxq_apply_student>  backdata) {

        if (backdata.size() > 0) {
            dataList.clear();
            for (Gxq_apply_student provience : backdata) {
                //    Toast.makeText(Gxq_apply_main.this, provience.getApply_student_beginname(), Toast.LENGTH_SHORT).show();
                dataList.add(provience.getLeave_begin());
            }
        }
        return dataList;
    }
}
