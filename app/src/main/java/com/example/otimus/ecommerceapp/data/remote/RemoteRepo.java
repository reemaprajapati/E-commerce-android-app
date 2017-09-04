package com.example.otimus.ecommerceapp.data.remote;

import com.example.otimus.ecommerceapp.rest.ApiInterface;
import com.google.gson.Gson;

/**
 * Created by brain on 9/4/17.
 */

public class RemoteRepo {

    private final ApiInterface api;
    private final Gson gson;

    public RemoteRepo(ApiInterface api , Gson gson) {
        this.api = api;
        this.gson = gson;
    }


    //TODO DO All the remote api works here
}
