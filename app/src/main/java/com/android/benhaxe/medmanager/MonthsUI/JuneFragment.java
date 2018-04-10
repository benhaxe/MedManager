package com.android.benhaxe.medmanager.MonthsUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.benhaxe.medmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuneFragment extends Fragment {
    View rootView;


    public JuneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_months, container, false);



        return rootView;
    }

}
