package com.example.otimus.ecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.otimus.ecommerceapp.adapters.ClothingAdapter;
import com.example.otimus.ecommerceapp.models.ItemClothing;
import com.example.otimus.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class LadiesWearActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_ladieswear);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_ladieswear);
        setSupportActionBar(toolbar);
        setTitle("Ladies Wear");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rec_ladieswear);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<ItemClothing> clothingList=new ArrayList<>();
        clothingList=getList();
        ClothingAdapter clothingAdapter=new ClothingAdapter(clothingList, new ClothingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemClothing item) {
                Intent intent=new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);            }
        });
        recyclerView.setAdapter(clothingAdapter);

    }

    private List<ItemClothing> getList(){
        List<ItemClothing> clothingsList=new ArrayList<>();
        clothingsList.add(new ItemClothing(R.drawable.greenonepiece,"Green One Piece","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.orangewhitetops,"Orange White top","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.pinkshirt,"Pink Shirt","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.lightpink,"Light Pink","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.blacktops,"Black Tops","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bluesaree,"Blue Saree Floral","Rs.5000/-"));
        clothingsList.add(new ItemClothing(R.drawable.yellowtshirt,"Yellow T-Shirt","Rs.1120/-"));


        return clothingsList;
    }



}
