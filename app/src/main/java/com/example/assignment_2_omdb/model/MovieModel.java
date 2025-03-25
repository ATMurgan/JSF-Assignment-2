package com.example.assignment_2_omdb.model;

public class MovieModel {
    //title, studio, rating, year, description, movie poster
    private String title;
    private String studio;
    private String rating;
    private String year;
    private String description;
    private int posterUrl;

    public MovieModel(String title, String studio, String rating, String year, String description, int posterUrl) {
        this.title = title;
        this.studio = studio;
        this.rating = rating;
        this.year = year;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    // May need to remove
    public MovieModel() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(int posterUrl) {
        this.posterUrl = posterUrl;
    }
}
