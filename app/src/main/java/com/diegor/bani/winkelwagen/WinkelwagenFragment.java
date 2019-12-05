package com.diegor.bani.winkelwagen;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegor.bani.R;

public class WinkelwagenFragment extends Fragment {

    private WinkelwagenViewModel mViewModel;

    public static WinkelwagenFragment newInstance() {
        return new WinkelwagenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.winkelwagen_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WinkelwagenViewModel.class);
        // TODO: Use the ViewModel
    }

}
