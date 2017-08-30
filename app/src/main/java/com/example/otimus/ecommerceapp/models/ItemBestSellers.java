package com.example.otimus.ecommerceapp.models;

import java.io.Serializable;

/**
 * Created by Otimus on 10/17/2016.
 */
public class ItemBestSellers implements Serializable{
    int new_image;
    String new_name;
    String new_price;

    public ItemBestSellers(int new_image, String new_name, String new_price) {
        this.new_image = new_image;
        this.new_name = new_name;
        this.new_price = new_price;
    }

    public int getNew_image() {
        return new_image;
    }

    public void setNew_image(int new_image) {
        this.new_image = new_image;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getNew_price() {
        return new_price;
    }

    public void setNew_price(String new_price) {
        this.new_price = new_price;
    }
}
