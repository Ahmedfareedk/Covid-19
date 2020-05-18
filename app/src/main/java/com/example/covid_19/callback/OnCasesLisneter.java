package com.example.covid_19.callback;


import com.example.covid_19.model.statistics.worldStatistics.Response;
import com.example.covid_19.model.statistics.worldStatistics.WorldStatistics;

public interface OnCasesLisneter {
    void onResponse(Response cases);
    void onError(String error);
}
