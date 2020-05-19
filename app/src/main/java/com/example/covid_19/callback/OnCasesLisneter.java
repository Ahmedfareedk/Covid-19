package com.example.covid_19.callback;


public interface OnCasesLisneter<T> {
    void onResponse(T cases);
    void onError(String error);
}
