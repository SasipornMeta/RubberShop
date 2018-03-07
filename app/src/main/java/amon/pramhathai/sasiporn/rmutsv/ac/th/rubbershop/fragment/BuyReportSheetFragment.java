package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataBuySheet;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataDeposit;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetAllValueFromServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositOwnerAdapter;

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

            final String[] dateTimeStrings = new String[jsonArray.length()];
            String[] nameStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];

            String[] weightStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];



            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                dateTimeStrings[i] = jsonObject.getString("b2_date");
                nameStrings[i] = jsonObject.getString("c_name");
                balanceStrings[i] = jsonObject.getString("b2_total");

                weightStrings[i] = jsonObject.getString("b2_weight");
                priceStrings[i] = jsonObject.getString("b2_price");

            }

            ShowReportSheet showReportSheet = new ShowReportSheet(getActivity(),
                    dateTimeStrings, nameStrings, balanceStrings, weightStrings,priceStrings);
            listView.setAdapter(showReportSheet);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view,final int itemInt, long l) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.ic_action_alert);
                    builder.setTitle("ข้อมูลการรับซื้อยางแผ่น");
                    builder.setMessage("คุณต้องการจะลบใช่หรือไม่ ?");
                    builder.setPositiveButton("ลบ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteDataWhere(dateTimeStrings[itemInt]);
                            dialogInterface.dismiss();
                        }
                    });

                    builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }

            });

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

    private void deleteDataWhere(String dateTimeString) {
        try {
            Log.d("1MarV1", "dateTimeDelete ==>" + dateTimeString);
            MyConstant myConstant = new MyConstant();
            DeleteDataBuySheet deleteDataBuySheet = new DeleteDataBuySheet(getActivity());
            deleteDataBuySheet.execute(dateTimeString, myConstant.getUrlDeleteBuySheet());

            String result = deleteDataBuySheet.get();
            Log.d("1Marv1", "result ==> " + result);

            if (true) {
                Toast.makeText(getActivity(), "ลบข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
                createListView();

            } else {
                Toast.makeText(getActivity(), "ไม่สามารถลบข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    .beginTransaction().replace(R.id.contentOwnerFragment,
                    OwnerFragment.ownerInstance(loginStrings))
                    .addToBackStack(null)
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
