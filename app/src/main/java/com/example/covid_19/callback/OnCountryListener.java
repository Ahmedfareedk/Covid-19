package com.example.covid_19.callback;

import com.example.covid_19.model.statistics.worldStatistics.WorldStatistics;
import com.example.covid_19.model.stats.Statistics;

public interface OnCountryListener<T> {
    void onResponse(T response);
    void onError(String error);
}
