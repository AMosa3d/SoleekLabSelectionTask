package com.example.abdel.soleeklabselectiontask.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdel.soleeklabselectiontask.Interfaces.HomeCountriesInterface;
import com.example.abdel.soleeklabselectiontask.Models.Country;
import com.example.abdel.soleeklabselectiontask.R;
import com.example.abdel.soleeklabselectiontask.RecyclerViewAdapters.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdel on 10/16/2018.
 */

public class HomeFragment extends Fragment {

    List<Country> countries;
    CountryAdapter adapter;
    RecyclerView countriesRecyclerView;

    final int GRID_COLUMNS_NUMBER = 3;
    final String COUNTRY_LIST_BUNDLE_KEY = "Countries";
    final String COUNTRY_RECYCLER_VIEW_POSITION_BUNDLE_KEY = "Position";

    public HomeFragment() {
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        adapter.setCountriesList(countries);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(COUNTRY_LIST_BUNDLE_KEY, (ArrayList<? extends Parcelable>) countries);
        outState.putInt(COUNTRY_RECYCLER_VIEW_POSITION_BUNDLE_KEY,countriesRecyclerView.getVerticalScrollbarPosition());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        adapter = new CountryAdapter(getContext(), (HomeCountriesInterface) getActivity());

        if (view.findViewById(R.id.country_grid_recycler_view) != null)
        {
            countriesRecyclerView = (RecyclerView) view.findViewById(R.id.country_grid_recycler_view);
            countriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),GRID_COLUMNS_NUMBER));
        }
        else
        {
            countriesRecyclerView = (RecyclerView) view.findViewById(R.id.country_list_recycler_view);
            countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        }

        countriesRecyclerView.setAdapter(adapter);

        if (savedInstanceState != null)
        {
            countries = savedInstanceState.getParcelableArrayList(COUNTRY_LIST_BUNDLE_KEY);
            adapter.setCountriesList(countries);
            countriesRecyclerView.setVerticalScrollbarPosition(
                    savedInstanceState.getInt(COUNTRY_RECYCLER_VIEW_POSITION_BUNDLE_KEY)
            );
        }

        return view;
    }
}
