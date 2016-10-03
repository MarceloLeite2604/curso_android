package com.example.sala01.aula9.util;

import android.util.Log;

/**
 * Created by marcelo on 29/09/16.
 */

public class LogUtil {

    private static boolean debug = false;

    public static void startDebug() {
        debug = true;
    }

    public static void stopDebug() {
        debug = false;
    }

    public static void d(String tag, String message) {
        if (debug) {
            Log.d(tag, message);
        }
    }
}
