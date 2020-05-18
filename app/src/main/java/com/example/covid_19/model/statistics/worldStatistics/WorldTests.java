
package com.example.covid_19.model.statistics.worldStatistics;

import com.google.gson.annotations.SerializedName;

public class WorldTests {

    @SerializedName("total")
    private Object mTotal;

    public Object getTotal() {
        return mTotal;
    }

    public void setTotal(Object total) {
        mTotal = total;
    }

}
