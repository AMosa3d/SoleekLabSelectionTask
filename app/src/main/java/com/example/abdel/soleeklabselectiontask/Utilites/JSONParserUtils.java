package com.example.abdel.soleeklabselectiontask.Utilites;

import com.example.abdel.soleeklabselectiontask.Models.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdel on 10/16/2018.
 */

public final class JSONParserUtils {

    public static List<Country> parseCountriesJSON(String unParsedJSON) throws JSONException {
        final String NAME_ID = "name";
        final String FLAG_URL_ID = "flag";
        final String REGION_ID = "subregion";
        final String CAPITAL_ID = "capital";

        List<Country> countries = new ArrayList<>();

        JSONArray countriesArray = new JSONArray(unParsedJSON);
        int countriesSize = countriesArray.length();

        for (int i = 0;i < countriesSize;i++)
        {
            JSONObject currentCountry = countriesArray.getJSONObject(i);
            String currentCountryName,currentCountryFlagUrl,currentCountryRegion,currentCountryCapital;

            currentCountryName = currentCountry.getString(NAME_ID);
            currentCountryFlagUrl = currentCountry.getString(FLAG_URL_ID);
            currentCountryRegion = currentCountry.getString(REGION_ID);
            currentCountryCapital = currentCountry.getString(CAPITAL_ID);

            countries.add(
                    new Country(
                            currentCountryName,
                            currentCountryFlagUrl,
                            currentCountryRegion,
                            currentCountryCapital
                    )
            );
        }

        return countries;
    }
}
