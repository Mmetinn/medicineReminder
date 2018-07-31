package com.example.mehme.ilacsaati;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import java.util.ArrayList;
import java.util.Collections;

public class girisActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nv;
    int stepGap;
    String dataEnd="0", dataBack="0";
    Button button1,button2,button3;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    TextView tx1,tx2,tx3;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);


        nv=(NavigationView)findViewById(R.id.navi);
        setNavigationViewListner();
        drawerLayout=(DrawerLayout)findViewById(R.id.girisAct);
        ll=(LinearLayout)findViewById(R.id.linearLayout);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        tx1=(TextView)findViewById(R.id.tx1);
        tx2=(TextView)findViewById(R.id.tx2);
        tx3=(TextView)findViewById(R.id.tx3);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.ac,R.string.kapa);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        drawerLayout.requestLayout();

        DecoView decoView = (DecoView) findViewById(R.id.dynamicArcView);

        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 50, 0)
                .build();

        int backIndex = decoView.addSeries(seriesItem);

        final SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#FFFF8800"))
                .setRange(0, 250, 0)
                .build();

        int series1Index = decoView.addSeries(seriesItem2);

        final TextView textPercentage = (TextView) findViewById(R.id.idTxKilo);
        final TextView textNekadar = (TextView) findViewById(R.id.idTxVerdin);

        seriesItem2.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.valueOf((int)currentPosition)+" "+girisActivity.this.getString(R.string.kilo_text));//(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        ilacSaatiDB dataB=new ilacSaatiDB(this);
        ArrayList<String> kiloArray= dataB.DBArrayOlcum("1");
        ArrayList<Float> olcumler=new ArrayList<>();

        int counter=0;
        String text = girisActivity.this.getString(R.string.no_measurement);

        if(kiloArray.size()!=0) {
            while (counter < kiloArray.size()) {
                String dizi[] = kiloArray.get(counter).split("--");
                olcumler.add(Float.parseFloat(dizi[0]));
                counter++;
            }
            decoView.addEvent(new DecoEvent.Builder(50)
                    .setIndex(backIndex)
                    .build());
            decoView.addEvent(new DecoEvent.Builder(olcumler.get(olcumler.size() - 1))
                    .setIndex(series1Index)
                    .setDelay(5000)
                    .build());

            if (olcumler.get(olcumler.size() - 1) - olcumler.get(0) > 0) {
                text = girisActivity.this.getString(R.string.take_weight);
            } else {
                text = girisActivity.this.getString(R.string.lose_weight);
            }
            textNekadar.setText(String.valueOf(olcumler.get(olcumler.size()-1)-olcumler.get(0))+" "+text);
        }else {
            textNekadar.setText("0 " + text);
            decoView.addEvent(new DecoEvent.Builder(50)
                    .setIndex(backIndex)
                    .build());
            decoView.addEvent(new DecoEvent.Builder(0)
                    .setIndex(series1Index)
                    .setDelay(3000)
                    .build());
        }



        DecoView decoViewStep = (DecoView) findViewById(R.id.dynamicArcViewStep);

        final SeriesItem seriesItemStep = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 50, 0)
                .build();

        int backIndexStep = decoViewStep.addSeries(seriesItemStep);

        final SeriesItem seriesItem2Step = new SeriesItem.Builder(Color.parseColor("#FFFF8800"))
                .setRange(0, 15000, 0)
                .build();



        int series1IndexStep = decoViewStep.addSeries(seriesItem2Step);

        final TextView textPercentageStep = (TextView) findViewById(R.id.idTxStep);
        final TextView textNekadarStep = (TextView) findViewById(R.id.idTxAzCok);

        seriesItem2Step.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItemStep.getMinValue()) / (seriesItemStep.getMaxValue() - seriesItemStep.getMinValue()));
                textPercentageStep.setText(String.valueOf((int)currentPosition)+" "+girisActivity.this.getString(R.string.step));//(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });


        int counter2=0;
        ArrayList<String> dataStepDB= dataB.DBArrayOlcum("3");
        ArrayList<String> dataStep=new ArrayList<>();
        ArrayList<String> dataStepDateSplitDay=new ArrayList<>();
        ArrayList<String> dataStepDateSplitMount=new ArrayList<>();
        if(dataStepDB.size()!=0) {
            while (counter2 < dataStepDB.size()) {
                String dizi[] = dataStepDB.get(counter2).split("--");
                dataStep.add(dizi[0]);
                String dizi2[] = dizi[2].split(" ");
                dataStepDateSplitDay.add(dizi2[0]);
                dataStepDateSplitMount.add(dizi2[1]);
                counter2++;
            }
            int counter3 = dataStepDateSplitDay.size() - 1;
            final int size = dataStepDateSplitDay.size() - 1;


            while (counter3 > 0) {
                int dayEnd = Integer.parseInt(dataStepDateSplitDay.get(size));
                int dayBack = Integer.parseInt(dataStepDateSplitDay.get(counter3 - 1));
                if (dataStepDateSplitMount.get(size).equals(dataStepDateSplitMount.get(counter3 - 1)) && dayEnd - dayBack == 1) {
                    dataEnd = dataStep.get(size);
                    dataBack = dataStep.get(counter3 - 1);
                }
                int i=0;
                boolean ayni=true;
                while (i<dataStepDateSplitDay.size()-1) {
                    if(!dataStepDateSplitDay.get(i).equals(dataStepDateSplitDay.get(i+1)))
                        ayni=false;
                    i++;
                }
                if(ayni){
                    dataEnd = dataStep.get(size);
                }
                counter3--;
            }
            stepGap=Integer.parseInt(dataEnd)-Integer.parseInt(dataBack);
        }

        String text2=girisActivity.this.getString(R.string.no_measurement);
        if(stepGap>0){
            text2=girisActivity.this.getString(R.string.toast_step_little);
        }else{
            text2=girisActivity.this.getString(R.string.toast_step_much);
        }

        textNekadarStep.setText(stepGap+" "+text2);


        decoViewStep.addEvent(new DecoEvent.Builder(50)
                .setIndex(backIndexStep)
                .build());
        decoViewStep.addEvent(new DecoEvent.Builder(Float.parseFloat(dataEnd))
                .setIndex(series1IndexStep)
                .setDelay(2000)
                .build());


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(girisActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(girisActivity.this,ilacKaydetActivity.class);
                startActivity(intent);

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(girisActivity.this,alarmListActivity.class);
                startActivity(intent);

            }
        });



        final int ilacSayisi=dataB.DBArray().size();
        ArrayList<String> l=dataB.DBArrayIdli();
        int s=0;
        ArrayList<Integer> ilacId=new ArrayList<>();

        while (s<ilacSayisi){
            String dizi[]=l.get(s).split("--");
            ilacId.add(Integer.parseInt(dizi[0]));
            s++;
        }
        ArrayList<Integer>ilacKacTaneAlinmadiIlacId =new ArrayList<>();
        ArrayList<Integer>ilacKacTaneAlinmadiIlacKac =new ArrayList<>();
        ArrayList<String >alindiMi=dataB.DBAldinMi();


        int sayac=0;

        while (sayac<alindiMi.size()){
            String dizi[]=alindiMi.get(sayac).split("--");
            int a=Integer.parseInt(dizi[1]);
            ilacKacTaneAlinmadiIlacId.add(a);
            sayac++;
        }
        ArrayList<String>ilacIdler= dataB.DBArrayId();
        int sayac2=0;
        ArrayList<uclu_deger> degerler=new ArrayList<>();
        while (sayac2<ilacIdler.size()){
            int kac_defa=Collections.frequency(ilacKacTaneAlinmadiIlacId,Integer.parseInt(ilacIdler.get(sayac2)));
            if (kac_defa!=0) {
                uclu_deger deger = new uclu_deger(sayac2, ilacId.get(sayac2), kac_defa);
                degerler.add(deger);
            }
            sayac2++;
        }


        int sayac3=0;
        ArrayList<uclu_deger> temp=degerler;
        uclu_deger temp2=new uclu_deger();

        int enbuyuk_deger = 0;
        Integer enbuyukuc[]=new Integer[3];
        Integer enbuyukucId[]=new Integer[3];

        enbuyukucId[0]=0;
        enbuyukucId[1]=0;
        enbuyukucId[2]=0;

        enbuyukuc[0]=0;
        enbuyukuc[1]=0;
        enbuyukuc[2]=0;

        int i_size=3;
        if(temp.size()<3)
            i_size=temp.size();
        for(int i = 0 ; i < i_size ; i++) {
            while (sayac3 < temp.size()) {
                if(temp.get(sayac3).kac_defa>=enbuyuk_deger) {
                    enbuyuk_deger = temp.get(sayac3).kac_defa;
                    temp2=temp.get(sayac3);
                }
                sayac3++;
            }
            sayac3=0;
            enbuyukucId[i]=temp2.getHangi_ilac();
            temp.remove(temp2);
            enbuyukuc[i]=enbuyuk_deger;
            enbuyuk_deger=0;
        }

        String ad1=dataB.DBArrayHangiIlacAd2(enbuyukucId[0]);
        String ad2=dataB.DBArrayHangiIlacAd2(enbuyukucId[1]);
        String ad3=dataB.DBArrayHangiIlacAd2(enbuyukucId[2]);
        tx1.setText(ad1);
        tx2.setText(ad2);
        tx3.setText(ad3);
        button3.setText(String.valueOf(enbuyukuc[0]));
        button2.setText(String.valueOf(enbuyukuc[1]));
        button1.setText(String.valueOf(enbuyukuc[2]));

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.kayitli_ilaclar:
                Intent intent = new Intent(girisActivity.this,ilacListeActivity.class);
                startActivity(intent);
                break;
            case R.id.kayitli_alarmlar:
                Intent intent4=new Intent(girisActivity.this,alarmListActivity.class);
                startActivity(intent4);
                break;
            case R.id.gunu_bitiyor:
                Intent intent5=new Intent(girisActivity.this,ilacGunuBitiyorActivity.class);
                startActivity(intent5);
                break;
            case R.id.olcumler:
                Intent intent9=new Intent(girisActivity.this,olcumKaydetActivity.class);
                startActivity(intent9);                break;
            case R.id.randevu:
                Intent intent6=new Intent(girisActivity.this,HastaneRandevuActivity.class);
                startActivity(intent6);
                break;
            case R.id.alarm_ayarla:
                Intent intent2 = new Intent(girisActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.ilac_kaydet:
                Intent intent3 = new Intent(girisActivity.this,ilacKaydetActivity.class);
                startActivity(intent3);
                break;
            case R.id.acil_numara:
                Intent intent7 = new Intent(girisActivity.this,acilNumberActivity.class);
                startActivity(intent7);
                break;
            case R.id.randevu_list:
                Intent intent8 = new Intent(girisActivity.this,randevuListActivity.class);
                startActivity(intent8);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void setNavigationViewListner() {
        nv = (NavigationView) findViewById(R.id.navi);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sag_ustmenu,menu);
        return true;
    }

    class uclu_deger{
        int id;
        int hangi_ilac;
        int kac_defa;

        public uclu_deger() {

        }

        public uclu_deger(int id, int hangi_ilac, int kac_defa) {
            this.id = id;
            this.hangi_ilac = hangi_ilac;
            this.kac_defa = kac_defa;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHangi_ilac() {
            return hangi_ilac;
        }

        public void setHangi_ilac(int hangi_ilac) {
            this.hangi_ilac = hangi_ilac;
        }

        public int getKac_defa() {
            return kac_defa;
        }

        public void setKac_defa(int kac_defa) {
            this.kac_defa = kac_defa;
        }
    }
}
