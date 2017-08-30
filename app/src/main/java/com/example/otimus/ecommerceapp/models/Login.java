package com.example.otimus.ecommerceapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Otimus on 8/28/2017.
 */

public class Login {
    @SerializedName("status")
    private String login;

    public Login(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
