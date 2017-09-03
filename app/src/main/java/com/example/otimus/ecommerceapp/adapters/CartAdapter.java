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

import java.util.List;

/**
 * Created by Otimus on 9/3/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    List<Products> cartList;
    private final OnItemClickListener listener;
    Context context;

    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {

//        TODO :
    }

    @Override
    public int getItemCount() {
         return cartList==null?0:cartList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Products item);
    }

    public CartAdapter(List<Products> cartList, OnItemClickListener listener,Context context) {
        this.listener=listener;
        this.cartList = cartList;
        this.context=context;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice,productQuantity;
        ImageView productImage;

        public CartViewHolder(View itemView) {
            super(itemView);
            productName= (TextView) itemView.findViewById(R.id.tvc_productName);
            productPrice= (TextView) itemView.findViewById(R.id.tvc_productPrice);
            productQuantity= (TextView) itemView.findViewById(R.id.tv_quantity);
            productImage= (ImageView) itemView.findViewById(R.id.iv_imageIcon);
        }

    }
}
