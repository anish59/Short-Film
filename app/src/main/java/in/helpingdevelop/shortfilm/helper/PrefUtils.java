package in.helpingdevelop.shortfilm.helper;

import android.content.Context;
import android.text.TextUtils;

import in.helpingdevelop.shortfilm.AppApplication;
import in.helpingdevelop.shortfilm.model.UserProfile;


public class PrefUtils {

    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String USER_OBJECT = "USER_OBJECT";


    public static void setUser(Context context, UserProfile userProfile) {
        String text = AppApplication.getGson().toJson(userProfile);
        Prefs.with(context).save(USER_OBJECT, text);
    }

    public static UserProfile getUser(Context context) {
        UserProfile user = new UserProfile();
        String text = Prefs.with(context).getString(USER_OBJECT, "");
        if (!TextUtils.isEmpty(text)) {
            user = AppApplication.getGson().fromJson(text, UserProfile.class);
        }
        return user;
    }


    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        Prefs.with(context).save(IS_LOGGED_IN, isLoggedIn);
    }

    public static boolean getLoggedIn(Context context) {
        return Prefs.with(context).getBoolean(IS_LOGGED_IN, false);
    }


}
