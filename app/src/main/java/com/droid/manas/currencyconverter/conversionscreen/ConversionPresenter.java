package com.droid.manas.currencyconverter.conversionscreen;


import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.droid.manas.currencyconverter.model.CurrencyRates;
import com.droid.manas.currencyconverter.realm.RealmController;

import io.realm.RealmResults;

public class ConversionPresenter implements ConversionScreenContract.ViewPresenterContract {
    private static final String UNIT_RATE_FORMAT = "1 %s = %.4f %s";
    private static final int HEAD_POSITION = 0;

    private CurrencyRates fromCurrency;
    private CurrencyRates toCurrency;
    private float currentAmount;

    private ConversionScreenContract.PresenterViewContract view;
    private RealmController realmController = RealmController.getInstance();
    private RealmResults<CurrencyNames> currencies;

    public ConversionPresenter(ConversionScreenContract.PresenterViewContract presenterViewContract) {
        view = presenterViewContract;
        loadCurrencyListing();

        //set initial currency
        setFromCurrency(currencies.get(HEAD_POSITION).getCurrencyAbbreviation());
        setToCurrency(currencies.get(HEAD_POSITION).getCurrencyAbbreviation());
    }

    @Override
    public void loadCurrencyListing() {
        realmController.refresh();
        currencies = realmController.getCurrencies();
    }

    @Override
    public void onAmountChanged(float amount) {
        currentAmount = amount;
        view.onAmountConverted((toCurrency.getExchangeRate() / fromCurrency.getExchangeRate()) * amount);
    }

    @Override
    public CurrencyRates loadExchangeRateByCode(String code) {
        return realmController.getCurrencyByCode(code);
    }

    @Override
    public RealmResults<CurrencyNames> getLoadedCurrencies() {
        return currencies;
    }

    @Override
    public void setFromCurrency(String code) {
        fromCurrency = loadExchangeRateByCode(code);
        view.onFromCurrencyChanged(code);

        if (toCurrency != null) {
            onAmountChanged(currentAmount);
            setUnitRates();
        }
    }

    @Override
    public void setToCurrency(String code) {
        toCurrency = loadExchangeRateByCode(code);
        view.onToCurrencyChanged(code);

        if (fromCurrency != null) {
            onAmountChanged(currentAmount);
            setUnitRates();
        }
    }

    private void setUnitRates() {
        String toUnitRate = String.format(UNIT_RATE_FORMAT, toCurrency.getCurrencyAbbreviation(),fromCurrency.getExchangeRate() / toCurrency.getExchangeRate(), fromCurrency.getCurrencyAbbreviation());
        String fromUnitRate = String.format(UNIT_RATE_FORMAT, fromCurrency.getCurrencyAbbreviation(),toCurrency.getExchangeRate() / fromCurrency.getExchangeRate(), toCurrency.getCurrencyAbbreviation());

        view.setUnitRateFrom(fromUnitRate);
        view.setUnitRateTo(toUnitRate);
    }

}
