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

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.models.Login;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_signin);
        tv_register= (TextView) findViewById(R.id.tv_register);


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

   }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
