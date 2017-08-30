package com.example.otimus.ecommerceapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.models.ItemClothing;
import com.example.otimus.ecommerceapp.R;

import java.util.List;

/**
 * Created by Otimus on 10/18/2016.
 */
public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>{
        List<ItemClothing> clothingList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(ItemClothing item);
    }

    public ClothingAdapter(List<ItemClothing> clothingList, OnItemClickListener listener) {
        this.listener=listener;
        this.clothingList = clothingList;
    }

    @Override
    public ClothingAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemclothing,parent,false);

        return new ClothingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClothingAdapter.ClothingViewHolder holder, int position) {
        holder.bind(clothingList.get(position),listener);
        final ItemClothing clothing =clothingList.get(position);
        holder.clothing_image.setImageResource(clothing.getClothing_image());
        holder.clothing_name.setText(clothing.getClothing_name());
        holder.clothing_price.setText(clothing.getClothing_price());

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
        public void bind(final ItemClothing item, final OnItemClickListener listener) {
//

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
