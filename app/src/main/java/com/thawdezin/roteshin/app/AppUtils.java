package com.thawdezin.roteshin.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Thaw De Zin on July 06, 2020.
 */
public final class AppUtils {

    private static final String TAG = "AppUtils";

    public static boolean isConnectingToInternet(Context context) {
        boolean isConnected = false;
        try {
            @Nullable final Object systemService = context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (systemService instanceof ConnectivityManager) {
                @NonNull final ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    @Nullable final Network activeNetwork = connectivityManager.getActiveNetwork();
                    if (activeNetwork != null) {
                        @Nullable final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                        if (networkCapabilities != null) {
                            isConnected = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                        }
                    }
                } else {
                    @Nullable final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        isConnected = activeNetworkInfo.isConnected();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "isInternetConnected: ", e);
        }
        Log.d(TAG, "isInternetConnected() returned: " + isConnected);
        return isConnected;
    }
}
