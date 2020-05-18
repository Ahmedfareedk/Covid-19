
package com.example.covid_19.model.stats;


import com.google.gson.annotations.SerializedName;

public class Tests {

    @SerializedName("total")
    private Object mTotal;

    public Object getTotal() {
        return mTotal;
    }

    public void setTotal(Object total) {
        mTotal = total;
    }

}
