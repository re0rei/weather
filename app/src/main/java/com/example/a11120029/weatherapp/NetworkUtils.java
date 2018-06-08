package com.example.a11120029.weatherapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by 11120029 on 2018-05-17.
 */

public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    private final static String WEATHERDB_BASE_URL=
            "http://dataservice.accuweather.com/forecasts/v1/daily/5day/222888";

    private final static String API_KEY = "7JIZYKGzExMkwg5azxJrJQm9PcEfeNxJ";

    private final static String METRIC_VALUE = "true";

    private final static String PARAM_API_KEY = "apikey";

    private final static String PARAM_METRIC = "metric";

    final static URL buildUrlForWeather(){
        Uri buildUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter(PARAM_METRIC, METRIC_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "buildUrlForWeather: uri: "+url);
        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            } else {
                return  null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
