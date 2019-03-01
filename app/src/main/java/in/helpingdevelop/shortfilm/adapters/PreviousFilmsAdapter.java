package in.helpingdevelop.shortfilm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.helpingdevelop.shortfilm.Film;
import in.helpingdevelop.shortfilm.R;

public class PreviousFilmsAdapter extends RecyclerView.Adapter<PreviousFilmsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movie_title;
        public TextView movie_language;
        public TextView movie_genres;
        public TextView movie_release_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_title = itemView.findViewById(R.id.movie_title);
            movie_language = itemView.findViewById(R.id.movie_language);
            movie_genres = itemView.findViewById(R.id.movie_genres);
            movie_release_date = itemView.findViewById(R.id.movie_release_date);
        }
    }

    private ArrayList<Film> mFilms;

    public PreviousFilmsAdapter(ArrayList<Film> films) {
        mFilms = films;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item_film, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Film film = mFilms.get(i);

        viewHolder.movie_title.setText(film.getName());
        viewHolder.movie_language.setText(film.getLanguage());
        viewHolder.movie_genres.setText(film.getGenres());
        viewHolder.movie_release_date.setText(film.getRelease_date());
    }



    @Override
    public int getItemCount() {
        return mFilms.size();
    }
}
