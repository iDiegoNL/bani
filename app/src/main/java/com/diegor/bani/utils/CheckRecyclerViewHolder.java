package com.diegor.bani.utils;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diegor.bani.R;

public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder{
    public TextView quantity, productName, productPrice, removeProduct;
    public CheckRecyclerViewHolder(View itemView) {
        super(itemView);
        quantity = (TextView)itemView.findViewById(R.id.quantity);
        productName =(TextView)itemView.findViewById(R.id.product_name);
        productPrice = (TextView)itemView.findViewById(R.id.product_price);
        removeProduct = (TextView)itemView.findViewById(R.id.remove_from_cart);
    }
}
