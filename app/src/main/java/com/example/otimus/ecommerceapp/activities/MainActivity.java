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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.adapters.BestSellersAdapter;
import com.example.otimus.ecommerceapp.adapters.NewArrivalAdapter;
import com.example.otimus.ecommerceapp.models.ItemBestSellers;
import com.example.otimus.ecommerceapp.models.ItemNewArrivals;
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
    List<ItemNewArrivals> newArrivalsList=new ArrayList<>();
    RecyclerView recycView;
    ApiInterface apiInterface;
    Call<List<Products>> call;




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

        //recycler view for new arrivals
        recycView=(RecyclerView)findViewById(R.id.rec_newarrivals);
        recycView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1=new GridLayoutManager(this,5,1,false);//5 columns, 1 horizental layout
        recycView.setLayoutManager(gridLayoutManager1);
        recycView.setItemAnimator(new DefaultItemAnimator());


        newArrivalsList= getNewArrivals();
        NewArrivalAdapter newArrivalAdapter = new NewArrivalAdapter(newArrivalsList, new NewArrivalAdapter.OnItemClickListener(){


            @Override
            public void onItemClick(ItemNewArrivals item) {
//                Toast.makeText(MainActivity.this, "hi" , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),LadiesWearActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        });
        recycView.setAdapter(newArrivalAdapter);


        //recycler view for bestsellers
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rec_bestsellers);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,5,1,false);//5 columns, 1 horizental layout
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<ItemBestSellers> bestSellersList=new ArrayList<>();
        bestSellersList=getBestSellers();
        BestSellersAdapter bestSellersAdapter = new BestSellersAdapter(bestSellersList, new BestSellersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemBestSellers item) {
                Intent intent=new Intent(getApplicationContext(), BestSellerDetail.class);
                intent.putExtra("data",item);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(bestSellersAdapter);



        //recylerview for winter sale
        RecyclerView recView=(RecyclerView)findViewById(R.id.rec_wintersale);
        recView.setHasFixedSize(true);
        GridLayoutManager layoutmanager= new GridLayoutManager(this,11,1,false);
        recView.setLayoutManager(layoutmanager);
        recView.setItemAnimator(new DefaultItemAnimator());
        bestSellersList=new ArrayList<>();
        bestSellersList=getDataWinter();
        bestSellersAdapter = new BestSellersAdapter(bestSellersList, new BestSellersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemBestSellers item) {
                Intent intent=new Intent(getApplicationContext(), BestSellerDetail.class);
                startActivity(intent);
            }

        });
        recView.setAdapter(bestSellersAdapter);
    }

    public List<Products> getBestSellers(){
        List<Products> bestSellersList=new ArrayList<>();
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        call =apiInterface.getProducts(9);
        display(call);
//        bestSellersList.add(new ItemBestSellers(R.drawable.blacktops,"Black Tops","Rs.1120/-"));
//        bestSellersList.add(new ItemBestSellers(R.drawable.bluedarkshoemen,"Blue Dark Shoe","Rs.1800/-"));
//        bestSellersList.add(new ItemBestSellers(R.drawable.huaweigr3,"Huawei GR3","Rs. 28000/-"));
//        bestSellersList.add(new ItemBestSellers(R.drawable.bluesaree,"Blue Saree Floral","Rs.5000/-"));
//        bestSellersList.add(new ItemBestSellers(R.drawable.hppendrive,"hp pendrive 32GB","Rs.1500/-"));


        return bestSellersList;
    }

    public List<ItemBestSellers> getDataWinter(){
        List<ItemBestSellers> bestSellersList=new ArrayList<>();
        bestSellersList.add(new ItemBestSellers(R.drawable.bag,"Bag","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.blackgolden,"Black Golden","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.bossiniwhite,"Bossini White","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.brownshirt,"Brown Shirt","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.darkgrey,"Dark Grey","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.greenonepiece,"Green One Piece","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.lightpink,"Light Pink","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.orangewhitetops,"Orange White top","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.pinkshirt,"Pink Shirt","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.whiteshirt,"White Shirt","Rs.1120/-"));
        bestSellersList.add(new ItemBestSellers(R.drawable.yellowtshirt,"Yellow T-Shirt","Rs.1120/-"));

        return bestSellersList;

    }

    public List<ItemNewArrivals> getNewArrivals(){
        List<ItemNewArrivals> newArrivalsList=new ArrayList<>();
           newArrivalsList.add(new ItemNewArrivals(R.drawable.ladieswear,"ladies wear"));
           newArrivalsList.add(new ItemNewArrivals(R.drawable.womenbackpack,"women backpack"));
           newArrivalsList.add(new ItemNewArrivals(R.drawable.leathershoes,"men Leather Shoes"));
           newArrivalsList.add(new ItemNewArrivals(R.drawable.homeappliances,"home appliances"));
           newArrivalsList.add(new ItemNewArrivals(R.drawable.watches,"watches"));
        return newArrivalsList;


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

    public void display( Call<List<Products>> call) {
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }


        });
    }
}
