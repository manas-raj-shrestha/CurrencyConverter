package com.droid.manas.currencyconverter.splash;

import android.util.Log;

import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.droid.manas.currencyconverter.model.CurrencyRates;
import com.droid.manas.currencyconverter.realm.RealmController;
import com.droid.manas.currencyconverter.retrofit.RetrofitManager;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityPresenter implements SplashContract.ViewPresenterContract {

    SplashContract.PresenterViewContract presenterViewContract;
    RealmController realmController = RealmController.getInstance();

    public SplashActivityPresenter(SplashContract.PresenterViewContract presenterViewContract) {
        this.presenterViewContract = presenterViewContract;
    }

    @Override
    public void startCurrencyListingRequest() {
        RetrofitManager.getInstance().getCurrencyList(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                realmController.refresh();
                realmController.clearAllCurrencies();

                Realm realm = realmController.getRealm();
                realm.beginTransaction();

                String json = response.body().getAsJsonObject().toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {
                        String key = iterator.next();

                        CurrencyNames currencyNames = new CurrencyNames();
                        currencyNames.setCurrencyAbbreviation(key);
                        currencyNames.setCurrencyFullName(jsonObject.getString(key));
                        currencyNames.setPrimaryKey(realmController.getNextKey());

                        realm.copyToRealm(currencyNames);
                    }
                } catch (JSONException e) {
                    presenterViewContract.onLoadFailure();
                }

                realm.commitTransaction();
                Log.e("---", realmController.getCurrencies().toString());
                startCurrencyExchangeRateRequest();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                presenterViewContract.onLoadFailure();
            }

        });
    }

    @Override
    public void startCurrencyExchangeRateRequest() {
        RetrofitManager.getInstance().getCurrencyRates(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                String json = response.body().getAsJsonObject().toString();

                try {

                    realmController.clearRatesCurrencies();
                    Realm realm = realmController.getRealm();
                    realm.beginTransaction();

                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject ratesObject = jsonObject.getJSONObject("rates");

                    for (Iterator<String> iterator = ratesObject.keys(); iterator.hasNext(); ) {
                        String key = iterator.next();

                        CurrencyRates currencyRates = new CurrencyRates();
                        currencyRates.setCurrencyAbbreviation(key);
                        currencyRates.setExchangeRate(Float.valueOf(ratesObject.getString(key)));
                        currencyRates.setPrimaryKey(realmController.getNextExchangeKey());
                        realm.copyToRealm(currencyRates);
                    }

                    realm.commitTransaction();
                    presenterViewContract.onLoadComplete();
                } catch (JSONException e) {
                    presenterViewContract.onLoadFailure();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                presenterViewContract.onLoadFailure();
            }
        });
    }
}
