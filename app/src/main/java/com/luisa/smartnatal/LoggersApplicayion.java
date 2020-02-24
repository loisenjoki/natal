package com.luisa.smartnatal;

import android.app.Application;

import timber.log.Timber;

public class LoggersApplicayion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }    }
}
