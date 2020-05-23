package com.example.covid_19.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.covid_19.R;
import com.example.covid_19.adapter.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_2);
        tabLayout = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        viewPager2.setAdapter( new ViewPager2Adapter(this));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position)
            {
                case 0:
                    tab.setText(getResources().getString(R.string.world_tab));
                    break;
                case 1:
                    tab.setText(getResources().getString(R.string.countries_tab));
                    break;
                case 2:
                    tab.setText(getResources().getString(R.string.saved_tab));
                    break;

            }
        }).attach();
     }

}
