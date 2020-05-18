package com.example.covid_19.activity;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class CoronaVirusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
