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

    @SerializedName("posterPath")
    private String posterPath;

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

    public GenresList getGenresList() {
        return genresList;
    }

    public void setGenresList(GenresList genresList) {
        this.genresList = genresList;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    
    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", genresList=" + genresList +
                '}';
    }

    public String getPosterThumbnailUrl(){
        return "http://image.tmdb.org/t/p/w500"+getPosterPath();
    }
}
