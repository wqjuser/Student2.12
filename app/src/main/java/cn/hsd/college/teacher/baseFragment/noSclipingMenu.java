package cn.hsd.college.teacher.baseFragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/12.
 */
public class noSclipingMenu extends ViewPager {
    public noSclipingMenu(Context context) {
        super(context);
    }

    public noSclipingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
