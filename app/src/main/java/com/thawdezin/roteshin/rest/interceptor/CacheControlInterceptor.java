package com.thawdezin.roteshin.rest.interceptor;

import android.content.Context;

import androidx.annotation.NonNull;

import com.thawdezin.roteshin.app.AppUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Thaw De Zin on July 06, 2020.
 */
public class CacheControlInterceptor implements Interceptor {

    private Context context;

    public CacheControlInterceptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365,TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if(AppUtils.isConnectingToInternet(context)){
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (AppUtils.isConnectingToInternet(context)) {
            int maxAge = 60  * 60; // read from cache
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
