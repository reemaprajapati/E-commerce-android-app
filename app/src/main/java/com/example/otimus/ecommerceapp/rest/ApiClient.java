package com.example.otimus.ecommerceapp.rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Otimus on 8/28/2017.
 */

public class ApiClient {


//    public static final String BASE_URL="http://172.18.13.106/onlineshop/";
    public static final String BASE_URL="http://192.168.10.6/onlineshop/";
//    public static final String BASE_URL="http://192.168.1.13/onlineshop/";

    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}


