package com.example.covid_19.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.covid_19.model.SavedCountryModel;

import java.util.List;

@Dao
public interface CountriesDao {

    @Insert
    void insertCountry(SavedCountryModel country);

    @Delete
    void deleteCountry(SavedCountryModel country);


    @Query("DELETE FROM saved_countries")
    void deleteAll();


    @Query("SELECT * FROM saved_countries")
    LiveData<List<SavedCountryModel>> getAllSavedCountries();
}