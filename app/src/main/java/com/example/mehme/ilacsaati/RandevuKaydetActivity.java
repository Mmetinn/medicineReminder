package com.example.mehme.ilacsaati;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RandevuKaydetActivity extends AppCompatActivity {
    String hastaneAd,polikinikAd,doktorAd,saat,tarih;
    EditText hastaneAdEt,poliklinikAdEt,doktorAdEt;
    long dayMillis,dateMillis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu_kaydet);
        hastaneAdEt=(EditText)findViewById(R.id.hastaneAdEt);
        poliklinikAdEt=(EditText)findViewById(R.id.poliklinikAdEt);
        doktorAdEt=(EditText)findViewById(R.id.doktorAdEt);
    }

    public void googleAra(View view){
        Uri uri = Uri.parse("http://www.google.com/#q="+hastaneAdEt.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void tarhiSecClick(View view){
        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dpd = new DatePickerDialog(RandevuKaydetActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateMillis=takvim.get(Calendar.DAY_OF_YEAR);
                        tarih=String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(dayOfMonth);
                    }
                }, yil, ay, gun);
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();

    }
    public void zamanClicked(View view){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        final Calendar current=Calendar.getInstance();
        mTimePicker = new TimePickerDialog(RandevuKaydetActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                dayMillis=current.get(Calendar.HOUR_OF_DAY);

                saat=String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void randevuClicked(View view){
        hastaneAd=hastaneAdEt.getText().toString();
        polikinikAd=poliklinikAdEt.getText().toString();
        doktorAd=doktorAdEt.getText().toString();
        randevu_class randevu=new randevu_class(hastaneAd,polikinikAd,doktorAd,tarih,saat);
        ilacSaatiDB DB = new ilacSaatiDB(RandevuKaydetActivity.this);
        DB.randevu_ekle(randevu);

        Toast.makeText(getApplicationContext(),R.string.kayit_tamamlandi_toast,Toast.LENGTH_SHORT).show();
        setAlarmNotification();
    }

    public void setAlarmNotification(){
        AlarmManager mgrAlarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        long date=System.currentTimeMillis();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date);
        int year=calendar2.get(Calendar.YEAR);
        long yearMillis=year*86400000*360;
        long millisAlarm=yearMillis+dateMillis*86400000+dayMillis*3600000;
        Intent intent = new Intent(RandevuKaydetActivity.this, myBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(RandevuKaydetActivity.this, 0, intent, 0);
        mgrAlarm.set(AlarmManager.RTC, year*86400000*360+yearMillis+dateMillis*86400000+dayMillis*3600000,pendingIntent);
    }
}
