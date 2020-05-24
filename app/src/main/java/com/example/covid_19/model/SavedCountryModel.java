package com.example.covid_19.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_countries", indices = @Index(value = {"country_name"}, unique = true))
public class SavedCountryModel {
    @ColumnInfo(name = "country_id")
    @PrimaryKey(autoGenerate = true)
    private int savedCountryId;

    @ColumnInfo(name = "country_name")
    private String savedCountryName;

    @ColumnInfo(name = "flag_url")
    private String savedCountryFlagUrl;

    public SavedCountryModel(String savedCountryName, String savedCountryFlagUrl) {

        this.savedCountryName = savedCountryName;
        this.savedCountryFlagUrl = savedCountryFlagUrl;
    }

    public String getSavedCountryName() {
        return savedCountryName;
    }

    public void setSavedCountryName(String savedCountryName) {
        this.savedCountryName = savedCountryName;
    }

    public String getSavedCountryFlagUrl() {
        return savedCountryFlagUrl;
    }

    public void setSavedCountryFlagUrl(String savedCountryFlagUrl) {
        this.savedCountryFlagUrl = savedCountryFlagUrl;
    }


    public int getSavedCountryId() {
        return savedCountryId;
    }

    public void setSavedCountryId(int savedCountryId) {
        this.savedCountryId = savedCountryId;
    }


}
