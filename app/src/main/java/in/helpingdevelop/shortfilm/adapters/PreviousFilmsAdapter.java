package in.helpingdevelop.shortfilm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.model.MovieData;

public class PreviousFilmsAdapter extends RecyclerView.Adapter<PreviousFilmsAdapter.PreviousFilmViewHolder> {

    private Context context;
    private List<MovieData> movieDataList;

    public PreviousFilmsAdapter(Context context) {
        this.context = context;
    }

    public void setMovieDataList(List<MovieData> movieDataList) {
        this.movieDataList = new ArrayList<>();
        this.movieDataList = movieDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PreviousFilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_film, viewGroup, false);

        return new PreviousFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousFilmViewHolder previousFilmViewHolder, int i) {
        previousFilmViewHolder.setData(movieDataList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieDataList != null ? movieDataList.size() : 0;
    }

    class PreviousFilmViewHolder extends RecyclerView.ViewHolder {
        TextView txtReleaseDate;
        TextView txtMovieGenres;
        TextView txtMovieLanguage;
        TextView txtMovieTitle;
        SimpleDraweeView imgMoviePoster;
        Button btnDoWhat;

        public PreviousFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReleaseDate = (TextView) itemView.findViewById(R.id.txtReleaseDate);
            txtMovieGenres = (TextView) itemView.findViewById(R.id.txtMovieGenres);
            txtMovieLanguage = (TextView) itemView.findViewById(R.id.txtMovieLanguage);
            txtMovieTitle = (TextView) itemView.findViewById(R.id.txtMovieTitle);
            imgMoviePoster = (SimpleDraweeView) itemView.findViewById(R.id.imgMoviePoster);
            btnDoWhat = (Button) itemView.findViewById(R.id.btnDoWhat);
        }

        void setData(MovieData data) {
            txtMovieTitle.setText(data.getTitle());
            txtReleaseDate.setText(data.getRelease_on());
            imgMoviePoster.setImageURI(data.getPoster_1());

            txtMovieLanguage.setText("English");
            txtMovieGenres.setText("Action/Sci-fi");
            btnDoWhat.setText(data.getButton_name());

        }
    }

}
