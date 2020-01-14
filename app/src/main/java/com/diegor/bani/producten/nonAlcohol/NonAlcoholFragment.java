package com.diegor.bani.producten.nonAlcohol;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegor.bani.R;

public class NonAlcoholFragment extends Fragment {

    private NonAlcoholViewModel mViewModel;

    public static NonAlcoholFragment newInstance() {
        return new NonAlcoholFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.non_alcohol_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NonAlcoholViewModel.class);
        // TODO: Use the ViewModel
    }

}
