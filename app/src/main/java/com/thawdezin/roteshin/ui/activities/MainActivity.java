package com.thawdezin.roteshin.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Genres;

import com.thawdezin.roteshin.model.Movie;
import com.thawdezin.roteshin.model.MovieResult;
import com.thawdezin.roteshin.model.Result;
import com.thawdezin.roteshin.rest.RestClient;
import com.thawdezin.roteshin.rest.RetrofitCallbackHelper;
import com.thawdezin.roteshin.ui.activities.base.BaseActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    private String TAG = "Main Activity";

    NestedScrollView contentMain;
    ConstraintLayout contentLoading;
    ConstraintLayout contentError;

    int vGone = View.GONE;
    int vVisible = View.VISIBLE;

    //HomeMovie homeMovie;

    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(false);

        bindViews();
        //callForGenre();
        //callForNowShowing();
        //fetchGenre();
        //fetchUpcoming();
        callForNowPlaying();
        callForNowPlayingMovie();
        customCall();
    }

    private void customCall() {
        Call<Movie> call = RestClient.getNowPlaying().getNowPlayingMovie("afd84ed60249491a627b9fb517b38ae0",LANGUAGE,PAGE);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    Log.e("CustomCall",response.body().toString());
                }
                else{
                    Log.e("CustomCall","response not success ful");
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG,t.getLocalizedMessage());
            }
        });
    }

    private void fetchUpcoming() {
        Call<MovieResult> call = RestClient.getUpcoming().getUpcoming("afd84ed60249491a627b9fb517b38ae0",LANGUAGE,PAGE);
        Log.e(TAG,call.request().url().toString());
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful() && response !=null){
                    Log.e(TAG,response.body().toString());
                    MovieResult movieResult = response.body();
                    if(movieResult!=null) {
                        Log.e(TAG, movieResult.toString());
                        List<Result> listOfMovies = movieResult.getResults();
                        //Log.e(TAG, listOfMovies.toString());
                        //Result firstMovie = listOfMovies.get(0);
//                        if (firstMovie != null) {
//                            //Log.e(TAG,firstMovie.getTitle().toString());
//                        } else {
//                            Log.e(TAG, "firstMovie.getTitle().toString()");
//                        }

                    }

                }else{
                    Log.e(TAG,"response not success");
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e(TAG,"Fail");
            }
        });

    }

    private void fetchGenre(){
        Call<Genres> api = RestClient.getGenreEndPoint().getGenresList("afd84ed60249491a627b9fb517b38ae0",LANGUAGE,PAGE);
        api.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                if (response.isSuccessful())
                {
                    Genres genresList = response.body();
                    Log.e(TAG,genresList.toString());
                    Log.e(TAG, String.valueOf(response.body()));
                }else{
                    Log.e(TAG,"ERROR");
                }
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
                Log.e(TAG,"onFailure");
                Log.e(TAG, t.getLocalizedMessage());
                Log.e(TAG,api.request().url().toString());
            }
        });
    }

    private void bindViews() {
        contentMain = findViewById(R.id.contentMain);
        contentLoading = findViewById(R.id.contentLoading);
        contentError = findViewById(R.id.contentError);

        viewLoading();
    }

    private void viewLoading(){
        Log.e(TAG,"Loading");
        contentLoading.setVisibility(vVisible);
        contentMain.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }
    private void viewError(){
        Log.e(TAG,"Error");
        contentError.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentMain.setVisibility(vGone);
    }
    private void viewMain(){
        Log.e(TAG,"Main");
        contentMain.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }

    private void callForNowPlaying() {
        Log.e(TAG,"I'm here to call NowShowing MovieResult");
        final Call<MovieResult> getNowPlaying = RestClient.getNowPlaying().getNowPlayingMovieResult("afd84ed60249491a627b9fb517b38ae0",LANGUAGE, PAGE);
        String requestUrl = getNowPlaying.request().url().toString();
        Log.e(TAG,requestUrl);
        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {
                Log.e("MovieResult",data.toString());

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                Log.e(TAG,t.getLocalizedMessage());
            }
            @Override
            protected void onComplete() {

            }
        });

    }

    private void callForNowPlayingMovie() {
        Log.e(TAG,"I'm here to call NowShowingMovie");
        final Call<Movie> getNowPlaying = RestClient.getNowPlaying().getNowPlayingMovie("afd84ed60249491a627b9fb517b38ae0",LANGUAGE, PAGE);
        String requestUrl = getNowPlaying.request().url().toString();
        Log.e(TAG,requestUrl);
        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<Movie>() {

            @Override
            protected void onSuccess(@NonNull Movie data, int responseCode) {

                Log.e(">>>>>>",data.toString());

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                //viewError();
                Log.e(TAG,t.getLocalizedMessage());
            }
            @Override
            protected void onComplete() {

            }
        });

    }

    private void finalStepForFetchingMovie() {
        Log.e(TAG,"******************************************************************");
        //Log.e(TAG,homeMovie.getNowPlaying().toString());
        //Log.e(TAG,homeMovie.getPopularMovie().toString());
        //Log.e(TAG,homeMovie.getUpcomingMovie().toString());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.search_movie) {
            //
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}