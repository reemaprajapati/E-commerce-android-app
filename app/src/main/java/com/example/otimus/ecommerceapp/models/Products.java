package com.example.otimus.ecommerceapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Otimus on 8/29/2017.
 */

public class Products implements Serializable {

    @SerializedName("productId")
    Integer productId;

    @SerializedName("productName")
    String productName;

    @SerializedName("productPrice")
    Integer productPrice;

    @SerializedName("productDesc")
    String productDesc;

    @SerializedName("productThumb")
    String productThumb;

    @SerializedName("productImage")
    String productImage;

    @SerializedName("productCatId")
    Integer productCatId;

    @SerializedName("productStock")
    Integer productStock;

    @SerializedName("categoryName")
    String categoryName;

    @SerializedName("categoryId")
    Integer categoryId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Integer productCatId) {
        this.productCatId = productCatId;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Products(Integer productId, String productName, Integer productPrice, String productDesc, String productThumb, String productImage, Integer productCatId, Integer productStock, String categoryName, Integer categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productThumb = productThumb;
        this.productImage = productImage;
        this.productCatId = productCatId;
        this.productStock = productStock;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
}
