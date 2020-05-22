package com.example.covid_19.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.covid_19.R;
import com.example.covid_19.adapter.ViewPagerAdapter;
import com.example.covid_19.callback.OnSaveCountry;
import com.example.covid_19.model.SavedCountryModel;
import com.example.covid_19.ui.countries.CountriesStateFragment;
import com.example.covid_19.ui.saved.SavedCountriesFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity /*implements OnSaveCountry */{

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private CountriesStateFragment countriesStateFragment;
    private SavedCountriesFragment savedCountriesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager =  findViewById(R.id.view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);
        tabLayout.setupWithViewPager(mViewPager);
        setSupportActionBar(toolbar);

     //   savedCountriesFragment = SavedCountriesFragment.getInstance();
    }


  /*  @Override
    public void onSavedDateReponse(SavedCountryModel savedModel) {
        savedCountriesFragment.insertSavedCountry(savedModel);
    }*/
}
