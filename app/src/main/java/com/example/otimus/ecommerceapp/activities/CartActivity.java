package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.otimus.ecommerceapp.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
        setTitle("Your Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button=(Button)findViewById(R.id.btn_proceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CheckOutActivity.class));
            }
        });
    }


}
