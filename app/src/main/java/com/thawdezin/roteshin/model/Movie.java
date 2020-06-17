package com.thawdezin.roteshin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public class Movie {

    @SerializedName("movieTitle")
    private String movieTitle;

    @SerializedName("id")
    private int id;

    @SerializedName("posterThumbnailUrl")
    private String posterThumbnailUrl;

    @SerializedName("genresList")
    private GenresList genresList;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterThumbnailUrl() {
        return posterThumbnailUrl;
    }

    public void setPosterThumbnailUrl(String posterThumbnailUrl) {
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

    public GenresList getGenresList() {
        return genresList;
    }

    public void setGenresList(GenresList genresList) {
        this.genresList = genresList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", id=" + id +
                ", posterThumbnailUrl='" + posterThumbnailUrl + '\'' +
                ", genresList=" + genresList +
                '}';
    }
}
