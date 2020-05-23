package com.example.covid_19.ui.saved;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.adapter.SavedCountriesAdapter;
import com.example.covid_19.model.SavedCountryModel;
import com.mindorks.nybus.NYBus;
import com.mindorks.nybus.annotation.Subscribe;
import com.mindorks.nybus.event.Channel;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedCountriesFragment extends Fragment{


   private RecyclerView savedRecyclerView;

    private  SavedCountriesViewModel viewModel;
    private SavedCountriesAdapter countriesAdapter;
    private static SavedCountriesFragment instance;
    private String saved;


    public SavedCountriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_countries, container, false);

        countriesAdapter = new SavedCountriesAdapter();
        savedRecyclerView = view.findViewById(R.id.saved_recycler_view);
        savedRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        savedRecyclerView.setHasFixedSize(true);
        savedRecyclerView.setAdapter(countriesAdapter);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(SavedCountriesViewModel.class);
        viewModel.getAllCountries().observe(getViewLifecycleOwner(), savedCountryModels -> {
            countriesAdapter.setSavedList(savedCountryModels);
            countriesAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "Say Hi to LiveData!", Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    public static SavedCountriesFragment createInstance() {
        if(instance == null)
     instance = new SavedCountriesFragment();
        return instance;
    }

    @Override
    public void onStart() {
        super.onStart();
        NYBus.get().register(this, Channel.ONE, Channel.TWO);
    }

    @Override
    public void onStop() {
        super.onStop();
        NYBus.get().unregister(this, Channel.ONE, Channel.TWO);
    }

    @Subscribe(channelId = Channel.ONE )
    public void insertSavedCountry(SavedCountryModel savedModel) {
        if (saved.equals("unSaved")) {
            viewModel.insert(savedModel);
            Toast.makeText(getActivity(), "Saved to your favorite List", Toast.LENGTH_LONG).show();
        } else if(saved.equals("saved")) {
            viewModel.delete(savedModel);
            Toast.makeText(getActivity(), "Removed from your favorite List", Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe(channelId = Channel.TWO)
    public void listenToIsSaved(String isSaved){
        saved = isSaved;
    }


}
