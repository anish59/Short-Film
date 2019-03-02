package in.helpingdevelop.shortfilm.client;

import android.content.Context;
import android.widget.Toast;

import in.helpingdevelop.shortfilm.AppApplication;
import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.apis.ApiServices;
import in.helpingdevelop.shortfilm.helper.AppConstants;
import in.helpingdevelop.shortfilm.helper.AppLogger;
import in.helpingdevelop.shortfilm.helper.ConnectionUtils;
import in.helpingdevelop.shortfilm.helper.PrefUtils;
import in.helpingdevelop.shortfilm.helper.ProgressUtils;
import in.helpingdevelop.shortfilm.model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiClient {
    private Context context;

    public LoginApiClient(Context context) {
        this.context = context;
    }

    public void doLogin(String email, String pwd, final LoginApiClientCallBack callBack) {
        if (!ConnectionUtils.isConnected(context)) {
            Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressUtils.showProgress(context);

        ApiServices services = AppApplication.getRetrofit().create(ApiServices.class);
        services.doLogin(email, pwd).enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                ProgressUtils.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus() == AppConstants.SUCCESS) {
                        PrefUtils.setUser(context, response.body().getData());
                        PrefUtils.setLoggedIn(context, true);
                        callBack.onSuccess(response.body().getMessage());
                        AppLogger.e("Login Success", response.body());
                    } else {
                        callBack.onError(response.body().getMessage());
                        AppLogger.e("Login Error", response.body());

                    }
                } else {
                    callBack.onError(context.getString(R.string.trouble_logging_in));
                    AppLogger.e("Login Error", context.getString(R.string.trouble_logging_in));
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                ProgressUtils.hideProgress();
                callBack.onError(context.getString(R.string.trouble_logging_in));
                AppLogger.e("Login Error", t.toString());
            }
        });


    }

    public interface LoginApiClientCallBack {
        void onSuccess(String msg);

        void onError(String errMsg);

    }
}
