package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otimus.ecommerceapp.AppModule;
import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.models.Login;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView tv_register;
    String username;
    String password;
    Button btFacebook;

    AppModule component;
    private  CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_signin);
        tv_register= (TextView) findViewById(R.id.tv_register);
        btFacebook= (Button) findViewById(R.id.btFacebook);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username =et_username.getText().toString();
                password=et_password.getText().toString();
                Log.d("username",username);

                ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
                Call<Login> call=apiInterface.authenticate(username,password);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Login login = response.body();
                        if(login.getLogin().equals("true")){
                            Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {

                            Toast.makeText(LoginActivity.this, "Login Denied", Toast.LENGTH_SHORT).show();
                        }
//                        Toast.makeText(LoginActivity.this,login.getLogin(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,t.toString() , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        instantiateFbLogin();
        btFacebook.setOnClickListener(view -> {
            loginWithFb();
        });
        

   }

    private void loginWithFb() {
        LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void instantiateFbLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());

        this.callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, onFbLogin());
    }

    private FacebookCallback<LoginResult> onFbLogin() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                final String facebookId = loginResult.getAccessToken().getUserId();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        (data, response) -> {

                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,gender,picture.type(large)");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Canceled", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_LONG).show();

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
