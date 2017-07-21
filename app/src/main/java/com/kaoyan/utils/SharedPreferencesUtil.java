package com.kaoyan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tx on 2017/7/20.
 * sharedPreferences封装工具类
 */
public class SharedPreferencesUtil {
    private static String PREFERENCE_NAME = "yantu_vip";

    /**
     * 键值对缓存
     * @param context
     * @param key 键
     * @param value 值 String类型
     * @return 是否保存成功
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 根据键获取值
     * @param context
     * @param key 键
     * @param defaultValue 默认值
     * @return 根据键得到的值 String类型
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * 键值对缓存
     * @param context
     * @param key 键
     * @param value 值 int类型
     * @return 是否保存成功
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 根据键获取值
     * @param context
     * @param key 键
     * @param defaultValue 默认值
     * @return 根据键得到的值 int类型
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * 键值对缓存
     * @param context
     * @param key 键
     * @param value 值 boolean类型
     * @return 是否保存成功
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 根据键获取值
     * @param context
     * @param key 键
     * @param defaultValue 默认值
     * @return 根据键得到的值 boolean类型
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

}
