package com.example.mehme.ilacsaati;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.common.internal.Constants;

import java.util.Random;

public class AppointmentNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_blood)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.appointment_time))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(context.getString(R.string.appointment_info)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.notifi));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,mBuilder.build());



    }
}
