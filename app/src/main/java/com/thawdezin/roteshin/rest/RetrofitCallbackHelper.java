package com.thawdezin.roteshin.rest;


import android.text.TextUtils;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zaw Myo Naing on 3/18/18.
 **/
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class RetrofitCallbackHelper<T> implements Callback<T> {

    /**
     * Flag to indicate http response code is not within 200...300
     */
    public static final int RESULT_NET_FAIL = 9384;

    /**
     * Flag to indicate http response code 400...414
     */
    public static final int RESULT_CLIENT_ERROR = 9428;

    /**
     * Flag to indicate http response code 500 and above
     */
    public static final int RESULT_SERVER_ERROR = 9472;

    /**
     * Flag to indicate http request failure (eg. Invoked network request when internet connection is not available)
     */
    public static final int RESULT_CLIENT_FAIL = 3948;

    /**
     * Flag to indicate http response body is null
     */
    public static final int RESULT_NO_DATA = 2932;

    /**
     * Flag to indicate when no http response code is received
     */
    public static final int NO_RESPONSE_CODE = -3246;

    private static final String TAG = "RetrofitCallbackHelper";

    @IntDef({RESULT_NET_FAIL, RESULT_CLIENT_ERROR, RESULT_SERVER_ERROR, RESULT_CLIENT_FAIL, RESULT_NO_DATA, NO_RESPONSE_CODE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetCallResult {
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        onComplete();
        final int responseCode = response.code();
        Log.d(TAG, "onResponse: Server Response Code : " + responseCode);
        if (response.isSuccessful()) {
            T data = response.body();
            if (data != null) {
                onSuccess(data, responseCode);
            } else {
                onFailure(new Throwable("Received Invalid data from response"), responseCode, RESULT_NO_DATA);
            }
        } else if (responseCode >= 400 && responseCode < 500) {
            String errMsg = "There are some problems in http request parameter.";
            onFailure(new Throwable(errMsg), responseCode, RESULT_CLIENT_ERROR);
        } else if (responseCode >= 500) {
            String errMsg = "There are some problem in server.";
            onFailure(new Throwable(errMsg), responseCode, RESULT_SERVER_ERROR);
        } else {
            String errMsg = "Unsuccessful Response\nResponse Code : " + response.code() + "\nMessage: " + response.message();
            onFailure(new Throwable(errMsg), responseCode, RESULT_NET_FAIL);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        final boolean canceled = call.isCanceled();
        Log.d(TAG, "onFailure: Is Call Canceled ? : " + canceled);
        if (!canceled) {
            onComplete();
            String errMsg = !TextUtils.isEmpty(t.getMessage()) ? t.getMessage() : "Unknown Error";
            onFailure(new Throwable(errMsg), NO_RESPONSE_CODE, RESULT_CLIENT_FAIL);
        }
    }

    protected abstract void onSuccess(@NonNull T data, int responseCode);

    protected abstract void onFailure(Throwable t, int responseCode, @NetCallResult int resultCode);

    /**
     * Called when http request is finished regardless of success or failure
     */
    protected abstract void onComplete();

}
