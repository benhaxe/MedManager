package com.android.benhaxe.medmanager.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.android.benhaxe.medmanager.BaseActivity;
import com.android.benhaxe.medmanager.R;
import com.android.benhaxe.medmanager.sync.ReminderUtilities;

public class MainActivity extends BaseActivity {

    Fragment medicineFragment;

    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        medicineFragment = new AllMedicine();

        gotoMedicine();
        useBottomNavigation();
    }

    public void gotoMedicine() {
        loadFragment(R.id.main_frame, medicineFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
