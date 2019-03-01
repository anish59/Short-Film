package in.helpingdevelop.shortfilm;

public class Film {
    private int id;
    private String name;
    private String language;
    private String genres;
    private String release_date;

    /*Booking variables*/
    private String booked;
    private String booking_date;
    private String booking_amount;
    private String movie_playing_time;
    private String poster;
    private Boolean expired;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    /*getter and setter for booking section*/
    public String getBooked(){
        return booked;
    }
    public String getBooking_date(){return booking_date;}
    public String getBooking_amount(){return booking_amount;}
    public String getMovie_playing_time(){return movie_playing_time; };
    public String getPoster(){return poster;}
    public void setBooked(String booked) {
        this.booked = booked;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public void setMovie_playing_time(String movie_playing_time) {
        this.movie_playing_time = movie_playing_time;
    }

    public void setBooking_amount(String booking_amount) {
        this.booking_amount = booking_amount;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    public boolean getExpired(){
        return expired;
    }
}
