package com.diegor.bani.winkelwagen;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.diegor.bani.R;
import com.diegor.bani.checkout.CheckoutFragment;
import com.diegor.bani.model.Product;
import com.diegor.bani.producten.ProductenFragment;
import com.diegor.bani.utils.CheckRecyclerViewAdapter;
import com.diegor.bani.utils.SharedPrefManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinkelwagenFragment extends Fragment {

    private WinkelwagenViewModel mViewModel;

    private RecyclerView checkRecyclerView;
    private TextView subtotal;
    private double mSubtotal = 0;
    private Gson gson;
    private SharedPrefManager sharedPrefManager;

    public static WinkelwagenFragment newInstance() {
        return new WinkelwagenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.winkelwagen_fragment, container, false);
        subtotal = (TextView) view.findViewById(R.id.sub_total);

        Context c = getActivity().getApplicationContext();
        sharedPrefManager = new SharedPrefManager(c);

        gson = new Gson();

        checkRecyclerView = (RecyclerView) view.findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);

        Product[] addCartProducts = gson.fromJson(sharedPrefManager.retrieveProductsFromCart(), Product[].class);
        List<Product> productList = convertObjectArrayToListObject(addCartProducts);
        CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(c, productList);
        checkRecyclerView.setAdapter(mAdapter);
        mSubtotal = getTotalPrice(productList);
        subtotal.setText("Subtotaal exclusief BTW: €" + String.format("%.2f", mSubtotal));

        Button shoppingbutton = (Button) view.findViewById(R.id.shopping);
        assert shoppingbutton != null;
        shoppingbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment fragment = new ProductenFragment();
               FragmentTransaction transaction = getFragmentManager().beginTransaction();

               transaction.replace(R.id.flContent, fragment);
               transaction.addToBackStack(null);

               transaction.commit();
           }
        });

        Button checkButton = (Button) view.findViewById(R.id.checkout);
        assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CheckoutFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }

    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        if(allProducts != null) {
            Collections.addAll(mProduct, allProducts);
        }
        return mProduct;
    }

    private int returnQuantitybyProductName(String productName, List<Product> mProducts){
        int  quantity = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Product pObject = mProducts.get(i);
            if(pObject.getArtikel().trim().equals(productName.trim())){
                quantity++;
            }
        }
        return quantity;
    }

    private double getTotalPrice(List<Product> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Product pObject = mProducts.get(i);
            totalCost += pObject.getPrijs();
        }
        return totalCost;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WinkelwagenViewModel.class);
        // TODO: Use the ViewModel
    }

}
