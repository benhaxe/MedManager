package com.android.benhaxe.medmanager.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.android.benhaxe.medmanager.BaseActivity;
import com.android.benhaxe.medmanager.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AllMed extends BaseActivity {
    public static final String TAG = AllMed.class.getSimpleName();

    public static Toolbar toolbar;
    private SearchView searchView;

    // Views
    private RecyclerView recyclerView;

    //Fire base
    private DatabaseReference dbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv_medicine);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbRef = FirebaseDatabase.getInstance().getReference(MEDICINE);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        dbRef.keepSynced(true);

        Query complainQuery = dbRef.orderByChild(DRUG_USER).equalTo(user.getUid());
        queryData(complainQuery);

        recyclerView.setAdapter(fireBaseRecyclerAdapter);
        useBottomNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(fireBaseRecyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(fireBaseRecyclerAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fireBaseRecyclerAdapter.stopListening();
    }
}
