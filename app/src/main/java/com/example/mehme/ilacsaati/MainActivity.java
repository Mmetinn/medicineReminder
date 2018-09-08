package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;



import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    //private AdView adView;
    TimePicker tp=null;
    AlarmManager mgrAlarm=null;
    Calendar c;
    int s=1;
    int counter=0;
    byte[] array;
    String []slogan=new String[20];
    int slogan_no;
    TextView ilacAciklamaTx,ilacAcTokTx,ilacKacDefaTx,ilacSureTx;
    ImageView ilacImage;
    Spinner ilacSecSp;
    ArrayList<String> ilacAdList=new ArrayList<>();
    ArrayList<String> ilacIdList=new ArrayList<>();
    ArrayList<String> ilacKacdefaList=new ArrayList<>();

    String []split_ilac=new String[100];
    Button btn;//runtime button
    TextView denemeText,ilacSecTx;
    int kacAlarmOldu_;
    int kacDefa_;
    int kacAlarmOldu;
    int kacDefa;
    int sayac=0;
    static MainActivity instance;
    boolean devam = false;
    private static Context context;


    AdView adView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ilacAciklamaTx=(TextView)findViewById(R.id.aciklama);
        ilacAcTokTx=(TextView)findViewById(R.id.actok);
        ilacKacDefaTx=(TextView)findViewById(R.id.kacdefa);
        ilacSureTx=(TextView)findViewById(R.id.sure);
        denemeText=(TextView)findViewById(R.id.surem);
        ilacImage=(ImageView)findViewById(R.id.ilacImagee);
        ilacSecSp=(Spinner)findViewById(R.id.spinner);
        ilacSecTx=(TextView)findViewById(R.id.ilacSecTx);


        MobileAds.initialize(this,"ca-app-pub-7334446571364171~4227399096");
        adView=(AdView)findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        LinearLayout layout = (LinearLayout) findViewById(R.id.reklamLin);
       // adView = new AdView(this, AdSize.BANNER, "MY_AD_UNIT_ID");

      //  layout.addView(adView);//Layout ' a reklamı ekliyoruz.
      //  AdRequest request = new AdRequest();

      //  adView.loadAd(request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mgrAlarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);





        ilacSaatiDB DB = new ilacSaatiDB(MainActivity.this);
        ArrayList<String> list;
        list=DB.DBArrayIdli();
        int i=0;
        int size=DB.DBArray().size();
        if(size==0){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle(MainActivity.this.getString(R.string.warning));
            alertDialog.setMessage(MainActivity.this.getString(R.string.no_registered_medicine));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, MainActivity.this.getString(R.string.ok_text),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alertDialog.show();
        }

        while(i<list.size()){
            String[] satir=list.get(i).split("--");
            ilacAdList.add(satir[1]);
            ilacIdList.add(satir[0]);
            ilacKacdefaList.add(satir[4]);
            i++;
        }
        split_ilac[0]="0";
        //array list to arrayAdapter. çünkü DB'deki DBArray arraylist döndürüyor ve spinner'a da array adpter bağlanabiliyor
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ilacAdList);
        ilacSecSp.setAdapter(arrayAdapter);


        Intent intent = getIntent();
        if(intent.hasExtra("ilac_adi")){
            ilacSecSp.setSelection(DB.kacIlacVar()-1);
        }

        slogan[0]="Doktorun vermediği ilacı içme, hayatını tok etme.";
        slogan[1]="Herkesin ilacı kendine.";
        slogan[2]="En iyi ilaç, doğru ilaçtır.";
        slogan[3]="Çok ilaç değil, doğru ilaç iyileştirir.";
        slogan[4]="Bilinçli içersen ilacı daha sağlıklısın.";
        slogan[5]="Bilinçli içersen ilacı daha sağlıklısın.";
        slogan[6]="Bilinçli ilaclar kullanılmalı, bilinçsiz ilaç kullanımı yasaklanmalı.";
        Random r = new Random();
        slogan_no=r.nextInt(6);
            AdapterView.OnItemSelectedListener countrySelectedListener = new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> spinner, View container,
                                           int position, long id) {
                    ilacSaatiDB DB2 = new ilacSaatiDB(MainActivity.this);
                    String secilen_ilac = DB2.DBArrayHangiIlac(parseInt(ilacIdList.get(position)));
                    split_ilac = secilen_ilac.split("--");

                    ilacAciklamaTx.setText(split_ilac[2]);
                    ilacAcTokTx.setText(split_ilac[3]);
                    ilacKacDefaTx.setText(split_ilac[4]);
                    ilacSureTx.setText(split_ilac[5]);
                    array = DB2.ilac_foto(parseInt(split_ilac[0]));
                    Bitmap bMap = BitmapFactory.decodeByteArray(array, 0, array.length);
                    ilacImage.setImageBitmap(bMap);

                    String ilac_id=ilacIdList.get(position);
                    ilacSaatiDB DB = new ilacSaatiDB(MainActivity.this);
                    kacAlarmOldu_=DB.alarmsSay(Integer.parseInt(ilac_id));
                    kacDefa_=Integer.parseInt(split_ilac[4]);

                    /*if(kacAlarmOldu_==kacDefa_) {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Uyarı");
                        alertDialog.setMessage("Bu ilaç için bütün alarmları ayarladınız.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                                new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                            });
                        alertDialog.show();
                    }*/
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            };

            ilacSecSp.setOnItemSelectedListener(countrySelectedListener);


            LinearLayout ll = (LinearLayout) findViewById(R.id.linear);
            tp = new TimePicker(MainActivity.this);
            btn = new Button(MainActivity.this);
            btn.setBackgroundResource(R.color.colorButton);
            btn.setTextColor(Color.WHITE);
            tp.setMinimumHeight(50);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(tp, lp);
            ll.addView(btn, lp);
            btn.setText(R.string.set_alarm);



            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ilacSaatiDB DBsize =new ilacSaatiDB(MainActivity.this);

                        int ilacSize = DBsize.DBArray().size();
                    if (ilacSize == 0) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        devam = true;
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        devam = false;
                                        break;
                                    default:
                                        devam = false;
                                        break;
                                }
                                if (devam == true) {
                                    devam = false;
                                    Intent intent = new Intent(MainActivity.this, ilacKaydetActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }
                        };
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(MainActivity.this.getString(R.string.no_registered_medicine)).setPositiveButton(MainActivity.this.getString(R.string.yes_text), dialogClickListener).setNegativeButton(MainActivity.this.getString(R.string.no_text), dialogClickListener).show();
                    } else {

                        c = Calendar.getInstance();
                        int s = tp.getCurrentHour();
                        int d = tp.getCurrentMinute();
                        counter++;

                        long saatfark = -1;
                        if (c.get(Calendar.AM_PM) == 0)
                            saatfark = (s - c.get(Calendar.HOUR)) * 3600000 + (d - c.get(Calendar.MINUTE)) * 60000;
                        else
                            saatfark = ((s - 12) - c.get(Calendar.HOUR)) * 3600000 + (d - c.get(Calendar.MINUTE)) * 60000;

                        Calendar today = Calendar.getInstance();
                        long now = System.currentTimeMillis();
                        saatfark += now;

                        Calendar today2 = Calendar.getInstance();
                        if (saatfark < today2.getTimeInMillis())
                            saatfark += 24 * 3600000;//86 400 000


                        int position = ilacSecSp.getSelectedItemPosition();
                        String ilac_id = ilacIdList.get(position);
                        String kacinci_alarm = String.valueOf(counter);

                        ilacSaatiDB DB = new ilacSaatiDB(MainActivity.this);
                        if(DBsize.alarmsSay(Integer.parseInt(ilac_id))==Integer.parseInt(ilacKacdefaList.get(position))){
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle(MainActivity.this.getString(R.string.warning));
                            alertDialog.setMessage(MainActivity.this.getString(R.string.alarm_count_full));
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, MainActivity.this.getString(R.string.ok_text),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
                                        }
                                    });
                            alertDialog.show();
                        }else{
                            alarmKaydet(ilac_id, kacinci_alarm, saatfark);
                            alarmiAyarla(saatfark);
                            Toast.makeText(getApplicationContext(),MainActivity.this.getString(R.string.alarm_added), Toast.LENGTH_SHORT).show();
                        }


                        kacDefa = Integer.parseInt(split_ilac[4]);
                        kacAlarmOldu = DB.alarmsSay(Integer.parseInt(ilac_id));


                    }
                }
            });
            MainActivity.context=getApplicationContext();
        }



    public static Context getAppContextMain(){
        return MainActivity.context;
    }

    public void alarmiAyarla(long alarmDeger){
        ilacSaatiDB d = new ilacSaatiDB(MainActivity.this);
        ArrayList<String>dataAlarm=d.DBArrayAlarm();
        int i=0;
        while (i<dataAlarm.size()){
            String dizi[]=dataAlarm.get(i).split("--");
            sayac=Integer.parseInt(dizi[0]);
            i++;
        }
        sayac++;
        /*int index=alarmMillisList.size()-1;
        String deger=alarmMillisList.get(index);
        long alarmDeger=Long.valueOf(deger);*/
        Intent intent = new Intent(MainActivity.this, myBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, sayac, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmDeger,AlarmManager.INTERVAL_DAY,pendingIntent);

        Intent intent2 = new Intent(MainActivity.this, uygulamaBaslat.class);
        int position = ilacSecSp.getSelectedItemPosition();
        String ilac_id=ilacIdList.get(position);
        intent2.putExtra("ilac_id",ilac_id);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, sayac, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,alarmDeger,AlarmManager.INTERVAL_DAY,pendingIntent2);

        sayac++;



       /* Intent intent3 = new Intent("my.action.string");
        intent3.putExtra("ilac_id",ilac_id);
        sendBroadcast(intent3);*/
       /* while(sayac<arrayList.size()) {

            String[] satir = arrayList.get(sayac).split("--");
            alarmIdList.add(satir[0]);
            alarmIlacIdList.add(satir[1]);
            alarmMillisList.add(satir[2]);
            long alarmDeger=Long.parseLong(satir[2]);
            long now=System.currentTimeMillis();
            if(alarmDeger<now){
                sayac++;
                continue;
            }

            Intent intent = new Intent(MainActivity.this, myBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, alarmiAyarlaFuncId, intent, 0);
            mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmDeger,AlarmManager.INTERVAL_DAY,pendingIntent);
/*
            int position = ilacSecSp.getSelectedItemPosition();
            String ilac_id=ilacIdList.get(position);

            Intent intent3 = new Intent("my.action.string");
            intent3.putExtra("ilac_id",ilac_id);
            sendBroadcast(intent3);
*/
           /* Intent intent2 = new Intent(MainActivity.this, uygulamaBaslat.class);
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, alarmiAyarlaFuncId, intent2, 0);
            mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,alarmDeger,AlarmManager.INTERVAL_DAY,pendingIntent2);
            sayac++;

            alarmiAyarlaFuncId++;
        }*/
    }

    public void alarmKaydet(String ilac_id,String kacinci_alarm,long millis){
        alarmlar_class alarm=null;
        ilacSaatiDB DB=null;
        alarm=new alarmlar_class(millis,ilac_id,kacinci_alarm);
        DB = new ilacSaatiDB(MainActivity.this);
        DB.alarm_ekle(alarm);
    }
}


