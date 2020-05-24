package com.example.covid_19.ui.countries;


import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
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
import com.example.covid_19.model.SavedCountryModel;
import com.example.covid_19.model.statistics.worldStatistics.WorldResponse;
import com.example.covid_19.model.stats.Statistics;
import com.example.covid_19.network.Networking;
import com.example.covid_19.repos.DatabaseRepository;
import com.example.covid_19.utils.DateFormatter;
import com.example.covid_19.utils.LoadingDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mindorks.nybus.NYBus;
import com.mindorks.nybus.event.Channel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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


    @Nullable
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
    @BindView(R.id.favourite_btn)
    CheckBox saveCountryBtn;
    private CountriesAdapter countriesAdapter;

    private List<SavedCountryModel> names;

    private static CountriesStateFragment countriesFragmentInstance;

    public CountriesStateFragment() {
        // Required empty public constructor
    }

    public static CountriesStateFragment createInstance() {
        if (countriesFragmentInstance == null) {
            countriesFragmentInstance = new CountriesStateFragment();
        }
        return countriesFragmentInstance;
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
        countriesRecyclerView = view.findViewById(R.id.countries_recycler_view);
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        countriesRecyclerView.setHasFixedSize(true);





        Networking.fetchCountryStatistics(new OnCountryListener<Statistics>() {

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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem deleteAll= menu.findItem(R.id.action_delete_all);
        deleteAll.setVisible(false);

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

        String date = DateFormatter.dateFormatter(dayOfMonth, month, year);
        Networking.fetchCountryHistoryByDate(getContext(), date, bottomSheetCountryName.getText().toString(), new OnCountryListener<WorldResponse>() {
            @Override
            public void onResponse(WorldResponse response) {

                bottomSheetDayDate.setText(DateFormatter.monthName(dayOfMonth, month, year));
                bottomNewCasesValue.setText(response.getCases().getNew());
                bottomActiveCasesValue.setText(String.valueOf(response.getCases().getActive()));
                bottomCriticalCasesValue.setText(String.valueOf(response.getCases().getCritical()));
                bottomRecoveredCasesValue.setText(String.valueOf(response.getCases().getRecovered()));
                bottomTotalCasesValue.setText(String.valueOf(response.getCases().getTotal()));
                bottomNewDeathValue.setText(String.valueOf(response.getDeaths().getNew()));
                bottomTotalDeathValue.setText(String.valueOf(response.getDeaths().getTotal()));
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillViewsData(Map<String, Object> passedCountryData) {
        names = new ArrayList<>();

        //fetch data to bottom sheet items
        bottomSheetCountryName.setText(String.valueOf(passedCountryData.get(getString(R.string.country_name))));
        bottomSheetDayDate.setText(DateFormatter.formateResponsedDate(String.valueOf(passedCountryData.get(getString(R.string.day_date)))));
        bottomNewCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.new_cases_sheet))));
        bottomActiveCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.active_cases_sheet))));
        bottomCriticalCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.critical_cases_sheet))));
        bottomRecoveredCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.recovered_cases_sheet))));
        bottomTotalCasesValue.setText(String.valueOf(passedCountryData.get(getString(R.string.total_cases_sheet))));
        bottomNewDeathValue.setText(String.valueOf(passedCountryData.get(getString(R.string.new_death_sheet))));
        bottomTotalDeathValue.setText(String.valueOf(passedCountryData.get(getString(R.string.total_death_sheet))));

        Picasso.get().load(String.valueOf(passedCountryData.get(getString(R.string.flag_url_sheet)))).into(bottomSheetFlagImage);

        //run a query in a background thread to check if the country exists in the saved list
        queryIfCountrySaved();


        saveCountryBtn.setOnClickListener(v -> {
           String isSaved;

           //change save button style when saved and unsaved
            if(!saveCountryBtn.isChecked()){
                saveCountryBtn.setText("Save");
                saveCountryBtn.setTextColor(getResources().getColor(R.color.dark_save_btn));
                isSaved = "saved";

            }else{
                saveCountryBtn.setText("Saved");
                saveCountryBtn.setTextColor(getResources().getColor(R.color.favorited_stroke));
                isSaved = "unSaved";

            }

            //send to saveFragment country model object and its case to decide delete or insert
            NYBus.get().post(isSaved, Channel.TWO);
            NYBus.get().post(new SavedCountryModel(bottomSheetCountryName.getText().toString(),
                    String.valueOf(passedCountryData.get(getString(R.string.flag_url_sheet)))), Channel.ONE);
        });

    }

    private void queryIfCountrySaved(){
        new Thread(() -> {
            DatabaseRepository databaseRepository = new DatabaseRepository(CountriesStateFragment.this.getActivity().getApplication());
            names = databaseRepository.isExists(bottomSheetCountryName.getText().toString());
            getActivity().runOnUiThread(() -> {

                if (names.size() > 0) {
                    saveCountryBtn.setChecked(true);
                    saveCountryBtn.setText("Saved");
                    saveCountryBtn.setTextColor(getResources().getColor(R.color.favorited_stroke));
                }else{
                    saveCountryBtn.setChecked(false);
                    saveCountryBtn.setText("Save");
                    saveCountryBtn.setTextColor(getResources().getColor(R.color.dark_save_btn));
                }
            });
        }).start();
    }

}
