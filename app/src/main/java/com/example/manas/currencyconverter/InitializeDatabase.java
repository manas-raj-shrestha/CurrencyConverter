package com.example.manas.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/16/2015.
 */
public class InitializeDatabase extends AsyncTask<Void, Void, Boolean> {
    private String fetch_json = "http://openexchangerates.org/api/latest.json?app_id=6d8df28bf2d04c52a75661f23065f545";
    private String fetch_json_CC = "http://openexchangerates.org/api/currencies.json";
    List<String> listOfRates = new ArrayList<>();
    String Countrycode, rate;
    Context context;
    Database_Handler db;
    SplashScreenHandler splash;

    InitializeDatabase(List<String> listOfRates, Context context, SplashScreenHandler splash) {
        this.listOfRates = listOfRates;
        this.context = context;
        this.splash = splash;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        db = new Database_Handler(context);

        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        JSONParser jParser = new JSONParser();
        JSONObject jsoni = jParser.getJSONFromUrl(fetch_json);
        JSONObject rates = null;
        int l = 1;

        try {
            rates = jsoni.getJSONObject("rates");
            l = rates.length();


            String jsonString = rates.toString();

            Image imageFromString = LoganSquare.parse(jsonString, Image.class);
            Log.e("LOGAN      !!!!!",imageFromString.NPR    +    "    rates.tostring   " + jsonString);


            for (int i = 0; i < rates.length(); i++) {

                db.CreateEntry(listOfRates.get(i), rates.getString(listOfRates.get(i)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jParser = new JSONParser();
        jsoni = jParser.getJSONFromUrl(fetch_json_CC);
        String CC;
        try {
            for (int i = 0; i < l; i++) {
                CC = jsoni.getString(listOfRates.get(i));
                db.CreateEntrydb2(listOfRates.get(i), CC);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

        db.close();
        splash.StartMainActivity();

    }


}
