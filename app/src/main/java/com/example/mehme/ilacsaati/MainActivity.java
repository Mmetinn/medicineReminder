package com.example.mehme.ilacsaati;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity  {
    TimePicker tp=null;
    AlarmManager mgrAlarm=null;
    Calendar c;
    int s=1;
    int counter=0;
    ArrayList<Integer>alarmList=new ArrayList<>();
    byte[] array;
    String []slogan=new String[20];
    int slogan_no;
    TextView ilacAciklamaTx,ilacAcTokTx,ilacKacDefaTx,ilacSureTx;
    ImageView ilacImage;
    Spinner ilacSecSp;
    ArrayList<String> ilacAdList=new ArrayList<>();
    ArrayList<String> ilacIdList=new ArrayList<>();
    String []split_ilac=new String[100];
    Button btn;//runtime button
    TextView denemeText;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmAyarla();

        ilacAciklamaTx=(TextView)findViewById(R.id.aciklama);
        ilacAcTokTx=(TextView)findViewById(R.id.actok);
        ilacKacDefaTx=(TextView)findViewById(R.id.kacdefa);
        ilacSureTx=(TextView)findViewById(R.id.sure);
        denemeText=(TextView)findViewById(R.id.surem);
        ilacImage=(ImageView)findViewById(R.id.ilacImagee);
        ilacSecSp=(Spinner)findViewById(R.id.spinner);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ilacSaatiDB DB = new ilacSaatiDB(MainActivity.this);
        ArrayList<String> list;
        list=DB.DBArrayIdli();
        int i=0;

        Log.d(String.valueOf(list.size()), " List size ");
        while(i<list.size()){
            String[] satir=list.get(i).split("--");
            ilacAdList.add(satir[1]);
            ilacIdList.add(satir[0]);
            i++;
        }
        split_ilac[0]="0";
        //array list to arrayAdapter. çünkü DB'deki DBArray arraylist döndürüyor ve spinner'a da array adpter bağlanabiliyor
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ilacAdList);
        /*ArrayList<String> listAlarm=DB.DBArrayAlarm();
        denemeText.setText(listAlarm.get(1)+listAlarm.get(2)+listAlarm.get(3));*/
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

                    Log.d(split_ilac[0], "onCreate: aaaaa" + split_ilac[0]);

                    ilacAciklamaTx.setText(split_ilac[2]);
                    ilacAcTokTx.setText(split_ilac[3]);
                    ilacKacDefaTx.setText(split_ilac[4]);
                    ilacSureTx.setText(split_ilac[5]);
                    array = DB2.ilac_foto(parseInt(split_ilac[0]));
                    Bitmap bMap = BitmapFactory.decodeByteArray(array, 0, array.length);
                    ilacImage.setImageBitmap(bMap);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            };

            ilacSecSp.setOnItemSelectedListener(countrySelectedListener);
            // Setting ItemClick Handler for Spinner Widget
        //int kacDefa = Integer.parseInt(split_ilac[4]);

            ilacAciklamaTx.setText(split_ilac[0]+split_ilac[5]+split_ilac[4]+split_ilac[3]+split_ilac[1]+split_ilac[2]);

            LinearLayout ll = (LinearLayout) findViewById(R.id.linear);
            tp = new TimePicker(MainActivity.this);
            btn = new Button(MainActivity.this);
            tp.setMinimumHeight(50);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(tp, lp);
            ll.addView(btn, lp);
            btn.setText("ALARM AYARLA");


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c = Calendar.getInstance();
                    int s=tp.getCurrentHour();
                    int d=tp.getCurrentMinute();
                    counter++;

                    int saatfark=-1;
                    if(c.get(Calendar.AM_PM)==0)
                        saatfark =(s-c.get(Calendar.HOUR))*3600000+(d-c.get(Calendar.MINUTE))*60000;
                    else
                        saatfark =((s-12)-c.get(Calendar.HOUR))*3600000+(d-c.get(Calendar.MINUTE))*60000;

                    Log.d("TAG", "onClick: "+(saatfark+24*3600000));

                    int kacDefa=Integer.parseInt(split_ilac[4]);

                    alarmList.add(saatfark);

                    if(kacDefa==counter){
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                alertDialog.setTitle("Uyarı");
                                alertDialog.setMessage("Bu ilaç için bütün alarmları ayarladınız.");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                btn.setClickable(false);
                                            }
                                        });
                                alertDialog.show();
                            }
                        });
                        tp.setClickable(false);
                    }

                    if(kacDefa==counter){
                        int position = ilacSecSp.getSelectedItemPosition();

                        alarmKaydet(alarmList, kacDefa,position);
                    }


                    Toast.makeText(getApplicationContext(),String.valueOf(counter)+" . ALARM EKLENDİ",Toast.LENGTH_SHORT).show();



                }
            });
    }

    public void alarmKaydet(ArrayList<Integer> millis_alarm,int kacAlarm,int position){
        alarmlar_class alarm=null;
        ilacSaatiDB DB=null;
        switch (kacAlarm){
            case 1:
                alarm=new alarmlar_class(Integer.parseInt(ilacIdList.get(position)),String.valueOf(millis_alarm.get(0))) ;
                DB = new ilacSaatiDB(MainActivity.this);
                DB.alarm_ekle(alarm,1);
                break;
            case 2:
                alarm=new alarmlar_class(Integer.parseInt(ilacIdList.get(position)),String.valueOf(millis_alarm.get(0)),String.valueOf(millis_alarm.get(1))) ;
                DB = new ilacSaatiDB(MainActivity.this);
                DB.alarm_ekle(alarm,2);
                break;
            case 3:
                alarm=new alarmlar_class(Integer.parseInt(ilacIdList.get(position)),String.valueOf(millis_alarm.get(0)),String.valueOf(millis_alarm.get(1)),String.valueOf(millis_alarm.get(2))) ;
                DB = new ilacSaatiDB(MainActivity.this);
                DB.alarm_ekle(alarm,3);
                break;
            case 4:
                alarm=new alarmlar_class(Integer.parseInt(ilacIdList.get(position)),String.valueOf(millis_alarm.get(0)),String.valueOf(millis_alarm.get(1)),String.valueOf(millis_alarm.get(2)),String.valueOf(millis_alarm.get(3))) ;
                DB = new ilacSaatiDB(MainActivity.this);
                DB.alarm_ekle(alarm,4);
                break;
            case 5:
                alarm=new alarmlar_class(Integer.parseInt(ilacIdList.get(position)),String.valueOf(millis_alarm.get(0)),String.valueOf(millis_alarm.get(1)),String.valueOf(millis_alarm.get(2)),String.valueOf(millis_alarm.get(3)),String.valueOf(millis_alarm.get(4)));
                DB = new ilacSaatiDB(MainActivity.this);
                DB.alarm_ekle(alarm,5);
                break;
        }

    }
    public void alarmAyarla(){
        ArrayList<String> alarmList = new ArrayList<>();
        ArrayList<String> alarmIdList = new ArrayList<>();
        ArrayList<String> alarmIlacIdList = new ArrayList<>();
        ArrayList<String> alarmAlarmList = new ArrayList<>();
        ArrayList<String> alarmAlarm1List = new ArrayList<>();
        ArrayList<String> alarmAlarm2List = new ArrayList<>();
        ArrayList<String> alarmAlarm3List = new ArrayList<>();
        ArrayList<String> alarmAlarm4List = new ArrayList<>();
        ArrayList<String> alarmAlarm5List = new ArrayList<>();
        ilacSaatiDB DB= new ilacSaatiDB(MainActivity.this);
        alarmList=DB.DBArrayAlarm();
        int sayac=0;
        mgrAlarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        while(sayac<alarmList.size()){
            String[] satir=alarmList.get(sayac).split("--");
            alarmIdList.add(satir[0]);
            alarmIlacIdList.add(satir[1]);
           /* alarmAlarm1List.add(satir[2]);
            alarmAlarm2List.add(satir[3]);
            alarmAlarm3List.add(satir[4]);
            alarmAlarm4List.add(satir[5]);
            alarmAlarm5List.add(satir[6]);*/
            alarmAlarmList.add(satir[2]);
            alarmAlarmList.add(satir[3]);
            alarmAlarmList.add(satir[4]);
            alarmAlarmList.add(satir[5]);
            alarmAlarmList.add(satir[6]);
            sayac++;
        }
        ArrayList<ArrayList> liste=new ArrayList<>();

        liste.add(alarmAlarm1List);
        liste.add(alarmAlarm2List);
        liste.add(alarmAlarm3List);
        liste.add(alarmAlarm4List);
        liste.add(alarmAlarm5List);


        //for (int i = 0; i < sayac; i++){
            for (int j = 0; j < 5; j++){
                //ArrayList<String> tempList=liste.get(i);
                //if(tempList.get(j).equals(null))
                    //continue;
                Intent intent = new Intent(MainActivity.this, myBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, j, intent, 0);
                //mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+Integer.parseInt(alarmAlarmList.get(j)),pendingIntent);
                mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime()+Integer.parseInt(alarmAlarmList.get(j)),AlarmManager.INTERVAL_DAY,pendingIntent);
               // intent.putExtra("ilac_ad",ilacSecSp.getSelectedItem().toString());
                Log.d("abc"," ilk: "+SystemClock.elapsedRealtime() + 60000+" iki: "+SystemClock.elapsedRealtime()+liste.get(j));

                Intent intent2 = new Intent(MainActivity.this, uygulamaBaslat.class);
                PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, j, intent2, 0);
                //mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime()+Integer.parseInt(alarmAlarmList.get(j)),pendingIntent2);
                mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime()+Integer.parseInt(alarmAlarmList.get(j)),AlarmManager.INTERVAL_DAY,pendingIntent2);
            }
     //   }

/*      Intent intent = new Intent(MainActivity.this, myBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, counter, intent, 0);
        mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+saatfark,pendingIntent);

        intent.putExtra("ilac_ad",ilacSecSp.getSelectedItem().toString());
        Log.d("abc"," ilk: "+SystemClock.elapsedRealtime() + 60000+" iki: "+SystemClock.elapsedRealtime()+saatfark);
        Intent intent2 = new Intent(MainActivity.this, uygulamaBaslat.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, counter, intent2, 0);
        mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime()+saatfark,pendingIntent2);*/


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sag_ustmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case R.id.ilacListe:
                Intent intent = new Intent(this,ilacListeActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
