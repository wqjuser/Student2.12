package cn.hsd.student.message;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import cn.hsd.student.R;

public class Qxx_MessageAdapter extends BaseAdapter {

	public Context context;
	public LayoutInflater inflater;
	public ArrayList<Map<String, Object>> list;

	public Qxx_MessageAdapter(Activity activity, ArrayList<Map<String, Object>> list) {

		this.context = activity;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub

		SetShow setShow = new SetShow();

		Map<String, Object> map = list.get(arg0);
		boolean boo = (Boolean) map.get("EXPANDED");
		if (!boo) {
			arg1 = inflater.inflate(R.layout.qxx_showtypes, arg2, false);
			setShow.contentView = (TextView) arg1.findViewById(R.id.qxx_contentview);
			setShow.topView = (TextView) arg1.findViewById(R.id.qxx_topview);
			setShow.dateview=(TextView) arg1.findViewById(R.id.qxx_dateview);
			String str = (String) list.get(arg0).get("message_detail");
			String str2=str.length()>10?" "+str.substring(0, 8):str;
			String dateStr = (String) list.get(arg0).get("message_time");
			String tonameStr=(String)list.get(arg0).get("message_receiveName");
			String top="       To:    " + tonameStr;
			setShow.contentView.setText("" + str2);
			setShow.topView.setText(top);
			setShow.dateview.setText(dateStr);
			setShow.showButtonDelete = (Button) arg1
					.findViewById(R.id.qxx_deleteview);
			setShow.showButtonDelete
					.setOnClickListener(new DeleteButtonListener(arg0));
		} else {
			arg1 = inflater.inflate(R.layout.qxx_style, arg2, false);
			setShow.cContentView = (Qxx_TextViewLine) arg1
					.findViewById(R.id.qxx_changeContent);
			setShow.ctopView = (TextView) arg1
					.findViewById(R.id.qxx_changeTo);
			setShow.cdateview=(TextView)arg1.findViewById(R.id.qxx_changeDate);
			setShow.ifreply=(TextView)arg1.findViewById(R.id.qxx_ifreply);
			final String str = (String) list.get(arg0).get("message_detail");
			final String dateStr = (String) list.get(arg0).get("message_time");
			final String tonameStr=(String)list.get(arg0).get("message_receiveName");
			final String top="       To:    " + tonameStr;
			setShow.cContentView.setText("" + str);
			setShow.ctopView.setText(top);
			setShow.cdateview.setText(dateStr);
			setShow.ifreply.setText("未回复");
			setShow.styleButtonDelete = (Button) arg1
					.findViewById(R.id.qxx_delete);
			setShow.styleButtonDelete
					.setOnClickListener(new DeleteButtonListener(arg0));
			setShow.ifreply.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {

					Intent intent=new Intent(context, Qxx_ReplyActivity.class);
					intent.putExtra("toname", top);
					intent.putExtra("todate", dateStr);
					intent.putExtra("tocontent", str);
					context.startActivity(intent);
				}
			});
		}
		return arg1;
	}

	class DeleteButtonListener implements OnClickListener {
		private int position;

		public DeleteButtonListener(int position) {
			this.position = position;

		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub

			Builder builder = new Builder(context);
			builder.setTitle("确定删除");
			builder.setNegativeButton("删除",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int i) {


						}
					});
			builder.setPositiveButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			builder.create();
			builder.show();
		}

	}

	class SetShow {
		public TextView contentView;
		public TextView topView;
		public Qxx_TextViewLine cContentView;
		public TextView ctopView;
		public TextView dateview;
		public TextView cdateview;
		public Button styleButtonDelete;
		public Button showButtonDelete;
		public TextView ifreply;
	}

}
