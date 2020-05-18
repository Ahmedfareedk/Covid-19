
package com.example.covid_19.model.statistics.worldStatistics;


import com.google.gson.annotations.SerializedName;


public class WorldDeaths {

    @SerializedName("new")
    private String mNew;
    @SerializedName("total")
    private Long mTotal;

    public String getNew() {
        return mNew;
    }

    public void setNew(String newDeaths) {
        mNew = newDeaths;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
