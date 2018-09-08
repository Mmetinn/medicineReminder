package com.example.mehme.ilacsaati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class viewPDFActivity extends AppCompatActivity {
    private PDFView pdfView;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        Bundle bundle =getIntent().getExtras();

        /*if(bundle!=null){
            file = new File(bundle.getString("path"));
        }*////storage/emulated/0/PDF/templatePDF.pdf
        file = new File("/storage/emulated/0/PDF/templatePDF.pdf");
        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();

    }
}
