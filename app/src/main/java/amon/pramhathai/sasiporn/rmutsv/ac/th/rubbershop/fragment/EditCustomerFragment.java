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

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.EditCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetCustomerWhereName;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostAddCustomerToServer;

/**
 * Created by sasiporn on 2/16/2018 AD.
 */

public class EditCustomerFragment extends Fragment {


    private EditText c_nameEditText, c_lnameEditText, c_addressEditText, c_telEditText, c_userEditText, c_passwordEditText;
    private String c_nameString, c_lnameString, c_addressString, c_telString, c_userString, c_passwordString;
    private String c_idString, o_idshopString, nameDeleteString;
    private String[] detailCustomerStrings;

    private String[] loginStrings;
    private String nameeditString, surnameeditString, addresseditString, teleditString,
            userloginString, passwordloginString;



    public static EditCustomerFragment editCustomerInstance(String[] loginStrings,
                                                            String nameCustomerString,
                                                            String idCustomerString) {
        EditCustomerFragment editCustomerFragment = new EditCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        bundle.putString("Customer", nameCustomerString);
        bundle.putString("idCustomer", idCustomerString);
        editCustomerFragment.setArguments(bundle);
        return editCustomerFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");
        c_nameString = getArguments().getString("Customer");
        nameDeleteString = c_nameString;
        c_idString = getArguments().getString("idCustomer");

//        Initial view
        initialView();

//        Create Toolbar
        createToolbar();

////        Show Text
        showText();



    }   // main method

    private void initialView() {
        c_nameEditText = getView().findViewById(R.id.edtEditName);
        c_lnameEditText = getView().findViewById(R.id.edtEditSurname);
        c_addressEditText = getView().findViewById(R.id.edtEditAddress);
        c_telEditText = getView().findViewById(R.id.edtEditTel);
        c_userEditText = getView().findViewById(R.id.edtEditUserLogin);
        c_passwordEditText = getView().findViewById(R.id.edtEditPasswordLogin);
    }

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


        c_nameString = c_nameEditText.getText().toString().trim();
        c_lnameString = c_lnameEditText.getText().toString().trim();
        c_addressString = c_addressEditText.getText().toString().trim();
        c_telString = c_telEditText.getText().toString().trim();
        c_userString = c_userEditText.getText().toString().trim();
        c_passwordString = c_passwordEditText.getText().toString().trim();

        if (c_nameString.isEmpty() || c_lnameString.isEmpty() ||
                c_addressString.isEmpty() || c_telString.isEmpty() ||
                c_userString.isEmpty() || c_passwordString.isEmpty()) {
//            Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));

        } else {

//            No Space

            deleteDataWhere(nameDeleteString);

            try {

                MyConstant myConstant = new MyConstant();
                PostAddCustomerToServer editCustomer = new PostAddCustomerToServer(getActivity());
                editCustomer.execute(
                        c_nameString,
                        c_lnameString,
                        c_addressString,
                        c_telString,
                        c_userString,
                        c_passwordString,
                        detailCustomerStrings[7],
                        myConstant.getUrlAddCustomer());

                if (Boolean.parseBoolean(editCustomer.get())) {

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

    private void deleteDataWhere(String nameString) {
        try {

            Log.d("26FebV1", "nameDelete ==> " + nameString);

            MyConstant myConstant = new MyConstant();
            DeleteDataCustomer deleteDataCustomer = new DeleteDataCustomer(getActivity());
            deleteDataCustomer.execute(nameString, myConstant.getUrlDeleteCustomer());

            String result = deleteDataCustomer.get();
            Log.d("26FebV1", "result ==> " + result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showText() {

        try {

            String tag = "26FebV1";
            MyConstant myConstant = new MyConstant();
            String[] columnCustomerStrings = myConstant.getColumnCustomer();
            detailCustomerStrings = new String[columnCustomerStrings.length];
            GetCustomerWhereName getCustomerWhereName = new GetCustomerWhereName(getActivity());
            getCustomerWhereName.execute(c_idString, myConstant.getUrlGetCustomerWhereName());

            String resultJSON = getCustomerWhereName.get();
            Log.d(tag, "c_name ==> " + c_nameString);
            Log.d(tag, "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            for (int i=0; i<columnCustomerStrings.length; i+=1) {
                detailCustomerStrings[i] = jsonObject.getString(columnCustomerStrings[i]);
            }


            c_nameEditText.setText(detailCustomerStrings[1]);
            c_lnameEditText.setText(detailCustomerStrings[2]);
            c_addressEditText.setText(detailCustomerStrings[3]);
            c_telEditText.setText(detailCustomerStrings[4]);
            c_userEditText.setText(detailCustomerStrings[5]);
            c_passwordEditText.setText(detailCustomerStrings[6]);

        } catch (Exception e) {
            e.printStackTrace();
        }


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