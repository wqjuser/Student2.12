package cn.hsd.student.activity.login;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {
	/*
	 * @author �ն���
	 * ����ʱ�� 2016��5��29������3:45:22
	 * ���� TODO

	 */
	
	public static void setBooleans(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(Mycontents.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();	
	}

	public static boolean getBooleans(Context context,String key,boolean defValue){
		SharedPreferences sp = context.getSharedPreferences(Mycontents.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
}
