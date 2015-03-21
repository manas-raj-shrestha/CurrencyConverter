package com.example.manas.currencyconverter.UpdateEveryThing;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.manas.currencyconverter.Database_Handler;
import com.example.manas.currencyconverter.InfoClasses.CountryInfoClass;
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
public class GetCountryListAndInfo extends Activity {
    private String url = "http://restcountries.eu/rest/v1";
    private Context context;

    public GetCountryListAndInfo(Context context) {
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
    }

    public void requestData() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        API api = adapter.create(API.class);

        api.getWeather(new Callback<List<CountryInfoClass>>() {
            @Override
            public void success(List<CountryInfoClass> countryInfoClass, Response response) {

                Database_Handler db = new Database_Handler(context);
                try {
                    db.open();
                    db.DeleteFromTable("country_details");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < countryInfoClass.size(); i++) {
                    CountryInfoClass CIC = countryInfoClass.get(i);
                    db.CreateEntryForCountryTable(CIC.name,CIC.alpha2Code,CIC.alpha3Code,CIC.currencies.get(0));
                }
//                Log.e("DB DATA",db.getDataOfCountries());
                db.close();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error", error.toString());
            }
        });

    }
}
