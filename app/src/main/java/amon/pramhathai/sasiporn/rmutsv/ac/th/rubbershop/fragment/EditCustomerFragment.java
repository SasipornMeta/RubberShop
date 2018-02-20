package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
        surnameeditString = getArguments().getString("surname");
        addresseditString = getArguments().getString("address");
        teleditString = getArguments().getString("tel");
        userloginString = getArguments().getString("user");
        passwordloginString = getArguments().getString("password");

//        Create Toolbar
        createToolbar();

//        Show Text
        showText();

//        check Log
        checkLog();




    }   // main method

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            saveController();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void saveController() {
        EditText nameeditText = getView().findViewById(R.id.edtEditName);
        EditText surnameeditText = getView().findViewById(R.id.edtEditSurname);
        EditText addresseditText = getView().findViewById(R.id.edtEditAddress);
        EditText teleditText = getView().findViewById(R.id.edtEditTel);
        EditText usereditText = getView().findViewById(R.id.edtEditUserLogin);
        EditText passwordeditText = getView().findViewById(R.id.edtEditPasswordLogin);

        nameeditString = nameeditText.getText().toString().trim();
        surnameeditString = surnameeditText.getText().toString().trim();
        addresseditString = addresseditText.getText().toString().trim();
        teleditString = teleditText.getText().toString().trim();
        userloginString = usereditText.getText().toString().trim();
        passwordloginString = passwordeditText.getText().toString().trim();

        if (nameeditString.isEmpty() || surnameeditString.isEmpty() ||
                addresseditString.isEmpty() || teleditString.isEmpty() ||
                userloginString.isEmpty() || passwordloginString.isEmpty()) {
//            Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));

        } else {

//            No Space
            try {
                MyConstant myConstant = new MyConstant();
                PostAddCustomerToServer postAddCustomerToServer = new PostAddCustomerToServer(getActivity());
                postAddCustomerToServer.execute(
                        nameeditString,
                        surnameeditString,
                        addresseditString,
                        teleditString,
                        userloginString,
                        passwordloginString,
                        loginStrings[2],
                        myConstant.getUrlEditCustomer());

                if (Boolean.parseBoolean(postAddCustomerToServer.get())) {

//                            Success upload
                    getActivity().getSupportFragmentManager().popBackStack();
                    Toast.makeText(getActivity(), "บันทึกข้อมูลเรียบร้อย",
                            Toast.LENGTH_SHORT).show();
                } else {

//                            Cannot upload
                    Toast.makeText(getActivity(), "ไม่สามารถบันทึกข้อมูลได้",
                            Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

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

    private void checkLog() {
        Log.d("19FebV1", "name ==> " + nameeditString);
        Log.d("19FebV1", "surname ==> " + surnameeditString);
        Log.d("19FebV1", "address ==> " + addresseditString);
        Log.d("19FebV1", "tel ==> " + teleditString);
        Log.d("19FebV1", "user ==> " + userloginString);
        Log.d("19FebV1", "password ==> " + passwordloginString);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        return view;
    }

}