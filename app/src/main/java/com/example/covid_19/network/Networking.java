package com.example.covid_19.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.covid_19.callback.OnCasesLisneter;
import com.example.covid_19.callback.OnCountryListener;
import com.example.covid_19.model.history_stats.HistoryResponse;
import com.example.covid_19.model.history_stats.HistoryStatistics;
import com.example.covid_19.model.statistics.worldStatistics.WorldStatistics;
import com.example.covid_19.model.stats.Response;
import com.example.covid_19.model.stats.Statistics;
import com.example.covid_19.utils.Constants;

public class Networking {

    public static void fetch(final  OnCasesLisneter listener)
    {
        AndroidNetworking.get("https://covid-193.p.rapidapi.com/statistics")
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.Api.API_KEY)
                .addQueryParameter("country", "all")
                .build()
                .getAsObject(WorldStatistics.class, new ParsedRequestListener<WorldStatistics>() {
                    @Override
                    public void onResponse(WorldStatistics response) {
                        listener.onResponse(response.getResponse().get(0));
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorDetail());
                    }
                });
    }

    public static void fetchCountry(final OnCountryListener listener)
    {
        AndroidNetworking.get("https://covid-193.p.rapidapi.com/statistics")
                  .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                  .addHeaders("x-rapidapi-key", Constants.Api.API_KEY)
                .build()
                .getAsObject(Statistics.class, new ParsedRequestListener<Statistics>() {
                    @Override
                    public void onResponse(Statistics response) {
                        listener.onResponse(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorDetail());
                    }
                });
    }

    public static void fetchCountryDate(Context mContext, String date, String countryName, final OnCountryListener listener)
    {
        AndroidNetworking.get("https://covid-193.p.rapidapi.com/history")
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.Api.API_KEY)
                .addQueryParameter("day", date)
                .addQueryParameter("country", countryName)
                .build()
                .getAsObject(HistoryStatistics.class, new ParsedRequestListener<HistoryStatistics>() {
                    @Override
                    public void onResponse(HistoryStatistics response) {

                        if(response.getResponse().size() ==0 ){

                            Toast.makeText(mContext, "Invalid Day!, Try another day", Toast.LENGTH_LONG).show();

                        }else{
                            listener.onResponse(response.getResponse().get(0));
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorDetail());
                    }
                });
    }

 /*   public static void fetchByDate(String date, String countryName, final  OnCasesLisneter listener)
    {
        AndroidNetworking.get("https://covid-193.p.rapidapi.com/statistics")
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.Api.API_KEY)
                .addQueryParameter("country", countryName)
                .addQueryParameter("day", date)
                .build()
                .getAsObject(Statistics.class, new ParsedRequestListener<Statistics>() {
                    @Override
                    public void onResponse(Statistics response) {
                        listener.onResponse(response.getResponse());
                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorDetail());
                    }
                });
    }*/
}
