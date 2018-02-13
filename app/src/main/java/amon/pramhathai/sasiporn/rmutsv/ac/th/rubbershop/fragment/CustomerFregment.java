package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/9/2018 AD.
 */

public class CustomerFregment extends Fragment{

    private String[] loginStrings;

    public static CustomerFregment customerInstance(String[] loginStrings) {
        CustomerFregment customerFregment = new CustomerFregment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerFregment.setArguments(bundle);
        return customerFregment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Private Controller
        privateController();


    }    // mime method

    private void privateController() {
        ImageView imageView = getView().findViewById(R.id.imvPrivate);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment, CustomerDataFragment.customerDataInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });

    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerHub);
        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(loginStrings[1]);
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle("ลุกค้า");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }
}
