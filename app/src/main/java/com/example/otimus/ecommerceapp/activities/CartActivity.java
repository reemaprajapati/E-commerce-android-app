package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.models.Products;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    List<Products> cartlist;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staticcart);
        /*
        TODO : add activity_cart.xml


         */
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        recyclerView=(RecyclerView)findViewById(R.id.cart_recV);
//        recyclerView.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cartlist  =new ArrayList<>();
        /*
            TODO : add items to cart
         */



    }


}
