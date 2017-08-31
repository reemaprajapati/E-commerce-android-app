package com.example.otimus.ecommerceapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.models.ItemBestSellers;
import com.example.otimus.ecommerceapp.R;

import java.util.List;

/**
 * Created by Otimus on 10/17/2016.
 */
public class BestSellersAdapter extends RecyclerView.Adapter<BestSellersAdapter.MyViewHolder> {

    List<ItemBestSellers> bestSellersList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(ItemBestSellers item);
    }
    public BestSellersAdapter(List<ItemBestSellers> bestSellersList, OnItemClickListener listener) {
        this.bestSellersList = bestSellersList;
        this.listener=listener;
    }

    @Override
    public BestSellersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bestsellers,parent,false);

        return new MyViewHolder(itemView);
   }

    @Override
    public void onBindViewHolder(BestSellersAdapter.MyViewHolder holder, int position) {
        final ItemBestSellers itemBestSellers =bestSellersList.get(position);
        holder.bind(bestSellersList.get(position),listener);
        holder.newarrival_image.setImageResource(itemBestSellers.getNew_image());
        holder.newarrival_name.setText(itemBestSellers.getNew_name());
        holder.newarrival_price.setText(itemBestSellers.getNew_price());

    }

    @Override
    public int getItemCount() {
        return bestSellersList==null?0:bestSellersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newarrival_name,
                 newarrival_price;
        ImageView newarrival_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            newarrival_name=(TextView)itemView.findViewById(R.id.tv_name);
            newarrival_price=(TextView)itemView.findViewById(R.id.tv_price);
            newarrival_image=(ImageView) itemView.findViewById(R.id.iv_image);
        }


        public void bind(final ItemBestSellers item, final OnItemClickListener listener) {
//

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }



}
