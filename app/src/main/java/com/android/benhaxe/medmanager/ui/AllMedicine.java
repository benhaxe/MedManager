package com.android.benhaxe.medmanager.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.benhaxe.medmanager.BaseActivity;
import com.android.benhaxe.medmanager.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.android.benhaxe.medmanager.BaseActivity.DRUG_USER;
import static com.android.benhaxe.medmanager.BaseActivity.MEDICINE;
import static com.android.benhaxe.medmanager.BaseActivity.fireBaseRecyclerAdapter;
import static com.android.benhaxe.medmanager.BaseActivity.queryData;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllMedicine extends Fragment {
    public static final String TAG = AllMedicine.class.getSimpleName();

    View rootView;
    // Views
    private RecyclerView recyclerView;

    //Fire base
    private DatabaseReference dbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public AllMedicine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_all_medicine, container, false);

        recyclerView = rootView.findViewById(R.id.rv_medicine);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbRef = FirebaseDatabase.getInstance().getReference(MEDICINE);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        dbRef.keepSynced(true);

        Query complainQuery = dbRef.orderByChild(DRUG_USER).equalTo(user.getUid());
        queryData(complainQuery);

        recyclerView.setAdapter(fireBaseRecyclerAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        fireBaseRecyclerAdapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        fireBaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        fireBaseRecyclerAdapter.stopListening();
    }
}
