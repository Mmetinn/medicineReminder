package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class harcananKaloriActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd MMM");
    SimpleDateFormat dateFormatTx= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    EditText etAciklama,etSeker;
    String kalori,aciklama,tarih;
    ScrollView scrollView;
    GraphView graphView;
    LinearLayout linnear;

    ilacSaatiDB DB = new ilacSaatiDB(harcananKaloriActivity.this);
    private static final String HARCANANKALORI_ID="7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harcanan_kalori);
        TextView tx;
        graphView = (GraphView) findViewById(R.id.grafik);
        tx = (TextView) findViewById(R.id.tarihTv);
        etAciklama = (EditText) findViewById(R.id.aciklamaEt);
        etSeker = (EditText) findViewById(R.id.harcananKaloriEt);
        scrollView = (ScrollView) findViewById(R.id.scrollViewAgirlik);
        linnear=(LinearLayout)findViewById(R.id.linList);
        tx.setText(dateFormatTx.format(new Date().getTime()));
        //tx.setText(DB.DBArrayOlcum("1").get(1));
        ArrayList<String> arrayList=DB.DBArrayOlcum(HARCANANKALORI_ID);
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

    private void createCardAll() {
        ilacSaatiDB db= new ilacSaatiDB(this);
        ArrayList<String>array=db.DBArrayOlcum(HARCANANKALORI_ID);
        int sayac=0;
        while (sayac<array.size()){
            String dizi[]=array.get(sayac).split("--");
            CardView cv = new CardView(harcananKaloriActivity.this);
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
        ArrayList<String>array=db.DBArrayOlcum(HARCANANKALORI_ID);
        int sayac=0;
        while (sayac<array.size()){
            if(sayac+1==array.size()) {
                String dizi[] = array.get(sayac).split("--");
                CardView cv = new CardView(harcananKaloriActivity.this);
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
                String mesaj = dataPoint.getY() + " calori";
                Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
            }
        });
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);
        /*series.setTitle("cal");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setTextSize(30);
        graphView.getLegendRenderer().setTextColor(Color.WHITE);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);*/
        graphView.addSeries(series);
    }
    private DataPoint[] getDataPoint(){
        ilacSaatiDB DB = new ilacSaatiDB(this);
        DataPoint[]dp=DB.DBArrayOlcumXY(HARCANANKALORI_ID);
        return dp;
    }

    public void harcananKaloriKaydetClicked(View view){
        kalori=etSeker.getText().toString();
        aciklama=etAciklama.getText().toString();
        tarih=dateFormatTx.format(new Date().getTime());

        olcum_class olcum = new olcum_class(kalori,aciklama,HARCANANKALORI_ID,tarih);

        DB.olcum_ekle(olcum);
        grafik();
        createCard();
    }

}