package com.example.otimus.ecommerceapp.models;

import java.io.Serializable;

/**
 * Created by Otimus on 10/18/2016.
 */
public class ItemClothing implements Serializable{

    int clothing_image;
    String clothing_name,
            clothing_price;

    public ItemClothing(int clothing_image, String clothing_name, String clothing_price) {
        this.clothing_image = clothing_image;
        this.clothing_name = clothing_name;
        this.clothing_price = clothing_price;
    }

    public int getClothing_image() {
        return clothing_image;
    }

    public void setClothing_image(int clothing_image) {
        this.clothing_image = clothing_image;
    }

    public String getClothing_name() {
        return clothing_name;
    }

    public void setClothing_name(String clothing_name) {
        this.clothing_name = clothing_name;
    }

    public String getClothing_price() {
        return clothing_price;
    }

    public void setClothing_price(String clothing_price) {
        this.clothing_price = clothing_price;
    }
}
