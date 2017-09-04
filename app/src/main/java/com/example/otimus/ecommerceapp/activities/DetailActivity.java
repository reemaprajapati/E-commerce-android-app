package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.AppModule;
import com.example.otimus.ecommerceapp.EcommerceApp;
import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.Utils;
import com.example.otimus.ecommerceapp.data.AppData;
import com.example.otimus.ecommerceapp.models.BaseResponse;
import com.example.otimus.ecommerceapp.models.Login;
import com.example.otimus.ecommerceapp.models.Products;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.otimus.ecommerceapp.rest.ApiClient.BASE_URL;

public class DetailActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    TextView detail_name, detail_price, detail_description;
    ImageView detail_image;
    AppModule component;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {

            startActivity(new Intent(getApplicationContext(), CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final Products products = (Products) intent.getSerializableExtra("data");
        Log.d("data", products.getProductName());

//THIS WAY WE CAN INITIALISE AppModule object component
        component = EcommerceApp.component(this);


        detail_name = (TextView) findViewById(R.id.detail_name);
        detail_price = (TextView) findViewById(R.id.detail_price);
        detail_image = (ImageView) findViewById(R.id.detail_image);
        detail_description = (TextView) findViewById(R.id.detail_description);

        detail_name.setText(products.getProductName());
        detail_price.setText(toString().valueOf((products.getProductPrice())));
        Picasso.with(this).load(BASE_URL + "images/" + products.getProductImage()).into(detail_image);
        detail_description.setText(products.getProductDesc());


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        Button btn_addtocart = (Button) findViewById(R.id.btn_addtocart);
        btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(products);
            }
        });


        Button btn_buynow = (Button) findViewById(R.id.btn_buynow);

        btn_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckOutActivity.class));
            }
        });


    }

    private void addToCart(Products product) {
        //We can access Appdata methods from component
        component.provideData().addToCart(product);
        showAddToCartSuccess();
    }

    private void showAddToCartSuccess() {
        Utils.showSnackbar(this, "Added to Cart");
    }

}
