package com.thawdezin.roteshin.rest;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Thaw De Zin on July 06, 2020.
 */
public class NoConnectivityException extends IOException {

    @Nullable
    @Override
    public String getMessage() {
        return "No internet access available currently.";
    }
}
