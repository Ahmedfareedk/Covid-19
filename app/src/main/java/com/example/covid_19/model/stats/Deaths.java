
package com.example.covid_19.model.stats;

import com.google.gson.annotations.SerializedName;


public class Deaths {

    @SerializedName("new")
    private Object mNew;
    @SerializedName("total")
    private Long mTotal;

    public Object getNew() {
        return mNew;
    }

    public void setNew(Object newDeaths) {
        mNew = newDeaths;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
