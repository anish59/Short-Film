package in.helpingdevelop.shortfilm.apis;

import in.helpingdevelop.shortfilm.helper.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(AppConstants.LOGIN)
    Call<String> doLogin(@Query("email") String email, @Query("password") String password);
}
