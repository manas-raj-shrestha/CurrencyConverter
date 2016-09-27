package com.droid.manas.currencyconverter.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CurrencyNames extends RealmObject {

    @PrimaryKey
    private int primaryKey;

    private String currencyAbbreviation;

    private String currencyFullName;

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

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }
}
