
package com.example.covid_19.model.history_stats;


import com.google.gson.annotations.SerializedName;


public class HistoryParameters {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("day")
    private String mDay;

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

}
