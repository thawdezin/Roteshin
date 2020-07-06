package com.thawdezin.roteshin.rest.interceptor;

import android.content.Context;

import androidx.annotation.NonNull;

import com.thawdezin.roteshin.app.AppUtils;
import com.thawdezin.roteshin.rest.NoConnectivityException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Thaw De Zin on July 06, 2020.
 */
public class NetworkConnectionInterceptor implements Interceptor {

    @NonNull
    private final Context mContext;

    public NetworkConnectionInterceptor(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        if (!AppUtils.isConnectingToInternet(mContext)) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
