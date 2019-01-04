package com.backgroundnetworkrequest;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FetchData extends AsyncTask {
    HttpURLConnection urlConnection;
    String url;
    FetchDataCallbackInterface callbackInterface;

    public FetchData(String url, FetchDataCallbackInterface callbackInterface) {
        this.url = url;
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        this.callbackInterface.fetchDataCallback(result);
    }
}