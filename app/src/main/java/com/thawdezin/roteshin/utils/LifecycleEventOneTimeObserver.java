package com.thawdezin.roteshin.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public abstract class LifecycleEventOneTimeObserver implements LifecycleEventObserver {

    @NonNull
    private final Lifecycle.Event targetEvent;

    protected LifecycleEventOneTimeObserver(@NonNull Lifecycle.Event targetEvent) {
        this.targetEvent = targetEvent;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == targetEvent) {
            onEventTriggered();
        }
    }

    protected abstract void onEventTriggered();
}
