package com.example.otimus.ecommerceapp.data.local;

import com.example.otimus.ecommerceapp.models.Products;

import java.util.List;

/**
 * Created by brain on 9/4/17.
 */

public class LocalRepo {
    LocalPrefs prefs;
    RealmDbManager realmDbManager;
    CartDao cartDao;

    //TODO DO ALL WORKS RELATED TO LOCAL DATABASE HERE

    public LocalRepo(LocalPrefs prefs, RealmDbManager realmDbManager) {

        this.prefs = prefs;
        RealmDbManager.open();
        this.realmDbManager = realmDbManager;
        cartDao = this.realmDbManager.createCartDao();
    }

    public void addToCart(Products product) {
        cartDao.save(product);
    }

    public List<Products> getCartItems() {
        return cartDao.getCartItems();
    }

    public void updateCartItems(List<Products> cartList) {
        cartDao.save(cartList);
    }
}
