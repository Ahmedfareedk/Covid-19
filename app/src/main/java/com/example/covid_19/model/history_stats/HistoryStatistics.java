
package com.example.covid_19.model.history_stats;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class HistoryStatistics {

    @SerializedName("errors")
    private List<Object> mErrors;
    @SerializedName("get")
    private String mGet;
    @SerializedName("parameters")
    private HistoryParameters mParameters;
    @SerializedName("response")
    private List<HistoryResponse> mResponse;
    @SerializedName("results")
    private Long mResults;

    public List<Object> getErrors() {
        return mErrors;
    }

    public void setErrors(List<Object> errors) {
        mErrors = errors;
    }

    public String getGet() {
        return mGet;
    }

    public void setGet(String get) {
        mGet = get;
    }

    public HistoryParameters getParameters() {
        return mParameters;
    }

    public void setParameters(HistoryParameters parameters) {
        mParameters = parameters;
    }

    public List<HistoryResponse> getResponse() {
        return mResponse;
    }

    public void setResponse(List<HistoryResponse> response) {
        mResponse = response;
    }

    public Long getResults() {
        return mResults;
    }

    public void setResults(Long results) {
        mResults = results;
    }

}
