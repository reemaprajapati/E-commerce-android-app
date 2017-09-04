package com.example.otimus.ecommerceapp.data;

import com.example.otimus.ecommerceapp.data.local.LocalRepo;
import com.example.otimus.ecommerceapp.data.remote.RemoteRepo;
import com.example.otimus.ecommerceapp.models.Products;

import java.util.List;


/**
 * Created by brain on 9/4/17.
 */

public class AppData {

    private final LocalRepo localRepo;
    private final RemoteRepo remoteRepo;


    //THIS CLASS CONTAINS ALL THE INTERNAL WORKING METHODS OF APP EITHER LOCAL
    // DATABASE OR REMOTE API

    public AppData(LocalRepo localRepo , RemoteRepo remoteRepo) {
        this.localRepo = localRepo;
        this.remoteRepo = remoteRepo;
    }

    public void addToCart(Products product) {
        localRepo.addToCart(product);
    }

    public List<Products> getCartItems() {
        return localRepo.getCartItems();
    }
}
