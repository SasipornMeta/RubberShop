package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

public class CustomerReportSheetFragment extends Fragment {

    private String[] loginStrings;

    public static CustomerReportSheetFragment customerReportSheetInstance (String[] loginStrings) {
        CustomerReportSheetFragment customerReportSheetFragment = new CustomerReportSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerReportSheetFragment.setArguments(bundle);
        return customerReportSheetFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Create Listview
        createListview();

    }   // main method

    private void createListview() {
        ListView listView = getView().findViewById(R.id.listViewReportSheet);
        try {
            MyConstant myConstant = new MyConstant();
            GetBuyReportWhereIdCustomer getBuyReportWhereIdCustomer = new GetBuyReportWhereIdCustomer(getActivity());
            getBuyReportWhereIdCustomer.execute(loginStrings[0], myConstant.getUrlGetSheetWhereIdCustomer());

            JSONArray jsonArray = new JSONArray(getBuyReportWhereIdCustomer.get());
            String[] dataTimeStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dataTimeStrings[i] = jsonObject.getString("b2_date");
                balanceStrings[i] = jsonObject.getString("b2_total");
            }

            ShowDepositAdapter showDepositAdapter = new ShowDepositAdapter(getActivity(),
                    dataTimeStrings, balanceStrings);
            listView.setAdapter(showDepositAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarReportSheet);
        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle("รายงานการขายยางแผ่น");
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
        View view = inflater.inflate(R.layout.fragment_customer_report_sheet, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class
