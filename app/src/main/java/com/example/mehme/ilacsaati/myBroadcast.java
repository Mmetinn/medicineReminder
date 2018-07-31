package com.example.mehme.ilacsaati;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class myBroadcast extends BroadcastReceiver {
    String []slogan=new String[20];
    int slogan_no;

    @Override
    public void onReceive(Context context, Intent intent) {

        slogan[0]="Doktorun vermediği ilacı içme, hayatını tok etme";
        slogan[1]="Herkesin ilacı kendine.";
        slogan[2]="En iyi ilaç, doğru ilaçtır.";
        slogan[3]="Çok ilaç değil, doğru ilaç iyileştirir.";
        slogan[4]="Bilinçli içersen ilacı daha sağlıklısın.";
        slogan[5]="Bilinçli içersen ilacı daha sağlıklısın.";
        slogan[6]="Bilinçli ilaclar kullanılmalı, bilinçsiz ilaç kullanımı yasaklanmalı.";

        Random r = new Random();
        slogan_no=r.nextInt(6);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher_hapiki)
                .setContentTitle(context.getString(R.string.medic_time))
                .setContentText(context.getString(R.string.app_name))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(context.getString(R.string.did_you_take)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.notifi));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,mBuilder.build());
       // Notification notification = mBuilder.build();
     //   notification.sound = Uri.parse("android.resource://com.example.mehme.ilacsaati/" + R.raw.notifi2);
    }
}