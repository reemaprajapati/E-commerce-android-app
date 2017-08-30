package com.example.otimus.ecommerceapp.clothingFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otimus.ecommerceapp.adapters.ClothingAdapter;
import com.example.otimus.ecommerceapp.activities.DetailActivity;
import com.example.otimus.ecommerceapp.models.ItemClothing;
import com.example.otimus.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PantsFragment extends Fragment {


    public PantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_bagsand_wallet, container, false);

        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.rec_bagsandwallet);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<ItemClothing> clothingList=new ArrayList<>();
        clothingList=getList();

        ClothingAdapter clothingAdapter=new ClothingAdapter(clothingList, new ClothingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemClothing item) {
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(clothingAdapter);
        return rootView;
    }

    private List<ItemClothing> getList(){
        List<ItemClothing> clothingsList=new ArrayList<>();
        clothingsList.add(new ItemClothing(R.drawable.bagsand1,"Brown Leather","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand2,"Gray Backpack","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand3,"Maroon Wallet","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand4," Grop Wallet","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand5,"Jute Backpack","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand6,"Laptop Bag","Rs.1120/-"));
        clothingsList.add(new ItemClothing(R.drawable.bagsand7,"Check Wallet","Rs.1120/-"));


        return clothingsList;
    }


}
