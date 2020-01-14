package com.diegor.bani.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.diegor.bani.R;
import com.diegor.bani.model.Product;

import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {
    private Context context;
    private List<Product> mProductObject;
    private SharedPrefManager sharedPrefManager;
    public CheckRecyclerViewAdapter(Context context, List<Product> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
        sharedPrefManager = new SharedPrefManager(context);
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(CheckRecyclerViewHolder holder, final int position) {
        if(mProductObject == null){
            holder.productName.setText("Er zijn helaas geen producten in uw winkelwagen...");
        }else{
        holder.quantity.setText(mProductObject.get(position).getAantalString());
        holder.productName.setText(mProductObject.get(position).getArtikel());
        holder.productPrice.setText(mProductObject.get(position).getPrijsString());
        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.removeProduct();
            }
        });
        }
    }
    @Override
    public int getItemCount() {
        return mProductObject.size();
    }
}
