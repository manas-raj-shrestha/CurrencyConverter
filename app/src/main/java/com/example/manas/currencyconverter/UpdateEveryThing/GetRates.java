package com.example.manas.currencyconverter.UpdateEveryThing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.manas.currencyconverter.Database_Handler;
import com.example.manas.currencyconverter.InfoClasses.CountryInfoClass;
import com.example.manas.currencyconverter.InfoClasses.RatesInfoClass;
import com.example.manas.currencyconverter.Interfaces.API;

import java.sql.SQLException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Manas on 3/20/2015.
 */
public class GetRates extends Activity{
    private Context context;
    private String fetch_json = "http://openexchangerates.org/api";
    public GetRates(Context context){
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
    }


    public void requestData() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(fetch_json)
                .build();
        API api = adapter.create(API.class);

        api.getRates(new Callback<RatesInfoClass>() {


            @Override
            public void success(RatesInfoClass ratesInfoClass, Response response) {




                Log.e("Rates DATA", "Rates " +ratesInfoClass.base + " NPR " + ratesInfoClass.rates.NPR);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error", error.toString());
            }
        });

    }
}
