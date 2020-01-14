package com.diegor.bani.producten;

import androidx.fragment.app.FragmentManager;
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
import android.widget.Button;

import com.diegor.bani.NavDrawerActivity;
import com.diegor.bani.R;
import com.diegor.bani.producten.alcohol.AlcoholFragment;
import com.diegor.bani.producten.groenteEnFruit.GnFArtikelenFragment;
import com.diegor.bani.producten.nonAlcohol.NonAlcoholFragment;
import com.diegor.bani.producten.zuivel.ZuivelFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductenFragment extends Fragment {

    private ProductenViewModel mViewModel;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();


    public static ProductenFragment newInstance() {
        return new ProductenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.producten_fragment, container, false);

        Button GnF = (Button) view.findViewById(R.id.GnF);
        GnF.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Fragment fragment = new GnFArtikelenFragment();
               FragmentTransaction transaction = getFragmentManager().beginTransaction();

               transaction.replace(R.id.flContent, fragment);
               transaction.addToBackStack(null);

               transaction.commit();
           }
        });

        Button alcohol = (Button) view.findViewById(R.id.Alcohol);
        alcohol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AlcoholFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        Button zuivel = (Button) view.findViewById(R.id.Zuivel);
        zuivel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new ZuivelFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.flContent, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        Button nonAlcohol = (Button) view.findViewById(R.id.GeenAlcohol);
        nonAlcohol.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Fragment fragment = new NonAlcoholFragment();
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
        mViewModel = ViewModelProviders.of(this).get(ProductenViewModel.class);
        // TODO: Use the ViewModel
    }

}
