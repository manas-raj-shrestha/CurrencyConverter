package com.example.manas.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.manas.currencyconverter.UpdateEveryThing.GetCountryListAndInfo;
import com.example.manas.currencyconverter.UpdateEveryThing.GetRates;

import org.json.JSONException;
import org.json.JSONObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewSplashScreen extends Activity {
    SharedPreferences currency;
    String Status,filename = "CurrencyConverter";
    private String fetch_json = "http://openexchangerates.org/api/latest.json?app_id=6d8df28bf2d04c52a75661f23065f545";
    private String fetch_json_CC = "http://openexchangerates.org/api/currencies.json";
    JSONParser jParser;
    JSONObject jsoni;
    int l = 1;

    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        GetCountryListAndInfo get = new GetCountryListAndInfo(NewSplashScreen.this);

        currency = getSharedPreferences(filename, 0);
        Status = currency.getString("isAppInitialized", "No");

        if(Status.contentEquals("No") && (isNetworkAvailable()==false)) {
            Toast.makeText(this, "Need Internet To Run App For The First Time", Toast.LENGTH_SHORT).show();
        }else{

            get.requestData();

            new GetRates(this,this,populateSpinnerList()).execute();


        }



    }




//    public class Fetch_CN extends AsyncTask<Void, Void, Boolean> {
//        Database_Handler db = new Database_Handler(NewSplashScreen.this);
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            try {
//                db.open();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//
//
//
//            jParser = new JSONParser();
//            jsoni = jParser.getJSONFromUrl(fetch_json_CC);
//            String CC;
//            try {
//                for (int i = 0; i < l; i++) {
//                    CC = jsoni.getString(list.get(i));
//                    db.CreateEntrydb2(list.get(i), CC);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            Log.e("RATES", db.getData2());
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            db.close();
//            Intent i = new Intent(NewSplashScreen.this,NewMainActivity.class);
//            startActivity(i);
//        }
//    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private List<String> populateSpinnerList() {
        List<String> list = new ArrayList<String>();
        list.add("AED");
        list.add("AFN");
        list.add("ALL");
        list.add("AMD");
        list.add("ANG");
        list.add("AOA");
        list.add("ARS");
        list.add("AUD");
        list.add("AWG");
        list.add("AZN");
        list.add("BAM");
        list.add("BBD");
        list.add("BDT");
        list.add("BGN");
        list.add("BHD");
        list.add("BIF");
        list.add("BMD");
        list.add("BND");
        list.add("BOB");
        list.add("BRL");
        list.add("BSD");
        list.add("BTC");
        list.add("BTN");
        list.add("BWP");
        list.add("BYR");
        list.add("BZD");
        list.add("CAD");
        list.add("CDF");
        list.add("CHF");
        list.add("CLF");
        list.add("CLP");
        list.add("CNY");
        list.add("COP");
        list.add("CRC");
        list.add("CUC");
        list.add("CUP");
        list.add("CVE");
        list.add("CZK");
        list.add("DJF");
        list.add("DKK");
        list.add("DOP");
        list.add("DZD");
        list.add("EEK");
        list.add("EGP");
        list.add("ERN");
        list.add("ETB");
        list.add("EUR");
        list.add("FJD");
        list.add("FKP");
        list.add("GBP");
        list.add("GEL");
        list.add("GGP");
        list.add("GHS");
        list.add("GIP");
        list.add("GMD");
        list.add("GNF");
        list.add("GTQ");
        list.add("GYD");
        list.add("HKD");
        list.add("HNL");
        list.add("HRK");
        list.add("HTG");
        list.add("HUF");
        list.add("IDR");
        list.add("ILS");
        list.add("IMP");
        list.add("INR");
        list.add("IQD");
        list.add("IRR");
        list.add("ISK");
        list.add("JEP");
        list.add("JMD");
        list.add("JOD");
        list.add("JPY");
        list.add("KES");
        list.add("KGS");
        list.add("KHR");
        list.add("KMF");
        list.add("KPW");
        list.add("KRW");
        list.add("KWD");
        list.add("KYD");
        list.add("KZT");
        list.add("LAK");
        list.add("LBP");
        list.add("LKR");
        list.add("LRD");
        list.add("LSL");
        list.add("LTL");
        list.add("LVL");
        list.add("LYD");
        list.add("MAD");
        list.add("MDL");
        list.add("MGA");
        list.add("MKD");
        list.add("MMK");
        list.add("MNT");
        list.add("MOP");
        list.add("MRO");
        list.add("MTL");
        list.add("MUR");
        list.add("MVR");
        list.add("MWK");
        list.add("MXN");
        list.add("MYR");
        list.add("MZN");
        list.add("NAD");
        list.add("NGN");
        list.add("NIO");
        list.add("NOK");
        list.add("NPR");
        list.add("NZD");
        list.add("OMR");
        list.add("PAB");
        list.add("PEN");
        list.add("PGK");
        list.add("PHP");
        list.add("PKR");
        list.add("PLN");
        list.add("PYG");
        list.add("QAR");
        list.add("RON");
        list.add("RSD");
        list.add("RUB");
        list.add("RWF");
        list.add("SAR");
        list.add("SBD");
        list.add("SCR");
        list.add("SDG");
        list.add("SEK");
        list.add("SGD");
        list.add("SHP");
        list.add("SLL");
        list.add("SOS");
        list.add("SRD");
        list.add("STD");
        list.add("SVC");
        list.add("SYP");
        list.add("SZL");
        list.add("THB");
        list.add("TJS");
        list.add("TMT");
        list.add("TND");
        list.add("TOP");
        list.add("TRY");
        list.add("TTD");
        list.add("TWD");
        list.add("TZS");
        list.add("UAH");
        list.add("UGX");
        list.add("USD");
        list.add("UYU");
        list.add("UZS");
        list.add("VEF");
        list.add("VND");
        list.add("VUV");
        list.add("WST");
        list.add("XAF");
        list.add("XAG");
        list.add("XAU");
        list.add("XCD");
        list.add("XDR");
        list.add("XOF");
        list.add("XPF");
        list.add("YER");
        list.add("ZAR");
        list.add("ZMK");
        list.add("ZMW");
        list.add("ZWL");
        return list;
    }

    public  void startNewMainActivity(){
        Intent i = new Intent(NewSplashScreen.this,NewMainActivity.class);
        startActivity(i);
    }

}
