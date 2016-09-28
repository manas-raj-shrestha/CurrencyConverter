package com.droid.manas.currencyconverter;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CurrencyConverterApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext(){
        return context;
    }
}
