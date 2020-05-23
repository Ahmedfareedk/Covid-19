package com.example.covid_19.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.model.SavedCountryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SavedCountriesAdapter extends RecyclerView.Adapter<SavedCountriesAdapter.SavedCountriesHolder> {

    private List<SavedCountryModel> savedList = new ArrayList<>();

    public void setSavedList(List<SavedCountryModel> savedList){
        this.savedList = savedList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SavedCountriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_country_item, parent, false);
       // ButterKnife.bind(this, view);
        return new SavedCountriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedCountriesHolder holder, int position) {
        holder.bindViews(savedList.get(position));
    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class SavedCountriesHolder extends RecyclerView.ViewHolder {
        private ImageView savedCountryFlagImage;
        private TextView savedCountryLabel;

        private SavedCountriesHolder(@NonNull View itemView) {
            super(itemView);
            savedCountryLabel = itemView.findViewById(R.id.saved_country_label_tv);
            savedCountryFlagImage = itemView.findViewById(R.id.saved_country_flag_image);
        }
        private void bindViews(SavedCountryModel model){
            savedCountryLabel.setText(model.getSavedCountryName());
           Picasso.get().load(model.getSavedCountryFlagUrl()).into(savedCountryFlagImage);
        }
    }
}