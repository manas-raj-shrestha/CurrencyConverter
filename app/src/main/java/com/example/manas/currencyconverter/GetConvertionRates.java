package com.example.manas.currencyconverter;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownServiceException;

/**
 * Created by Manas on 3/16/2015.
 */
public class GetConvertionRates extends AsyncTask<Void, Void, Boolean> {
    ProgressDialog pd;
    Context context;
    MainActivity main;
    private String fetch_json = "http://openexchangerates.org/api/latest.json?app_id=6d8df28bf2d04c52a75661f23065f545";
    String From, To;
    Double num;
    Double ans;
    GetConvertionRates(Context context, MainActivity obj, String From, String To, Double num) {
        this.context = context;
        main = obj;
        this.From = From;
        this.To = To;
        this.num = num;
        Log.e("Inside Cons", this.From + "   " + this.To + "  " + num);


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setTitle("Converting Currency");
        pd.setMessage("Fetching the rates");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        JSONParser jParser = new JSONParser();
        JSONObject json = jParser.getJSONFromUrl(fetch_json);
        JSONObject rates = null;
        try {
            rates = json.getJSONObject("rates");
            String USDV1 = rates.getString(From);
            String USDV2 = rates.getString(To);
            Double c1 = Double.parseDouble(USDV1);
            Double c2 = Double.parseDouble(USDV2);
            Double ratio = c2 / c1;
            ans = ratio * num;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        main.ChangeText(""+ans);
        pd.dismiss();
    }


}
