package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/13/2018 AD.
 */

public class CustomerDataFragment extends Fragment {

    private String[] loginStrings;


    public static CustomerDataFragment customerDataInstance(String[] loginStrings) {
        CustomerDataFragment customerDataFragment = new CustomerDataFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerDataFragment.setArguments(bundle);
        return customerDataFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Show Text
        showText();


    }   // main method

    private void showText() {
        TextView nameTextView = getView().findViewById(R.id.txtName);
        TextView surNameTextView = getView().findViewById(R.id.txtSurname);
        TextView addressTextView = getView().findViewById(R.id.txtAddress);
        TextView phoneTextView = getView().findViewById(R.id.txtTel);
        TextView userTextView = getView().findViewById(R.id.txtUserLogin);
        TextView passwordTextView = getView().findViewById(R.id.txtPasswordLogin);

        nameTextView.setText(loginStrings[1]);
        surNameTextView.setText(loginStrings[2]);
        addressTextView.setText(loginStrings[3]);
        phoneTextView.setText(loginStrings[4]);
        userTextView.setText(loginStrings[5]);
        passwordTextView.setText(loginStrings[6]);

    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerData);

        ((CustomerActivity) getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.private_customer));
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.customer_login) + loginStrings[1]);

        ((CustomerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((CustomerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        View view = inflater.inflate(R.layout.fragment_customer_data, container, false);
        return view;
    }
}
