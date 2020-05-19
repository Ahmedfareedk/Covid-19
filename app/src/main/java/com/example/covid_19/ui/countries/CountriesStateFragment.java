package com.example.covid_19.ui.countries;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.adapter.CountriesAdapter;
import com.example.covid_19.callback.OnCountryListener;
import com.example.covid_19.model.stats.Statistics;
import com.example.covid_19.network.Networking;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesStateFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    @Nullable
    @BindView(R.id.countries_recycler_view)
    RecyclerView countriesRecyclerView;


    @BindView(R.id.close_dialog)
    ImageView closeDialog;
    @BindView(R.id.bottom_sheet_flag_image)
    ImageView bottomSheetFlagImage;
    @BindView(R.id.bottom_sheet_country_name)
    TextView bottomSheetCountryName;
    @BindView(R.id.bottom_sheet_day_date)
    TextView bottomSheetDayDate;
    @BindView(R.id.bottom_sheet_date_picker)
    ImageView bottomSheetDatePicker;


    @BindView(R.id.bottom_new_cases_value)
    TextView bottomNewCasesValue;


    @BindView(R.id.bottom_active_cases_value)
    TextView bottomActiveCasesValue;

    @BindView(R.id.bottom_critical_cases_value)
    TextView bottomCriticalCasesValue;

    @BindView(R.id.bottom_recovered_cases_value)
    TextView bottomRecoveredCasesValue;
    @BindView(R.id.bottom_total_cases_value)
    TextView bottomTotalCasesValue;
    @BindView(R.id.bottom_new_death_value)
    TextView bottomNewDeathValue;
    @BindView(R.id.bottom_total_death_value)
    TextView bottomTotalDeathValue;
    private CountriesAdapter countriesAdapter;



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
        ButterKnife.bind(getActivity(), view);

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
        ButterKnife.bind(this, bottomSheetView);

        //fill views data we get from callback
        fillViewsData(countryData);

        closeDialog.setOnClickListener(v -> countryDetailsDialog.dismiss());
        bottomSheetDatePicker.setOnClickListener(v -> buildDatePickerDialog());
        countryDetailsDialog.setContentView(bottomSheetView);
        countryDetailsDialog.setCancelable(false);
        countryDetailsDialog.setDismissWithAnimation(true);
        countryDetailsDialog.setCanceledOnTouchOutside(false);
        countryDetailsDialog.show();
    }

    private void buildDatePickerDialog() {
        DatePickerDialog stateDatePicker = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        stateDatePicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        bottomSheetDayDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
    }

    private void fillViewsData(Map<String, Object> passedCountryData){
        bottomSheetCountryName.setText(String.valueOf(passedCountryData.get(getString(R.string.country_name))));
        bottomSheetDayDate.setText(String.valueOf(passedCountryData.get(getString(R.string.day_date))));
        bottomNewCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.new_cases_sheet))));
        bottomActiveCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.active_cases_sheet))));
        bottomCriticalCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.critical_cases_sheet))));
        bottomRecoveredCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.recovered_cases_sheet))));
        bottomTotalCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.total_cases_sheet))));
        bottomNewDeathValue.setText(String.valueOf(passedCountryData.get(getString(R.string.new_death_sheet))));
        bottomTotalDeathValue.setText(String.valueOf(passedCountryData.get(getString(R.string.total_death_sheet))));

        Picasso.get().load(String.valueOf(passedCountryData.get(getString(R.string.flag_url_sheet)))).into(bottomSheetFlagImage);
    }
}
