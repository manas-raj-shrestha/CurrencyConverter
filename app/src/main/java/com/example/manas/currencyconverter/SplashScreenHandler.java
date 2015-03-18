package com.example.manas.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/16/2015.
 */
public class SplashScreenHandler extends ActionBarActivity {
    SharedPreferences currency;
    String filename = "CurrencyConverter";
    List<String> listOfRates = new ArrayList<>();
    private String db_name = "currency";
    String Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splash);

        currency = getSharedPreferences(filename, 0);
        Status = currency.getString("isAppInitialized", "No");
        if(Status.contentEquals("No") && (isNetworkAvailable()==false)) {
            Toast.makeText(this,"Need Internet To Run App For The First Time", Toast.LENGTH_SHORT).show();
        }else {
            if (isNetworkAvailable() == true) {

                getBaseContext().deleteDatabase(db_name);
                listOfRates = populateSpinnerList();
                new InitializeDatabase(listOfRates, SplashScreenHandler.this, SplashScreenHandler.this).execute();
                SharedPreferences.Editor edit = currency.edit();
                edit.putString("isAppInitialized", "Yes");
                edit.commit();

            } else {
                SharedPreferences.Editor edit = currency.edit();
                edit.putString("isAppInitialized", "Yes");
                edit.commit();


                StartMainActivity();
            }

        }
    }

    public void StartMainActivity() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

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
}
