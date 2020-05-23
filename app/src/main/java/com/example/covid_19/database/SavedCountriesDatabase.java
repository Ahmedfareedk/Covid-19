package com.example.covid_19.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.covid_19.model.SavedCountryModel;

@Database(entities = {SavedCountryModel.class}, version = 2, exportSchema = false)
public abstract class SavedCountriesDatabase extends RoomDatabase {

    private static  SavedCountriesDatabase countriesDatabaseInstance;
    public abstract CountriesDao savedCountriesDao();

    public static synchronized SavedCountriesDatabase getInstance(Context context){

        if(countriesDatabaseInstance == null){

            countriesDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), SavedCountriesDatabase.class, "countries_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return countriesDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new DefaultAsyncTask(countriesDatabaseInstance).execute();
            super.onCreate(db);
        }
    };

    private static class DefaultAsyncTask extends AsyncTask<Void, Void, Void>{
        private CountriesDao countriesDao;

        public DefaultAsyncTask(SavedCountriesDatabase db) {
            countriesDao = db.savedCountriesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countriesDao.insertCountry(new SavedCountryModel("Egypt",
                    "https://www.countryflags.io/eg/shiny/64.png"));
            return null;
        }
    }
}
