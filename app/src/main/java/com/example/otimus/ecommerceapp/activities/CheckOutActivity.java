package com.example.otimus.ecommerceapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.otimus.ecommerceapp.R;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_check);
        setSupportActionBar(toolbar);
        setTitle("Check Out");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
