package in.helpingdevelop.shortfilm.helper;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import in.helpingdevelop.shortfilm.LoginActivity;
import in.helpingdevelop.shortfilm.NavigationActivity;
import in.helpingdevelop.shortfilm.model.UserProfile;

public class Functions {

    public static String toString(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String toString(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static void doLogOut(Context context) {
        PrefUtils.setUser(context, new UserProfile());
        PrefUtils.setLoggedIn(context, false);
        IntentUtils.fireIntent(context, LoginActivity.class, true);
    }
}
