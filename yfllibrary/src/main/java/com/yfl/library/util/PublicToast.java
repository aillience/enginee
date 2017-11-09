package com.yfl.library.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;
/***
 * @author YFL
 * 因为功能需要，根据网上资料整理加工，定义一个可控制显示时间的Toast
 */

/**调用Toast*/
@SuppressLint("ShowToast")
public class PublicToast {

	public static final int LENGTH_MAX = -1;
	public static final int LENGTH_SHORT = 1;
	private boolean mCanceled = true;
	private Handler mHandler;
	private Context mContext;
	private Toast mToast;

	public PublicToast(Context context) {
		this(context, new Handler());
		mHandler=new Handler();
	}

	public PublicToast(Context context, Handler h) {
		mContext = context;
		mHandler = h;
		mToast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.BOTTOM, 0, 0);
	}

	public void show(int resId, int duration) {
		mToast.setText(resId);
		if (duration != LENGTH_MAX) {
			mToast.setDuration(duration);
			mToast.show();
		} else if (mCanceled) {
			mToast.setDuration(Toast.LENGTH_LONG);
			mCanceled = false;
			showUntilCancel();
		}
	}

	/**
	 * @param text
	 *            要显示的内容
	 * @param duration
	 *            显示的时间长 根据LENGTH_MAX进行判断 如果不匹配，进行系统显示 如果匹配，永久显示，直到调用hide()
	 */
	/**注意：永久显示showMax时，若不取消永久显示hide，直接调用将出现Toast闪屏*/
	public void show(String text, long duration) {
		mToast.setText(text);
		ShowAnyTime(text,duration);
	}
	/**
	 * 默认3秒显示
	 */
	public void show(String text) {
		mToast.setText(text);
		ShowAnyTime(text,3000);
	}
	/**永久显示*/
	public void showMax(String text) {
		mToast.setText(text);
		if (mCanceled) {
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setGravity(Gravity.CENTER, 0, -1);
//			mToast.getView().setBackgroundColor(Color.TRANSPARENT);
			mCanceled = false;
			showUntilCancel();
		}

	}

	/**
	 * 隐藏Toast
	 */
	public void hide() {
		mToast.setGravity(Gravity.BOTTOM, 0, 0);
		mToast.cancel();
		mCanceled = true;
	}

	public boolean isShowing() {
		return !mCanceled;
	}

	private void showUntilCancel() {
		if (mCanceled)
			return;
		mToast.show();
		mHandler.postDelayed(new Runnable() {
			public void run() {
				showUntilCancel();
			}
		}, 3000);
	}

	/**
	 * @param context
	 * @param str
	 * 默认toast
	 */
	public static void PublicShow(Context context, Object str) {
		Toast.makeText(context, str.toString(), Toast.LENGTH_LONG).show();
	}
	public void ShowCenter(String text, long duration){
		mToast.setGravity(Gravity.CENTER, 0, 0);
		ShowAnyTime(text,duration);
	}

	public static void ShowDialog(Context context, String title, String text,
                                  OnClickListener ClickEvent) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(text)
				.setPositiveButton("确定", ClickEvent).create().show();
	}

	/**
	 * @param text
	 * @param t
	 * 自定义显示时间
	 */
	public void ShowAnyTime(String text, long t) {
		showMax(text);
		mHandler.postDelayed(new Runnable() {
			public void run() {
				hide();
			}
		}, t);
	}

}