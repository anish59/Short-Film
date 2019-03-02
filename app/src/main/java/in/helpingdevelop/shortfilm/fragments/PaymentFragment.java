package in.helpingdevelop.shortfilm.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.helpingdevelop.shortfilm.R;

public class PaymentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView movie_title = view.findViewById(R.id.txtMovieTitle);
        movie_title.setText(getString(R.string.movie_title));

        TextView movie_generes = view.findViewById(R.id.movieGenres);
        movie_generes.setText(getString(R.string.movie_genre));

        TextView movie_release_info = view.findViewById(R.id.txtMovieReleaseInfo);
        movie_release_info.setText(getString(R.string.movie_release));
    }
}
