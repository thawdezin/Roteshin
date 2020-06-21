package com.thawdezin.roteshin.rest.endpoints;

import com.thawdezin.roteshin.model.Film;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Thaw De Zin on June 21, 2020
 */
public interface FilmEndPoint {

    @GET("/3/movie/now_playing")
    Call<Film> getNowPlaying(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );

}
