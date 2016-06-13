package cn.hsd.student.message;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class Qxx_TextViewLine extends TextView{
	private Paint ePaint = new Paint();

	public Qxx_TextViewLine(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.ePaint.setColor(Color.BLACK);
		this.ePaint.setStyle(Paint.Style.STROKE);
	}
	public void onDraw(Canvas canvas) {
		int count = getLineCount();
		for (int i = 0; i < count + 11; i++) {
			float[] pts = { 15.0F, (i + 1) * getLineHeight(),
					this.getWidth() - 20.0F, (i + 1) * getLineHeight() };

			// i*50-280,50�������м���285�������߿��ϡ�
			// canvas.drawLine(15, i*42, this.getWidth()-20,i*42, ePaint);
			canvas.drawLines(pts, ePaint);
		}
		super.onDraw(canvas);
	}
//	@Override
//	protected void onDraw(Canvas canvas) {

//		super.onDraw(canvas);
//		int i=getLineCount();
//	    for (int j = 0; ; ++j)
//	    {
//	      if (j >= i)
//	      {
//	        super.onDraw(canvas);
//	        return;
//	      }
//	      float[] arrayOfFloat = new float[4];
//	      arrayOfFloat[0] = 15.0F;
//	      arrayOfFloat[1] = ((j + 1) * getLineHeight());
//	      arrayOfFloat[2] = (-20 + getWidth());
//	      arrayOfFloat[3] = ((j + 1) * getLineHeight());
//	      canvas.drawLines(arrayOfFloat, this.ePaint);
//	    }
//	}

}
