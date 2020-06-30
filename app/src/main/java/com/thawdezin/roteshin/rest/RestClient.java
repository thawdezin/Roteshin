package com.thawdezin.roteshin.rest;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.thawdezin.roteshin.rest.endpoints.MovieEndPoint;
import com.thawdezin.roteshin.utils.LifecycleEventOneTimeObserver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {

    private static Retrofit sRetrofit;

    private static MovieEndPoint sMovieEndPoint;

    private RestClient() {
    }

    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {

            String BASE_URL = "https://api.themoviedb.org";
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static <T> void enqueue(@NonNull final LifecycleOwner lifecycleOwner,
                                   @NonNull final Call<T> call,
                                   @NonNull final Callback<T> callback) {
        lifecycleOwner.getLifecycle().addObserver(new LifecycleEventOneTimeObserver(Lifecycle.Event.ON_DESTROY) {
            @Override
            protected void onEventTriggered() {
                RestClient.cancel(call);
            }
        });
        call.enqueue(callback);
    }

    public static void cancel(@NonNull Call... calls) {
        for (Call call : calls) {
            try {
                if (call != null && !call.isCanceled()) {
                    call.cancel();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static MovieEndPoint getMovieEndPoint(){
        if(sMovieEndPoint==null){
            sMovieEndPoint = getRetrofit().create(MovieEndPoint.class);
        }
        return sMovieEndPoint;
    }

}
