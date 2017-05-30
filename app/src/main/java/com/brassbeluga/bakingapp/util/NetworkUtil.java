package com.brassbeluga.bakingapp.util;


import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();
    private static final int REQUEST_TIMEOUT_MILLIS = 5000;

    /**
     * Builds the URL
     */
    public static URL buildUrl(String baseUrl, String... params) {
        Uri.Builder uriBuilder = Uri.parse(baseUrl).buildUpon();

        for (int i = 0; i < params.length - 1; i+=2) {
            uriBuilder.appendQueryParameter(params[i],params[i+1]);
        }
        Uri builtUri = uriBuilder.build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     */
    public static String getResponseFromHttpUrl(URL url) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            // given a url open a connection
            URLConnection c = url.openConnection();

            // set the connection timeout.
            c.setConnectTimeout(REQUEST_TIMEOUT_MILLIS);

            // get a stream to read data from
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (IOException ex) {
            Log.e(TAG, "Unable to http response due to a network issue", ex);
            return null;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}