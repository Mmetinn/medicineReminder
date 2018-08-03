package com.example.mehme.ilacsaati;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class alarmListActivity extends AppCompatActivity {
    ilacSaatiDB DB;
    int sayac=0;
    ArrayList<TextView>tx1Array=new ArrayList<>();
    String dizi[] = new String[100];
    String alarmMillis[] = new String[100];
    String alarmIlacId[] = new String[100];
    TextView tx1,tx2,tx3,tx4;
    ArrayList<String> sabahAlarmId=new ArrayList<>();
    ArrayList<String> sabahAlarmMillis=new ArrayList<>();
    ArrayList<String> ogleAlarmId=new ArrayList<>();
    ArrayList<String> ikindiAlarmId=new ArrayList<>();
    ArrayList<String> geceAlarmId=new ArrayList<>();
    Button deleteBtn,deleteBtn2,deleteBtn3,deleteBtn4;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        DB=new ilacSaatiDB(alarmListActivity.this);
        ArrayList<String> arrayAlarm = DB.DBArrayAlarm();

        while (arrayAlarm.size() > sayac) {
            dizi = arrayAlarm.get(sayac).split("--");
            alarmMillis[sayac] = dizi[2];
            alarmIlacId[sayac] = dizi[1];
            sayac++;
        }
        int i = 0;
        long millisToDate[] = new long[100];
        final long millisToDateF[] = new long[100];
        while (i < arrayAlarm.size()) {
            millisToDate[i] = Long.parseLong(alarmMillis[i].trim());
            millisToDateF[i]=Long.parseLong(alarmMillis[i].trim());
            i++;
        }
        int j = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        LinearLayout lls = (LinearLayout) findViewById(R.id.sabahLin);
        LinearLayout llo = (LinearLayout) findViewById(R.id.ogleLin);
        LinearLayout lli = (LinearLayout) findViewById(R.id.ikindiLin);
        LinearLayout llg = (LinearLayout) findViewById(R.id.geceLin);
        lls.setGravity(Gravity.CENTER);
        llo.setGravity(Gravity.CENTER);
        lli.setGravity(Gravity.CENTER);
        llg.setGravity(Gravity.CENTER);
        //Listedeki değerlere göre kartların içerisinde alarm değerlerimi gösterdim
        while (j < arrayAlarm.size()){
            LinearLayout ll = new LinearLayout(alarmListActivity.this);
            LinearLayout ll2 = new LinearLayout(alarmListActivity.this);
            LinearLayout ll3 = new LinearLayout(alarmListActivity.this);
            LinearLayout ll4 = new LinearLayout(alarmListActivity.this);

            ll.setGravity(Gravity.CENTER);
            ll2.setGravity(Gravity.CENTER);
            ll3.setGravity(Gravity.CENTER);
            ll4.setGravity(Gravity.CENTER);
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millisToDate[j]);
            ActionBar.LayoutParams lpnewSize = new ActionBar.LayoutParams(50,50);



            if(calendar.get(Calendar.HOUR_OF_DAY)>=0 && calendar.get(Calendar.HOUR_OF_DAY)<=11){
                deleteBtn=new Button(alarmListActivity.this);
                deleteBtn.setBackgroundResource(R.drawable.ic_trash);
                deleteBtn.setTextColor(Color.WHITE);
                deleteBtn.setWidth(30);
                deleteBtn.setHeight(30);
                deleteBtn.setGravity(Gravity.CENTER);

                tx1 = new TextView(alarmListActivity.this);
                String ilacInfo = DB.DBArrayHangiIlacAd(Integer.parseInt(alarmIlacId[j]));
                String hour_of_day = null,minute;
                if(calendar.get(Calendar.HOUR_OF_DAY)<10){
                    hour_of_day="0"+calendar.get(Calendar.HOUR_OF_DAY);
                }if(calendar.get(Calendar.MINUTE)<10){
                    minute="0"+calendar.get(Calendar.MINUTE);
                }else{
                    hour_of_day=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                    minute=String.valueOf(calendar.get(Calendar.MINUTE));
                }
                String d=ilacInfo+" "+hour_of_day+":"+minute;
                tx1.setText(d);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

                lls.addView(ll,lp);
                ll.addView(tx1, lp);
                final int index=j;
                deleteBtn.setId(index);
                tx1Array.add(tx1);

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    deleteBtn.setBackgroundResource(R.drawable.ic_trash_dark);
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                    int id = DB.DBArrayHangiAlarm(millisToDateF[index])+1;
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent);

                                    AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    Intent myIntent2 = new Intent(getApplicationContext(), uygulamaBaslat.class);

                                    PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager2.cancel(pendingIntent2);

                                    DB.alarmDelete(millisToDateF[index]);
                                    Toast.makeText(getApplicationContext(),alarmListActivity.this.getString(R.string.delete_registration_toast),Toast.LENGTH_SHORT).show();
                                    v.setVisibility(View.GONE);
                                    tx1Array.get(index).setVisibility(View.GONE);
                                    /*AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                    ArrayList<String> array= DB.DBArrayAlarm();
                                    int size=0;
                                    int id=0;
                                    String millis=String.valueOf(millisToDateF[index]);
                                    while(size<array.size()){
                                        String dizi[]=array.get(size).split("--");
                                        if(dizi[3].equals(millis)){
                                            id=Integer.parseInt(dizi[0]);
                                            break;
                                        }
                                        size++;
                                    }
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                            getApplicationContext(), 0, myIntent,
                                            id);

                                    alarmManager.cancel(pendingIntent);*/
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;

                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(alarmListActivity.this);
                    builder.setMessage(alarmListActivity.this.getString(R.string.delete_ques_alert)).setPositiveButton(alarmListActivity.this.getString(R.string.yes_text),dialogClickListener).setNegativeButton(alarmListActivity.this.getString(R.string.no_text),dialogClickListener).show();

                   }
                });


                ll.addView(deleteBtn,lpnewSize);

                //id degerlerini updateDeleteAlarmActivty'e gödenremek için sakladım
                sabahAlarmId.add(alarmIlacId[j]);
                j++;
            }

            if(calendar.get(Calendar.HOUR_OF_DAY)>11 && calendar.get(Calendar.HOUR_OF_DAY)<=14){

                deleteBtn2=new Button(alarmListActivity.this);
                deleteBtn2.setBackgroundResource(R.drawable.ic_trash);
                deleteBtn2.setTextColor(Color.WHITE);
                deleteBtn2.setWidth(30);
                deleteBtn2.setHeight(30);
                deleteBtn2.setGravity(Gravity.CENTER);

                tx2 = new TextView(alarmListActivity.this);
                String ilacInfo = DB.DBArrayHangiIlacAd(Integer.parseInt(alarmIlacId[j]));
                String d=ilacInfo+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                tx2.setText(d);
                tx1Array.add(tx2);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                llo.addView(ll2,lp);
                ll2.addView(tx2, lp);
                ogleAlarmId.add(alarmIlacId[j]);
                final int index=j;
                deleteBtn2.setId(index);

                deleteBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        deleteBtn2.setBackgroundResource(R.drawable.ic_trash_dark);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:

                                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                        int id = DB.DBArrayHangiAlarm(millisToDateF[index])+1;
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager.cancel(pendingIntent);

                                        AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent2 = new Intent(getApplicationContext(), uygulamaBaslat.class);

                                        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager2.cancel(pendingIntent2);

                                        DB.alarmDelete(millisToDateF[index]);
                                        Toast.makeText(getApplicationContext(),alarmListActivity.this.getString(R.string.delete_registration_toast),Toast.LENGTH_SHORT).show();
                                        v.setVisibility(View.GONE);
                                        tx1Array.get(index).setVisibility(View.GONE);

                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;

                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(alarmListActivity.this);
                        builder.setMessage(alarmListActivity.this.getString(R.string.delete_ques_alert)).setPositiveButton(alarmListActivity.this.getString(R.string.yes_text),dialogClickListener).setNegativeButton(alarmListActivity.this.getString(R.string.no_text),dialogClickListener).show();

                    }
                });
                ll2.addView(deleteBtn2,lpnewSize);


                j++;
            }

            if(calendar.get(Calendar.HOUR_OF_DAY)>14 && calendar.get(Calendar.HOUR_OF_DAY)<=18){

                deleteBtn3=new Button(alarmListActivity.this);
                deleteBtn3.setBackgroundResource(R.drawable.ic_trash);
                deleteBtn3.setTextColor(Color.WHITE);
                deleteBtn3.setWidth(30);
                deleteBtn3.setHeight(30);
                deleteBtn3.setGravity(Gravity.CENTER);

                tx3 = new TextView(alarmListActivity.this);
                String ilacInfo = DB.DBArrayHangiIlacAd(Integer.parseInt(alarmIlacId[j]));
                String d=ilacInfo+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                tx3.setText(d);
                tx1Array.add(tx3);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                lli.addView(ll3, lp);
                ll3.addView(tx3, lp);
                ikindiAlarmId.add(alarmIlacId[j]);
                final int index=j;
                deleteBtn3.setId(index);

                deleteBtn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        deleteBtn3.setBackgroundResource(R.drawable.ic_trash_dark);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                        int id = DB.DBArrayHangiAlarm(millisToDateF[index])+1;
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager.cancel(pendingIntent);

                                        AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent2 = new Intent(getApplicationContext(), uygulamaBaslat.class);

                                        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager2.cancel(pendingIntent2);

                                        DB.alarmDelete(millisToDateF[index]);
                                        Toast.makeText(getApplicationContext(),alarmListActivity.this.getString(R.string.update_alert),Toast.LENGTH_SHORT).show();
                                        v.setVisibility(View.GONE);
                                        tx1Array.get(index).setVisibility(View.GONE);

                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;

                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(alarmListActivity.this);
                        builder.setMessage(alarmListActivity.this.getString(R.string.delete_ques_alert)).setPositiveButton(alarmListActivity.this.getString(R.string.yes_text),dialogClickListener).setNegativeButton(alarmListActivity.this.getString(R.string.no_text),dialogClickListener).show();

                    }
                });
                ll3.addView(deleteBtn3,lpnewSize);


                j++;
            }

            if(calendar.get(Calendar.HOUR_OF_DAY)>18 && calendar.get(Calendar.HOUR_OF_DAY)<=23){

                deleteBtn4=new Button(alarmListActivity.this);
                deleteBtn4.setBackgroundResource(R.drawable.ic_trash);
                deleteBtn4.setTextColor(Color.WHITE);
                deleteBtn4.setWidth(30);
                deleteBtn4.setHeight(30);
                deleteBtn4.setGravity(Gravity.CENTER);

                tx4 = new TextView(alarmListActivity.this);
                String ilacInfo = DB.DBArrayHangiIlacAd(Integer.parseInt(alarmIlacId[j]));
                String d=ilacInfo+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                tx4.setText(d);
                tx1Array.add(tx4);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                llg.addView(ll4, lp);
                ll4.addView(tx4, lp);
                final int index=j;
                deleteBtn4.setId(index);

                deleteBtn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        deleteBtn4.setBackgroundResource(R.drawable.ic_trash_dark);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                        int id = DB.DBArrayHangiAlarm(millisToDateF[index])+1;
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager.cancel(pendingIntent);

                                        AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent2 = new Intent(getApplicationContext(), uygulamaBaslat.class);

                                        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), id, myIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
                                        alarmManager2.cancel(pendingIntent2);


                                        DB.alarmDelete(millisToDateF[index]);
                                        Toast.makeText(getApplicationContext(),alarmListActivity.this.getString(R.string.delete_complated),Toast.LENGTH_SHORT).show();
                                        v.setVisibility(View.GONE);
                                        tx1Array.get(index).setVisibility(View.GONE);


                                        /*AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                        Intent myIntent = new Intent(getApplicationContext(), myBroadcast.class);
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                                getApplicationContext(), 1, myIntent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);

                                        alarmManager.cancel(pendingIntent);*/
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;

                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(alarmListActivity.this);
                        builder.setMessage(alarmListActivity.this.getString(R.string.delete_ques_alert)).setPositiveButton(alarmListActivity.this.getString(R.string.yes_text),dialogClickListener).setNegativeButton(alarmListActivity.this.getString(R.string.no_text),dialogClickListener).show();

                    }
                });
                ll4.addView(deleteBtn4,lpnewSize);
                geceAlarmId.add(alarmIlacId[j]);
                j++;
            }
        }
    }

    public void sabahClicked(View view){
        Intent intent = new Intent(alarmListActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void ogleClicked(View view){
        Intent intent = new Intent(alarmListActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void ikindiClicked(View view){
        Intent intent = new Intent(alarmListActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void geceClicked(View view){
        Intent intent = new Intent(alarmListActivity.this,MainActivity.class);
        startActivity(intent);
    }


}
