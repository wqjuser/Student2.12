package cn.hsd.student.activity.logcat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by MR.WEN on 2016/6/1.
 */
public class receiver extends BroadcastReceiver {
     public static String info;
    @Override
    public void onReceive(Context context, Intent intent1) {
        Toast.makeText(context,"个人信息接收成功"+intent1.getData(),Toast.LENGTH_LONG).show();
        info=intent1.getDataString();

    }
}
