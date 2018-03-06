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
import android.widget.Button;
import android.widget.ImageView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/8/2018 AD.
 */

public class OwnerFragment extends Fragment {

    private String[] loginStrings;

    public static OwnerFragment ownerInstance(String[] loginStrings) {
        OwnerFragment ownerFragment = new OwnerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        ownerFragment.setArguments(bundle);

        return ownerFragment;                       //  มาจากการกด Alt+Enter
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Login Vale
        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Customer Controller
        customerController();

//        BuyRubber Controller
        buyRubberContoller();

//        Price Controller
        priceController();

//        Buy Report Controller
        buyReportController();

//        Deposit Controller
        depositController();

    }   // main method



    private void depositController() {
        ImageView imageView = getView().findViewById(R.id.imvDeposit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                DepositFragment.depositInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    private void buyReportController() {
        ImageView imageView = getView().findViewById(R.id.imvReportBuy);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                BuyReportFragment.buyReportInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void priceController() {
        ImageView imageView = getView().findViewById(R.id.imvEditPrice);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                EditPriceFragment.editPriceInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void buyRubberContoller() {
        ImageView imageView = getView().findViewById(R.id.imvBuyRubber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                BuyRubberFragment.buyRubberInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    private void customerController() {
        ImageView imageView = getView().findViewById(R.id.imvCustomer);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                DetailCustomerFragment.detailCustomerInstance(loginStrings))
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
        Toolbar toolbar = getView().findViewById(R.id.toobarOwnerHub);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(loginStrings[1]);
        ((OwnerActivity) getActivity()).getSupportActionBar().setSubtitle("เจ้าของร้าน");

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner, container, false);
        return view;                                                        //  มาจากการกด Alt+Enter
    }




}   // main class
