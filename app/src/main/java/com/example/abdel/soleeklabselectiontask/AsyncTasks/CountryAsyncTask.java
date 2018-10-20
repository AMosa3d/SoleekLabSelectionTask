package com.example.abdel.soleeklabselectiontask.AsyncTasks;

import android.os.AsyncTask;
import android.view.View;

import com.example.abdel.soleeklabselectiontask.Interfaces.HomeCountriesInterface;
import com.example.abdel.soleeklabselectiontask.Models.Country;
import com.example.abdel.soleeklabselectiontask.Utilites.JSONParserUtils;
import com.example.abdel.soleeklabselectiontask.Utilites.NetworkUtils;

import java.net.URL;
import java.util.List;

/**
 * Created by abdel on 10/16/2018.
 */

public class CountryAsyncTask extends AsyncTask<Void,Void,List<Country>> {

    final static String COUNTRIES_API_URL = "https://restcountries.eu/rest/v2";
    final static String ALL_COUNTRIES_ID = "/all";
    final static String NETWORK_ERROR_MESSAGE = "Network Connection Error, Please Check Your Internet";

    HomeCountriesInterface mHomeCountriesInterface;

    public CountryAsyncTask(HomeCountriesInterface mHomeCountriesInterface) {
        this.mHomeCountriesInterface = mHomeCountriesInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mHomeCountriesInterface.handleProgressBar(View.VISIBLE);
    }

    @Override
    protected List<Country> doInBackground(Void... params) {
        URL url = NetworkUtils.BuildURL(COUNTRIES_API_URL + ALL_COUNTRIES_ID);

        try {
            String countriesJSONString = NetworkUtils.RetrieveJSONString(url);

            List<Country> countries = JSONParserUtils.parseCountriesJSON(countriesJSONString);

            return countries;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Country> countries) {
        mHomeCountriesInterface.handleProgressBar(View.INVISIBLE);

        if (countries == null)
            mHomeCountriesInterface.toastErrorMessage(NETWORK_ERROR_MESSAGE);
        else
            mHomeCountriesInterface.sendCountriesData(countries);
    }

}
