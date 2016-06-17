package cn.hsd.tutor.Stu_log;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import cn.hsd.student.R;

/**
 * Created by MR.WEN on 2016/6/16.
 */
public class Stu_log_Adapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    public ArrayList<Map<String, Object>> list;
    public Stu_log_Adapter (Activity activity, ArrayList<Map<String, Object>> list) {

        this.context = activity;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        SetShow setShow = new SetShow();

        Map<String, Object> map = list.get(arg0);
        arg1=inflater.inflate(R.layout.activity_vistlog,arg2,false);
        setShow.lv1=(ListView)arg1.findViewById(R.id.wqj_log_List);
        String str = (String) list.get(arg0).get("contents");
        String str2=str.length()>10?" "+str.substring(0, 8):str;
        setShow.lv1.setFilterText("" + str2);
        return arg1;
    }
    class SetShow{
        public ListView lv1;

    }
}
