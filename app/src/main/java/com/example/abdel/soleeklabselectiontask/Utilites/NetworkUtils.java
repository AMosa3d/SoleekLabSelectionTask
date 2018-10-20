package com.example.abdel.soleeklabselectiontask.Utilites;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by abdel on 10/16/2018.
 */

public final class NetworkUtils {

    public static URL BuildURL(final String API_URL)
    {
        Uri uri = Uri.parse(API_URL).buildUpon().build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String RetrieveJSONString(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        final String REQUEST_METHOD = "GET";

        urlConnection.setRequestMethod(REQUEST_METHOD);

        try {
            InputStream stream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext())
                return scanner.next();
            return null;
        }
        finally {
            urlConnection.disconnect();
        }
    }
}
