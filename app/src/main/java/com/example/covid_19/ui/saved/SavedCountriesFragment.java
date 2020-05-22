package com.example.covid_19.ui.saved;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.adapter.CountriesAdapter;
import com.example.covid_19.callback.OnSaveCountry;
import com.example.covid_19.model.SavedCountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedCountriesFragment extends Fragment{
    private static SavedCountriesFragment savedCountriesInstance;

   private RecyclerView savedRecyclerView;

    private  SavedCountriesViewModel viewModel;
    private SavedCountriesAdapter countriesAdapter;

    public SavedCountriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_countries, container, false);

       /* countriesAdapter = new SavedCountriesAdapter();
        savedRecyclerView = view.findViewById(R.id.saved_recycler_view);
        savedRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        savedRecyclerView.setAdapter(countriesAdapter);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(SavedCountriesViewModel.class);
        viewModel.getAllCountries().observe(getViewLifecycleOwner(), savedCountryModels -> {
            countriesAdapter.setSavedList(savedCountryModels);
            Toast.makeText(getContext(), "Say Hi to LiveData!", Toast.LENGTH_SHORT).show();

        });*/

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    public static SavedCountriesFragment getInstance() {
        if (savedCountriesInstance == null) {
            savedCountriesInstance = new SavedCountriesFragment();
        }
        return savedCountriesInstance;
    }
    public void insertSavedCountry(SavedCountryModel savedModel){
        viewModel.insert(savedModel);
    }
}
