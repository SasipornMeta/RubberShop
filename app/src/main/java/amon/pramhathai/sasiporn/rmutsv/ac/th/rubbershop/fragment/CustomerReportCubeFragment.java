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
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetBuyReportWhereIdCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;

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

//        Create Toolbar
        createToolbar();

//        Create ListView
        createListView();

    }   // main method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewReportCube);

        try {

            MyConstant myConstant = new MyConstant();
            GetBuyReportWhereIdCustomer getBuyReportWhereIdCustomer = new GetBuyReportWhereIdCustomer(getActivity());
            getBuyReportWhereIdCustomer.execute(loginStrings[0], myConstant.getUrlGetCubeWhereIdCustomer());

            JSONArray jsonArray = new JSONArray(getBuyReportWhereIdCustomer.get());
            String[] dataTimeStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];
            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dataTimeStrings[i] = jsonObject.getString("b3_date");
                balanceStrings[i] = jsonObject.getString("b3_total");
            }

            ShowDepositAdapter showDepositAdapter = new ShowDepositAdapter(getActivity(),
                    dataTimeStrings, balanceStrings);
            listView.setAdapter(showDepositAdapter);

            String totalString = findTotal(balanceStrings);
            TextView textView = getView().findViewById(R.id.txtTotal);
            textView.setText("เงินรวม ==> " + totalString);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemHome) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.contentCustomerFragment,
                    CustomerFregment.customerInstance(loginStrings))
                    .addToBackStack(null)
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarReportCube);
        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle("รายงานการขายยางก้อน");
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.customer_login) + " " +loginStrings[1]);

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
        View view = inflater.inflate(R.layout.fragment_customer_report_cube, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class
