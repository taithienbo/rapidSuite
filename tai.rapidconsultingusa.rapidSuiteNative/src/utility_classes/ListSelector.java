package utility_classes;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import android.graphics.drawable.Drawable;

import android.util.Log;

import android.widget.ListView;



public class ListSelector extends Drawable
{
	private static final String TAG = "Selector";
	private Paint mPaint;
	private ListView mList;
	private RectF mRectF;
	
	public static final String LOG_INFO_TAG = "ListSelector";

	public ListSelector(ListView list) 
	{
		mList = list;
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#357EC7"));
		mPaint.setAlpha(150);		// To see through the underlying view
		
		
		mRectF = new RectF();
	}

	@Override
	public void draw(Canvas canvas) {
		Rect b = getBounds();
		
		//get SelectedItemPosition not work
	
		int position =  mList.pointToPosition(b.left, b.top);//mList.getCheckedItemPosition();
		Log.d(LOG_INFO_TAG, "Position selected is: " + position);

		canvas.save();
		canvas.clipRect(b.left, b.top, b.right, (b.bottom + b.top) / 2);
		

		drawHalf(canvas, b, position == 0);//position == 0);
	
		
		canvas.restore();
		canvas.save();
		canvas.clipRect(b.left, (b.bottom + b.top) / 2, b.right, b.bottom);
	
		drawHalf(canvas, b, position == mList.getAdapter().getCount() - 1 && b.bottom == mList.getHeight());
		canvas.restore();
		Log.d(TAG, "draw " + b);
	}

	private boolean needRounded(int position)
	{
		
		// TODO Auto-generated method stub
		return false;
	}

	private void drawHalf(Canvas canvas, Rect b ,boolean round) {
		if (round)
		{
			mRectF.set(b);
			canvas.drawRoundRect(mRectF, 10, 10, mPaint);
		} else {
			canvas.drawRect(b, mPaint);
		}
	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int alpha)
	{
	
	}

	@Override
	public void setColorFilter(ColorFilter cf)
	{
		
	}
	
	

}