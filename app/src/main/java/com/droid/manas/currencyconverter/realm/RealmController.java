package com.droid.manas.currencyconverter.realm;

import com.droid.manas.currencyconverter.model.CurrencyNames;
import com.droid.manas.currencyconverter.model.CurrencyRates;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private static RealmController realmController;
    private Realm realm;

    public static RealmController getInstance() {
        if (realmController == null)
            realmController = new RealmController();

        return realmController;
    }

    private RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.refresh();
    }

    //find all objects in the Book.class
    public RealmResults<CurrencyNames> getCurrencies() {
        return realm.where(CurrencyNames.class).findAll();
    }

    public RealmResults<CurrencyRates> getAllExchangeRates() {
        return realm.where(CurrencyRates.class).findAll();
    }

    public int getNextKey() {
        return (int) (realm.where(CurrencyNames.class).maximumInt("primaryKey") + 1);
    }

    public int getNextExchangeKey() {
        return (int) (realm.where(CurrencyRates.class).maximumInt("primaryKey") + 1);
    }

    public void clearAllCurrencies() {
        realm.beginTransaction();
        realm.clear(CurrencyNames.class);
        realm.commitTransaction();
    }

    public void clearRatesCurrencies() {
        realm.beginTransaction();
        realm.clear(CurrencyRates.class);
        realm.commitTransaction();
    }

    public CurrencyRates getCurrencyByCode(String code) {

        return realm.where(CurrencyRates.class).equalTo("currencyAbbreviation", code).findFirst();
    }


}
