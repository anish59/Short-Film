package in.helpingdevelop.shortfilm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.adapters.PreviousFilmsAdapter;
import in.helpingdevelop.shortfilm.helper.AppLogger;
import in.helpingdevelop.shortfilm.model.MovieData;
import in.helpingdevelop.shortfilm.viewModels.PreviousFilmsFragmentViewModel;

public class PreviousFilmsFragment extends Fragment implements PreviousFilmsFragmentViewModel.PreviousFilmsFragmentCallBack {
    private RecyclerView rvFilm;
    private PreviousFilmsFragmentViewModel viewModel;
    private PreviousFilmsAdapter filmsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.previous_film_frag, container, false);
        this.rvFilm = (RecyclerView) view.findViewById(R.id.rvFilm);
        init();
        return view;
    }

    private void init() {
        viewModel = new PreviousFilmsFragmentViewModel(getContext(), this);
        viewModel.getPreviousFilmDetail();

        filmsAdapter = new PreviousFilmsAdapter(getContext());
        rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFilm.setAdapter(filmsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSuccess(List<MovieData> movieDataList) {
        filmsAdapter.setMovieDataList(movieDataList);
    }

    @Override
    public void onError(String errMsg) {
        AppLogger.e(errMsg);
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }
}

