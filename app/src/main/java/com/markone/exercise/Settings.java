package com.markone.exercise;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jschnall on 12/12/14.
 */
public class Settings {
    private static final String PREFS_NAME = "settings";

    public static final String OVAL_VALUE = "oval";
    public static final String TRIANGLE_VALUE = "triangle";
    public static final String RECT_VALUE = "rect";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}