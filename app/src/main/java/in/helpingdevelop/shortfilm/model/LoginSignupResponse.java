package in.helpingdevelop.shortfilm.model;

public class LoginSignupResponse extends BaseResponse {
    private UserProfile data;

    public UserProfile getData() {
        return data;
    }

    public void setData(UserProfile data) {
        this.data = data;
    }
}
