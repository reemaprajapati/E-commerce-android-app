package com.example.otimus.ecommerceapp.models;

import java.io.Serializable;

/**
 * Created by Otimus on 10/17/2016.
 */
public class ItemNewArrivals implements Serializable {
    int product_image;
    String product_name;

    public ItemNewArrivals(int product_image, String product_name) {
        this.product_image = product_image;
        this.product_name = product_name;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
