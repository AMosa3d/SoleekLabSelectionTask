package com.example.abdel.soleeklabselectiontask.Utilites;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;

import com.example.abdel.soleeklabselectiontask.R;



public final class SharedPreferencesUtils {

    static final String PREF_NAME = "Soleek_Lab_Pref";
    static final String VIEW_MODE_ID = "View_Mode";
    static final String EMAIL_ID = "Email";
    static final int LIGHT_MODE = 0;
    static final int DARK_MODE = 1;

    private static int GetTheme(int savedTheme)
    {
        if (savedTheme == LIGHT_MODE)
            return R.style.AppTheme_LightMode;
        else
            return R.style.AppTheme_DarkMode;
    }

    public static void SetAppTheme(Activity activity)
    {
        int savedTheme = activity.
                getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
                .getInt(VIEW_MODE_ID,LIGHT_MODE);   //Light mode as default mode

        int theme = GetTheme(savedTheme);

        activity.setTheme(theme);
    }

    public static void SwitchViewMode(Activity activity, MenuItem item)
    {
        SharedPreferences preferences = activity.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int mode = preferences.getInt(VIEW_MODE_ID,LIGHT_MODE);
        int newTheme = Math.abs(1 - mode);
        editor.putInt(VIEW_MODE_ID,newTheme);
        editor.apply();

    }

    public static void SetMenuViewModeIcon(Activity activity, MenuItem item)
    {
        SharedPreferences preferences = activity.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        int theme = preferences.getInt(VIEW_MODE_ID,LIGHT_MODE);

        if (theme == DARK_MODE)
            item.setIcon(R.drawable.sun);
        else
            item.setIcon(R.drawable.moon);
    }

    public static void SaveLoginCred(Activity activity, String email)
    {
        SharedPreferences.Editor editor = activity
                .getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
                .edit();

        editor.putString(EMAIL_ID,email);

        editor.apply();
    }

    public static String getSavedEmail(Activity activity)
    {
        return activity.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE).getString(EMAIL_ID,"");
    }

}
