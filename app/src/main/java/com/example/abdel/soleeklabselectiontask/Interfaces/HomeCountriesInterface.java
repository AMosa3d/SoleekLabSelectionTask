package com.example.abdel.soleeklabselectiontask.Interfaces;

import com.example.abdel.soleeklabselectiontask.Models.Country;

import java.util.List;

/**
 * Created by abdel on 10/16/2018.
 */

public interface HomeCountriesInterface {
    void sendCountriesData(List<Country> countries);
    void handleProgressBar(int visibility);
    void toastErrorMessage(final String ERROR_MESSAGE);
    void onCountryClick(Country country);
}
