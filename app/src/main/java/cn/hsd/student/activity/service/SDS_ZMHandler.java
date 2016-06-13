package cn.hsd.student.activity.service;

import android.os.Handler;
import android.os.Message;

public class SDS_ZMHandler extends Handler {
	public void onSuccess(String content){
		
	}
	public void onFailture(String content){
		
	}
	

	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			onSuccess(msg.obj.toString());
			break;

		case 2:
			onFailture(msg.obj.toString());
			break;
			
		}
		super.handleMessage(msg);
	}
}
