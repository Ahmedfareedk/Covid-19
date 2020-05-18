package com.example.covid_19.callback;

import com.example.covid_19.model.statistics.worldStatistics.WorldStatistics;
import com.example.covid_19.model.stats.Statistics;

public interface OnCountryListener {
    void onResponse(Statistics response);
    void onError(String error);
}
