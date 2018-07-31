package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ilacGunuBitiyorActivity extends AppCompatActivity {
    ilacSaatiDB DB = new ilacSaatiDB(ilacGunuBitiyorActivity.this);
    GridLayout gl;
    ArrayList<String> bitmekUzereIlac;

    int gunfark;
    Point size;
    TextView tx2;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_gunu_bitiyor);
        ArrayList<String>listBitmekUzereIlac=hesapla();
        gl = (GridLayout)findViewById(R.id.gridimm);
        gl.setRowCount(listBitmekUzereIlac.size());
        gridOlustur(listBitmekUzereIlac.size());
    }
    public ArrayList<String> hesapla(){
        ArrayList<String> arrayAlarm=DB.DBArrayAlarm();
        ArrayList<String> alarmMillis=new ArrayList<>();
        ArrayList<String> alarmIlacId=new ArrayList<>();
        bitmekUzereIlac=new ArrayList<>();
        String []a=new String[100];


        long now = System.currentTimeMillis();
        int sayac=0;
        while (sayac<arrayAlarm.size()){
            a=arrayAlarm.get(sayac).split("--");
            alarmMillis.add(a[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(a[2]));
            String v[] = DB.DBArrayHangiIlac(Integer.parseInt(a[1])).split("--");
            String t[]=v[5].split(" ");
            int sure=0;
            switch (t[1]){
                case "Hafta":
                    sure=7;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=sure-calendar.get(Calendar.DAY_OF_MONTH);
                case "Ay":
                    sure = 30;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=sure-calendar.get(Calendar.DAY_OF_MONTH);
                case "Yıl":
                    sure=365;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=sure-calendar.get(Calendar.DAY_OF_YEAR);
            }


            if(gunfark<7 && !alarmIlacId.contains(a[1])){
                bitmekUzereIlac.add(DB.DBArrayHangiIlac(Integer.parseInt(a[1])));
                alarmIlacId.add(a[1]);
            }
            sayac++;
        }
        return bitmekUzereIlac;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void gridOlustur(int gridRowSize){
        for (int i = 0 ; i < gridRowSize ; i++){
            CardView cv = new CardView(ilacGunuBitiyorActivity.this);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            gl.addView(cv, lp);
            TextView tx = new TextView(ilacGunuBitiyorActivity.this);
            tx2 = new TextView(ilacGunuBitiyorActivity.this);
            LinearLayout ll2 = new LinearLayout(ilacGunuBitiyorActivity.this);
            ll2.setOrientation(LinearLayout.VERTICAL);
            tx.setText(bitmekUzereIlac.get(i)+" ");
            tx2.setText(String.valueOf(gunfark)+" "+R.string.day_text);
            tx2.setTextColor(Color.parseColor("#0055ff"));
            tx.animate().translationY(10/2).withStartAction(new Runnable() {
                @Override
                public void run() {
                    tx2.animate().translationY(10/4);
                }
            });
            cv.setMinimumHeight(90);
            cv.addView(ll2,lp);
            ll2.addView(tx,lp);
            ll2.addView(tx2,lp);


        }
    }
}
