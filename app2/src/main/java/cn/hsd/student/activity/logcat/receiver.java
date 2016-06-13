package cn.hsd.student.activity.logcat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by MR.WEN on 2016/6/1.
 */
public class receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"个人信息接收成功",Toast.LENGTH_LONG).show();

    }
}
