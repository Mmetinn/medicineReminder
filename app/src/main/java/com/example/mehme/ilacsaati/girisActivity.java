package com.example.mehme.ilacsaati;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class girisActivity extends AppCompatActivity {
    CircleMenu circle;
    String []menu_adlari={"İlaç Ekle","Alarm Kur","İlaçları Listele"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);



        circle=(CircleMenu)findViewById(R.id.menu_circle);
        //sabah_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sabah,0,0,0);
        /*ogle_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gunduz,0,0,0);
        aksam_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sunrise,0,0,0);
        gece_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sleep,0,0,0);*/



        circle.setMainMenu(Color.parseColor("#ffffff"),R.mipmap.ic_launcher_ekle_hastane,R.mipmap.ic_launcher_ekle_hastane)
        .addSubMenu(Color.parseColor("#ff3399"),R.mipmap.ic_launcher_listele)
        .addSubMenu(Color.parseColor("#ff3399"),R.mipmap.ic_launcher_alarmhap)
        .addSubMenu(Color.parseColor("#ff3399"),R.mipmap.ic_launcher_hapiki)
        .addSubMenu(Color.parseColor("#ff3399"),R.mipmap.ic_launcher_takvim)
        .setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(girisActivity.this,ilacKaydetActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(girisActivity.this,MainActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(girisActivity.this,ilacListeActivity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(girisActivity.this,sabahOgleAksamActivity.class);
                        startActivity(intent4);
                    default:
                        break;
                }
            }
        });

    }
    /*public void clik(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void ilackaydetClicked(View v){
        Intent intent = new Intent(this,ilacKaydetActivity.class);
        startActivity(intent);
    }*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sag_ustmenu,menu);
        return true;

    }
}
