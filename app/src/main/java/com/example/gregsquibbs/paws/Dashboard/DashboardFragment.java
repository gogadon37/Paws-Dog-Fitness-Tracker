package com.example.gregsquibbs.paws.Dashboard;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.gregsquibbs.paws.R;

/**
 * Created by George PC on 06/03/2018.
 */

public class DashboardFragment extends Fragment {


   public Toolbar dashboardToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Inflate the view to which contains the dashboard_fragement layout then return the view *******George LA

        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        //dashboardToolbar =  view.findViewById(R.id. dashboard_actionbar);
        //dashboardToolbar.setTitle("Dashboard");

        return view;

    }






}
