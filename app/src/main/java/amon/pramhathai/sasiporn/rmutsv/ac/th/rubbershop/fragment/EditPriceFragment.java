package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostAddCustomerToServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostPriceToServer;

/**
 * Created by sasiporn on 2/12/2018 AD.
 */

public class EditPriceFragment extends Fragment {

    private String[] loginStrings;
    private String[] priceStrings = new String[3];

    private String buyDateTimeString, id1String, id2String, id3String;


    public static EditPriceFragment editPriceInstance(String[] loginStrings) {
        EditPriceFragment editPriceFragment = new EditPriceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        editPriceFragment.setArguments(bundle);
        return editPriceFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Show Date
        showDate();



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

        EditText id1EditText = getView().findViewById(R.id.edtID1);
        EditText id2EditText = getView().findViewById(R.id.edtID2);
        EditText id3EditText = getView().findViewById(R.id.edtID3);

        priceStrings[0] = id1EditText.getText().toString().trim();
        priceStrings[1] = id2EditText.getText().toString().trim();
        priceStrings[2] = id3EditText.getText().toString().trim();



        MyAlert myAlert = new MyAlert(getActivity());
        MyConstant myConstant = new MyConstant();
        boolean resultBoolean = false;
        String tag = "17FebV1";

        if (priceStrings[0].isEmpty() || priceStrings[1].isEmpty() || priceStrings[2].isEmpty()) {
            myAlert.normalDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));
        } else {

            for (int i=0; i<priceStrings.length; i+=1) {

                try {

                    PostPriceToServer postPriceToServer = new PostPriceToServer(getActivity());

                    postPriceToServer.execute(
                            buyDateTimeString,
                            priceStrings[i],
                            Integer.toString(i+1),
                            myConstant.getUrlAddPrice());
                    String resutlString = postPriceToServer.get();
                    resultBoolean = Boolean.getBoolean(resutlString);
                    Log.d(tag, "Result ==> " + "[" + i + "]" + resultBoolean);

                    if (i == priceStrings.length-1) {
                        getActivity().getSupportFragmentManager().popBackStack();

//                        Success Upload
                        Toast.makeText(getActivity(), "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT)
                                .show();
                    }

                } catch (Exception e) {
                    Log.d(tag, "e ==> " + e.toString());
                }

            }



        } // if

    }   // saveController

    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtShowDate);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        buyDateTimeString = dateFormat.format(calendar.getTime());
        textView.setText(buyDateTimeString);

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarEditPrice);
        ((OwnerActivity) getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.edit_price));
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_price, container, false);
        return view;
    }

}   // main class
