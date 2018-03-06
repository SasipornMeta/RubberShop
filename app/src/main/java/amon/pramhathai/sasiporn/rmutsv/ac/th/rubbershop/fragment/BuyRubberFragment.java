package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.OwnerActivity;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 2/9/2018 AD.
 */

public class BuyRubberFragment extends Fragment {

    private String[] loginStrings;

    public static BuyRubberFragment buyRubberInstance(String[] loginstrings) {
        BuyRubberFragment buyRubberFragment = new BuyRubberFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginstrings);
        buyRubberFragment.setArguments(bundle);
        return buyRubberFragment;
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
        ImageView imageView = getView().findViewById(R.id.imvcubeRubber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                CubeRubberFragment.cubeRubberFragment(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void sheetController() {
        ImageView imageView = getView().findViewById(R.id.imvSheetRubber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                SheetRubberFragment.sheetRubberFragment(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    private void latexController() {
        ImageView imageView = getView().findViewById(R.id.imvlatexRubber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentOwnerFragment,
                                LatexRubberFragment.latexRubberFragment(loginStrings))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarBuyRubber);
        ((OwnerActivity)getActivity()).setSupportActionBar(toolbar);

        ((OwnerActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.buy_rubber));
        ((OwnerActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.user_login) + " "+ loginStrings[1]);

        ((OwnerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((OwnerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_rubber, container, false);
        return view;
    }
}
