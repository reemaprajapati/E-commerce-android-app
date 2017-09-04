package com.example.otimus.ecommerceapp;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by brain on 9/4/17.
 */

public class EcommerceApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static AppModule component(Context context) {
       return new AppModule(context.getApplicationContext());
    }

}
