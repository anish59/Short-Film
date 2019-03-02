package in.helpingdevelop.shortfilm.viewModels;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import in.helpingdevelop.shortfilm.NavigationActivity;
import in.helpingdevelop.shortfilm.client.LoginApiClient;
import in.helpingdevelop.shortfilm.helper.Functions;
import in.helpingdevelop.shortfilm.helper.IntentUtils;
import in.helpingdevelop.shortfilm.helper.PrefUtils;

public class LoginViewModel {
    private Context context;
    private LoginViewModelCallBack callBack;

    public LoginViewModel(Context context, LoginViewModelCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public boolean isUserLoggedIn() {
        if (PrefUtils.getLoggedIn(context)) {
            IntentUtils.fireIntent(context, NavigationActivity.class, true);
            return true;
        }
        return false;
    }

    public void doLogin(EditText edtEmail, EditText edtPassword) {
        String email = Functions.toString(edtEmail);
        String password = Functions.toString(edtPassword);
        if (email.isEmpty()) {
            Toast.makeText(context, "Please enter your email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        new LoginApiClient(context).doLogin(email, password, new LoginApiClient.LoginApiClientCallBack() {
            @Override
            public void onSuccess(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                IntentUtils.fireIntent(context, NavigationActivity.class, true);
            }

            @Override
            public void onError(String errMsg) {
                Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface LoginViewModelCallBack {
        void onSuccess(String msg);

        void onError(String errMsg);
    }
}
