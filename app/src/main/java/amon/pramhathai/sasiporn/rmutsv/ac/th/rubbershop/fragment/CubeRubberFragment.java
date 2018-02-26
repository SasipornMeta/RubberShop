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
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetLastPriceWheretid;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostBuyCube;

/**
 * Created by DR-PC61059 on 9/2/2561.
 */

public class CubeRubberFragment extends Fragment {

    private boolean status = true;

    private String[] loginStrings, c_idStrings, c_nameStrings ;

    private String idCustomerString, nameCustomerString, weightString, persentString,
            priceString, totalString, buyDateTimeString;

    public static CubeRubberFragment cubeRubberFragment(String[] loginStrings) {
        CubeRubberFragment cubeRubberFragment = new CubeRubberFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        cubeRubberFragment.setArguments(bundle);
        return cubeRubberFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");


//       create toolbar
        createToolbar();

//        Create Spinner
        createSpinner();

//        Show Date
        showDate();

        showLastPrice();

//        Calculate TotalPrice
        calculateTotalPrice();

//        Save Controller
        saveController();

//        Portion Controller
        portionController();


    }   //main Method

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSaveBuyCube);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status) {

                    addCube();

                } else {
                    showToast("Cannot Save Replace");
                }
            }
        });
    }

    private void addCube() {

        try {

            MyConstant myConstant = new MyConstant();
            PostBuyCube postBuyCube = new PostBuyCube(getActivity());
            postBuyCube.execute(buyDateTimeString, idCustomerString, nameCustomerString,
                    weightString, persentString, priceString, totalString,
                    myConstant.getUrlAddBunCube());

            if (Boolean.parseBoolean(postBuyCube.get())) {
                showToast("Save Success");
            } else {
                showToast("Please Try Again Cannot Save");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showToast(String messageString) {
        Toast.makeText(getActivity(), messageString, Toast.LENGTH_SHORT).show();
    }

    private void calculateTotalPrice() {
        Button button = getView().findViewById(R.id.btnCalculateTotal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    EditText weightEditText = getView().findViewById(R.id.edtWeight);
                    EditText persenEditText = getView().findViewById(R.id.edtPersent);

                    weightString = weightEditText.getText().toString().trim();
                    persentString = persenEditText.getText().toString().trim();

                    if (weightString.isEmpty()) {
                        weightString = "0";
                    } else if (persentString.isEmpty()) {
                        persentString = "0";
                    } else {

                        double weightADouble = Double.parseDouble(weightString);
                        double persenADouble = Double.parseDouble(persentString);
                        double priceADouble = Double.parseDouble(priceString);
                        totalString = Double.toString(weightADouble * priceADouble * persenADouble / 100);

                        TextView textView = getView().findViewById(R.id.txtTotal);
                        textView.setText(totalString);

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showLastPrice() {
        TextView textView = getView().findViewById(R.id.txtPriceCube);
        MyConstant myConstant = new MyConstant();
        String tag = "25FebV1";
        try {

            GetLastPriceWheretid getLastPriceWheretid = new GetLastPriceWheretid(getActivity());
            getLastPriceWheretid.execute("3", myConstant.getUrlGetLastPriceWhere_t_id());

            String resultJSoN = getLastPriceWheretid.get();
            Log.d(tag, "JSON ==> " + resultJSoN);

            JSONArray jsonArray = new JSONArray(getLastPriceWheretid.get());
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            priceString = jsonObject.getString("p_price");
            Log.d(tag, "LastPrice ==> " + priceString);
            textView.setText(priceString);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void portionController() {
        Button button = getView().findViewById(R.id.btnPortion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status) {
                    addCube();
                }


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                PortionFragment.portionInstance(loginStrings, idCustomerString,
                                        nameCustomerString, totalString, weightString, priceString))
                        .commit();
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
        Toolbar toolbar = getView().findViewById(R.id.toolbarCubeRubber);

        ((OwnerActivity) getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar()
                .setTitle(getString(R.string.cube_rubber));
        ((OwnerActivity) getActivity()).getSupportActionBar()
                .setSubtitle(getString(R.string.user_login) + loginStrings[1]);

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
        View view = inflater.inflate(R.layout.fragment_buy_rubber_cube, container, false);
        return view;
    }
}





