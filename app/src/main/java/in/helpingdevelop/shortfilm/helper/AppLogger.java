package in.helpingdevelop.shortfilm.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.helpingdevelop.shortfilm.BuildConfig;

/**
 * Created by Shreyash on 10/7/2017.
 */

public class AppLogger {

    private static Gson gson;
    private static String TAG = "AppLogger";

    private AppLogger() {

    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg + "");
        }
    }

    public static void i(boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg + "");
        }
    }


    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg + "");
        }
    }

    public static void e(boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg + "");
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg + "");
        }
    }

    public static void d(boolean msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg + "");
        }
    }


    public static void i(String tag, int msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, String.valueOf(msg));
        }
    }

    public static void i(int msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, String.valueOf(msg));
        }
    }

    public static void e(String tag, int msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, String.valueOf(msg));
        }
    }

    public static void e(int msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, String.valueOf(msg));
        }
    }

    public static void d(String tag, int msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, String.valueOf(msg));
        }
    }

    public static void d(int msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.valueOf(msg));
        }
    }

    public static void i(String tag, Object obj) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, jsonString(obj));
            destroyGson();
        }
    }

    public static void i(Object obj) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, jsonString(obj));
            destroyGson();
        }
    }

    public static void e(String tag, Object obj) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, jsonString(obj));
            destroyGson();
        }
    }

    public static void e(Object obj) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, jsonString(obj));
            destroyGson();
        }
    }

    public static void e(String tag, float obj) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, obj + "");
        }
    }

    public static void e(float obj) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, obj + "");
        }
    }

    public static void d(String tag, Object obj) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, jsonString(obj));
            destroyGson();
        }
    }

    public static void d(Object obj) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, jsonString(obj));
            destroyGson();
        }
    }

    public static String jsonString(Object obj) {
        gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(obj);
    }

    private static void destroyGson() {
        gson = null;
    }
}