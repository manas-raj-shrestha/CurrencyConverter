package com.droid.manas.currencyconverter.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.droid.manas.currencyconverter.conversionscreen.ConversionActivity;
import com.example.manas.currencyconverter.R;

/**
 * Gets the latest rates and save it for further use
 */
public class SplashActivity extends AppCompatActivity implements SplashContract.PresenterViewContract {

    SplashContract.ViewPresenterContract viewPresenterContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewPresenterContract = new SplashActivityPresenter(this);
        viewPresenterContract.startCurrencyRequest();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "Need Internet Connection On First Run.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadComplete() {
        startActivity(new Intent(this, ConversionActivity.class));
        finish();
    }

    @Override
    public void onLoadFailure() {
        Toast.makeText(this, "Something went wrong \nPlease try again in few minutes", Toast.LENGTH_SHORT).show();
    }
}
