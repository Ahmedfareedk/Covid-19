
package com.example.covid_19.model.history_stats;
import com.google.gson.annotations.SerializedName;

public class HistoryDeaths {

    @SerializedName("new")
    private String mNew;
    @SerializedName("total")
    private Long mTotal;

    public String getNew() {
        return mNew;
    }

    public void setNew(String newDeath) {
        mNew = newDeath;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
