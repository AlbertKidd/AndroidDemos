package com.demo.kidd.library.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.kidd.library.BuildConfig;

import lombok.Getter;

/**
 * SharedPreferences 增查删改工具类
 * @author Kidd
 */
public class SharedPreferencesUtil {

    public static final String KEY_BASE_URL = "BaseUrl";

    @Getter
    private SharedPreferences preferences;

    /**
     * 获取实例，读写目录为当前应用包名
     * @param context
     * @return
     */
    public static SharedPreferencesUtil getInstance(Context context){
        return getInstance(context, BuildConfig.APPLICATION_ID);
    }

    /**
     * 获取实例，读写目录为指定名称
     * @param context
     * @param name
     * @return
     */
    public static SharedPreferencesUtil getInstance(Context context, String name){
        return new SharedPreferencesUtil(context, name);
    }

    private SharedPreferencesUtil(Context context, String name){
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 为sharedPreferences设值
     * @param key
     * @param value
     */
    public void put(String key, Object value){
        SharedPreferences.Editor editor = preferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.apply();
    }

    /**
     * 删除指定sharedPreferences
     * @param key
     */
    public void remove(String key){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
