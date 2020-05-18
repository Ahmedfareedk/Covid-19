package com.example.covid_19.ui.world;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.R;
import com.example.covid_19.callback.OnCasesLisneter;
import com.example.covid_19.model.statistics.worldStatistics.Response;
import com.example.covid_19.network.Networking;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldStateFragment extends Fragment {
    private TextView newCasesTV;
    private TextView activeCasesTV;
    private TextView criticalCasesTV;
    private TextView recoveredCasesTV;
    private TextView totalCasesTV;
    private TextView newDeathsTV;
    private TextView totalDeathsTV;


    public WorldStateFragment() {
        // Required empty public constructor
    }

    public static WorldStateFragment createInstance(int page, String title) {
        WorldStateFragment fragment = new WorldStateFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someText", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_world_state, container, false);

        Networking.fetch(new OnCasesLisneter() {
            @Override
            public void onResponse(Response cases) {
                newCasesTV.setText(cases.getCases().getNew());
                activeCasesTV.setText(String.valueOf(cases.getCases().getActive()));
                criticalCasesTV.setText(String.valueOf(cases.getCases().getCritical()));
                recoveredCasesTV.setText(String.valueOf(cases.getCases().getRecovered()));
                totalCasesTV.setText(String.valueOf(cases.getCases().getTotal()));
                newDeathsTV.setText(String.valueOf(cases.getDeaths().getNew()));
                totalDeathsTV.setText(String.valueOf(cases.getDeaths().getTotal()));
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
        newCasesTV = view.findViewById(R.id.new_world_text_view);
        activeCasesTV = view.findViewById(R.id.active_world_text_view);
        criticalCasesTV = view.findViewById(R.id.critical_world_text_view);
        recoveredCasesTV = view.findViewById(R.id.recovered_world_text_view);
        totalCasesTV = view.findViewById(R.id.total_world_text_view);
        newDeathsTV = view.findViewById(R.id.new_death_world_text_view);
        totalDeathsTV = view.findViewById(R.id.total_death_text_view);
        setHasOptionsMenu(true);
    }
/*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }*/
}
