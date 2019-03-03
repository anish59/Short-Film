package in.helpingdevelop.shortfilm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.model.MovieData;
import in.helpingdevelop.shortfilm.viewModels.UpcomingFilmsFragmentViewModel;

public class UpcomingFilmsFragment extends Fragment implements UpcomingFilmsFragmentViewModel.UpcomingFilmsFragmentCallBack {
    private com.facebook.drawee.view.SimpleDraweeView imgMoviePoster;
    private UpcomingFilmsFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upcoming_film_frag, container, false);
        this.imgMoviePoster = (SimpleDraweeView) view.findViewById(R.id.imgMoviePoster);
        viewModel = new UpcomingFilmsFragmentViewModel(getContext(), this);
        viewModel.getUpcomingFilmDetail();
        return view;
    }

    @Override
    public void onSuccess(List<MovieData> movieDataList) {
        imgMoviePoster.setImageURI(movieDataList.get(0).getPoster_1());
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }
}

