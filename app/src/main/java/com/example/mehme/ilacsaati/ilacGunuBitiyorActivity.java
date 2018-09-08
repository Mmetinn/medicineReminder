package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ilacGunuBitiyorActivity extends AppCompatActivity {
    ilacSaatiDB DB = new ilacSaatiDB(ilacGunuBitiyorActivity.this);
    GridLayout gl;
    ArrayList<String> bitmekUzereIlac;
    ArrayList<Integer> gunfarkList;
    boolean bitmismi=false;

    int gunfark;
    Point size;
    TextView tx2;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_gunu_bitiyor);
        gunfarkList=new ArrayList<>();
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


        int sayac=0;
        while (sayac<arrayAlarm.size()){
            a=arrayAlarm.get(sayac).split("--");
            alarmMillis.add(a[2]);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(a[2]));
            String v[] = DB.DBArrayHangiIlac(Integer.parseInt(a[1])).split("--");
            String t[]=v[5].split(" ");
            int sure=1;
            switch (t[1]){
                case "Haftalık":
                    sure=7;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=calendar.get(Calendar.DAY_OF_MONTH)-calendar2.get(Calendar.DAY_OF_MONTH);
                    if(sure<gunfark)
                        bitmismi=true;
                    break;
                case "Aylık":
                    sure = 30;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=calendar.get(Calendar.DAY_OF_MONTH)-calendar2.get(Calendar.DAY_OF_YEAR);
                    break;
                case "Yıllık":
                    sure=365;
                    sure*=Integer.parseInt(t[0]);
                    gunfark=sure-calendar.get(Calendar.DAY_OF_YEAR);
                    break;
            }


            if(gunfark<7 && !alarmIlacId.contains(a[1])){
                gunfarkList.add(gunfark-sure);
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
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            gl.addView(cv, lp);
            TextView tx = new TextView(ilacGunuBitiyorActivity.this);
            tx2 = new TextView(ilacGunuBitiyorActivity.this);
            LinearLayout ll2 = new LinearLayout(ilacGunuBitiyorActivity.this);
            LinearLayout ll3 = new LinearLayout(ilacGunuBitiyorActivity.this);
            LinearLayout ll4 = new LinearLayout(ilacGunuBitiyorActivity.this);

            ll2.setOrientation(LinearLayout.VERTICAL);
            ll4.setOrientation(LinearLayout.HORIZONTAL);

            tx.setText(bitmekUzereIlac.get(i)+" ");
            if(gunfarkList.get(i)<5 &&gunfarkList.get(i)>-1) {
                tx2.setText(String.valueOf(gunfarkList.get(i)) + " " + ilacGunuBitiyorActivity.this.getString(R.string.day_text));
            }
            else if(gunfarkList.get(i)<0)
                tx2.setText(ilacGunuBitiyorActivity.this.getString(R.string.medicine_is_end));
            tx2.setTextColor(Color.parseColor("#0055ff"));

            Button takeMedicBtn=new Button(ilacGunuBitiyorActivity.this);

            cv.setMinimumHeight(90);
            cv.addView(ll4,lp);
            ll4.addView(ll2,lp);
            ll4.addView(ll3,lp);
            ll2.addView(tx,lp);
            ll2.addView(tx2,lp);
            ll3.addView(takeMedicBtn,lp);
            takeMedicBtn.setBackgroundResource(R.drawable.ic_reload);


        }
    }
}
