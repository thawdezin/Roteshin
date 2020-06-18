package com.thawdezin.roteshin.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.GenresList;
import com.thawdezin.roteshin.rest.RestClient;
import com.thawdezin.roteshin.rest.RetrofitCallbackHelper;
import com.thawdezin.roteshin.rest.endpoints.GenreEndPoint;
import com.thawdezin.roteshin.ui.activities.base.BaseActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    private String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(false);

        final Call<GenresList> getGenre = RestClient.getGenreEndPoint().getGenresList("afd84ed60249491a627b9fb517b38ae0");
        String requestUrl = getGenre.request().url().toString();
        Log.e(TAG,requestUrl);
        RestClient.enqueue(this, getGenre, new RetrofitCallbackHelper<GenresList>() {

            @Override
            protected void onSuccess(@NonNull GenresList data, int responseCode) {
                Log.e(TAG,"Success");
            }

            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                Log.e(TAG,"Fail1");
            }

            @Override
            protected void onComplete() {
                Log.e(TAG,"Complete");
            }

        });

        final Call<List<GenresList>> getGenres = RestClient.getGenreEndPoint().getGenres("afd84ed60249491a627b9fb517b38ae0");
        String url = getGenres.request().url().toString();
        Log.e(TAG,url);
        RestClient.enqueue(this, getGenres, new RetrofitCallbackHelper<List<GenresList>>() {



            @Override
            protected void onSuccess(@NonNull List<GenresList> data, int responseCode) {
                Log.e(TAG,"Success");
            }

            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                Log.e(TAG,"Fail2");
            }

            @Override
            protected void onComplete() {
                Log.e(TAG,"Complete");
            }


        });

        GenreEndPoint service = RestClient.getGenreEndPoint();
        Call<List<GenresList>> call = service.getGenres("afd84ed60249491a627b9fb517b38ae0");
        String checkUrl = call.request().url().toString();
        Log.e(TAG,checkUrl);
        call.enqueue(new Callback<List<GenresList>>() {
            @Override
            public void onResponse(Call<List<GenresList>> call, Response<List<GenresList>> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG,"Success");
                }
                Log.e(TAG, String.valueOf(response.isSuccessful()));

            }

            @Override
            public void onFailure(Call<List<GenresList>> call, Throwable t) {
                Log.e(TAG,"Fail3");
            }
        });


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