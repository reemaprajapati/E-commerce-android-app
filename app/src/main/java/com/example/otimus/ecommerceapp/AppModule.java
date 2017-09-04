package com.example.otimus.ecommerceapp;

import android.content.Context;

import com.example.otimus.ecommerceapp.data.AppData;
import com.example.otimus.ecommerceapp.data.local.LocalPrefs;
import com.example.otimus.ecommerceapp.data.local.LocalRepo;
import com.example.otimus.ecommerceapp.data.local.RealmDbManager;
import com.example.otimus.ecommerceapp.data.remote.RemoteRepo;
import com.example.otimus.ecommerceapp.rest.ApiInterface;
import com.google.gson.Gson;

/**
 * Created by brain on 9/4/17.
 */

public class AppModule {

    Context app;
    LocalRepo localRepo;
    RemoteRepo remoteRepo;
    ApiInterface api;

    //THIS IS THE MAIN MODULE CLASS WHICH CAN BE ACCESSED FROM ANYWHERE AND THIS CLASS
    //PROVIDE APPDATA WHICH CAN ACCESS REMOTE AND LOCAL DATA CLASS

    public AppModule(Context context) {
        this.app = context;
        localRepo = new LocalRepo(new LocalPrefs(context, gson()), new RealmDbManager());
        remoteRepo = new RemoteRepo(api, gson());
    }

    Gson gson() {
        return new Gson();
    }

    public AppData provideData() {
        return new AppData(localRepo, remoteRepo);
    }
}
