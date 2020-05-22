package com.example.covid_19.ui.saved;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid_19.model.SavedCountryModel;
import com.example.covid_19.repos.DatabaseRepository;

import java.util.List;

public class SavedCountriesViewModel extends AndroidViewModel {

    private DatabaseRepository repository;
    private LiveData<List<SavedCountryModel>> allCountries;
    public SavedCountriesViewModel(@NonNull Application application) {
        super(application);
        repository = new DatabaseRepository(application);
        allCountries = repository.getAllCountries();
    }

    public void insert(SavedCountryModel country){
        repository.insert(country);
    }

    public void delete(SavedCountryModel country){
        repository.delete(country);
    }

    public void deleteAll(){
        repository.deleAll();
    }

    public LiveData<List<SavedCountryModel>> getAllCountries(){
        return allCountries;
    }

}
