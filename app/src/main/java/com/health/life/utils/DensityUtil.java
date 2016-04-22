package com.health.life.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class DensityUtil {
	public static double STANDARD_DENSITY = 160;

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dp2px(Context context, int dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 */
	public static float sp2px(Resources resources, float sp) {
		final float scale = resources.getDisplayMetrics().scaledDensity;
		return sp * scale;
	}
	
	/**
	 * 获得屏幕的高宽尺寸
	 * 
	 * @param act
	 * @return
	 */
	public static int[] getScreenSize(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		int width = lMetrics.widthPixels;
		int height = lMetrics.heightPixels;
		return new int[] { width, height };
	}

	/**
	 * 获得屏幕的密度
	 * 
	 * @param act
	 * @return
	 */
	public static float getScreenDensity(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		return lMetrics.density;
	}

	/**
	 * 获得屏幕dpi
	 * 
	 * @param act
	 * @return
	 */
	public static int getScreenDensityDpi(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		return lMetrics.densityDpi;
	}

	/**
	 * 获得屏幕高度
	 * 
	 * @param act
	 * @return
	 */
	public static int getScreenHeight2(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		Rect rect = new Rect();
		((Activity) context).getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(rect);

		int height = lMetrics.heightPixels - rect.top;
		return height;
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @param act
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		return lMetrics.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics lMetrics = new DisplayMetrics();
		lMetrics = context.getResources().getDisplayMetrics();
		return lMetrics.heightPixels;
	}

}
