package com.diegor.bani.profiel;

import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegor.bani.R;
import com.diegor.bani.model.User;
import com.firebase.client.Query;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfielFragment extends Fragment {

    private ProfielViewModel mViewModel;
    private DatabaseReference mDatabase;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

    private String mName;
    private String mAdres;
    private String mMail;
    private String mTel;

    private String adres;
    private String phone;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();


    public static ProfielFragment newInstance() {
        ProfielFragment fragment = new ProfielFragment();

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final String name = getArguments().getString("name");
        final String mail = getArguments().getString("mail");
        String image = getArguments().getString("photo");

        final DatabaseReference usersRef = ref.child("users").child("users/" + mail.replace('.', ','));

        View view = inflater.inflate(R.layout.profiel_fragment, container, false);
        View navView = inflater.inflate(R.layout.nav_header, container, false);

        final EditText adresView = (EditText) view.findViewById(R.id.adres);
        final EditText telView = (EditText) view.findViewById(R.id.tel);

        ValueEventListener userListener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                User user = dataSnapshot.getValue(User.class);

                adres = user.getAdres();
                Log.i("current data Adres: ", adres);
                phone = user.getTelephone();
                Log.i("current data phone: ", phone);

                adresView.setText(adres);
                telView.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
                Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
            }
        };
        usersRef.addValueEventListener(userListener);

        Context c = getActivity().getApplicationContext();

        final TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(name);

        TextView email = (TextView) view.findViewById(R.id.mail);
        email.setText(mail);

        CircleImageView viewer = (CircleImageView) navView.findViewById(R.id.profileImage);
        ImageView photoView = (ImageView) view.findViewById(R.id.photo);

        Picasso.with(c)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(photoView);

        Button updater = (Button) view.findViewById(R.id.wijzigButton);
        updater.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mName = name;
                mAdres = adresView.getText().toString();
                mMail = mail.replace('.', ',');
                mTel = telView.getText().toString();

                User user = new User(mName, mTel, mMail, mAdres);

                Map<String, Object> users = new HashMap<>();
                users.put("Adres", mAdres);
                users.put("Telephone", mTel);

                usersRef.child(mMail).updateChildren(users);
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfielViewModel.class);
        // TODO: Use the ViewModel
    }

}
