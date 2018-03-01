package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetLastPriceWheretid;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;

/**
 * Created by sasiporn on 2/13/2018 AD.
 */

public class CustomerPriceFragment extends Fragment {

    private String[] loginStrings;

    private String buyDateTimeString;


    public static CustomerPriceFragment customerPriceInstance(String[] loginStrings) {
        CustomerPriceFragment customerPriceFragment = new CustomerPriceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerPriceFragment.setArguments(bundle);
        return customerPriceFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Show Date
        showDate();

//        Show Price
        showPrice();


    }   // main method

    private void showPrice() {
        TextView latexTextView = getView().findViewById(R.id.txtPriceLatex);
        TextView sheetTextView = getView().findViewById(R.id.txtPriceSheet);
        TextView cubbeTextView = getView().findViewById(R.id.txtPriceCube);

        latexTextView.setText(findPriceWheretid("1"));
        sheetTextView.setText(findPriceWheretid("2"));
        cubbeTextView.setText(findPriceWheretid("3"));


    }

    private String findPriceWheretid(String i_idString) {
        try {

            MyConstant myConstant = new MyConstant();
            String result = null;
            GetLastPriceWheretid getLastPriceWheretid = new GetLastPriceWheretid(getActivity());
            getLastPriceWheretid.execute(i_idString, myConstant.getUrlGetLastPriceWhere_t_id());

            String resultJSON = getLastPriceWheretid.get();
            JSONArray jsonArray = new JSONArray(resultJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            result = jsonObject.getString("p_price");


            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtShowDate);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        buyDateTimeString = dateFormat.format(calendar.getTime());
        textView.setText(buyDateTimeString);

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerPrice);

        ((CustomerActivity) getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.price_customer));
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.customer_login) + " "+ loginStrings[1]);

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
        View view = inflater.inflate(R.layout.fragment_customer_price, container, false);
        return view;
    }
}
