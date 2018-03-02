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
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataDeposit;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetAllValueFromServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetDepositWhereID;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositAdapter;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.ShowDepositOwnerAdapter;

/**
 * Created by sasiporn on 3/1/2018 AD.
 */

public class DepositFragment extends Fragment {

    private String[] loginStrings;

    public static DepositFragment depositInstance (String[] loginStrings) {
        DepositFragment depositFragment = new DepositFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        depositFragment.setArguments(bundle);
        return depositFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Create ListView
        createListView();



    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewDepositOwner);
        try {
            MyConstant myConstant = new MyConstant();
            GetAllValueFromServer getAllValueFromServer = new GetAllValueFromServer(getActivity());
            getAllValueFromServer.execute(myConstant.getUrlGetAllDeposit());

            JSONArray jsonArray = new JSONArray(getAllValueFromServer.get());

            final String[] dateTimeStrings = new String[jsonArray.length()];
            String[] idStrings = new String[jsonArray.length()];
            String[] balanceStrings = new String[jsonArray.length()];

            for (int i = 0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                dateTimeStrings[i] = jsonObject.getString("s_date");
                idStrings[i] = jsonObject.getString("c_id");
                balanceStrings[i] = jsonObject.getString("s_balance");
            }

            ShowDepositOwnerAdapter showDepositOwnerAdapter = new ShowDepositOwnerAdapter(getActivity(),
                    dateTimeStrings,
                    idStrings,
                    balanceStrings);
            listView.setAdapter(showDepositOwnerAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int itemInt, long l) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.ic_action_alert);
                    builder.setTitle("ข้อมูลเงินฝาก");
                    builder.setMessage("ต้องการเบิกเงินที่ฝากไว้หรือ ?");
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


                } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteDataWhere(String idString) {
        try {
            Log.d("1MarV1", "dateDelete ==>" + idString);

            MyConstant myConstant = new MyConstant();
            DeleteDataDeposit deleteDataDeposit = new DeleteDataDeposit(getActivity());
            deleteDataDeposit.execute(idString, myConstant.getUrlDeleteDeposit());

            String result = deleteDataDeposit.get();
            Log.d("1MarV1", "result ==> " + result);

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

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarDepositOwner);

        ((OwnerActivity) getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle("เงินฝากของลูกค้า");
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
        View view = inflater.inflate(R.layout.fragment_deposit, container, false);
        return view;                                                        //  มาจากการกด Alt+Enter
    }

}
