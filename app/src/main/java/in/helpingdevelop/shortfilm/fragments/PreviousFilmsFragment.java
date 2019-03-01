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

import java.util.ArrayList;

import in.helpingdevelop.shortfilm.Film;
import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.adapters.PreviousFilmsAdapter;

public class PreviousFilmsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.previous_film_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvFilm = view.findViewById(R.id.rvFilm);
        ArrayList<Film> films = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Film film = new Film();
            film.setName("JACKSON BOY");
            film.setLanguage("HINDI");
            film.setGenres("Action, Sci-Fi");
            film.setRelease_date("1 JAN 2018");
            films.add(film);
        }

        PreviousFilmsAdapter adapter = new PreviousFilmsAdapter(films);
        rvFilm.setAdapter(adapter);
        rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

