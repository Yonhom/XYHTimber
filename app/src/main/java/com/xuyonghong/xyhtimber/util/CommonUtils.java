package com.xuyonghong.xyhtimber.util;

import android.content.Context;

/**
 * Created by xuyonghong on 2017/6/3.
 */

public class CommonUtils {

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f) / 3; // i dont know why divide it by 3, but it works :(
    }
}
