package com.example.abdel.soleeklabselectiontask.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abdel on 10/16/2018.
 */

public class Country implements Parcelable {

    private String name;
    private String flagUrl;
    private String region;
    private String capital;

    public Country(String name, String flagUrl, String region, String capital) {
        this.name = name;
        this.flagUrl = flagUrl;
        this.region = region;
        this.capital = capital;
    }

    public Country(Parcel parcel)
    {
        name = parcel.readString();
        flagUrl = parcel.readString();
        region = parcel.readString();
        capital = parcel.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    //Note: no need to setters since the list of countries will not be updated and will
    //be filled only in constructing each element

    public String getName() {
        return name;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public String getRegion() {
        return region;
    }

    public String getCapital() {
        return capital;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(flagUrl);
        dest.writeString(region);
        dest.writeString(capital);
    }
}
