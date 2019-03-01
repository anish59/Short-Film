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
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;

import in.helpingdevelop.shortfilm.Film;
import in.helpingdevelop.shortfilm.R;
import in.helpingdevelop.shortfilm.adapters.BookingAdapter;
import in.helpingdevelop.shortfilm.adapters.PreviousFilmsAdapter;

public class BookingFragment extends Fragment {
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_my_booking, container, false);
        init();
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView bookingRecycler = view.findViewById(R.id.booking_recyclerview);
        ArrayList<Film> films = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Film film = new Film();
            film.setPoster("");
            film.setName("JACKSON BOY");
            film.setExpired(Math.random() < 0.5);
            film.setGenres("Action, Sci-Fi");
            film.setRelease_date("1 JAN 2018");
            film.setBooked("Ticket Booked");
            film.setBooking_date("10 Jan 2019");
            film.setBooking_amount("Online Ticket Booked Rs.50/-");
            film.setMovie_playing_time("Play Movie On 1 FEB 2019 At 12 P.M.");
            films.add(film);
        }

        BookingAdapter adapter = new BookingAdapter(films, getContext());
        bookingRecycler.setAdapter(adapter);
        bookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void init(){
        Switch toggleBtn = mView.findViewById(R.id.toggleBtn);
        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showEmptyLayout(b);
            }
        });
    }
    private void showEmptyLayout(boolean show){
        if(show){
            mView.findViewById(R.id.empty_booking_container).setVisibility(View.VISIBLE);
            mView.findViewById(R.id.booking_container).setVisibility(View.GONE);
        }else {
            mView.findViewById(R.id.empty_booking_container).setVisibility(View.GONE);
            mView.findViewById(R.id.booking_container).setVisibility(View.VISIBLE);
        }
    }
}
