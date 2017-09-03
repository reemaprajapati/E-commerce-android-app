package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.adapters.ClothingAdapter;
import com.example.otimus.ecommerceapp.models.Products;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FootWearActivity extends AppCompatActivity {

    List<Products> footwearList;
    ClothingAdapter footwearAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_wear);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_foot);
        setSupportActionBar(toolbar);
        setTitle("Foot Wear");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         recyclerView=(RecyclerView)findViewById(R.id.footwear_recView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        footwearList  =new ArrayList<>();

        footwearAdapter=new ClothingAdapter(footwearList, new ClothingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent = new Intent(getApplication(),DetailActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        },getApplicationContext());
        recyclerView.setAdapter(footwearAdapter);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(2);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                footwearList.addAll(response.body());
                footwearAdapter.notifyDataSetChanged();
                Log.d("newarrivals",footwearList.get(1).getProductName());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
