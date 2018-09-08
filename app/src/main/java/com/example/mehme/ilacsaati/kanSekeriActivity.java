package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class kanSekeriActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd MMM");
    SimpleDateFormat dateFormatTx= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    EditText etAciklama,etSeker;
    String kansekeri,aciklama,tarih;
    ScrollView scrollView;
    GraphView graphView;
    LinearLayout linnear;
    ilacSaatiDB DB = new ilacSaatiDB(kanSekeriActivity.this);
    ProgressDialog progressDoalog;
    String []headres={"seker degeri","aciklama","olcum tarihi"};
    String shortText="Merhaba,";
    String longText="asagidaki tabloda kaydedilen seker sonuclarım, olcum saatleri olcum aciklarmalarim ve bir grafik mevcuttur.\n\n";
    templatePDF templatePdf;
    private static final String KANSEKERI_ID="2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_sekeri);
        TextView tx;
        graphView = (GraphView) findViewById(R.id.grafik);
        tx = (TextView) findViewById(R.id.tarihTv);
        etAciklama = (EditText) findViewById(R.id.aciklamaEt);
        etSeker = (EditText) findViewById(R.id.kanEt);
        scrollView = (ScrollView) findViewById(R.id.scrollViewAgirlik);
        linnear=(LinearLayout)findViewById(R.id.linList);
        tx.setText(dateFormatTx.format(new Date().getTime()));
        //tx.setText(DB.DBArrayOlcum("1").get(1));
        ArrayList<String> arrayList=DB.DBArrayOlcum(KANSEKERI_ID);
        ArrayList<String>deger=new ArrayList<>();
        ArrayList<String>tarih=new ArrayList<>();
        ArrayList<String>aciklama=new ArrayList<>();
        int sayac=0;
        while (sayac<arrayList.size()){
            String dizi[]=arrayList.get(sayac).split("--");
            deger.add(dizi[0]);
            tarih.add(dizi[1]);
            aciklama.add(dizi[2]);
            sayac++;
        }

        grafik();
        createCardAll();

    }
    private ArrayList<String[]>getClients(){
        ArrayList<String []>rows=new ArrayList<>();
        rows.add(new String[]{"1","Muhammet","Metin"});
        rows.add(new String[]{"1","Muhammet","Metin2"});
        rows.add(new String[]{"1","Muhammet","Metin2"});
        rows.add(new String[]{"1","Muhammet","Metin3"});

        return rows;
    }

    private void createCardAll() {
        ilacSaatiDB db= new ilacSaatiDB(this);
        ArrayList<String>array=db.DBArrayOlcum(KANSEKERI_ID);
        int sayac=0;
        while (sayac<array.size()){
            String dizi[]=array.get(sayac).split("--");
            CardView cv = new CardView(kanSekeriActivity.this);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            linnear.addView(cv, lp);
            TextView tx1=new TextView(this);
            TextView tx2=new TextView(this);
            TextView tx3=new TextView(this);
            tx1.setText(dizi[0]);
            tx2.setText(dizi[1]);
            tx3.setText(dizi[2]);
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            cv.addView(ll,lp);
            ll.addView(tx1,lp);
            ll.addView(tx2,lp);
            ll.addView(tx3,lp);
            sayac++;
        }
    }
    private void createCard() {
        ilacSaatiDB db= new ilacSaatiDB(this);
        ArrayList<String>array=db.DBArrayOlcum(KANSEKERI_ID);
        int sayac=0;
        while (sayac<array.size()){
            if(sayac+1==array.size()) {
                String dizi[] = array.get(sayac).split("--");
                CardView cv = new CardView(kanSekeriActivity.this);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                linnear.addView(cv, lp);
                TextView tx1 = new TextView(this);
                TextView tx2 = new TextView(this);
                TextView tx3 = new TextView(this);
                tx1.setText(dizi[0]);
                tx2.setText(dizi[1]);
                tx3.setText(dizi[2]);
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.VERTICAL);
                cv.addView(ll, lp);
                ll.addView(tx1, lp);
                ll.addView(tx2, lp);
                ll.addView(tx3, lp);
            }
            sayac++;
        }
    }
    public void grafik(){
        series = new LineGraphSeries<DataPoint>(getDataPoint());

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return dateFormat.format(new Date((long) value));
                } else
                    return super.formatLabel(value, isValueX);
            }
        });
        graphView.getGridLabelRenderer().setHumanRounding(false);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(2);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String mesaj = dataPoint.getY() + " mg/dl";
                Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
            }
        });

        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graphView.addSeries(series);
    }
    private DataPoint[] getDataPoint(){
        ilacSaatiDB DB = new ilacSaatiDB(this);
        DataPoint[]dp=DB.DBArrayOlcumXY(KANSEKERI_ID);
        return dp;
    }

    public void kanKaydetClicked(View view){
        kansekeri=etSeker.getText().toString();
        aciklama=etAciklama.getText().toString();
        tarih=dateFormatTx.format(new Date().getTime());

        olcum_class olcum = new olcum_class(kansekeri,aciklama,KANSEKERI_ID,tarih);

        DB.olcum_ekle(olcum);
        grafik();
        createCard();

    }

    public void pdfCreateClicked(View view){
        kanSekeriActivity.LoginProgressTask asyn = new LoginProgressTask();
        asyn.execute();
        graphView.takeSnapshotAndShare(kanSekeriActivity.this, "exampleGraph", "GraphViewSnapshot");

        Bitmap bitmap = graphView.takeSnapshot();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
        Image myImg = null;
        try {
            myImg = Image.getInstance(stream.toByteArray());
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myImg.setAlignment(Image.MIDDLE);

        templatePdf=new templatePDF(getApplicationContext());
        templatePdf.openDocument();
        templatePdf.addMetaData("Clientes","Ventas","Marines");
        templatePdf.addTitle(kanSekeriActivity.this.getString(R.string.app_name_small),kanSekeriActivity.this.getString(R.string.title_olcum_grafik),dateFormatTx.format(new Date().getTime()));
        templatePdf.addParagraph(shortText);
        templatePdf.addParagraph(longText);
        templatePdf.addSingleTitle(kanSekeriActivity.this.getString(R.string.grafik));
        templatePdf.addImage(myImg);
        templatePdf.addSingleTitle(kanSekeriActivity.this.getString(R.string.olcum_tablosu));
        templatePdf.createTable(headres,getClients());
        templatePdf.closeDocument();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDoalog.incrementProgressBy(1);
        }
    };
    class LoginProgressTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        }
        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(kanSekeriActivity.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage(kanSekeriActivity.this.getString(R.string.installing_text));
            progressDoalog.setTitle(kanSekeriActivity.this.getString(R.string.creating_pdf));
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (progressDoalog.getProgress() <= progressDoalog
                                .getMax()) {
                            Thread.sleep(50);
                            handle.sendMessage(handle.obtainMessage());
                            if (progressDoalog.getProgress() == progressDoalog
                                    .getMax()) {
                                progressDoalog.dismiss();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        @Override
        protected void onPostExecute(Boolean result) {
            // result is the value returned from doInBackground
            progressDoalog.cancel();
            templatePdf.viewPDF();
        }
    }
}