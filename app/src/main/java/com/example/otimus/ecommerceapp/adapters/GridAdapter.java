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
 * Created by Otimus on 8/30/2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.Holder> {
    List<Products> productsList;
    private final OnItemClickListener listener;
    Context context;
    public interface OnItemClickListener {
        void onItemClick(Products item);
    }

    public GridAdapter(List<Products> productsList, OnItemClickListener listener,Context context) {
        this.productsList = productsList;
        this.listener = listener;
        this.context=context;
    }

    @Override
    public GridAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bestsellers,parent,false);
        return new Holder(itemView);    }

    @Override
    public void onBindViewHolder(GridAdapter.Holder holder, int position) {
        final Products itemproducts =productsList.get(position);
        holder.bind(productsList.get(position),listener);
//        holder.image.setImageResource(itemproducts.getProductImage());
        Picasso.with(context).load(BASE_URL+"images/"+itemproducts.getProductImage()).into(holder.image);
        holder.name.setText(itemproducts.getProductName());
        holder.price.setText(toString().valueOf(itemproducts.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return  productsList==null?0:productsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;


        public Holder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            price=(TextView)itemView.findViewById(R.id.tv_price);
            image=(ImageView) itemView.findViewById(R.id.iv_image);
        }

        public void bind(final Products item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
