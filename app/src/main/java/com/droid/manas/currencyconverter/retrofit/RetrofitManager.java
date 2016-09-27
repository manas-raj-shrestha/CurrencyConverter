package com.droid.manas.currencyconverter.retrofit;

import android.util.Log;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "http://openexchangerates.org/api/";

    private static RetrofitManager retrofitManager;

    private Retrofit retrofit;
    private CurrencyService currencyService;

    public static RetrofitManager getInstance() {
        if (retrofitManager == null)
            retrofitManager = new RetrofitManager();

        return retrofitManager;
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        currencyService = retrofit.create(CurrencyService.class);
    }

    public void getCurrencyList(Callback<JsonElement> responseCallback) {
        Call<JsonElement> call = currencyService.getCurrencyListing();
        call.enqueue(responseCallback);
    }

    public void getCurrencyRates(Callback<JsonElement> responseCallback){
        Call<JsonElement> call = currencyService.getCurrencyRates("6d8df28bf2d04c52a75661f23065f545");
        call.enqueue(responseCallback);
    }

}
