/**
 * 
 */
package com.tcl.roselauncher.ui.cartoonanimate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;


/**
 * @Project CartoonAnimate	
 * @author houxb
 * @Date 2015-11-9
 */
public class FloatWindowService extends Service {
	
	private Handler handler = new Handler();
	private Timer timer;

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new RefreshTask(), 0, 500);
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
		timer = null;
	}
	
	class RefreshTask extends TimerTask {

		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (isHome() && !MyWindowManager.isWindowShowing()) {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyWindowManager.createSmallWindow(getApplicationContext());
					}
				});
			}
			else if (!isHome() && MyWindowManager.isWindowShowing()) {
				handler.post(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyWindowManager.removeSmallWindow(getApplicationContext());
						MyWindowManager.removeBigWindow(getApplicationContext());
					}
				});
			}
			else if (isHome() && MyWindowManager.isWindowShowing()) {
				handler.post(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//MyWindowManager.updateUsedPercent(getApplicationContext());
					}
				});
			}
		}
		
	}
	
	private boolean isHome() {
		ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
		List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
		return getHomes().contains(rti.get(0).topActivity.getPackageName());
	}

	private List<String> getHomes() {
		List<String> names = new ArrayList<String>();
		PackageManager packageManager = this.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, 
				PackageManager.MATCH_DEFAULT_ONLY);
		for (ResolveInfo ri : resolveInfo) {
			names.add(ri.activityInfo.packageName);
		}
		return names;
	}
}
