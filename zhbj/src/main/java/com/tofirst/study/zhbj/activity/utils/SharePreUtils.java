package com.tofirst.study.zhbj.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Study on 2015/12/2.
 */
public class SharePreUtils {
    public static final String CONFIG_NAME = "config";

    /**
     * 获得本地SharedPreferences的boolean数据
     *
     * @param context     传入的上下文对象
     * @param key         得到数据的标识
     * @param defaltValue 如果获得不到的默认值
     * @return
     */
    public static boolean getsPreBoolean(Context context, String key, boolean defaltValue) {
        SharedPreferences pre = context.getSharedPreferences(CONFIG_NAME, context.MODE_PRIVATE);
        return pre.getBoolean(key, defaltValue);
    }

    /**
     * 保存到本地SharedPreferences的boolean数据
     *
     * @param context 传入的上下文对象
     * @param key     得到数据的标识
     * @param value   如果获得不到的默认值
     */
    public static void putPreBoolean(Context context, String key, boolean value) {
        SharedPreferences pre = context.getSharedPreferences(CONFIG_NAME, context.MODE_PRIVATE);
        pre.edit().putBoolean(key, value).commit();
    }
}
