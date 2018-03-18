package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetCustomerWhereOidShop;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetLastPriceWheretid;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyAlert;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.PostBuyLatex;

/**
 * Created by sasiporn on 2/9/2018 AD.
 */

public class LatexRubberFragment extends Fragment {

    private boolean statusABoolean = true;

    private TextView dryRubbertextView;

    private String[] loginStrings, c_idStrings, c_nameStrings;

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

//        Show Last Price
        showLastPrice();

//        Calculate Controller
        calculateContoller();

//        Calculate Total Price
        calculateTotalPrice();

//        Save Controller
        saveController();

//        Portion Controller
        portionController();

//        SaveBitmap Controller


    }   // main method



    private void saveController() {
        Button button = getView().findViewById(R.id.btnSaveBuyLatex);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusABoolean) {

                    Log.d("25FebV1", "b1_date = " + buyDateTimeString);
                    Log.d("25FebV1", "c_id = " + idCustomerString);
                    Log.d("25FebV1", "c_name = " + nameCustomerString);
                    Log.d("25FebV1", "b1_weight = " + weightString);
                    Log.d("25FebV1", "b1_percent = " + persentString);
                    Log.d("25FebV1", "b1_dry = " + dryRubberString);
                    Log.d("25FebV1", "b1_price = " + priceString);
                    Log.d("25FebV1", "b1_total = " + totalString);

                    if (statusABoolean) {

//                    Add BuyRuber
                        addBuyRuber();

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentOwnerFragment,
                                        OwnerFragment.ownerInstance(loginStrings))
                                .addToBackStack(null)
                                .commit();
                    }

                } else {

                    Toast.makeText(getActivity(), "ึบันทึกข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

//    public void shareScreenShotM(View view, NestedScrollView scrollView) {
//
//        Bitmap bm = takeScreenShort(view, scrollView);
//        File file = savePic(bm);
//    }
//
//    public Bitmap takeScreenShort(View u, NestedScrollView z) {
//        u.setDrawingCacheEnabled(true);
//        int totalHeight = z.getChildAt(0).getHeight();
//        int totalWidth = z.getChildAt(0).getWidth();
//
//        Log.d("yoheight", " " + totalHeight);
//        Log.d("yowidth", " " + totalWidth);
//        u.layout(0, 0, totalWidth, totalHeight);
//        u.buildDrawingCache();
//        Bitmap b = Bitmap.createBitmap(u.getDrawingCache());
//        u.setDrawingCacheEnabled(false);
//        u.destroyDrawingCache();
//        return b;
//    }
//
//    public static File savePic(Bitmap bm) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        File sdCardDirectory = new File(Environment.getExternalStorageDirectory() + "/Foldername");
//
//        if (!sdCardDirectory.exists()) {
//            sdCardDirectory.mkdirs();
//        }
//
//        File file = null;
//        try {
//            file = new File(sdCardDirectory, Calendar.getInstance()
//                    .getTimeInMillis() + ".jpg");
//            file.createNewFile();
//            new FileOutputStream(file).write(bytes.toByteArray());
//            Log.d("Fabsolute", "File Saved:: ==>" + file.getAbsolutePath());
//            Log.d("Sabsolute", "File Saved:: ==>" + sdCardDirectory.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//
//    }

    private void addBuyRuber() {
        try {
            statusABoolean = false;

            MyConstant myConstant = new MyConstant();
            PostBuyLatex postBuyLatex = new PostBuyLatex(getActivity());
            postBuyLatex.execute(buyDateTimeString, idCustomerString, nameCustomerString,
                    weightString, persentString, dryRubberString, priceString,
                    totalString, myConstant.getUrlAddBuyLatex());

            if (Boolean.parseBoolean(postBuyLatex.get())) {
                Toast.makeText(getActivity(),"บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(),"ไม่สามารถบันทึกข้อมูลได้", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateTotalPrice() {
        Button button = getView().findViewById(R.id.btnCalculateTotal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    double dryRubber = Double.parseDouble(dryRubberString);
                    double totalPrice = dryRubber * Double.parseDouble(priceString);
                    totalString = Double.toString(totalPrice);

                    TextView textView = getView().findViewById(R.id.txtTotal);
                    textView.setText(totalString);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void showLastPrice() {
        TextView textView = getView().findViewById(R.id.txtPriceLatex);
        MyConstant myConstant = new MyConstant();
        String tag = "25FebV1";
        try {

            GetLastPriceWheretid getLastPriceWheretid = new GetLastPriceWheretid(getActivity());
            getLastPriceWheretid.execute("1", myConstant.getUrlGetLastPriceWhere_t_id());

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

                if (statusABoolean) {
                    addBuyRuber();
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

                    dryRubbertextView = getView().findViewById(R.id.txtDryRubber);
                    dryRubbertextView.setText(dryRubberString);

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

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                c_idStrings[i] = jsonObject.getString("c_id");
                c_nameStrings[i] = jsonObject.getString("c_name");

            }   // for

            idCustomerString = c_idStrings[0];
            nameCustomerString = c_nameStrings[0];

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, c_nameStrings);
            spinner.setAdapter(stringArrayAdapter);

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
        Toolbar toolbar = getView().findViewById(R.id.toolbarLatexRubber);

        ((OwnerActivity) getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.latex_rubber));
        ((OwnerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.user_login) + " "+loginStrings[1]);

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_rubber_latex, container, false);
        return view;
    }
}
