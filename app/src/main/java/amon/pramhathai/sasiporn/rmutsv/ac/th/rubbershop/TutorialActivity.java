package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment.TutorialFragment;

public class TutorialActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial);

        PDFView pdfView = findViewById(R.id.pdfViewTutorial);
        pdfView.fromAsset("tutorial.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .onPageChange(TutorialActivity.this)
                .onLoad(TutorialActivity.this)
                .scrollHandle(new DefaultScrollHandle(TutorialActivity.this))
                .load();

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}
