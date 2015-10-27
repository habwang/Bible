package com.sinnus.bible.util;

/**
 * Created by sinnus on 2015/8/31.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.sinnus.bible.fragment.SettingsFragment;

public class PreferenceUtil {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor shareEditor;

    private static PreferenceUtil preferenceUtils = null;

    private PreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(SettingsFragment.SETTING_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    public static PreferenceUtil getInstance(Context context) {
        if (preferenceUtils == null) {
            synchronized (PreferenceUtil.class) {
                if (preferenceUtils == null) {
                    preferenceUtils = new PreferenceUtil(context.getApplicationContext());
                }
            }
        }
        return preferenceUtils;
    }

    public String getStringParam(String key) {
        return getStringParam(key, "");
    }

    public String getStringParam(String key, String defaultString) {
        return sharedPreferences.getString(key, defaultString);
    }

    public void saveStringParam(String key, String value) {
        shareEditor.putString(key, value).commit();
    }

    public boolean getBooleanParam(String key) {
        return getBooleanParam(key, false);
    }

    public boolean getBooleanParam(String key, boolean defaultBool) {
        return sharedPreferences.getBoolean(key, defaultBool);
    }

    public void saveBooleanParam(String key, boolean value) {
        shareEditor.putBoolean(key, value).commit();
    }

    public int getIntParam(String key) {
        return getIntParam(key, 0);
    }

    public int getIntParam(String key, int defaultInt) {
        return sharedPreferences.getInt(key, defaultInt);
    }

    public void saveIntParam(String key, int value) {
        shareEditor.putInt(key, value).commit();
    }

    public long getLongParam(String key) {
        return getLongParam(key, 0);
    }

    public long getLongParam(String key, long defaultInt) {
        return sharedPreferences.getLong(key, defaultInt);
    }

    public void saveLongParam(String key, long value) {
        shareEditor.putLong(key, value).commit();
    }

    public void removeKey(String key) {
        shareEditor.remove(key).commit();
    }
}

