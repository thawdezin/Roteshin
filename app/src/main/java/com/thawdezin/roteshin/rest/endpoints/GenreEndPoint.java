package com.thawdezin.roteshin.rest.endpoints;

import com.thawdezin.roteshin.model.Genres;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public interface GenreEndPoint {

    @GET("/3/genre/movie/list")
    Call<Genres> getGenresList(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );

    @GET("/3/genre/movie/list")
    Call<List<Genres>> getGenres(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int pageNo
    );
}
