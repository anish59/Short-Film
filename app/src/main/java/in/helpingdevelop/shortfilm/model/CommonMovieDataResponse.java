package in.helpingdevelop.shortfilm.model;

import java.util.List;

public class CommonMovieDataResponse extends BaseResponse {
    private List<MovieData> data;

    public List<MovieData> getData() {
        return data;
    }

    public void setData(List<MovieData> data) {
        this.data = data;
    }
}
