package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostAddCustomerToServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostOwnerToServer;

/**
 * Created by sasiporn on 2/8/2018 AD.
 */

public class AddNewCustomerFragment extends Fragment{

    private String[] loginStrings;
    private String nameeditString, surnameeditString, addresseditString, teleditString,
            userloginString, passwordloginString;


    public static AddNewCustomerFragment addNewCustomerInstance(String[] loginStrings) {
        AddNewCustomerFragment addNewCustomerFragment = new AddNewCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        addNewCustomerFragment.setArguments(bundle);
        return addNewCustomerFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Create User Login
        createUserLogin();

//        Create Password Login
        createPasswordLogin();


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

    private void createPasswordLogin() {
        TextView textView = getView().findViewById(R.id.txtPasswordLogin);
        Random random = new Random();
        int myInt = random.nextInt(100000);
        passwordloginString = Integer.toString(myInt);
        textView.setText(passwordloginString);
    }

    private void createUserLogin(){
        TextView textView = getView().findViewById(R.id.txtUserLogin);
        Random random = new Random();
        int myInt = random.nextInt(1000);
        userloginString = "customer" + Integer.toString(myInt);
        textView.setText(userloginString);
    }

    private void saveController() {

        EditText nameeditText = getView().findViewById(R.id.edtNameCustomer);
        EditText surnameeditText = getView().findViewById(R.id.edtSurname);
        EditText addresseditText = getView().findViewById(R.id.edtAddress);
        EditText teleditText = getView().findViewById(R.id.edtTel);

        nameeditString = nameeditText.getText().toString().trim();
        surnameeditString = surnameeditText.getText().toString().trim();
        addresseditString = addresseditText.getText().toString().trim();
        teleditString = teleditText.getText().toString().trim();

        if (nameeditString.isEmpty() || surnameeditString.isEmpty() ||
                addresseditString.isEmpty() || teleditString.isEmpty()) {
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
                        myConstant.getUrlAddCustomer());

                if (Boolean.parseBoolean(postAddCustomerToServer.get())) {
//                            Success upload
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
//                            Connot upload
                    Toast.makeText(getActivity(), "Connot upload to server",
                            Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarAddCustomer);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.add_customer));
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
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);
        return view;
    }
}
