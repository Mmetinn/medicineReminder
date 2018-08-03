package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
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

public class adimSayActivity extends AppCompatActivity implements SensorEventListener{
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd MMM");
    SimpleDateFormat dateFormatTx= new SimpleDateFormat("dd MMM HH:MM");
    EditText etAciklama,etAdim;
    String adim,aciklama,tarih;
    ScrollView scrollView;
    GraphView graphView;
    LinearLayout linnear;

    SensorManager adimSensor;
    boolean running=false;

    ilacSaatiDB DB = new ilacSaatiDB(adimSayActivity.this);
    private static final String ADIMSAY_ID="3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adim_say);
        TextView tx;
        graphView = (GraphView) findViewById(R.id.grafik);
        tx = (TextView) findViewById(R.id.tarihTv);
        etAciklama = (EditText) findViewById(R.id.aciklamaEt);
        etAdim = (EditText) findViewById(R.id.adimEt);
        scrollView = (ScrollView) findViewById(R.id.scrollViewAgirlik);
        adimSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        linnear=(LinearLayout)findViewById(R.id.linList);
        tx.setText(dateFormatTx.format(new Date().getTime()));
        //tx.setText(DB.DBArrayOlcum("1").get(1));
        ArrayList<String> arrayList=DB.DBArrayOlcum(ADIMSAY_ID);
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
        ArrayList<String>array=db.DBArrayOlcum(ADIMSAY_ID);
        int sayac=0;
        while (sayac<array.size()){
            String dizi[]=array.get(sayac).split("--");
            CardView cv = new CardView(adimSayActivity.this);
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
        ArrayList<String>array=db.DBArrayOlcum(ADIMSAY_ID);
        int sayac=0;
        while (sayac<array.size()){
            if(sayac+1==array.size()) {
                String dizi[] = array.get(sayac).split("--");
                CardView cv = new CardView(adimSayActivity.this);
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
                String mesaj = dataPoint.getY() + " "+adimSayActivity.this.getString(R.string.step_text);
                Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
            }
        });

        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);
        /*series.setTitle("adım");
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setTextSize(30);
        graphView.getLegendRenderer().setTextColor(Color.WHITE);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);*/
        graphView.addSeries(series);
    }
    private DataPoint[] getDataPoint(){
        ilacSaatiDB DB = new ilacSaatiDB(this);
        DataPoint[]dp=DB.DBArrayOlcumXY(ADIMSAY_ID);
        return dp;
    }

    public void adimKaydetClicked(View view){
        adim=etAdim.getText().toString();
        aciklama=etAciklama.getText().toString();
        tarih=dateFormatTx.format(new Date().getTime());

        olcum_class olcum = new olcum_class(adim,aciklama,ADIMSAY_ID,tarih);

        DB.olcum_ekle(olcum);
        grafik();
        createCard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor countSensor = adimSensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(adimSensor!=null){
            adimSensor.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        }else {
            Toast.makeText(getApplicationContext(),"Not found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            etAciklama.setText(String.valueOf(event.values[0]));
            Log.d(String.valueOf(event.values[0]), "onSensorChanged: ");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}