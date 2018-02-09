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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetCustomerWhereOidShop;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;

/**
 * Created by sasiporn on 2/9/2018 AD.
 */

public class LatexRubberFragment extends Fragment {

    private String[] loginStrings, c_idStrings, c_nameStrings ;

    private String idCustomerString, nameCustomerString, weightString, persentString,
            dryRubberString, priceString, totalString, buyDateTimeString;

    public static LatexRubberFragment latexRubberFragment(String[] loginStrings) {
        LatexRubberFragment latexRubberFragment = new LatexRubberFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        latexRubberFragment.setArguments(bundle);
        return latexRubberFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Create Spinner
        createSpinner();

//        Show Date
        showDate();

//        Calculate Contoller
        calculateContoller();





    }   // main method





    private void calculateContoller() {
        Button button = getView().findViewById(R.id.btnCalculate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText weightEditText = getView().findViewById(R.id.edtWeight);
                EditText persentEditText = getView().findViewById(R.id.edtPersent);

                weightString = weightEditText.getText().toString().trim();
                persentString = persentEditText.getText().toString().trim();

                if (weightString.isEmpty() || persentString.isEmpty()) {

                    weightString = "0";
                    persentString = "0";

                } else {

//                    int dryRubberInt = Integer.parseInt(weightString) * Integer.parseInt(persentString) /100;

                    double dryRubberDouble = Double.parseDouble(weightString) *
                            Double.parseDouble(persentString) / 100;

                    dryRubberString = Double.toString(dryRubberDouble);

                    TextView textView = getView().findViewById(R.id.txtDryRubber);
                    textView.setText(dryRubberString);

                }

            }
        });

    }

    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtShowDate);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        buyDateTimeString = dateFormat.format(calendar.getTime());
        textView.setText(buyDateTimeString);

    }


    private void createSpinner() {
        Spinner spinner = getView().findViewById(R.id.spinnerCustomer);

        MyConstant myConstant = new MyConstant();
        try {

            GetCustomerWhereOidShop getCustomerWhereOidShop = new GetCustomerWhereOidShop(getActivity());
            getCustomerWhereOidShop.execute(loginStrings[2],
                    myConstant.getUrlGetCustomerWhereOidShop());

            String jsonSrting = getCustomerWhereOidShop.get();
            Log.d("9FebV1", "JSON ==> " + jsonSrting);

            JSONArray jsonArray = new JSONArray(jsonSrting);

            c_idStrings = new String[jsonArray.length()];
            c_nameStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                c_idStrings[i] = jsonObject.getString("c_id");
                c_nameStrings[i] = jsonObject.getString("c_name");

            }   // for

            idCustomerString = c_idStrings[0];
            nameCustomerString = c_nameStrings[0];



            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, c_nameStrings);
            spinner.setAdapter(stringArrayAdapter );

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    idCustomerString = c_idStrings[i];
                    nameCustomerString = c_nameStrings[i];


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                    idCustomerString = c_idStrings[0];
                    nameCustomerString = c_nameStrings[0];



                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarLatexRubber);

        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.latex_rubber));
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
        View view = inflater.inflate(R.layout.fragment_buy_rubber_latex, container, false);
        return view;
    }
}
