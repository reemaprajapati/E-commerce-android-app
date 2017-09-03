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

public class BeautyCareActivity extends AppCompatActivity {
    List<Products> beautylist;
    ClothingAdapter beautyAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_care);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_beautycare);
        setSupportActionBar(toolbar);
        setTitle("Beauty Care");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.beautycare_recView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        beautylist  =new ArrayList<>();

        beautyAdapter=new ClothingAdapter(beautylist, new ClothingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent = new Intent(getApplication(),DetailActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        },getApplicationContext());
        recyclerView.setAdapter(beautyAdapter);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(5);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                beautylist.addAll(response.body());
                beautyAdapter.notifyDataSetChanged();
                Log.d("newarrivals",beautylist.get(1).getProductName());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
