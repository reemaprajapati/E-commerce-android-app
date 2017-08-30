package com.example.otimus.ecommerceapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otimus.ecommerceapp.models.ItemNewArrivals;
import com.example.otimus.ecommerceapp.R;

import java.util.List;

/**
 * Created by Otimus on 10/17/2016.
 */
public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.ViewHolder> {

    List<ItemNewArrivals> newArrivalsList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(ItemNewArrivals item);
    }

    public NewArrivalAdapter(List<ItemNewArrivals> newArrivalsList,OnItemClickListener listener) {
        this.newArrivalsList = newArrivalsList;
        this.listener=listener;

    }

    @Override
    public NewArrivalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newarrivals,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewArrivalAdapter.ViewHolder holder, int position) {


        holder.bind(newArrivalsList.get(position),listener);
        final ItemNewArrivals itemNewArrivals=newArrivalsList.get(position);
        holder.product_name.setText(itemNewArrivals.getProduct_name());
        holder.product_image.setImageResource(itemNewArrivals.getProduct_image());

    }

    @Override
    public int getItemCount() {

        return newArrivalsList==null?0:newArrivalsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        ImageView product_image;

        public ViewHolder(View itemView) {
            super(itemView);
            product_image=(ImageView)itemView.findViewById(R.id.arrival_image);
            product_name=(TextView)itemView.findViewById(R.id.arrival_text);
        }

        public void bind(final ItemNewArrivals item, final OnItemClickListener listener) {
//

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
