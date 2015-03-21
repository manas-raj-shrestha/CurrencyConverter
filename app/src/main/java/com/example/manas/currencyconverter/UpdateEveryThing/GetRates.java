package com.example.manas.currencyconverter.UpdateEveryThing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.manas.currencyconverter.Database_Handler;
import com.example.manas.currencyconverter.InfoClasses.CountryInfoClass;
import com.example.manas.currencyconverter.InfoClasses.RatesInfoClass;
import com.example.manas.currencyconverter.Interfaces.API;
import com.example.manas.currencyconverter.JSONParser;
import com.example.manas.currencyconverter.NewMainActivity;
import com.example.manas.currencyconverter.NewSplashScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Manas on 3/20/2015.
 */

public class GetRates extends AsyncTask<Void, Void, Boolean> {

int l;
    private String fetch_json = "http://openexchangerates.org/api/latest.json?app_id=6d8df28bf2d04c52a75661f23065f545";
    private String fetch_json_CC = "http://openexchangerates.org/api/currencies.json";
    JSONParser jParser;
    JSONObject jsoni;
    Context context;
    List<String> list;
    Database_Handler db;
NewSplashScreen nmain;
    public GetRates(Context context,NewSplashScreen nmain, List<String> list) {
        this.list = list;
        this.context = context;
        db = new Database_Handler(context);
        this.nmain = nmain;    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        jParser = new JSONParser();
        jsoni = jParser.getJSONFromUrl(fetch_json);
        JSONObject rates = null;

        db.DeleteFromTable("currency_offline_table");


        try {


            rates = jsoni.getJSONObject("rates");
            l = rates.length();

            for (int i = 0; i < rates.length(); i++) {

                db.CreateEntry(list.get(i), rates.getString(list.get(i)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        db.close();
 nmain.startNewMainActivity();


    }
}