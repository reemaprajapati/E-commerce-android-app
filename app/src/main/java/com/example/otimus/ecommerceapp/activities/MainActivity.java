package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.adapters.GridAdapter;
import com.example.otimus.ecommerceapp.models.Products;
import com.example.otimus.ecommerceapp.rest.ApiClient;
import com.example.otimus.ecommerceapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    List<Products> newArrivalsList,bestSellersList,winterSaleList;
    RecyclerView recNewArrivals;
    GridAdapter gridAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.cart) {

            startActivity(new Intent(getApplicationContext(),CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Online Shop");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation);
         navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //recyclerview for new arrivals
        recNewArrivals =(RecyclerView)findViewById(R.id.rec_newarrivals);
        recNewArrivals.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1=new GridLayoutManager(this,4,1,false);//5 columns, 1 horizental layout
        recNewArrivals.setLayoutManager(gridLayoutManager1);
        recNewArrivals.setItemAnimator(new DefaultItemAnimator());
        newArrivalsList= new ArrayList<>();
        gridAdapter=new GridAdapter(newArrivalsList, new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent=new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        },getApplicationContext());
        recNewArrivals.setAdapter(gridAdapter);
        setNewArrivals();


        //recycler view for bestsellers
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rec_bestsellers);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4,1,false);//5 columns, 1 horizental layout
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       bestSellersList=new ArrayList<>();
        gridAdapter = new GridAdapter(bestSellersList, new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent=new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        },getApplicationContext());
        recyclerView.setAdapter(gridAdapter);
        setBestSellers();

//        //recylerview for winter sale
        RecyclerView recView=(RecyclerView)findViewById(R.id.rec_wintersale);
        recView.setHasFixedSize(true);
        GridLayoutManager layoutmanager= new GridLayoutManager(this,3,1,false);
        recView.setLayoutManager(layoutmanager);
        recView.setItemAnimator(new DefaultItemAnimator());
        winterSaleList=new ArrayList<>();
        gridAdapter = new GridAdapter(winterSaleList, new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products item) {
                Intent intent=new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }

        },getApplicationContext());
        recView.setAdapter(gridAdapter);
        setWinterSales();
    }

    public void setNewArrivals(){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(8);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                newArrivalsList.addAll(response.body());
                gridAdapter.notifyDataSetChanged();
                Log.d("newarrivals",newArrivalsList.get(1).getProductName());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.d("hi",t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setBestSellers(){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(9);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                bestSellersList.addAll(response.body());
                gridAdapter.notifyDataSetChanged();
                Log.d("newarrivals",bestSellersList.get(1).getProductName());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.d("hi",t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setWinterSales(){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<List<Products>> call=apiInterface.getProducts(14);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                winterSaleList.addAll(response.body());
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.d("hello",t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

            case R.id.clothing:
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.footwear:
                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.bagsandwallets:
                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.jewellery:
                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.beautycare:
                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.accessories:
                break;

            case R.id.womensgrooming:
                startActivity(new Intent(getApplicationContext(),ClothingActivity.class));
                break;

            case R.id.account:
                break;

            case R.id.settings:
                break;


        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
