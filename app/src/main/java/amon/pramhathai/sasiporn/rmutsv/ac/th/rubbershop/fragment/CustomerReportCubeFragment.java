package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/14/2018 AD.
 */

public class CustomerReportCubeFragment extends Fragment {

    private String[] loginStrings;

    public static CustomerReportCubeFragment customerReportCubeInstance (String[] loginStrings) {
        CustomerReportCubeFragment customerReportCubeFragment = new CustomerReportCubeFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerReportCubeFragment.setArguments(bundle);
        return customerReportCubeFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");



    }   // main method


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_report_cube, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class
