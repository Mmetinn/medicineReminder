package com.example.mehme.ilacsaati;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class uygulamaBaslat extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ilacAldinMiActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
//ALARM_TABLE
//id---ilac_id---alarm1---alarm2---alarm3---alarm4---alarm5