
package com.example.covid_19.model.statistics.worldStatistics;


import com.google.gson.annotations.SerializedName;


public class WorldResponse {
    @SerializedName("cases")
    private WorldCases mCases;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("day")
    private String mDay;
    @SerializedName("deaths")
    private WorldDeaths mDeaths;
    @SerializedName("tests")
    private WorldTests mTests;
    @SerializedName("time")
    private String mTime;

    public WorldCases getCases() {
        return mCases;
    }

    public void setCases(WorldCases cases) {
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

    public WorldDeaths getDeaths() {
        return mDeaths;
    }

    public void setDeaths(WorldDeaths deaths) {
        mDeaths = deaths;
    }

    public WorldTests getTests() {
        return mTests;
    }

    public void setTests(WorldTests tests) {
        mTests = tests;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

}
