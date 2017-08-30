package com.example.otimus.ecommerceapp.rest;

import com.example.otimus.ecommerceapp.models.Login;
import com.example.otimus.ecommerceapp.models.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Otimus on 8/27/2017.
 */

public interface ApiInterface {
    @GET("checkUser.php")
    Call<Login> authenticate(@Query("userName") String username, @Query("userPwd") String password);
    @GET("getProducts.php")
    Call<List<Products>> getProducts(@Query("id") Integer id);
}
