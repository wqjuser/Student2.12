package cn.hsd.college.tcatch.basefragment;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.hsd.student.R;


/**
 * Created by 苏东升 on 2016/6/11.
 */
public class BaseTagPager1 {

    protected Context context;
    protected View view;
    protected ImageButton iv_menu;
    protected TextView tv_title;
    protected FrameLayout fl_content;

    public BaseTagPager1(Context context) {
        this.context = context;
        initView();
        initData();
        initEvent();
    }

    protected void initEvent() {
        // TODO Auto-generated method stub
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    protected void initData() {
        // TODO Auto-generated method stub

    }


    protected void initView() {
        view = View.inflate(context, R.layout.c_frament_content_base_content, null);
        iv_menu = (ImageButton) view.findViewById(R.id.ib_base_content_menu1);
        tv_title = (TextView) view.findViewById(R.id.tv_base_content_title1);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content_base_tag1);

    }

    public View getRoot() {
        return view;
    }
}
