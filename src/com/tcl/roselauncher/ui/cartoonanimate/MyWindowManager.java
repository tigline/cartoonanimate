/**
 * 
 */
package com.tcl.roselauncher.ui.cartoonanimate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

/**
 * @Project CartoonAnimate	
 * @author houxb
 * @Date 2015-11-9
 */
public class MyWindowManager {
	private static FloatWindowSmallView smallWindow;
	
	private static FloatWindowBigView bigWindow;
	
	private static LayoutParams smallWindowParams;
	
	private static LayoutParams bigWindowParams;
	
	private static WindowManager mWindowManager;
	
	private static ActivityManager mActivityManager;
	
	@SuppressWarnings("deprecation")
	public static void createSmallWindow(Context context) {  
        WindowManager windowManager = getWindowManager(context);  
        int screenWidth = windowManager.getDefaultDisplay().getWidth();  
        int screenHeight = windowManager.getDefaultDisplay().getHeight();  
        if (smallWindow == null) {  
            smallWindow = new FloatWindowSmallView(context);  
            if (smallWindowParams == null) {  
                smallWindowParams = new LayoutParams();  
                smallWindowParams.type = LayoutParams.TYPE_PHONE;  
                smallWindowParams.format = PixelFormat.RGBA_8888;  
                smallWindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL  
                        | LayoutParams.FLAG_NOT_FOCUSABLE;  
                smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;  
                smallWindowParams.width = FloatWindowSmallView.viewWidth;  
                smallWindowParams.height = FloatWindowSmallView.viewHeight;  
                smallWindowParams.x = screenWidth;  
                smallWindowParams.y = screenHeight / 2;  
            }  
            smallWindow.setParams(smallWindowParams);  
            windowManager.addView(smallWindow, smallWindowParams);  
        }  
    }  
	
	public static void removeSmallWindow(Context context) {  
        if (smallWindow != null) {  
            WindowManager windowManager = getWindowManager(context);  
            windowManager.removeView(smallWindow);  
            smallWindow = null;  
        }  
    }
	
	@SuppressWarnings("deprecation")
	public static void createBigWindow(Context context) {
		
		WindowManager windowManager = getWindowManager(context);
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		if (bigWindow == null) {
			bigWindow = new FloatWindowBigView(context);
			if (bigWindowParams == null) {
				bigWindowParams = new LayoutParams();
				bigWindowParams.x = screenWidth / 2 - FloatWindowBigView.viewWidth / 2;  
                bigWindowParams.y = screenHeight / 2 - FloatWindowBigView.viewHeight / 2;  
                bigWindowParams.type = LayoutParams.TYPE_PHONE;  
                bigWindowParams.format = PixelFormat.RGBA_8888;  
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;  
                bigWindowParams.width = FloatWindowBigView.viewWidth;  
                bigWindowParams.height = FloatWindowBigView.viewHeight;  
            }  
            windowManager.addView(bigWindow, bigWindowParams);  
		}
	}

	/**
	 * @param context
	 * @return
	 */
	private static WindowManager getWindowManager(Context context) {
		// TODO Auto-generated method stub
		if (mWindowManager == null) {
			mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		}
		return mWindowManager;
	}
	
	public static void removeBigWindow(Context context) {  
        if (bigWindow != null) {  
            WindowManager windowManager = getWindowManager(context);  
            windowManager.removeView(bigWindow);  
            bigWindow = null;  
        }  
    } 
	
	public static void updateUsedPercent(Context context) {  
        if (smallWindow != null) {  
            TextView percentView = (TextView) smallWindow.findViewById(R.id.percent);  
            percentView.setText(getUsedPercentValue(context));  
        }  
    }  
	
	private static ActivityManager getActivityManager(Context context) {  
        if (mActivityManager == null) {  
            mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
        }  
        return mActivityManager;  
    } 
	public static String getUsedPercentValue(Context context) {  
        String dir = "/proc/meminfo";  
        try {  
            FileReader fr = new FileReader(dir);  
            BufferedReader br = new BufferedReader(fr, 2048);  
            String memoryLine = br.readLine();  
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));  
            br.close();  
            long totalMemorySize = Integer.parseInt(subMemoryLine.replaceAll("\\D+", ""));  
            long availableSize = getAvailableMemory(context) / 1024;  
            int percent = (int) ((totalMemorySize - availableSize) / (float) totalMemorySize * 100);  
            return percent + "%";  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "悬浮窗";  
    }  
  
    /** 
     * 获取当前可用内存，返回数据以字节为单位。 
     *  
     * @param context 
     *            可传入应用程序上下文。 
     * @return 当前可用内存。 
     */  
    private static long getAvailableMemory(Context context) {  
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();  
        getActivityManager(context).getMemoryInfo(mi);  
        return mi.availMem;  
    }

	/**
	 * @return
	 */
    public static boolean isWindowShowing() {  
        return smallWindow != null || bigWindow != null;  
    }   
	
}
