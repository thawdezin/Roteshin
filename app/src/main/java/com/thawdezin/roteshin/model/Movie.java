package com.thawdezin.roteshin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
//    public String getPosterThumbnailUrl(){
//        return "http://image.tmdb.org/t/p/w500"+getPosterPath();
//    }
//}

public class Movie {

    private List<Result> nowPlaying;
    private List<Result> popularMovie;
    private List<Result> upcomingMovie;

    public List<Result> getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(List<Result> nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public List<Result> getPopularMovie() {
        return popularMovie;
    }

    public void setPopularMovie(List<Result> popularMovie) {
        this.popularMovie = popularMovie;
    }

    public List<Result> getUpcomingMovie() {
        return upcomingMovie;
    }

    public void setUpcomingMovie(List<Result> upcomingMovie) {
        this.upcomingMovie = upcomingMovie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "nowPlaying=" + nowPlaying +
                ", popularMovie=" + popularMovie +
                ", upcomingMovie=" + upcomingMovie +
                '}';
    }
}