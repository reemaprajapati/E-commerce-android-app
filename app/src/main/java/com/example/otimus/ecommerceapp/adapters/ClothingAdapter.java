package com.example.otimus.ecommerceapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.R;
import com.example.otimus.ecommerceapp.models.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.otimus.ecommerceapp.rest.ApiClient.BASE_URL;

/**
 * Created by Otimus on 10/18/2016.
 */
public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>{
        List<Products> clothingList;
    private final OnItemClickListener listener;
    Context context;
    public interface OnItemClickListener {
        void onItemClick(Products item);
    }

    public ClothingAdapter(List<Products> clothingList, OnItemClickListener listener,Context context) {
        this.listener=listener;
        this.clothingList = clothingList;
        this.context=context;
    }

    @Override
    public ClothingAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemclothing,parent,false);

        return new ClothingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClothingAdapter.ClothingViewHolder holder, int position) {
        holder.bind(clothingList.get(position),listener);
        final Products products =clothingList.get(position);
        Picasso.with(context).load(BASE_URL+"images/"+products.getProductImage()).into(holder.clothing_image);

//        holder.clothing_image.setImageResource(clothing.getClothing_image());
        holder.clothing_name.setText(products.getProductName());
        holder.clothing_price.setText(toString().valueOf(products.getProductPrice()));

    }

    @Override
    public int getItemCount() {
        return clothingList==null?0:clothingList.size();
    }

    public class ClothingViewHolder extends RecyclerView.ViewHolder {

        TextView clothing_name,clothing_price;
        ImageView clothing_image;
        public ClothingViewHolder(View itemView) {
            super(itemView);
            clothing_name=(TextView)itemView.findViewById(R.id.clothing_name);
            clothing_price=(TextView)itemView.findViewById(R.id.clothing_price);
            clothing_image=(ImageView) itemView.findViewById(R.id.clothing_image);


        }
        public void bind(final Products item, final OnItemClickListener listener) {
//

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
