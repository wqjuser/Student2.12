package cn.hsd.student.message;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PORT.port;
import cn.hsd.student.R;
import cn.hsd.student.activity.Json.json;
import cn.hsd.student.activity.model.Counselor;
import cn.hsd.student.activity.model.Qxx_Message;
import cn.hsd.student.activity.service.SDS_Httpclient;
import cn.hsd.student.activity.service.SDS_ZMHandler;


public class Qxx_MessageActivity extends Activity {
	public String EXPANDED = "EXPANDED";
	public Qxx_MessageAdapter adapter;
	public ArrayList<Map<String, Object>> itemList;
	private ListView listView;
	private Button qxx_select_Bt1;
	private ImageButton qxx_img;
	private TextView qxx_tv_clr;
	private HashMap<String, Object> item;
	private String[] s;
	private ArrayList<Counselor> listcr;
	private ArrayList<Qxx_Message> list;
	private String student_no = "1234567890";
	private String student_name = "张三";
	private ArrayList<HashMap<String, Object>> data;
	private AlertDialog alertDialog;
	protected View view;

	SDS_ZMHandler handler = new SDS_ZMHandler() {


		public void onSuccess(String content) {


			json<Counselor> returnJson = new json<Counselor>();
			List list = returnJson.jsonToList2(content);
			for (int i = 0; i < list.size(); i++) {
				HashMap map = (HashMap) list.get(i);
				data.add(map);
			}
			System.out.println(data);
			alertDialog.setTitle("请选择辅导员");
			alertDialog.setView(view);
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();

			ListView lv = (ListView) view.findViewById(R.id.qxx_lt_dialog);
			lv.setItemsCanFocus(false);
			lv.setItemsCanFocus(false);
			lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			String[] s = {"counselor_name"};
			SimpleAdapter myadapter = new SimpleAdapter(Qxx_MessageActivity.this, data,
					R.layout.qxx_dialog_item, s,
					new int[]{R.id.qxx_dialog_name});
			lv.setAdapter(myadapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
					item = data.get(position);
					qxx_tv_clr.setText(item.get("counselor_name").toString());
					alertDialog.cancel();

				}
			});

		}

		;

		public void onFailture(String content) {

			Toast.makeText(getApplicationContext(), "联网失败", Toast.LENGTH_SHORT).show();
		}

		;

	};
	SDS_ZMHandler handler2 = new SDS_ZMHandler() {
		public void onSuccess(String content) {
			try {
				JSONArray jsonArray = new JSONArray(content);
				list = new ArrayList<Qxx_Message>();
				for (int i = 0; i < jsonArray.length(); i++) {
					String time = (String) jsonArray.getJSONObject(i).get("message_time");
					String detail = (String) jsonArray.getJSONObject(i).get("message_detail");
					String receiveno = (String) jsonArray.getJSONObject(i).get("message_receiveNo");
					String sendno = (String) jsonArray.getJSONObject(i).get("message_sendNo");
					String receivename = (String) jsonArray.getJSONObject(i).get("message_receiveName");
					String sendname = (String) jsonArray.getJSONObject(i).get("message_sendName");

					if (student_no.equals(sendno)) {
						Qxx_Message mge = new Qxx_Message();
						mge.setMessage_time(time);
						mge.setMessage_detail(detail);
						mge.setMessage_receiveNo(receiveno);
						mge.setMessage_sendNo(sendno);
						mge.setMessage_receiveName(receivename);
						mge.setMessage_sendName(sendname);
						list.add(mge);
					}

				}
			} catch (JSONException e) {

				e.printStackTrace();
			}
			for (Qxx_Message message : list) {
				Map<String, Object> item = new HashMap<String, Object>();

				item.put("message_time", message.getMessage_time());
				item.put("message_detail", message.getMessage_detail());
				item.put("message_receiveName", message.getMessage_receiveName());
				item.put("message_sendName", message.getMessage_sendName());
				item.put("EXPANDED", Boolean.valueOf(false));
				itemList.add(item);
			}
			adapter = new Qxx_MessageAdapter(Qxx_MessageActivity.this, itemList);
			listView.setAdapter(adapter);
		}

		;

		public void onFailture(String content) {


		}

		;

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qxx_message);
		qxx_img = (ImageButton) findViewById(R.id.qxx_img);
		qxx_tv_clr = (TextView) findViewById(R.id.qxx_tv_clr);
		listView = (ListView) findViewById(R.id.qxx_listview);
		qxx_select_Bt1 = (Button) findViewById(R.id.qxx_select_Bt1);
		// this.listView.setDivider(getResources().getDrawable(android.R.color.white));
		listView.setDivider(null);
		listView.setOnItemClickListener(new ItemClick());


		qxx_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String toname = qxx_tv_clr.getText().toString();
				if (!toname.equals("")) {
					Toast.makeText(Qxx_MessageActivity.this, "写留言", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Qxx_MessageActivity.this,
							Qxx_WriteActivity.class);
					intent.putExtra("stu_no", student_no);
					intent.putExtra("counselor", item);
					startActivity(intent);
				} else {
					Toast.makeText(Qxx_MessageActivity.this, "请选择辅导员", Toast.LENGTH_SHORT).show();
				}


			}
		});

		qxx_select_Bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {


				new Thread() {//ע����û��װ���߳�

					public void run() {

						String path = port.port + "/qxx_counselor.action";

						SDS_Httpclient conn = new SDS_Httpclient();
						conn.Postclient(path, "", handler);

					}
				}.start();

				view = View.inflate(Qxx_MessageActivity.this, R.layout.qxx_dialog_list, null);

				alertDialog = new AlertDialog.Builder(Qxx_MessageActivity.this).create();

				data = new ArrayList<HashMap<String, Object>>();


			}
		});
	}

	@Override
	protected void onResume() {

		super.onResume();
		showUpdate();
	}

	public void showUpdate() {


		new Thread() {//ע����û��װ���߳�

			public void run() {

				String path = port.port + "/qxx_message.action";

				SDS_Httpclient conn = new SDS_Httpclient();
				conn.Postclient(path, "", handler2);

			}
		}.start();
		itemList = new ArrayList<Map<String, Object>>();

	}

	class ItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> paramAdapterView,
								View paramView, int paramInt, long paramLong) {

			System.out.println("item----------click");
			Map<String, Object> localMap = Qxx_MessageActivity.this.itemList
					.get(paramInt);
			if (((Boolean) localMap.get("EXPANDED")).booleanValue()) {
				localMap.put("EXPANDED", Boolean.valueOf(false));
			} else {
				localMap.put("EXPANDED", Boolean.valueOf(true));
			}
			Qxx_MessageActivity.this.adapter.notifyDataSetChanged();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qxx__message, menu);
		return true;
	}

}
