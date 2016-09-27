package com.droid.manas.currencyconverter.splash;

public interface SplashContract {

    interface ViewPresenterContract {
        void startCurrencyListingRequest();

        void startCurrencyExchangeRateRequest();
    }

    interface PresenterViewContract {
        void showNetworkError();

        void onLoadComplete();

        void onLoadFailure();
    }
}
