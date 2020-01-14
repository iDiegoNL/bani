package com.diegor.bani.producten.groenteEnFruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.diegor.bani.R;
import com.diegor.bani.model.Product;
import com.diegor.bani.utils.SharedPrefManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GnFArtikelenFragment extends Fragment {

    private GnFartikelenViewModel mViewModel;

    private int cartProductNumber = 0;

    private Product aardappel;
    private Product bloemkool;

    private Gson gson;

    SharedPrefManager sharedPrefManager;
    private String mMail;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    public static GnFArtikelenFragment newInstance() {
        return new GnFArtikelenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final Context c = getActivity().getApplicationContext();
        sharedPrefManager = new SharedPrefManager(c);
        mMail = sharedPrefManager.getUserEmail();

        aardappel = new Product(1, "Aardappelen", 1.90);
        bloemkool = new Product(1, "Bloemkool", 1.55);

        gson = new Gson();

        View view = inflater.inflate(R.layout.gn_fartikelen_fragment, container, false);

        Button aardappelen = (Button) view.findViewById(R.id.aardappelen);
        aardappelen.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
                String productsFromCart = sharedPrefManager.retrieveProductsFromCart();
                if(productsFromCart.equals("")){
                    List<Product> cartProductList = new ArrayList<Product>();
                    cartProductList.add(aardappel);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPrefManager.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPrefManager.retrieveProductsFromCart();
                    Product[] storedProducts = gson.fromJson(productsInCart, Product[].class);
                    List<Product> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(aardappel);
                    String addAndStoreProduct = gson.toJson(allNewProduct);
                    sharedPrefManager.addProductToTheCart(addAndStoreProduct);
                    cartProductNumber = allNewProduct.size();
                }
                Toast.makeText(c, "Product is toegevoegd aan de winkelwagen", Toast.LENGTH_SHORT).show();
                sharedPrefManager.addProductCount(cartProductNumber);
                invalidateCart();
           }
        });

        Button bloemkolen = (Button) view.findViewById(R.id.bloemkool);
        bloemkolen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String productsFromCart = sharedPrefManager.retrieveProductsFromCart();
                if(productsFromCart.equals("")){
                    List<Product> cartProductList = new ArrayList<Product>();
                    cartProductList.add(bloemkool);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPrefManager.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPrefManager.retrieveProductsFromCart();
                    Product[] storedProducts = gson.fromJson(productsInCart, Product[].class);
                    List<Product> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(bloemkool);
                    String addAndStoreProduct = gson.toJson(allNewProduct);
                    sharedPrefManager.addProductToTheCart(addAndStoreProduct);
                    cartProductNumber = allNewProduct.size();
                }
                Toast.makeText(c, "Product is toegevoegd aan de winkelwagen", Toast.LENGTH_SHORT).show();
                sharedPrefManager.addProductCount(cartProductNumber);
                invalidateCart();
            }
        });

        return view;
    }

    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private void invalidateCart() {

        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GnFartikelenViewModel.class);
        // TODO: Use the ViewModel
    }

}
