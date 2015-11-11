/**
 * 
 */
package com.tcl.roselauncher.ui.cartoonanimate;


import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @Project CartoonAnimate	
 * @author houxb
 * @Date 2015-11-10
 */
public class StartActivity extends Activity implements OnClickListener {
	 
	 
	 	//private ImageView startFrame;
		private LaunchAnimationView launchAnimationView;
	 	private Button runBt;
	 	//private AnimationDrawable animationDrawable;
	 	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);

			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        setContentView(R.layout.activity_start);
	        //startFrame = (ImageView) findViewById(R.id.runway_frame);
	        
	        runBt = (Button) findViewById(R.id.run_bt);
	        runBt.setOnClickListener(this);
	    }

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
	 	
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.run_bt:
//				if (animationDrawable != null) {
//					animationDrawable.stop();
//				}
//				startFrame.setImageResource(R.drawable.start_frame_animation); 
//				animationDrawable = (AnimationDrawable) startFrame.getDrawable();
//				startFrame.post(new Runnable() {	
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						animationDrawable.start();
//					}
//				});	
				
				if (launchAnimationView != null) {
					launchAnimationView.appear = null;
					launchAnimationView.temp = 0;
				}

				launchAnimationView = (LaunchAnimationView) findViewById(R.id.launch_aim);
				launchAnimationView.start();
				
				break;
			default:
				break;
			}
		}
	 	
	 	
}
