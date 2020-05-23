package com.example.covid_19.repos;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.covid_19.database.CountriesDao;
import com.example.covid_19.database.SavedCountriesDatabase;
import com.example.covid_19.model.SavedCountryModel;

import java.util.List;

public class DatabaseRepository {
    private static  CountriesDao countriesDao;
    private LiveData<List<SavedCountryModel>> allSavedCountries;
    private SavedCountryModel model;

    private static List<SavedCountryModel> names;


    public DatabaseRepository(Application application) {
     //   model = new SavedCountryModel(null, null);
        SavedCountriesDatabase database = SavedCountriesDatabase.getInstance(application);
        countriesDao = database.savedCountriesDao();
        allSavedCountries = countriesDao.getAllSavedCountries();
       // names = countriesDao.isExists(model.getSavedCountryName());
    }

    public void insert(SavedCountryModel country){
        new InsertCountryAsynctask(countriesDao).execute(country);

    }
    public void delete(SavedCountryModel country){
        new DeleteCountryAsynctask(countriesDao).execute(country);
    }

    public void deleAll(){
        new DeleteAllCountryAsynctask(countriesDao).execute();
    }

    public List<SavedCountryModel> isExists(String name){
        return countriesDao.isExists(name);
    }

    public LiveData<List<SavedCountryModel>> getAllCountries(){
        return allSavedCountries;
    }

    private static class InsertCountryAsynctask extends AsyncTask<SavedCountryModel, Void, Void>{

        private CountriesDao countriesDao;

        public InsertCountryAsynctask(CountriesDao countriesDao) {
            this.countriesDao = countriesDao;
        }

        @Override
        protected Void doInBackground(SavedCountryModel... savedCountryModels) {
            countriesDao.insertCountry(savedCountryModels[0]);
            return null;
        }
    }
    private static class DeleteCountryAsynctask extends AsyncTask<SavedCountryModel, Void, Void>{

        private CountriesDao countriesDao;

        public DeleteCountryAsynctask(CountriesDao countriesDao) {
            this.countriesDao = countriesDao;
        }

        @Override
        protected Void doInBackground(SavedCountryModel... savedCountryModels) {
            countriesDao.deleteCountry(savedCountryModels[0]);
            return null;
        }
    }
       private static class DeleteAllCountryAsynctask extends AsyncTask<Void, Void, Void>{

        private CountriesDao countriesDao;

        public DeleteAllCountryAsynctask(CountriesDao countriesDao) {
            this.countriesDao = countriesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countriesDao.deleteAll();
            return null;
        }
    }

    private static class CheckIfExists extends AsyncTask<List<SavedCountryModel>, Void, String> {
        private CountriesDao countriesDao;


        public CheckIfExists(CountriesDao countriesDao){
            this.countriesDao =countriesDao;
        }
        @Override
        protected String doInBackground(List<SavedCountryModel>... lists) {
            countriesDao.isExists(lists[0].get(0).getSavedCountryName());
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

   /* private static class CheckIfExists extends AsyncTask<String, Void, String>{
        private CountriesDao countriesDao;

        public CheckIfExists(CountriesDao countriesDao){
            this.countriesDao = countriesDao;
        }

        @Override
        protected String doInBackground(String... strings) {
            countriesDao.isExists(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }*/
}
