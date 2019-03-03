package in.helpingdevelop.shortfilm.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.adapters.PictureAdapter;
import in.helpingdevelop.shortfilm.model.MovieData;
import in.helpingdevelop.shortfilm.viewModels.RunningFilmsFragmentViewModel;

public class RunningFilmsFragment extends Fragment implements RunningFilmsFragmentViewModel.RunningFragmentCallBack {
    View mView;
    CountDownTimer mCountDownTimer;
    private RecyclerView mRecyclerView;
    private PictureAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Integer> images;
    private static final String TAG = "MainActivity";
    private String movieUrl = "";
    private RunningFilmsFragmentViewModel viewModel;
    private SimpleDraweeView imagePoster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        mView = inflater.inflate(R.layout.running_film_frag, container, false);
        imagePoster = (SimpleDraweeView) mView.findViewById(R.id.imagePoster);
        init();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout relativeLayout = view.findViewById(R.id.thumbnailView);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_layout, NowPlayingFragment.newInstance(movieUrl));
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button bookBtn = view.findViewById(R.id.bookBtn);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft2 = getActivity().getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.main_layout, new PaymentFragment());
                ft2.addToBackStack(null);
                ft2.commit();
            }
        });


    }

    private void init() {
        mRecyclerView = mView.findViewById(R.id.screen_recycler_view);
        viewModel = new RunningFilmsFragmentViewModel(getContext(), this);
        setRecyclerView();
        viewModel.getRunningFilmDetail();
    }

    private void setRecyclerView() {

        if (getContext() != null) {
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new PictureAdapter(getContext());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onSuccess(List<MovieData> movieDataList) {
        movieUrl = movieDataList.get(0).getMovie();
        imagePoster.setImageURI(movieDataList.get(0).getPoster_1());
        ArrayList<String> imgPosters = new ArrayList<>();
        imgPosters.add(movieDataList.get(0).getPoster_1());
        imgPosters.add(movieDataList.get(0).getPoster_2());
        imgPosters.add(movieDataList.get(0).getPoster_3());
        imgPosters.add(movieDataList.get(0).getPoster_4());
        mAdapter.setmDataset(imgPosters);
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }
}

