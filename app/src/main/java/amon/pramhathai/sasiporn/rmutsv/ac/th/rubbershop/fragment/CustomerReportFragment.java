package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.CustomerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/13/2018 AD.
 */

public class CustomerReportFragment extends Fragment {

    private String[] loginStrings;

    public static CustomerReportFragment customerReportInstance(String[] loginStrings) {
        CustomerReportFragment customerReportFragment = new CustomerReportFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        customerReportFragment.setArguments(bundle);
        return customerReportFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginStrings = getArguments().getStringArray("Login");

//        Create Toolbar
        createToolbar();

//        Latex Controller
        latexController();

//        Sheet Controller
        sheetController();

//        Cube Controller
        cubeController();


    }   // main method



    private void cubeController() {
        ImageView imageView = getView().findViewById(R.id.imvCustomerReportCube);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerReportCubeFragment.customerReportCubeInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void sheetController() {
        ImageView imageView = getView().findViewById(R.id.imvCustomerReportSheet);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerReportSheetFragment.customerReportSheetInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void latexController() {
        ImageView imageView = getView().findViewById(R.id.imvCustomerReportLatex);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentCustomerFragment,
                                CustomerReportLatexFragment.customerReportLatexInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarCustomerReport);

        ((CustomerActivity)getActivity()).setSupportActionBar(toolbar);

        ((CustomerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.report_customer));
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
        View view = inflater.inflate(R.layout.fragment_customer_report, container, false);
        return view;
    }
}
