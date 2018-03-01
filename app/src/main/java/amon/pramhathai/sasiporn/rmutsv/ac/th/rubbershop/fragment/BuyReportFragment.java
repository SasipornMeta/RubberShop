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

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/12/2018 AD.
 */

public class BuyReportFragment extends Fragment {

    private String[] loginStrings;

    public static BuyReportFragment buyReportInstance (String[] loginStrings) {
        BuyReportFragment buyReportFragment = new BuyReportFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        buyReportFragment.setArguments(bundle);
        return buyReportFragment;
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
        ImageView imageView = getView().findViewById(R.id.imvcubeReport);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                BuyReportCubeFragment.buyReportCubeInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void sheetController() {
        ImageView imageView = getView().findViewById(R.id.imvSheetReport);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                BuyReportSheetFragment.buyReportSheetInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    private void latexController() {
        ImageView imageView = getView().findViewById(R.id.imvlatexReport);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                BuyReportLatexFragment.buyReportLatexInstance(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarBuyReport);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.buy_report));
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
        View view = inflater.inflate(R.layout.fragment_buy_report, container, false);
        return view;
    }

}   // main class
