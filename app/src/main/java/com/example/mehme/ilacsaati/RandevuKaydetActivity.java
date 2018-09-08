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
    int dpYear, dpMonth, dpDay, tpHour, tpMinute;
    long mills;
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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                        dpYear=year;
                        dpMonth=month;
                        dpDay=dayOfMonth;
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
                tpHour=selectedHour;
                tpMinute=selectedMinute;
                saat=String.valueOf(selectedHour)+":"+String.valueOf(selectedMinute);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void randevuClicked(View view){
        hastaneAd=hastaneAdEt.getText().toString();
        polikinikAd=poliklinikAdEt.getText().toString();
        doktorAd=doktorAdEt.getText().toString();


        Toast.makeText(getApplicationContext(),R.string.kayit_tamamlandi_toast,Toast.LENGTH_SHORT).show();
        setAlarmNotification();
        randevu_class randevu=new randevu_class(hastaneAd,polikinikAd,doktorAd,tarih,saat,mills);
        ilacSaatiDB DB = new ilacSaatiDB(RandevuKaydetActivity.this);
        DB.randevu_ekle(randevu);
    }

    public void setAlarmNotification(){
        AlarmManager mgrAlarm = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dpYear);
        cal.set(Calendar.MONTH, dpMonth);
        cal.set(Calendar.DAY_OF_MONTH, dpDay);
        cal.set(Calendar.HOUR_OF_DAY, tpHour);
        cal.set(Calendar.MINUTE, tpMinute);
        cal.set(Calendar.SECOND, 0);
        mills = cal.getTimeInMillis();
        mills-=1000*60*60;
        Intent intent = new Intent(RandevuKaydetActivity.this, AppointmentNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(RandevuKaydetActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mgrAlarm.set(AlarmManager.RTC_WAKEUP, mills,pendingIntent);


    }
}
