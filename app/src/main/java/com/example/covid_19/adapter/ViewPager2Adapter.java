package com.example.covid_19.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.covid_19.ui.countries.CountriesStateFragment;
import com.example.covid_19.ui.saved.SavedCountriesFragment;
import com.example.covid_19.ui.world.WorldStateFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private static final int VIEW_ITEM_SIZE = 3;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position)
        {
            case 0:
                fragment = WorldStateFragment.createInstance();
                break;
            case 1:
                fragment =  CountriesStateFragment.createInstance();
                break;
            case 2:
                fragment =SavedCountriesFragment.createInstance();
                break;
            default:
                return null;
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return VIEW_ITEM_SIZE;
    }
}
