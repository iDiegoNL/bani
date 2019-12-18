package com.diegor.bani.profiel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegor.bani.MainActivity;
import com.diegor.bani.NavDrawerActivity;
import com.diegor.bani.R;
import com.diegor.bani.utils.SharedPrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfielFragment extends Fragment {

    private ProfielViewModel mViewModel;
    private DatabaseReference mDatabase;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

    private String mName;
    private String mAdres;
    private String mMail;
    private String mTel;


    public static ProfielFragment newInstance() {
        ProfielFragment fragment = new ProfielFragment();

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Context c = getActivity().getApplicationContext();

        String name = getArguments().getString("name");
        String mail = getArguments().getString("mail");
        String image = getArguments().getString("photo");

        View view = inflater.inflate(R.layout.profiel_fragment, container, false);
        View navView = inflater.inflate(R.layout.nav_header, container, false);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(name);

        EditText email = (EditText) view.findViewById(R.id.mail);
        email.setText(mail);

        CircleImageView viewer = (CircleImageView) navView.findViewById(R.id.profileImage);
        ImageView photoView = (ImageView) view.findViewById(R.id.photo);
        //photoView.setImageDrawable(viewer.getDrawable());

        Picasso.with(c)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(photoView);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfielViewModel.class);
        // TODO: Use the ViewModel
    }

}
