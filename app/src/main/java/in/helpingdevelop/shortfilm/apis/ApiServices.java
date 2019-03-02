package in.helpingdevelop.shortfilm.apis;

import in.helpingdevelop.shortfilm.helper.AppConstants;
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
}
