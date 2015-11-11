/**
 * 
 */
package com.tcl.roselauncher.ui.cartoonanimate;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Project 	
 * @author houxb
 * @Date 2015-11-11
 */
public class LaunchAnimationView extends View implements Runnable {

	/**
	 * @param context
	 */
	private Paint paint;   
	public Bitmap[] appear;   //图片资源数组
	private RectF mDestRect;    //绘制框
	private boolean ThreadFlag = false; //线程标志位
	private boolean StartFlag = false;  //开启标志位
	@SuppressWarnings("unused")
	private boolean drawFlag = true;  
	public int temp = 0;
	Thread animThread = null; 
	Context mContext;
	Canvas canvas;
	public LaunchAnimationView(Context context ,AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		paint = new Paint();    
		paint.setAntiAlias(true);   
		paint.setFilterBitmap(true);    
        paint.setDither(true);   
		init();
	}
	
	/**
	 * 初始化
	 */
	
	public void init()
	{
		appear = new Bitmap[13];
		appear[0] = BitmapFactory.decodeResource(getResources(), R.drawable.appear_0);
		appear[1] = BitmapFactory.decodeResource(getResources(), R.drawable.appear_1);
	}
		
	@SuppressLint("DrawAllocation") protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		mDestRect = new RectF(0, 0, this.getWidth(), this.getHeight());  
		canvas.drawBitmap(appear[temp], null, mDestRect, paint);
	}

	//执行线程
	@SuppressWarnings("static-access")
	public void start()
	{
		this.setVisibility(this.VISIBLE);
		ThreadFlag = true;
		if(animThread == null) {
			animThread = new Thread(this);
		}
		if(!StartFlag)
		{
			StartFlag = true;
			animThread.start();				
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
			// TODO Auto-generated method stub
			while(ThreadFlag){ 
				if(!ThreadFlag) break;
				if(temp-1 >= 0)
				{
					if(appear[temp-1] != null && !appear[temp-1].isRecycled()) appear[temp-1].recycle();
				}
				if(temp < 12) {	
					//第11张停顿900ms
					if (temp == 10) {
						try {
							Thread.sleep(900);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					//第12张停顿500ms
					if (temp == 11) {
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					temp++;
					Resources res = mContext.getResources();
					if(temp+1 <= 12) {
						if(appear[temp+1] == null) appear[temp+1] = BitmapFactory.decodeResource(res, res.getIdentifier("appear_"+(temp+1), "drawable", mContext.getPackageName()));
					}
				} else 
				{
					drawFlag = false;
				}
	            //刷新屏幕，间隔100毫秒  
	            this.postInvalidate(); 
	            try { 
	                Thread.sleep(80); 
	            } catch (InterruptedException e) { 
	                e.printStackTrace(); 
	            } 
	        } 
		}
	
	
}
