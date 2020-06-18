package com.thawdezin.roteshin.rest.endpoints;

import com.thawdezin.roteshin.model.GenresList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public interface GenreEndPoint {

    @POST("genre/movie/list")
    Call<GenresList> getGenresList(@Query("api_key") String api_key);

    @GET("genre/movie/list")
    Call<List<GenresList>> getGenres(@Query("api_key") String api_key);
}
