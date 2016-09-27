package com.droid.manas.currencyconverter.conversionscreen;

import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.droid.manas.currencyconverter.model.CurrencyRates;

import io.realm.RealmResults;

public interface ConversionScreenContract {

    interface ViewPresenterContract {

        void loadCurrencyListing();

        void onAmountChanged(float i);

        CurrencyRates loadExchangeRateByCode(String code);

        RealmResults<CurrencyNames> getLoadedCurrencies();

        void setFromCurrency(String code);

        void setToCurrency(String code);

    }

    interface PresenterViewContract {

        void onFromCurrencyChanged(String code);

        void onToCurrencyChanged(String code);

        void onAmountConverted(float concertedAmount);

        void setUnitRateFrom(String string);

        void setUnitRateTo(String string);

    }
}
