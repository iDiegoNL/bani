package com.diegor.bani.checkout;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.diegor.bani.R;
import com.diegor.bani.home.HomeFragment;
import com.diegor.bani.model.Product;
import com.diegor.bani.producten.ProductenFragment;
import com.diegor.bani.utils.SharedPrefManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutFragment extends Fragment {

    private CheckoutViewModel mViewModel;
    private SharedPrefManager sharedPrefManager;
    private Gson gson;
    private String producten;
    private int i = 0;

    public static CheckoutFragment newInstance() {
        return new CheckoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        Context c = getActivity().getApplicationContext();
        sharedPrefManager = new SharedPrefManager(c);
        String mail = sharedPrefManager.getUserEmail();

        gson = new Gson();

        View view =  inflater.inflate(R.layout.checkout_fragment, container, false);

        FrameLayout frame = (FrameLayout) view.findViewById(R.id.checkFrame);

        Product[] addCartProducts = gson.fromJson(sharedPrefManager.retrieveProductsFromCart(), Product[].class);
        List<Product> productList = convertObjectArrayToListObject(addCartProducts);

        Map<String, Object> dbProducten = new HashMap<>();

        dbProducten.put(String.valueOf(i), productList);
        i++;

        ref.child("Order").child(mail.replace('.', ',')).updateChildren(dbProducten);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.removeProduct();

                Fragment fragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
        // TODO: Use the ViewModel
    }

    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        if(allProducts != null) {
            Collections.addAll(mProduct, allProducts);
        }
        return mProduct;
    }

}
