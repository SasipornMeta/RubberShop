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

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetAllValueFromServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;

/**
 * Created by sasiporn on 2/13/2018 AD.
 */

public class BuyReportSheetFragment extends Fragment {

    private String[] loginStrings;

    public static BuyReportSheetFragment buyReportSheetInstance (String[] loginStrings) {
        BuyReportSheetFragment buyReportSheetFragment = new BuyReportSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        buyReportSheetFragment.setArguments(bundle);
        return buyReportSheetFragment;
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
        ListView listView = getView().findViewById(R.id.listViewBuyReportSheet);
        try {
            MyConstant myConstant = new MyConstant();
            GetAllValueFromServer getAllValueFromServer = new GetAllValueFromServer(getActivity());
            getAllValueFromServer.execute(myConstant.getUrlGetAllBuySheet());

            JSONArray jsonArray = new JSONArray(getAllValueFromServer.get());

            String[] dateTimeStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];
//            String[] nameStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dateTimeStrings[i] = jsonObject.getString("b2_date");
                balanceStrings[i] = jsonObject.getString("b2_total");
//                nameStrings[i] = jsonObject.getString("c_name");

            }

            ShowDepositAdapter showDepositAdapter = new ShowDepositAdapter(getActivity(),
                    dateTimeStrings, balanceStrings);
            listView.setAdapter(showDepositAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarBuyReportSheet);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.sheet_report));
        ((OwnerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.user_login) + " "+ loginStrings[1]);

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
        View view = inflater.inflate(R.layout.fragment_buy_report_sheet, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class
