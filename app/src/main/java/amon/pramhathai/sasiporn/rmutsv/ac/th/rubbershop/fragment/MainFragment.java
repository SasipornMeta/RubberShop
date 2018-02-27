package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;
import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.TutorialActivity;

/**
 * Created by sasiporn on 2/7/2018 AD.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Authen Controller
        authenController();                                                 // มาจากการกด option+command+m

//        pdf Controller
        pdfController();

    }   // main method

    private void pdfController() {
        TextView textView = getView().findViewById(R.id.txtPDF);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.contentCustomerFragment, new TutorialFragment())
//                        .addToBackStack(null)
//                        .commit();

                Intent intent = new Intent(getActivity(), TutorialActivity.class);
                startActivity(intent);

            }
        });
    }


    private void authenController() {
        Button button = getView().findViewById(R.id.btnstart);              //  เสร็จบรรทัดนี้ คุมทั้งบรรทัด กด option+command+m
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new AuthenFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

} // main class
