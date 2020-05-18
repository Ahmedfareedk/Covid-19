
package com.example.covid_19.model.statistics.worldStatistics;


import com.google.gson.annotations.SerializedName;


public class WorldParameters {

    @SerializedName("country")
    private String mCountry;

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

}
