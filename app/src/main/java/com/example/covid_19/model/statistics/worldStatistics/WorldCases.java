
package com.example.covid_19.model.statistics.worldStatistics;


import com.google.gson.annotations.SerializedName;


public class WorldCases {

    @SerializedName("active")
    private Long mActive;
    @SerializedName("critical")
    private Long mCritical;
    @SerializedName("new")
    private String mNew;
    @SerializedName("recovered")
    private Long mRecovered;
    @SerializedName("total")
    private Long mTotal;

    public Long getActive() {
        return mActive;
    }

    public void setActive(Long active) {
        mActive = active;
    }

    public Long getCritical() {
        return mCritical;
    }

    public void setCritical(Long critical) {
        mCritical = critical;
    }

    public String getNew() {
        return mNew;
    }

    public void setNew(String newCases) {
        mNew = newCases;
    }

    public Long getRecovered() {
        return mRecovered;
    }

    public void setRecovered(Long recovered) {
        mRecovered = recovered;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
