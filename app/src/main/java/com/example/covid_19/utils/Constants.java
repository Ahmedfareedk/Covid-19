package com.example.covid_19.utils;

public interface Constants {

    interface RapidCovidApi {
        String API_STATISTICS_BASE_URL = "https://covid-193.p.rapidapi.com/statistics";
        String API_HISTORY_BASE_URL = "https://covid-193.p.rapidapi.com/history";

        String API_KEY = "1b643bbf98msh8ba2ab403338884p1b40dbjsnc8d01918bb13";
    }

    interface FlagIoApi {
        String LOAD_FLAG_URL = "https://www.countryflags.io/";
        String FLAG_STYLE = "/shiny/64.png";
    }

}
