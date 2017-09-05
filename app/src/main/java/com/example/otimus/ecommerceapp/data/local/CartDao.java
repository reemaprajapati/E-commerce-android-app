package com.example.otimus.ecommerceapp.data.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.otimus.ecommerceapp.models.Products;

import java.util.List;

import io.realm.Realm;

/**
 * Created by brain on 6/21/17.
 */

public class CartDao {


    private Realm mRealm;

    //THIS CLASS IS FOR SAVING, RETREIVING ,UPDATING DATA IN REALM.

    public CartDao(@NonNull Realm realm) {
        mRealm = Realm.getDefaultInstance();
    }

    public void save(final List<Products> products) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(products);
        });
    }

    public void save(final Products product) {
        mRealm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(product);
        });
    }

    public List<Products> getCartItems() {
        Realm realm1 = Realm.getDefaultInstance();
        List<Products> products = realm1.copyFromRealm(realm1
                .where(Products.class)
                .findAll());
        realm1.close();
        return products;
    }

    public void deleteProduct(int productId) {
      /*  Realm realm1 = Realm.getDefaultInstance();
        Log.e("delete thread::", Thread.currentThread().getName());
        realm1.executeTransaction(realm -> {
            realm.delete(Products.class);
        });
        realm1.close();*/

    }
}
