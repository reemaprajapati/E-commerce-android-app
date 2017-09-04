package com.example.otimus.ecommerceapp.models;

/**
 * Created by brain on 9/4/17.
 */

public class BaseResponse {

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
