package com.droid.manas.currencyconverter.retrofit;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CurrencyService {
    @Headers("Content-Type:application/json")
    @GET("currencies.json")
    Call<JsonElement> getCurrencyListing();

    @Headers("Content-Type:application/json")
    @GET("latest.json")
    Call<JsonElement> getCurrencyRates(@Query("app_id") String appId);

}
