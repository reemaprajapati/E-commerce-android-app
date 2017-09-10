package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.otimus.ecommerceapp.AppModule;
import com.example.otimus.ecommerceapp.EcommerceApp;
import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.adapters.CartAdapter;
import com.example.otimus.ecommerceapp.databinding.ActivityCartBinding;
import com.example.otimus.ecommerceapp.models.Products;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener {

    ActivityCartBinding binding;
    AppModule component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        setSupportActionBar(binding.toolbarCart1);
        setTitle("Your Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        component = EcommerceApp.component(this);

        binding.btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckOutActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        recyclerView=(RecyclerView)findViewById(R.id.cart_recV);
//        recyclerView.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());


        showCartItems();


    }


    public void showCartItems() {
        List<Products> cartItems = component.provideData().getCartItems();
        binding.cartRecV.setAdapter(new CartAdapter(cartItems, this, this));
        showTotalAmount(cartItems);

    }

    @Override
    public void onItemClick(Products item) {

    }

    @Override
    public void onQuantityChanged(List<Products> cartList) {
        component.provideData().updateCartItems(cartList);
        showTotalAmount(cartList);
    }

    @Override
    public void onDeleteItem(Products product) {
        component.provideData().deleteCartItem(product);
        List<Products> cartItems = component.provideData().getCartItems();
        showTotalAmount(cartItems);


    }

    private  void showTotalAmount(List<Products> cartList){
        float totalAmt = 0;
        for (int i = 0; i < cartList.size(); i++) {
            totalAmt += cartList.get(i).getProductPrice() * cartList.get(i).getQuantity();
        }
        binding.tvTotal.setText("TOTAL $ " + totalAmt + "/-");
    }
}
