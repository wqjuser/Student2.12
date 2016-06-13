package cn.hsd.student.message;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class Qxx_DrawLine extends EditText {
	private Paint ePaint;

	// private Rect rect;
	public Qxx_DrawLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		ePaint = new Paint();


		ePaint.setColor(Color.BLACK);
		ePaint.setStyle(Paint.Style.STROKE);

	}



	public void onDraw(Canvas canvas) {
		int count = getLineCount();
		for (int i = 0; i < count + 11; i++) {
			float[] pts = { 15.0F, (i + 1) * getLineHeight(),
					this.getWidth() - 20.0F, (i + 1) * getLineHeight() };


			canvas.drawLines(pts, ePaint);
		}
		super.onDraw(canvas);
	}
}
