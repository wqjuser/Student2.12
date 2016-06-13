package cn.hsd.student.activity.sqyxsxs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.apply_commit_return;


public class qmf_ApplyCon extends AppCompatActivity {

    private ListView qmf_ASlv;
    public List<String> dataList = new ArrayList<String>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qmf__apply_con);
        qmf_ASlv = (ListView) this.findViewById(R.id.qmf_applylv);


    }

    //进入申请填写

    public void qmf_Applyapply(View v) {
        //苏东升版  共三处qmf_ApplyTrainee内一处
        Intent intent = new Intent(this,qmf_ApplyTrainee.class);
        startActivityForResult(intent,0);

//        Intent intent=new Intent();
//        intent.setClass(qmf_ApplyCon.this,qmf_ApplyTrainee.class);
//        startActivity(intent);
//        finish();

    }
//返回主页
    public void qmf_ApplyReturnM(View v) {
        Toast.makeText(qmf_ApplyCon.this, "未添加", Toast.LENGTH_LONG).show();

    }
//苏东升版
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Bundle bundle = data.getExtras();
            String str = bundle.getString("qq");
            json<apply_commit_return> reyurnJson = new json<apply_commit_return>();



        List<apply_commit_return> list = reyurnJson.jsonToList2(str);
        dataList =     diaoyong(list);


        Toast.makeText(getApplicationContext(),""+data.toString(),Toast.LENGTH_LONG).show();
        }catch (Exception e){}

    }


    public List<String> diaoyong(List<apply_commit_return>  backdata) {

        if (backdata.size() > 0) {
            dataList.clear();
            for (apply_commit_return provience : backdata) {
                dataList.add(provience.getApply_retutntime());
            }
        }
        return dataList;
    }
}