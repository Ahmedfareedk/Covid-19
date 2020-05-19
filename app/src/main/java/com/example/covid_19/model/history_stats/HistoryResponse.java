
package com.example.covid_19.model.history_stats;
import com.google.gson.annotations.SerializedName;


public class HistoryResponse {

    @SerializedName("cases")
    private HistoryCases mCases;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("day")
    private String mDay;
    @SerializedName("deaths")
    private HistoryDeaths mDeaths;
    @SerializedName("tests")
    private HistoryTests mTests;
    @SerializedName("time")
    private String mTime;

    public HistoryCases getCases() {
        return mCases;
    }

    public void setCases(HistoryCases cases) {
        mCases = cases;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public HistoryDeaths getDeaths() {
        return mDeaths;
    }

    public void setDeaths(HistoryDeaths deaths) {
        mDeaths = deaths;
    }

    public HistoryTests getTests() {
        return mTests;
    }

    public void setTests(HistoryTests tests) {
        mTests = tests;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

}
