package in.helpingdevelop.shortfilm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import in.helpingdevelop.shortfilm.Film;
import in.helpingdevelop.shortfilm.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private Context mCtx;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView movie_poster;
        public TextView movie_title;
        public TextView movie_genres;
        public TextView movie_release_date;
        public TextView booked;
        public TextView booking_date;
        public TextView booking_amount;
        public TextView movie_playing_time;
        public LinearLayout ticket_background;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster = itemView.findViewById(R.id.imgMoviePoster);
            movie_title = itemView.findViewById(R.id.txtMovieTitle);
            movie_genres = itemView.findViewById(R.id.movieGenres);
            movie_release_date = itemView.findViewById(R.id.txtMovieReleaseInfo);
            booked = itemView.findViewById(R.id.booked);
            booking_date = itemView.findViewById(R.id.booked_date);
            booking_amount = itemView.findViewById(R.id.booking_amount);
            movie_playing_time = itemView.findViewById(R.id.movie_play_timing);
            ticket_background = itemView.findViewById(R.id.ticket_expire_background);
        }
    }

    private ArrayList<Film> mFilms;

    public BookingAdapter(ArrayList<Film> films, Context mCtx) {
        mFilms = films;
        this.mCtx = mCtx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.booking_list, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Film film = mFilms.get(i);

        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.default_poster)
                .placeholder(R.drawable.default_poster);
        Glide
                .with(mCtx)
                .asBitmap()
                .apply(requestOptions)
                .load(film.getPoster())
                .into(viewHolder.movie_poster);

        if(film.getExpired()){
            viewHolder.booked.setText(mCtx.getString(R.string.expired));
            viewHolder.ticket_background.setBackground(mCtx.getResources().getDrawable(R.drawable.button_style_4));
        }else {
            viewHolder.booked.setText(film.getBooked());
            viewHolder.ticket_background.setBackground(mCtx.getResources().getDrawable(R.drawable.button_style_6));
        }
        viewHolder.movie_title.setText(film.getName());
        viewHolder.movie_genres.setText(film.getGenres());
        viewHolder.movie_release_date.setText(film.getRelease_date());
        viewHolder.booking_date.setText(film.getBooking_date());
        viewHolder.booking_amount.setText(film.getBooking_amount());
        viewHolder.movie_playing_time.setText(film.getMovie_playing_time());
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }
}
