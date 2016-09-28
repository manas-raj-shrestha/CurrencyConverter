package com.droid.manas.currencyconverter.splash;

public interface SplashContract {

    interface ViewPresenterContract {

        void startCurrencyRequest();

    }

    interface PresenterViewContract {
        void showNetworkError();

        void onLoadComplete();

        void onLoadFailure();
    }
}
