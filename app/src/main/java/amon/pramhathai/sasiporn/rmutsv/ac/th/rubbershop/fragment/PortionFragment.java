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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetCustomerWhereOidShop;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostSale;

/**
 * Created by sasiporn on 2/23/2018 AD.
 */

public class PortionFragment extends Fragment {

    private String[] loginStrings, c_idStrings, c_nameStrings;

    private String idCustomerString, nameCustomerString, buyDateTimeString, totalString,
            weightString, priceString, s_statusString, s_blanceString;


    public static PortionFragment portionInstance(
            String[] loginStrings,
            String idCustomerString,
            String nameCustomerString,
            String totalPriceString,
            String weightString,
            String priceString) {

        PortionFragment portionFragment = new PortionFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        bundle.putString("idCustomer", idCustomerString);
        bundle.putString("nameCustomer", nameCustomerString);
        bundle.putString("totalPrice", totalPriceString);
        bundle.putString("Weight", weightString);
        bundle.putString("Price", priceString);
        portionFragment.setArguments(bundle);
        return portionFragment;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        getValueFromArgument();

//        Create Toolbar
        createToolbar();

//        Show Date
        showDate();

//        Calculate Controller
        calculateController();

//        Save Sale Controller
        saveSaleController();


    }   // main method

    private void saveSaleController() {
        Button button = getView().findViewById(R.id.btnSaveSale);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    MyConstant myConstant = new MyConstant();
                    PostSale postSale = new PostSale(getActivity());
                    postSale.execute(buyDateTimeString, weightString, priceString,
                            totalString, idCustomerString, s_statusString,
                            s_blanceString, myConstant.getUrlAddSale());

                    if (Boolean.parseBoolean(postSale.get())) {
                        getActivity().getSupportFragmentManager()
                                .popBackStack();

                    } else {
                        showToase("Please Try Again Cannot Save");
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void calculateController() {
        Button button = getView().findViewById(R.id.btnCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = getView().findViewById(R.id.edtPersent);
                String persentString = editText.getText().toString().trim();

                if (persentString.isEmpty()) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Have Space", "Please Fill Percent");
                } else {
                    calculatePercent(persentString);
                }

            }
        });
    }

    private void calculatePercent(String persentString) {

        double percentADouble = Double.parseDouble(persentString);
        if (percentADouble > 100) {
            showToase("Percent Over");
        } else {

            double totalPriceADouble = Double.parseDouble(totalString);
            double s_StatusADouble = totalPriceADouble * percentADouble / 100;
            double s_BalanceADoubel = totalPriceADouble - s_StatusADouble;

            s_statusString = Double.toString(s_StatusADouble);
            s_blanceString = Double.toString(s_BalanceADoubel);

            TextView sStatusTextView = getView().findViewById(R.id.txts_status);
            sStatusTextView.setText(s_statusString);

            TextView sBlanceTextView = getView().findViewById(R.id.txtb_balance);
            sBlanceTextView.setText(s_blanceString);

        }

    }

    private void showToase(String messageString) {
        Toast.makeText(getActivity(), messageString, Toast.LENGTH_SHORT).show();
    }

    private void getValueFromArgument() {
        loginStrings = getArguments().getStringArray("Login");
        idCustomerString = getArguments().getString("idCustomer");
        nameCustomerString = getArguments().getString("nameCustomer");
        totalString = getArguments().getString("totalPrice");
        weightString = getArguments().getString("Weight");
        priceString = getArguments().getString("Price");
    }


    private void showDate() {

        TextView textView = getView().findViewById(R.id.txtShowDate);
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        buyDateTimeString = dateFormat.format(calendar.getTime());
        textView.setText(buyDateTimeString);

        TextView customerTextView1 = getView().findViewById(R.id.txtCustomer);
        customerTextView1.setText(nameCustomerString);

        TextView totalPriceTextView1 = getView().findViewById(R.id.txtTotal);
        totalPriceTextView1.setText(totalString);

    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarPortion);

        ((OwnerActivity) getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle("แบ่งจ่าย");
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
        View view = inflater.inflate(R.layout.fragment_portion, container, false);
        return view;
    }


}
