package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetDepositWhereID;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;

/**
 * Created by sasiporn on 2/13/2018 AD.
 */

public class CustomerDepositFragment extends Fragment{

    private String[] loginStrings;

    public static CustomerDepositFragment customerDepositInstance(String[] loginStrings) {
        CustomerDepositFragment customerDepositFragment = new CustomerDepositFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerDepositFragment.setArguments(bundle);
        return customerDepositFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Create ListView
        createListView();

    } // main method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewShowDeposit);

        try {

            MyConstant myConstant = new MyConstant();
            GetDepositWhereID getDepositWhereID = new GetDepositWhereID(getActivity());
            getDepositWhereID.execute(loginStrings[0], myConstant.getUrlGetDepositWhereID());

            String resultJSON = getDepositWhereID.get();
            Log.d("27FebV1", "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);

            String[] dateTimeStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dateTimeStrings[i] = jsonObject.getString("s_date");
                balanceStrings[i] = jsonObject.getString("s_balance");
            }

            ShowDepositAdapter showDepositAdapter = new ShowDepositAdapter(getActivity(), dateTimeStrings, balanceStrings);
            listView.setAdapter(showDepositAdapter);

            String totalString = findTotal(balanceStrings);
            TextView textView = getView().findViewById(R.id.txtTotal);
            textView.setText("Total ==> " + totalString);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String findTotal(String[] balanceStrings) {

        String result = null;
        double totalADouble = 0;

        for (int i=0; i<balanceStrings.length; i+=1) {
            totalADouble = totalADouble + Double.parseDouble(balanceStrings[i]);
        }

        result = Double.toString(totalADouble);


        return result;
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerDeposit);

        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.deposit_customer));
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
        View view = inflater.inflate(R.layout.fragment_customer_deposit, container, false);
        return view;
    }
}
