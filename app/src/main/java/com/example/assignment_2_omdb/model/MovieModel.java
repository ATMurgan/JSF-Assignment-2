package com.example.assignment_2_omdb.model;

public class MovieModel {
    //title, studio, rating, year, description, movie poster
    private String title;
    private String studio;
    private String rating;
    private String year;
    private String plot;
    private String posterUrl;
    private String movieId;

    private String movieType;

    public MovieModel(String title, String studio, String rating, String year, String plot, String movieType, String posterUrl, String movieId) {
        this.title = title;
        this.studio = studio;
        this.rating = rating;
        this.year = year;
        this.plot = plot;
        this.posterUrl = posterUrl;
        this.movieType = movieType;
        this.movieId = movieId;
    }

    // May need to remove
    public MovieModel() {

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
