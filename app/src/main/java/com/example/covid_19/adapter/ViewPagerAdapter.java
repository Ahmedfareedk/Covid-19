package com.example.covid_19.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covid_19.ui.countries.CountriesStateFragment;
import com.example.covid_19.ui.saved.SavedCountriesFragment;
import com.example.covid_19.ui.world.WorldStateFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;

    public ViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position)
        {
            case 0:
                fragment = new WorldStateFragment();
                break;
            case 1:
                fragment = new CountriesStateFragment();
                break;
            case 2:
                fragment = new SavedCountriesFragment();
                break;
                default:
                    return null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "World";
            case 1:
                return "Countries";
            case 2:
                return "Saved";
                default:
                    return null;

        }

    }
}
