package com.example.covid_19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.callback.OnCountryDataListener;
import com.example.covid_19.model.stats.Response;
import com.example.covid_19.utils.Constants;
import com.example.covid_19.utils.ParseJson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesHolder> implements Filterable {
    //@BindView(R.id.country_flag_image)
    ImageView countryFlagImage;
    //@BindView(R.id.country_label_tv)
    TextView countryLabelTv;
    private List<Response> countriesList;
    private List<Response> countriesTempList;
    private Context mContext;
    private OnCountryDataListener listener;
    private Map<String, Object> countryData;


    public CountriesAdapter(Context mContext, List<Response> countriesList, OnCountryDataListener listener) {
        this.countriesList = countriesList;
        countriesTempList = new ArrayList<>(countriesList);
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        mContext = parent.getContext();
        //ButterKnife.bind(this, view);
        return new CountriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesHolder holder, int position) {
        holder.bindViews(countriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }


    public class CountriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView countryLabel;
        private ImageView countryFlag;
        private String countryCode;


        private CountriesHolder(@NonNull View itemView) {
            super(itemView);
            countryLabel = itemView.findViewById(R.id.country_label_tv);
            countryFlag = itemView.findViewById(R.id.country_flag_image);
            countryFlag.setClipToOutline(true);
        }

        private void bindViews(final Response model) {
            countryLabel.setText(model.getCountry());
            countryCode = ParseJson.readCountryCode(mContext, model.getCountry());
            Picasso.get().load(Constants.FlagIoApi.LOAD_FLAG_URL + countryCode + Constants.FlagIoApi.FLAG_STYLE)
                    .placeholder(R.drawable.broken_image)
                    .error(R.drawable.broken_image)
                    .into(countryFlag);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            countryData = new HashMap<>();
            int position = getAdapterPosition();
            countryData.put("country_name", countriesList.get(position).getCountry());
            countryData.put("day_date", countriesList.get(position).getDay());
            countryData.put("new_cases", countriesList.get(position).getCases().getNew());
            countryData.put("active_cases", countriesList.get(position).getCases().getActive());
            countryData.put("critical_cases", countriesList.get(position).getCases().getCritical());
            countryData.put("recovered_cases", countriesList.get(position).getCases().getRecovered());
            countryData.put("total_cases", countriesList.get(position).getCases().getTotal());
            countryData.put("new_death", countriesList.get(position).getDeaths().getNew());
            countryData.put("total_death", countriesList.get(position).getDeaths().getTotal());
            countryData.put("flag_url", Constants.FlagIoApi.LOAD_FLAG_URL + countryCode + Constants.FlagIoApi.FLAG_STYLE);

            listener.onCountryItemListener(countryData);
        }
    }


    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Response> filteredCountryList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredCountryList.addAll(countriesTempList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Response filterItem : countriesTempList) {
                    if (filterItem.getCountry().toLowerCase().contains(filterPattern)) {
                        filteredCountryList.add(filterItem);
                    }
                }
            }
            FilterResults searchResult = new FilterResults();
            searchResult.values = filteredCountryList;
            return searchResult;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countriesList.clear();
            countriesList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}