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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.DeleteDataCustomer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.GetAllValueFromServer;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility.MyConstant;

/**
 * Created by sasiporn on 2/8/2018 AD.
 */

public class DetailCustomerFragment extends Fragment{

    private String[] loginStrings;

    public static DetailCustomerFragment detailCustomerInstance(String[] loginStrings) {
        DetailCustomerFragment detailCustomerFragment = new DetailCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        detailCustomerFragment.setArguments(bundle);
        return detailCustomerFragment;
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
        ListView listView = getView().findViewById(R.id.listViewCustomer);
        MyConstant myConstant = new MyConstant();
        String tag = "9FebV1";

        try {

            GetAllValueFromServer getAllValueFromServer = new GetAllValueFromServer(getActivity());
            getAllValueFromServer.execute(myConstant.getUrlGetAllCustomer());
            String jsonString = getAllValueFromServer.get();
            Log.d(tag, "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] nameStrings = new String[jsonArray.length()];
            final String[] idCustomer = new String[jsonArray.length()];


            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("c_name");
                idCustomer[i] = jsonObject.getString("c_id");

            }   // for

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1, nameStrings);
            listView.setAdapter(stringArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int itemInt, long l) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.ic_action_alert);
                    builder.setTitle("จัดการข้อมูลลูกค้า");
                    builder.setMessage("คุณต้องการจะ ?");
                    builder.setPositiveButton("ลบ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteDataWhere(nameStrings[itemInt]);
                            dialogInterface.dismiss();


                        }
                    });

                    builder.setNegativeButton("แก้ไข", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contentOwnerFragment, EditCustomerFragment.editCustomerInstance(
                                            loginStrings, nameStrings[itemInt], idCustomer[itemInt]))
                                    .addToBackStack(null).commit();
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }

            });



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteDataWhere(String nameString) {
        try {

            Log.d("26FebV1", "nameDelete ==> " + nameString);

            MyConstant myConstant = new MyConstant();
            DeleteDataCustomer deleteDataCustomer = new DeleteDataCustomer(getActivity());
            deleteDataCustomer.execute(nameString, myConstant.getUrlDeleteCustomer());

            String result = deleteDataCustomer.get();
            Log.d("26FebV1", "result ==> " + result);

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
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemAddNewCustoemr) {

            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.contentOwnerFragment,
                    AddNewCustomerFragment.addNewCustomerInstance(loginStrings))
                    .addToBackStack(null)
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toobarDetailCustomer);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.detail_customer));
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
        View view = inflater.inflate(R.layout.fragment_detail_customer, container, false);
        return view;                                            //  มาจากการกด Alt+Enter
    }


}   // main class