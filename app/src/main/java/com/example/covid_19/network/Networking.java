package com.example.covid_19.network;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.covid_19.callback.OnCasesLisneter;
import com.example.covid_19.callback.OnCountryListener;
import com.example.covid_19.model.statistics.worldStatistics.WorldStatistics;
import com.example.covid_19.model.stats.Statistics;
import com.example.covid_19.utils.Constants;
import com.example.covid_19.utils.LoadingDialog;

public class Networking {

    public static void fetchWorldStatistics(final OnCasesLisneter listener) {
        AndroidNetworking.get(Constants.RapidCovidApi.API_STATISTICS_BASE_URL)
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.RapidCovidApi.API_KEY)
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

    public static void fetchCountryStatistics(final OnCountryListener listener) {
        AndroidNetworking.get(Constants.RapidCovidApi.API_STATISTICS_BASE_URL)
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.RapidCovidApi.API_KEY)
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

    public static void fetchCountryHistoryByDate(Context mContext, String date, String countryName, final OnCountryListener listener) {
        AndroidNetworking.get(Constants.RapidCovidApi.API_HISTORY_BASE_URL)
                .addHeaders("x-rapidapi-host", "covid-193.p.rapidapi.com")
                .addHeaders("x-rapidapi-key", Constants.RapidCovidApi.API_KEY)
                .addQueryParameter("day", date)
                .addQueryParameter("country", countryName)
                .build()
                .getAsObject(WorldStatistics.class, new ParsedRequestListener<WorldStatistics>() {
                    @Override
                    public void onResponse(WorldStatistics response) {

                        if (response.getResponse().size() == 0)
                            Toast.makeText(mContext, "Invalid Day!, there is no data", Toast.LENGTH_LONG).show();
                        else
                            listener.onResponse(response.getResponse().get(0));
                    }
                    @Override
                    public void onError(ANError anError) {
                        listener.onError(anError.getErrorDetail());
                    }
                });
    }

}
