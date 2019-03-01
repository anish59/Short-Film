package in.helpingdevelop.shortfilm;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import in.helpingdevelop.shortfilm.helper.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApplication extends Application {
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        initRetroFit();
    }

    private void initRetroFit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
