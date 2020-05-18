package com.example.covid_19.ui.countries;


import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.R;
import com.example.covid_19.adapter.CountriesAdapter;
import com.example.covid_19.callback.OnCountryDataListener;
import com.example.covid_19.callback.OnCountryListener;

import com.example.covid_19.model.stats.Statistics;
import com.example.covid_19.network.Networking;
import com.example.covid_19.utils.GridAutoFitLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesStateFragment extends Fragment {
    private RecyclerView countriesRecyclerView;
    private CountriesAdapter countriesAdapter;
    private TextView countryName, dayDate, countryNewCases,
            countryActiveCases, countryCriticalCases, countryRecoveredCases,
            countryTotalCases, countryNewDeath, countryTotalDeath;
    private ImageView countryFlag;
    private ImageView closeBottomSheetImage;


    public CountriesStateFragment() {
        // Required empty public constructor
    }

    public static CountriesStateFragment createInstance(int page, String title) {
        CountriesStateFragment fragment = new CountriesStateFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_countries_state, container, false);

        Networking.fetchCountry(new OnCountryListener() {

            @Override
            public void onResponse(Statistics response) {
                countriesAdapter = new CountriesAdapter(getActivity(), response.getResponse(), countryData -> buildBottomSheetView(countryData, container));
                countriesRecyclerView.setAdapter(countriesAdapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countriesRecyclerView = view.findViewById(R.id.countries_recycler_view);
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countriesAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void buildBottomSheetView(Map<String, Object> countryData, ViewGroup container) {
        BottomSheetDialog countryDetailsDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getContext())
                .inflate(R.layout.bottom_sheet_dialog, container, false);

        countryName = bottomSheetView.findViewById(R.id.bottom_sheet_country_name);
        dayDate = bottomSheetView.findViewById(R.id.bottom_sheet_day_date);
        countryNewCases = bottomSheetView.findViewById(R.id.bottom_new_cases_value);
        countryActiveCases = bottomSheetView.findViewById(R.id.bottom_active_cases_value);
        countryCriticalCases = bottomSheetView.findViewById(R.id.bottom_critical_cases_value);
        countryRecoveredCases = bottomSheetView.findViewById(R.id.bottom_recovered_cases_value);
        countryTotalCases = bottomSheetView.findViewById(R.id.bottom_total_cases_value);
        countryNewDeath = bottomSheetView.findViewById(R.id.bottom_new_death_value);
        countryTotalDeath = bottomSheetView.findViewById(R.id.bottom_total_death_value);
        countryFlag = bottomSheetView.findViewById(R.id.bottom_sheet_flag_image);
        closeBottomSheetImage = bottomSheetView.findViewById(R.id.close_dialog);


        countryName.setText(String.valueOf(countryData.get(getString(R.string.country_name))));
        dayDate.setText(String.valueOf(countryData.get(getString(R.string.day_date))));
        countryNewCases.setText(String.valueOf(countryData.get(getString(R.string.new_cases_sheet))));
        countryActiveCases.setText(String.valueOf(countryData.get(getString(R.string.active_cases_sheet))));
        countryCriticalCases.setText(String.valueOf(countryData.get(getString(R.string.critical_cases_sheet))));
        countryRecoveredCases.setText(String.valueOf(countryData.get(getString(R.string.recovered_cases_sheet))));
        countryTotalCases.setText(String.valueOf(countryData.get(getString(R.string.total_cases_sheet))));
        countryNewDeath.setText(String.valueOf(countryData.get(getString(R.string.new_death_sheet))));
        countryTotalDeath.setText(String.valueOf(countryData.get(getString(R.string.total_death_sheet))));

        Picasso.get().load(String.valueOf(countryData.get(getString(R.string.flag_url_sheet)))).into(countryFlag);

        closeBottomSheetImage.setOnClickListener(v -> countryDetailsDialog.dismiss());
        countryDetailsDialog.setContentView(bottomSheetView);
        countryDetailsDialog.setCancelable(false);
        countryDetailsDialog.setDismissWithAnimation(true);
        countryDetailsDialog.setCanceledOnTouchOutside(false);
        countryDetailsDialog.show();
    }

}
