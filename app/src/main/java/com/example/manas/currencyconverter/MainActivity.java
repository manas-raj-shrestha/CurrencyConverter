package com.example.manas.currencyconverter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    Spinner spinner1, spinner2;
    List<String> list = new ArrayList<String>();
    String From, To;
    Button convert;
    EditText editText;
    TextView tv;
    ArrayAdapter<String> dataAdapter; //for 1st spinner
    ArrayAdapter<String> dataAdapter2; //for 2nd spinner
    SpinnerAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //set the action bar title text color to white

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //floating action button initialization
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Floating Button", "Clicked");
            }
        });
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        editText = (EditText) findViewById(R.id.et1);
        convert = (Button) findViewById(R.id.convert);
        tv = (TextView) findViewById(R.id.et2);

        list = populateSpinnerList();

        try {

            spinnerAdapter = new SpinnerAdapter(addCountryNametoList(),MainActivity.this);

        } catch (SQLException e) {
            e.printStackTrace();
            dataAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, list);
            dataAdapter2 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, list);
        }




               spinner1.setAdapter(spinnerAdapter);
               spinner2.setAdapter(spinnerAdapter);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_to_convet = editText.getText().toString();
                if (number_to_convet.contentEquals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter A Number", Toast.LENGTH_SHORT).show();

                } else {
                    Double num = Double.parseDouble(number_to_convet);
                    if (isNetworkAvailable() == true) {
                        new GetConvertionRates(MainActivity.this, MainActivity.this, From, To, num).execute();
                    } else {
                        Database_Handler db = new Database_Handler(MainActivity.this);
                        try {
                            db.open();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        String USDV1 = db.getRate(From);
                        String USDV2 = db.getRate(To);
                        Double c1 = Double.parseDouble(USDV1);
                        Double c2 = Double.parseDouble(USDV2);
                        Double ratio = c2 / c1;
                        Double ans = ratio * num;

                        ChangeText(ans + "");
                        db.close();


                    }
                }
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                From = list.get(spinner1.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                From = spinner1.getSelectedItem().toString();

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                To = list.get(spinner2.getSelectedItemPosition());
                Log.e("Which Selected", To);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                To = spinner2.getSelectedItem().toString();
//                Log.e("Nothing  Selected", From);
            }
        });


    }


    private void initialiseTheSpinner() {

    }

    public void ChangeText(String ans) {
        tv.setText(ans);

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

    private List<String> addCountryNametoList() throws SQLException {
        List<String> list = populateSpinnerList();
        List<String> listtobereturned = new ArrayList<String>();
        Database_Handler db = new Database_Handler(MainActivity.this);
        db.open();
        for (int i = 0; i <= (list.size() - 1); i++) {
            listtobereturned.add(list.get(i) + "  " + db.getCountryName(list.get(i)));

        }
        db.close();

        return listtobereturned;
    }
}
