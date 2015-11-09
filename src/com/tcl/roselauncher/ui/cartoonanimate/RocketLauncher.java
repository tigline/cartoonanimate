/**
 * 
 */
package com.tcl.roselauncher.ui.cartoonanimate;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @Project CartoonAnimate	
 * @author houxb
 * @Date 2015-11-9
 */
public class RocketLauncher extends LinearLayout {

	public static int width;
	
	public static int height;
	
	private ImageView launcherImg;
	/**
	 * @param context
	 */
	public RocketLauncher(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.rocket, this);
		launcherImg = (ImageView) findViewById(R.id.launcher_img);
		width = launcherImg.getLayoutParams().width;
		height = launcherImg.getLayoutParams().height;
	}
	
	public void updateLauncherStatus(boolean isReadyToLauncher) {
		if (isReadyToLauncher) {
			launcherImg.setImageResource(R.drawable.launcher_bg_fire);
		}else {
			launcherImg.setImageResource(R.drawable.launcher_bg_hold);
		}
	}

}
