package com.android.benhaxe.medmanager.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.android.benhaxe.medmanager.BaseActivity;
import com.android.benhaxe.medmanager.MonthsUI.AprilFragment;
import com.android.benhaxe.medmanager.MonthsUI.AugustFragment;
import com.android.benhaxe.medmanager.MonthsUI.DecemberFragment;
import com.android.benhaxe.medmanager.MonthsUI.FebruaryFragment;
import com.android.benhaxe.medmanager.MonthsUI.JanuaryFragment;
import com.android.benhaxe.medmanager.MonthsUI.JulyFragment;
import com.android.benhaxe.medmanager.MonthsUI.JuneFragment;
import com.android.benhaxe.medmanager.MonthsUI.MarchFragment;
import com.android.benhaxe.medmanager.MonthsUI.MayFragment;
import com.android.benhaxe.medmanager.MonthsUI.NovemberFragment;
import com.android.benhaxe.medmanager.MonthsUI.OctoberFragment;
import com.android.benhaxe.medmanager.MonthsUI.SeptemberFragment;
import com.android.benhaxe.medmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineByMonths extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_by_months);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tablayout = findViewById(R.id.tabs);
        // Assign the viewpager to tab
        tablayout.setupWithViewPager(viewPager);

        useBottomNavigation();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new JanuaryFragment(), "January");
        viewPagerAdapter.addFragment(new FebruaryFragment(), "February");
        viewPagerAdapter.addFragment(new MarchFragment(), "March");
        viewPagerAdapter.addFragment(new AprilFragment(), "April");
        viewPagerAdapter.addFragment(new MayFragment(), "May");
        viewPagerAdapter.addFragment(new JuneFragment(), "June");
        viewPagerAdapter.addFragment(new JulyFragment(), "July");
        viewPagerAdapter.addFragment(new AugustFragment(), "August");
        viewPagerAdapter.addFragment(new SeptemberFragment(), "September");
        viewPagerAdapter.addFragment(new OctoberFragment(), "October");
        viewPagerAdapter.addFragment(new NovemberFragment(), "November");
        viewPagerAdapter.addFragment(new DecemberFragment(), "December");

        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
