package in.helpingdevelop.shortfilm.viewModels;

import android.content.Context;

import java.util.List;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.client.CommonMovieDataApiClient;
import in.helpingdevelop.shortfilm.helper.PrefUtils;
import in.helpingdevelop.shortfilm.model.CommonMovieDataResponse;
import in.helpingdevelop.shortfilm.model.MovieData;

public class RunningFilmsFragmentViewModel {
    private Context context;
    private RunningFragmentCallBack callBack;

    public RunningFilmsFragmentViewModel(Context context, RunningFragmentCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void getRunningFilmDetail() {
        new CommonMovieDataApiClient(context).getNowRunningMovieData(PrefUtils.getUser(context).getId(), new CommonMovieDataApiClient.CommonMovieDataApiCallBack() {
            @Override
            public void onFetchingData(CommonMovieDataResponse commonMovieDataResponse) {
                if (commonMovieDataResponse != null && commonMovieDataResponse.getData() != null
                        && commonMovieDataResponse.getData().size() > 0) {

                    callBack.onSuccess(commonMovieDataResponse.getData());

                } else {
                    callBack.onError(context.getString(R.string.error_fetching_data));
                }
            }

            @Override
            public void onErrorFetching(String err) {
                callBack.onError(err);
            }
        });
    }

    public interface RunningFragmentCallBack {
        void onSuccess(List<MovieData> movieDataList);

        void onError(String errMsg);
    }
}
