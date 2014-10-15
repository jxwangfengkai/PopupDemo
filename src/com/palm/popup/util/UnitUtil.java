package com.palm.popup.util;

import android.content.Context;

/**
 * 单位转换工具类
 * 
 * @author weixiang.qin
 * 
 */
public class UnitUtil {
	/**
	 * dip转换为px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2Px(Context context, float dipValue) {
		/**
		 * return
		 * Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
		 * value, context.getResources().getDisplayMetrics()));
		 */
		final float scale = getDensity(context);
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = getDensity(context);
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp转px
	 * 
	 * @param context
	 * @param spValue
	 * @return
	 */ 
	public static int sp2px(Context context, float spValue) {
		final float scale = getDensity(context);
		return (int) (spValue * scale + 0.5f);
	}

	/**
	 * px转sp
	 * 
	 * @param pxValue
	 * @param fontScale
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float scale = getDensity(context);
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 根据密度计算大小
	 * 
	 * @param context
	 * @param size
	 * @return
	 */
	public static int getDensitySize(Context context, int size) {
		return (int) getDensity(context) * size;
	}
}
