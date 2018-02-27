package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by masterung on 28/2/2018 AD.
 */

public class TutorialFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Tutorial
        PDFView pdfView = getView().findViewById(R.id.pdfViewTutorial);
        pdfView.fromAsset("tutorial.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .onPageChange((OnPageChangeListener) getActivity())
                .onLoad((OnLoadCompleteListener) getActivity())
                .scrollHandle(new DefaultScrollHandle(getActivity()))
                .load();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        return view;
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}
