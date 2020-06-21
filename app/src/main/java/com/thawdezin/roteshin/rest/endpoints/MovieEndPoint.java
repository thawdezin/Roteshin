package com.thawdezin.roteshin.rest.endpoints;

import com.thawdezin.roteshin.model.Movie;
import com.thawdezin.roteshin.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public interface MovieEndPoint {

    @GET("/3/movie/now_playing")
    Call<MovieResult> getNowPlaying(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );

    @GET("/3/movie/popular")
    Call<MovieResult> getPopular(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );

    @GET("/3/movie/upcoming")
    Call<MovieResult> getUpcoming(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );
}
