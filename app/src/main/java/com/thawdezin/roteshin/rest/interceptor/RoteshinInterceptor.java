package com.thawdezin.roteshin.rest.interceptor;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Thaw De Zin on July 06, 2020.
 */
public class RoteshinInterceptor implements Interceptor {

    private Context context;

    public RoteshinInterceptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key","afd84ed60249491a627b9fb517b38ae0")
                .addQueryParameter("language","en-US")
                .addQueryParameter("page","1")
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
