package com.android.benhaxe.medmanager.MonthsUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class JanuaryFragment extends Fragment {
    private static final String TAG = JanuaryFragment.class.getSimpleName();

    View rootView;

    // Views
    public RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public JanuaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_months, container, false);

        // Set up the recycler view here
        recyclerView = rootView.findViewById(R.id.rv_medicine);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // What to query for
        dbRef = FirebaseDatabase.getInstance().getReference(MEDICINE);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Query query = dbRef
                .orderByChild(DRUG_USER)
                .equalTo(user.getUid());

        // Set the above query into fire base
        queryData(query);

        // Set up the fire base adapter so that queried data can show inside the Recycler view
        recyclerView.setAdapter(fireBaseRecyclerAdapter);

        Log.d(TAG, "Has Queried date: " + recyclerView.toString());
        return rootView;
    }
}
