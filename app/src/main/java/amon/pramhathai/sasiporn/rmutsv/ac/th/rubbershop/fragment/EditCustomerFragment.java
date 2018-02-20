package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostAddCustomerToServer;

/**
 * Created by sasiporn on 2/16/2018 AD.
 */

public class EditCustomerFragment extends Fragment {

    private String[] loginStrings;
    private String nameeditString, surnameeditString, addresseditString, teleditString,
            userloginString, passwordloginString;
    private EditText nameEditText, surnameEditText, addressEditText, telEditText,
    userEditText, passwordEditText;

    public static EditCustomerFragment editCustomerInstance(String[] loginStrings) {
        EditCustomerFragment editCustomerFragment = new EditCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        editCustomerFragment.setArguments(bundle);
        return editCustomerFragment;
    }

//    public static EditCustomerFragment editCustomerInstance(String nameeditString,
//                                                           String surnameeditString,
//                                                           String addresseditString,
//                                                           String teleditString,
//                                                           String userloginString,
//                                                           String passwordloginString) {
//        EditCustomerFragment editCustomerFragment = new EditCustomerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("name", nameeditString);
//        bundle.putString("surname", surnameeditString);
//        bundle.putString("address", addresseditString);
//        bundle.putString("tel", teleditString);
//        bundle.putString("user", userloginString);
//        bundle.putString("password", passwordloginString);
//        editCustomerFragment.setArguments(bundle);
//        return editCustomerFragment;
//
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

        nameeditString = getArguments().getString("name");
        surnameeditString = getArguments().getString("surmane");
        addresseditString = getArguments().getString("address");
        teleditString = getArguments().getString("tel");
        userloginString = getArguments().getString("user");
        passwordloginString = getArguments().getString("password");

//        Create Toolbar
        createToolbar();

//        Show Text
        showText();


    }   // main method

    private void showText() {
        nameEditText = getView().findViewById(R.id.edtEditName);
        surnameEditText = getView().findViewById(R.id.edtEditSurname);
        addressEditText = getView().findViewById(R.id.edtEditAddress);
        telEditText = getView().findViewById(R.id.edtEditTel);
        userEditText = getView().findViewById(R.id.edtEditUserLogin);
        passwordEditText = getView().findViewById(R.id.edtEditPasswordLogin);

        nameEditText.setText(nameeditString);
        surnameEditText.setText(surnameeditString);
        addressEditText.setText(addresseditString);
        telEditText.setText(teleditString);
        userEditText.setText(userloginString);
        passwordEditText.setText(passwordloginString);
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarEditCustomer);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.edit_customer));
        ((OwnerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.user_login) + loginStrings[1]);

        ((OwnerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((OwnerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        setHasOptionsMenu(true);



    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        return view;
    }

}