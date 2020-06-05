package com.thawdezin.roteshin.rest;

import com.thawdezin.roteshin.model.NowPlaying;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Thaw De Zin on June 04, 2020
 */

interface MovieAPI {

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlayingMovies(@Query("page") int page, @Query("api_key") String userkey);
}
