package com.example.dragremotecontrol;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.dragremotecontrol
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/5/29 14:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/29 14:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DisplayUtil {
//    2960*1440

    // 可用区域
    public static void getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" + ",densityDpi = " + densityDpi + "\n" + ",density = " + density + ",scaledDensity = " + scaledDensity);
        }
    }

    // 原始大小
    public static void getRealScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            // 可用显示大小的绝对高度（以像素为单位）。
            // 屏幕密度表示为每英寸点数。
            // 显示器的逻辑密度。
            // 显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" + ",densityDpi = " + densityDpi + "\n" + ",density = " + density + ",scaledDensity = " + scaledDensity);
        }
    }
}

