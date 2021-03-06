
package com.example.covid_19.model.statistics.worldStatistics;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WorldStatistics {

    @SerializedName("errors")
    private List<Object> mErrors;
    @SerializedName("get")
    private String mGet;
    @SerializedName("parameters")
    private WorldParameters mParameters;
    @SerializedName("response")
    private List<WorldResponse> mResponse;
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

    public WorldParameters getParameters() {
        return mParameters;
    }

    public void setParameters(WorldParameters parameters) {
        mParameters = parameters;
    }

    public List<WorldResponse> getResponse() {
        return mResponse;
    }

    public void setResponse(List<WorldResponse> response) {
        mResponse = response;
    }

    public Long getResults() {
        return mResults;
    }

    public void setResults(Long results) {
        mResults = results;
    }

}
