package in.helpingdevelop.shortfilm.apis;

import in.helpingdevelop.shortfilm.helper.AppConstants;
import in.helpingdevelop.shortfilm.model.CommonMovieDataResponse;
import in.helpingdevelop.shortfilm.model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(AppConstants.LOGIN)
    Call<LoginSignupResponse> doLogin(@Query("email") String email, @Query("password") String password);

    @GET(AppConstants.REGISTER)
    Call<LoginSignupResponse> doSignUp(@Query("email") String email, @Query("password") String password,
                                       @Query("name") String name, @Query("ip") String ip);

    @GET(AppConstants.UPDATE_PROFILE)
    Call<LoginSignupResponse> profileUpdate(@Query("email") String email, @Query("password") String password,
                                            @Query("name") String name, @Query("mobile") String mobile,
                                            @Query("dob") String dob, @Query("ticket_bookd") String ticket_booked);

    @GET(AppConstants.PREVIOUS)
    Call<CommonMovieDataResponse> getPreviousMovie(@Query("uid") String uid);

    @GET(AppConstants.NOW_RUNNING)
    Call<CommonMovieDataResponse> getNowRunningMovie(@Query("uid") String uid);

    @GET(AppConstants.UPCOMING)
    Call<CommonMovieDataResponse> getUpcomingMovies(@Query("uid") String uid);
}
