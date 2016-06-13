package cn.hsd.student.message;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;

import cn.hsd.student.R;

public class Qxx_ReplyActivity extends Activity {

	private TextView qxx_topT;
	private TextView qxx_topR;
	private TextView qxx_dateT;
	private TextView qxx_dateR;
	private TextView qxx_contentT;
	private TextView qxx_contentR;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qxx__reply);
		qxx_topT=(TextView)findViewById(R.id.qxx_topT);
		qxx_topR=(TextView)findViewById(R.id.qxx_topR);
		qxx_dateT=(TextView)findViewById(R.id.qxx_dateT);
		qxx_dateR=(TextView)findViewById(R.id.qxx_dateR);
		qxx_contentT=(TextView)findViewById(R.id.qxx_contentT);
		qxx_contentR=(TextView)findViewById(R.id.qxx_contentR);
		String namestr=(String)getIntent().getExtras().get("toname");
		String datestr=(String)getIntent().getExtras().get("todate");
		String contentstr=(String)getIntent().getExtras().get("tocontent");
		qxx_topT.setText(namestr);
		qxx_dateT.setText(datestr);
		qxx_contentT.setText(contentstr);
		qxx_contentT.setMovementMethod(ScrollingMovementMethod.getInstance());
		qxx_contentR.setMovementMethod(ScrollingMovementMethod.getInstance());
		
//		ViewTreeObserver observer = qxx_contentT.getViewTreeObserver(); //textAbstractΪTextView�ؼ�
//		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//
//			@Override
//			public void onGlobalLayout() {

//				ViewTreeObserver obs = qxx_contentT.getViewTreeObserver();
//				obs.removeGlobalOnLayoutListener(this);
//				if(qxx_contentT.getLineCount() > 6){
//					int lineEndIndex = qxx_contentT.getLayout().getLineEnd(5); //���õ����д�ʡ�Ժ�
//					String text=qxx_contentT.getText().subSequence(0, lineEndIndex-3)+"...";
//					qxx_contentT.setText(text);
//				}
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qxx__reply, menu);
		return true;
	}

}
