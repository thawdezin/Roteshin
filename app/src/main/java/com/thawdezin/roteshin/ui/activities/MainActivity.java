package com.thawdezin.roteshin.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Film;
import com.thawdezin.roteshin.model.FilmItem;
import com.thawdezin.roteshin.model.Genres;

import com.thawdezin.roteshin.model.MovieResult;
import com.thawdezin.roteshin.model.Result;
import com.thawdezin.roteshin.rest.RestClient;
import com.thawdezin.roteshin.rest.RetrofitCallbackHelper;
import com.thawdezin.roteshin.ui.activities.base.BaseActivity;
import com.thawdezin.roteshin.ui.adapters.RecyclerAdapterNowShowing;
import com.thawdezin.roteshin.ui.adapters.RecyclerAdapterPopular;
import com.thawdezin.roteshin.ui.adapters.RecyclerAdapterUpcoming;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    private String TAG = "Main Activity";

    NestedScrollView contentMain;
    ConstraintLayout contentLoading;
    ConstraintLayout contentError;

    RecyclerView recyclerViewNowShowing;
    RecyclerView recyclerViewPopular;
    RecyclerView recyclerViewUpcoming;

    RecyclerAdapterNowShowing recyclerAdapterNowShowing;
    RecyclerAdapterPopular recyclerAdapterPopular;
    RecyclerAdapterUpcoming recyclerAdapterUpcoming;

    MaterialTextView tvGenres;
    MaterialTextView tvItemTitle;
    ImageView ivItem;

    int vGone = View.GONE;
    int vVisible = View.VISIBLE;

    //HomeMovie homeMovie;

    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;

    List<Result> movieList = new ArrayList<Result>();
    List<FilmItem> filmList = new ArrayList<FilmItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(false);

        bindViews();
        recyclerViewPrepare();
        //callForNowPlaying();
        //callForGenre();
        //callForNowShowing();
        //fetchGenre();
        //fetchUpcoming();
        callTest();
    }

    private void recyclerViewPrepare() {
        LinearLayout linearLayout = new LinearLayout(this);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNowShowing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Result res1 = new Result();
        res1.setTitle("Thaw De Zin");
        Result res2 = new Result();
        res2.setTitle("Thaw Thaw");
        movieList.add(res1);
        movieList.add(res2);
        movieList.add(res1);
        movieList.add(res2);
        movieList.add(res1);
        movieList.add(res2);

        recyclerAdapterNowShowing = new RecyclerAdapterNowShowing(getApplicationContext(),movieList);
        recyclerAdapterPopular = new RecyclerAdapterPopular(getApplicationContext(),filmList);
        recyclerViewNowShowing.setAdapter(recyclerAdapterNowShowing);
        recyclerViewPopular.setAdapter(recyclerAdapterPopular);
        recyclerViewUpcoming.setAdapter(recyclerAdapterNowShowing);

    }

    private void bindViews() {
        contentMain = findViewById(R.id.contentMain);
        contentLoading = findViewById(R.id.contentLoading);
        contentError = findViewById(R.id.contentError);

        recyclerViewNowShowing = findViewById(R.id.rvNowShowing);
        recyclerViewPopular = findViewById(R.id.rvPopularMovie);
        recyclerViewUpcoming = findViewById(R.id.rvUpcomingMovie);

        tvGenres = findViewById(R.id.tvMoiveItemGenre);
        tvItemTitle = findViewById(R.id.tvItemTitle);
        ivItem = findViewById(R.id.ivMovieItem);


        viewLoading();
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


    private void callTest() {
        final Call<Film> getNowPlaying = RestClient.getFilmEndPoint().getNowPlaying("afd84ed60249491a627b9fb517b38ae0",LANGUAGE, PAGE);
        String requestUrl = getNowPlaying.request().url().toString();
        Log.e(TAG,requestUrl);
        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<Film>() {

            @Override
            protected void onSuccess(@NonNull Film data, int responseCode) {
                Log.e("FilmResult",data.toString());
                List<FilmItem> filmItem = data.getFilmItem();
                filmList = filmItem;
                recyclerAdapterPopular.setMovieList(filmList);
                Log.e("filmItem.toString()",filmItem.toString());

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                t.printStackTrace();
            }
            @Override
            protected void onComplete() {

            }
        });
    }


    private void fetchUpcoming() {
        Call<MovieResult> call = RestClient.getMovieEndPoint().getUpcoming("afd84ed60249491a627b9fb517b38ae0",LANGUAGE,PAGE);
        Log.e(TAG,call.request().url().toString());
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful() && response.body() !=null){
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
                t.printStackTrace();
                Log.e(TAG,api.request().url().toString());
            }
        });
    }



    private void viewLoading(){
        Log.e(TAG,"Loading View");
        contentLoading.setVisibility(vVisible);
        contentMain.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }
    private void viewError(){
        Log.e(TAG,"Error View");
        contentError.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentMain.setVisibility(vGone);
    }
    private void viewMain(){
        Log.e(TAG,"Main View");
        contentMain.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }

    private void callForNowPlaying() {
        Log.e(TAG,"I'm here to call NowShowing MovieResult");
        final Call<MovieResult> getNowPlaying = RestClient.getMovieEndPoint().getNowPlaying("afd84ed60249491a627b9fb517b38ae0",LANGUAGE, PAGE);
        String requestUrl = getNowPlaying.request().url().toString();
        Log.e(TAG,requestUrl);
        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {
                Log.e("MovieResult",data.toString());

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                t.printStackTrace();
            }
            @Override
            protected void onComplete() {
                viewMain();
            }
        });

    }



    private void finalStepForFetchingMovie() {
        Log.e(TAG,"******************************************************************");
        //Log.e(TAG,homeMovie.getNowPlaying().toString());
        //Log.e(TAG,homeMovie.getPopularMovie().toString());
        //Log.e(TAG,homeMovie.getUpcomingMovie().toString());
    }




}

//    private void customCall() {
//        Call<MovieResult> call = RestClient.getNowPlaying().getNowPlaying("afd84ed60249491a627b9fb517b38ae0",LANGUAGE,PAGE);
//        call.enqueue(new Callback<MovieResult>() {
//            @Override
//            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
//                if(response.isSuccessful()){
//                    Log.e("CustomCall",response.body().toString());
//                }
//                else{
//                    Log.e("CustomCall","response not success ful");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResult> call, Throwable t) {
//                Log.e(TAG,t.getLocalizedMessage());
//            }
//        });
//    }