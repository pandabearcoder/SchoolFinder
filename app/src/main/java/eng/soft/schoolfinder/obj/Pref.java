package eng.soft.schoolfinder.obj;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {

    public static final String PREF_FILENAME = "uxPreference";
    public static final String KEY_SELECTED_OPTION = "selected";
    public static final String MENU_MODE = "menu_mode";
    public static final String LOGGED = "false";

    public static void saveToPreferences(Context context, String prefName, Boolean prefValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(prefName, prefValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String prefName, int prefValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(prefName, prefValue);
        editor.apply();
    }

    public static Boolean readFromPreferences(Context context, String prefName, Boolean defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        return sharedPref.getBoolean(prefName, defaultValue);
    }

    public static String readFromPreferences(Context context, String prefName, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        return sharedPref.getString(prefName, defaultValue);
    }

    public static int readFromPreferences(Context context, String prefName, int defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(Pref.PREF_FILENAME,Context.MODE_PRIVATE);
        return sharedPref.getInt(prefName, defaultValue);
    }
}
