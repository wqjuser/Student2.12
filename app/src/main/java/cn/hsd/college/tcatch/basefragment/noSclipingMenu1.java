package cn.hsd.college.tcatch.basefragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/12.
 */
public class noSclipingMenu1 extends ViewPager {
    public noSclipingMenu1(Context context) {
        super(context);
    }

    public noSclipingMenu1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }


    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
