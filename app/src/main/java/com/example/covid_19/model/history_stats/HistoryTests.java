
package com.example.covid_19.model.history_stats;
import com.google.gson.annotations.SerializedName;


public class HistoryTests {
    @SerializedName("total")
    private Long mTotal;

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
