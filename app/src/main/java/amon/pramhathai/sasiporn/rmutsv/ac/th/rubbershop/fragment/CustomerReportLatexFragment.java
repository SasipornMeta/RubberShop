package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetBuyReportWhereIdCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;

/**
 * Created by sasiporn on 2/14/2018 AD.
 */

public class CustomerReportLatexFragment extends Fragment {

    private String[] butReportStrings = new String[]{
            "http://androidthai.in.th/gif/getBuyLatexWhereIDcustomer.php",
            "http://androidthai.in.th/gif/getBuySheetWhereIDcustomer.php",
            "http://androidthai.in.th/gif/getBuyCubeWhereIDcustomer.php"
    };
    private int[] buyReportInts = new int[]{R.string.latex_report, R.string.sheet_report, R.string.cube_report};

    private int anInt = 0;

    private String[] loginStrings;

    public static CustomerReportLatexFragment customerReportLatexInstance (String[] loginStrings) {
        CustomerReportLatexFragment customerReportLatexFragment = new CustomerReportLatexFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerReportLatexFragment.setArguments(bundle);
        return customerReportLatexFragment;
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
        ListView listView = getView().findViewById(R.id.listViewReportLatex);

        try {

            MyConstant myConstant = new MyConstant();
            GetBuyReportWhereIdCustomer getBuyReportWhereIdCustomer = new GetBuyReportWhereIdCustomer(getActivity());
            getBuyReportWhereIdCustomer.execute(loginStrings[0], butReportStrings[0]
            );

            String[] dateColumnStrings = new String[]{"b1_date", "b2_date", "b3_date"};
            String[] balanceColumnStrings = new String[]{"b1_total", "b2_total", "b3_total"};

            String resultJSON = getBuyReportWhereIdCustomer.get();
            Log.d("27FebV1", "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            String[] dateTimeStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];

            String[] weightStrings = new String[jsonArray.length()];
            String[] percentStrings = new String[jsonArray.length()];
            String[] dryStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i = +1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dateTimeStrings[i] = jsonObject.getString("b1_date");
                balanceStrings[i] = jsonObject.getString("b1_total");

                weightStrings[i] = jsonObject.getString("b1_weight");
                percentStrings[i] = jsonObject.getString("b1_percent");
                dryStrings[i] = jsonObject.getString("b1_dry");
                priceStrings[i] = jsonObject.getString("b1_price");


            }

            ShowReportCustomerlatex showReportCustomerlatex = new ShowReportCustomerlatex(getActivity(),
                    dateTimeStrings, balanceStrings,weightStrings,percentStrings,dryStrings,priceStrings);
            listView.setAdapter(showReportCustomerlatex);

            getBuyReportWhereIdCustomer.cancel(true);

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

        result = Integer.toString((int) totalADouble);

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
        Toolbar toolbar = getView().findViewById(R.id.toolbarReportLatex);
        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle("รายงานการขายน้ำยาง");
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.customer_login) + " " +loginStrings[1]);

        ((CustomerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((CustomerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });

        setHasOptionsMenu(true);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_report_latex, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class
