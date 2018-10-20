package com.example.abdel.soleeklabselectiontask.RecyclerViewAdapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.abdel.soleeklabselectiontask.Interfaces.HomeCountriesInterface;
import com.example.abdel.soleeklabselectiontask.Models.Country;
import com.example.abdel.soleeklabselectiontask.R;

import java.util.List;

/**
 * Created by abdel on 10/16/2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    Context context;
    List<Country> countries;
    HomeCountriesInterface mHomeCountriesInterface;

    public CountryAdapter(Context context, HomeCountriesInterface mHomeCountriesInterface) {
        this.context = context;
        this.mHomeCountriesInterface = mHomeCountriesInterface;
    }


    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.country_list_item,parent,false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.bind(countries.get(position).getName(),countries.get(position).getFlagUrl());
    }

    @Override
    public int getItemCount() {
        if (countries == null)
            return 0;
        return countries.size();
    }

    public void setCountriesList(List<Country> countriesList) {
        this.countries = countriesList;
        notifyDataSetChanged();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView flagImageView;
        TextView countryName;

        public CountryViewHolder(View itemView) {
            super(itemView);
            flagImageView = (ImageView) itemView.findViewById(R.id.flag_item_imageView);
            countryName = (TextView) itemView.findViewById(R.id.name_item_textView);
            itemView.setOnClickListener(this);
        }

        void bind(String name, String flagUrl)
        {
            //AndroidSvgLoader is a simple open source library for loading SVGs from internet
            //Here is the link if you want to check it out
            //Github : https://github.com/ar-android/AndroidSvgLoader
            SvgLoader.pluck()
                    .with((Activity) context).setPlaceHolder(R.mipmap.ic_launcher,R.mipmap.ic_launcher)
                    .load(flagUrl,flagImageView);

            countryName.setText(name);
        }

        @Override
        public void onClick(View v) {
            mHomeCountriesInterface.onCountryClick(countries.get(getAdapterPosition()));
        }
    }
}
