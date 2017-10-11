package com.dayanidhid.jussave;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dayanidhi.d on 11/10/17.
 */

public class Utils {

    private static final String KEYVALUESTORE_KEY = "JUSSAVE_KEY";
    private static SharedPreferences preferences;

    public Utils(Context context) {
        preferences = context.getSharedPreferences(KEYVALUESTORE_KEY, Activity.MODE_PRIVATE);
    }

    public static String getValue(String payload, String defaultValue) {return getString(payload, defaultValue);}

    public static void setValue(String key, String value) {
        write(key, value);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void write(String key, Long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public boolean contains(String key){
        return this.preferences.contains(key);
    }

    public static void write(String key, Boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void write(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key, boolean value) {
        return this.preferences.getBoolean(key, value);
    }

    public long getLong(String key, long value) {
        return this.preferences.getLong(key, value);
    }

    public static String getString(String key, String value) {
        return preferences.getString(key, value);
    }

    public int getInt(String key, int value) {
        return preferences.getInt(key, value);
    }

    public static void remove(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }
    public static void clearAllPreferences(Context context) {
        SharedPreferences settings = context.getSharedPreferences(KEYVALUESTORE_KEY, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
