package com.zyjk.posmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/9/4.
 */

public class ShareUtil {
    public static SharedPreferences sp;
    public static String name = "shared_demo";

    // 存放布尔类型；
    public static void putData(Context context, String key, boolean value) {
        // 获取SharedPreferences 的实例；需要用到context ；
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 存放object类型；
    public static void putData(Context context, String key, Object value) {
        // 获取SharedPreferences 的实例；需要用到context ；
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }

    // 根据key获取SharedPreferences里的value；通过判断defaultValue的类型，来选择不同的get方法；
    public static Object getData(Context context, String key, Object defValue) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        } else if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Float) {
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

    // 根据key来移除数据
    public static void removeData(Context context, String key) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    // 清除所有数据
    public static void clearData(Context context) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    // 根据key判断这个value是否存在；
    public static boolean isExist(Context context, String key) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.contains(key);
    }
}
