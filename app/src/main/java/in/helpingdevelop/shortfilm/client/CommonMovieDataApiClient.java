package in.helpingdevelop.shortfilm.client;

import android.content.Context;
import android.widget.Toast;

import in.helpingdevelop.shortfilm.AppApplication;
import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.apis.ApiServices;
import in.helpingdevelop.shortfilm.helper.AppConstants;
import in.helpingdevelop.shortfilm.helper.AppLogger;
import in.helpingdevelop.shortfilm.helper.ConnectionUtils;
import in.helpingdevelop.shortfilm.helper.ProgressUtils;
import in.helpingdevelop.shortfilm.model.CommonMovieDataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonMovieDataApiClient {
    private Context context;

    public CommonMovieDataApiClient(Context context) {
        this.context = context;
    }

    public void getPreviousMovieData(String uid, final CommonMovieDataApiCallBack callBack) {
        if (!ConnectionUtils.isConnected(context)) {
            Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressUtils.showProgress(context);

        ApiServices services = AppApplication.getRetrofit().create(ApiServices.class);
        services.getPreviousMovie(uid).enqueue(new Callback<CommonMovieDataResponse>() {
            @Override
            public void onResponse(Call<CommonMovieDataResponse> call, Response<CommonMovieDataResponse> response) {
                ProgressUtils.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus() == AppConstants.SUCCESS) {
                        AppLogger.e(response.body());
                        callBack.onFetchingData(response.body());
                    } else {
                        callBack.onErrorFetching(response.body().getMessage());
                        AppLogger.e("Fetch previous Error", response.body());

                    }
                } else {
                    callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                    AppLogger.e("Fetch previous Error", context.getString(R.string.error_fetching_data));

                }

            }

            @Override
            public void onFailure(Call<CommonMovieDataResponse> call, Throwable t) {
                ProgressUtils.hideProgress();
                callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                AppLogger.e("Fetch previous Error", t.toString());
            }
        });

    }

    public void getNowRunningMovieData(String uid, final CommonMovieDataApiCallBack callBack) {
        if (!ConnectionUtils.isConnected(context)) {
            Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressUtils.showProgress(context);

        ApiServices services = AppApplication.getRetrofit().create(ApiServices.class);
        services.getNowRunningMovie(uid).enqueue(new Callback<CommonMovieDataResponse>() {
            @Override
            public void onResponse(Call<CommonMovieDataResponse> call, Response<CommonMovieDataResponse> response) {
                ProgressUtils.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus() == AppConstants.SUCCESS) {
                        AppLogger.e(response.body());
                        callBack.onFetchingData(response.body());
                    } else {
                        callBack.onErrorFetching(response.body().getMessage());
                        AppLogger.e("Fetch Running movie Error", response.body());

                    }
                } else {
                    callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                    AppLogger.e("Fetch Running movie Error", context.getString(R.string.error_fetching_data));

                }

            }

            @Override
            public void onFailure(Call<CommonMovieDataResponse> call, Throwable t) {
                ProgressUtils.hideProgress();
                callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                AppLogger.e("Fetch Running movie Error", t.toString());
            }
        });

    }

    public void getUpcomingMovieData(String uid, final CommonMovieDataApiCallBack callBack) {
        if (!ConnectionUtils.isConnected(context)) {
            Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressUtils.showProgress(context);

        ApiServices services = AppApplication.getRetrofit().create(ApiServices.class);
        services.getUpcomingMovies(uid).enqueue(new Callback<CommonMovieDataResponse>() {
            @Override
            public void onResponse(Call<CommonMovieDataResponse> call, Response<CommonMovieDataResponse> response) {
                ProgressUtils.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus() == AppConstants.SUCCESS) {
                        AppLogger.e(response.body());
                        callBack.onFetchingData(response.body());
                    } else {
                        callBack.onErrorFetching(response.body().getMessage());
                        AppLogger.e("Fetch upcoming movie Error", response.body());

                    }
                } else {
                    callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                    AppLogger.e("Fetch upcoming movie Error", context.getString(R.string.error_fetching_data));

                }

            }

            @Override
            public void onFailure(Call<CommonMovieDataResponse> call, Throwable t) {
                ProgressUtils.hideProgress();
                callBack.onErrorFetching(context.getString(R.string.error_fetching_data));
                AppLogger.e("Fetch upcoming movie Error", t.toString());
            }
        });

    }

    public interface CommonMovieDataApiCallBack {
        void onFetchingData(CommonMovieDataResponse commonMovieDataResponse);

        void onErrorFetching(String err);
    }
}
