package com.thawdezin.roteshin.rest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.thawdezin.roteshin.rest.endpoints.MovieEndPoint;
import com.thawdezin.roteshin.rest.interceptor.NetworkConnectionInterceptor;
import com.thawdezin.roteshin.utils.LifecycleEventOneTimeObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {

    private static Retrofit sRetrofit;

    private static MovieEndPoint sMovieEndPoint;

    private RestClient() { }

    private static Retrofit getRetrofit(Context context) {
        if (sRetrofit == null) {

            long SIZE_OF_CACHE = 50 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(new File(context.getCacheDir(), "http"), SIZE_OF_CACHE);

            OkHttpClient okClientBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new NetworkConnectionInterceptor(context))
                    .addInterceptor(
                            chain -> {
                                Request request = chain.request();
                                HttpUrl url = request.url().newBuilder()
                                        .addQueryParameter("api_key","afd84ed60249491a627b9fb517b38ae0")
                                        .addQueryParameter("language","en-US")
                                        .addQueryParameter("page","1")
                                        .build();
                                request = request.newBuilder().url(url).build();
                                return chain.proceed(request);
                            })
                    .connectTimeout(5,TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .writeTimeout(5,TimeUnit.SECONDS)
                    .cache(cache)
                    .build();

            String BASE_URL = "https://api.themoviedb.org";
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClientBuilder)
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

    public static MovieEndPoint getMovieEndPoint(Context context){
        if(sMovieEndPoint==null){
            sMovieEndPoint = getRetrofit(context).create(MovieEndPoint.class);
        }
        return sMovieEndPoint;
    }

}
