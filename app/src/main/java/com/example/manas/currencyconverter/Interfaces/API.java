package com.example.manas.currencyconverter.Interfaces;

import com.example.manas.currencyconverter.InfoClasses.CountryInfoClass;
import com.example.manas.currencyconverter.InfoClasses.RatesInfoClass;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Manas on 3/20/2015.
 */
public interface API {
    @GET("/all")
    void getWeather(retrofit.Callback<List<CountryInfoClass>> callback);


    @GET("/latest.json?app_id=6d8df28bf2d04c52a75661f23065f545")
    void getRates(Callback<RatesInfoClass> rates);
}
