package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/9/2018 AD.
 */

public class CustomerFregment extends Fragment{

    private String[] loginStrings;

    public static CustomerFregment customerInstance(String[] loginStrings) {
        CustomerFregment customerFregment = new CustomerFregment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerFregment.setArguments(bundle);
        return customerFregment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Private Controller
        privateController();

//        Price Controller
        priceController();

//        Deposit Controller
        depositController();

//        Report Controller
        reportController();


    }    // mime method


    private void reportController() {
        ImageView imageView = getView().findViewById(R.id.imvReport);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerReportFragment.customerReportInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void depositController() {
        ImageView imageView = getView().findViewById(R.id.imvDeposit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerDepositFragment.customerDepositInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void priceController() {
        ImageView imageView = getView().findViewById(R.id.imvPriceRubber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerPriceFragment.customerPriceInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void privateController() {
        ImageView imageView = getView().findViewById(R.id.imvPrivate);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerDataFragment.customerDataInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemExit) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setIcon(R.drawable.ic_action_exit);
            builder.setTitle("ออกจากระบบ");
            builder.setMessage("คุณต้องการออกจากระบบใช่หรือไม่ ?");
            builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(1);
                }
            });

            builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerHub);
        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(loginStrings[1]);
        ((CustomerActivity) getActivity()).getSupportActionBar().setSubtitle("ลุกค้า");

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }
}
