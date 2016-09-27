package com.droid.manas.currencyconverter.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CurrencyRates extends RealmObject
{

    @PrimaryKey
    private int primaryKey;

    private String currencyAbbreviation;
    private float exchangeRate;

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
