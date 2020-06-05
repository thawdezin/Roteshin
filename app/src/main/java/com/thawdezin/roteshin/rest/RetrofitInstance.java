package com.thawdezin.roteshin.rest;
import com.thawdezin.roteshin.model.NowPlaying;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thaw De Zin on June 04, 2020
 */

public class RetrofitInstance {

    private static Retrofit retrofit;
    //public MovieAPI api;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}