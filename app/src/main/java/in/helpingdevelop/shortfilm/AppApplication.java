package in.helpingdevelop.shortfilm;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import in.helpingdevelop.shortfilm.helper.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApplication extends Application {
    private static Retrofit retrofit;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        initRetroFit();
        Stetho.initializeWithDefaults(this);
        initGson();
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

    private void initGson() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public static Gson getGson() {
        return gson;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
