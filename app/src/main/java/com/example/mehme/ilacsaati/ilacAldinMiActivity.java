package com.example.mehme.ilacsaati;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class ilacAldinMiActivity extends AppCompatActivity {
    Button iptal_btn,al_btn,ertele_btn;
    ilacSaatiDB DB = new ilacSaatiDB(ilacAldinMiActivity.this);
    TextView ilacAdi,ilacAciklama,ilacAcTok;
    ImageView ivIlac;
    int id;
    static boolean secti_mi=true;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_aldin_mi);
        iptal_btn=(Button)findViewById(R.id.iptal);
        al_btn=(Button)findViewById(R.id.al);
        ertele_btn=(Button)findViewById(R.id.ertele);
        ilacAdi=(TextView)findViewById(R.id.TxilacAdi);
        ilacAciklama=(TextView)findViewById(R.id.TxilacAciklama);
        ilacAcTok=(TextView)findViewById(R.id.TxilacAcTok);
        ivIlac=(ImageView)findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        String x=extras.getString("ilac_id");
        id=Integer.valueOf(x);
        //int id=1;
        String ilac_bilgi=DB.DBArrayHangiIlac(id);
        String dizi[]=ilac_bilgi.split("--");
        byte []data;
        data = DB.ilac_foto(id);
        Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length);
        ivIlac.setImageBitmap(bMap);
        String []ilacArray=ilac_bilgi.split("--");
        ilacAdi.setText(ilacAldinMiActivity.this.getString(R.string.medicine_name)+": "+dizi[1]);
        ilacAciklama.setText(ilacAldinMiActivity.this.getString(R.string.explation)+": "+dizi[2]);
        ilacAcTok.setText(ilacAldinMiActivity.this.getString(R.string.hungry_satiated)+": "+dizi[3]);
        ilacAldinMiActivity.context=getApplicationContext();

    }

    public void iptalClicked(View view){
        if(secti_mi) {
            secti_mi=false;
            aldinmi_class aldinmi = new aldinmi_class(String.valueOf(id), "0");
            ilacSaatiDB Db = new ilacSaatiDB(this);
            Db.aldinmi_ekle(aldinmi);
            this.finish();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(ilacAldinMiActivity.this).create();
            alertDialog.setTitle(ilacAldinMiActivity.this.getString(R.string.warning));
            alertDialog.setMessage(ilacAldinMiActivity.this.getString(R.string.alreadt_select));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, ilacAldinMiActivity.this.getString(R.string.ok_text),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==DialogInterface.BUTTON_NEUTRAL)
                                ilacAldinMiActivity.this.finish();
                        }
                    });
            alertDialog.show();
        }
    }

    public void alClicked(View view){
        if(secti_mi) {
            secti_mi = false;
            aldinmi_class aldinmi = new aldinmi_class(String.valueOf(id), "1");
            ilacSaatiDB Db = new ilacSaatiDB(this);
            Db.aldinmi_ekle(aldinmi);
            this.finish();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(ilacAldinMiActivity.this).create();
            alertDialog.setTitle(ilacAldinMiActivity.this.getString(R.string.warning));
            alertDialog.setMessage(ilacAldinMiActivity.this.getString(R.string.alreadt_select));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, ilacAldinMiActivity.this.getString(R.string.ok_text),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(which==DialogInterface.BUTTON_NEUTRAL)
                                ilacAldinMiActivity.this.finish();
                        }


                    });
            alertDialog.show();
        }
    }

    public void erteleClicked(View view){
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager)ilacAldinMiActivity.this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ilacAldinMiActivity.this, myBroadcast.class);
        alarmIntent = PendingIntent.getBroadcast(ilacAldinMiActivity.this, 0, intent, 0);

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        60 * 1000*5, alarmIntent);

        AlarmManager alarmMgr2;
        PendingIntent alarmIntent2;
        alarmMgr2 = (AlarmManager)ilacAldinMiActivity.this.getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(ilacAldinMiActivity.this, uygulamaBaslat.class);
        alarmIntent2 = PendingIntent.getBroadcast(ilacAldinMiActivity.this, 0, intent2, 0);

        alarmMgr2.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        60 * 1000*5, alarmIntent2);
        Toast.makeText(getApplicationContext(),ilacAldinMiActivity.this.getString(R.string.alarm_delayed_toast),Toast.LENGTH_SHORT).show();
        this.finish();
    }
    public static Context getAppContextAldinMi(){
        return ilacAldinMiActivity.context;
    }
}
