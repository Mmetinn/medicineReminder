package com.example.mehme.ilacsaati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class olcumKaydetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olcum_kaydet);
    }
    public void agirlikClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,agirlikOlcumActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void kanSekeriClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,kanSekeriActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void adimSayClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,adimSayActivity.class);
        startActivity(intent);
    }
    public void nabizClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,kanBasinciActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void tuketilenKaloriClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,tuketilenKaloriActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void harcananKaloriClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,harcananKaloriActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void kolestrolClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,kolestrolActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void vucutSicakligiClicked(View view){
        Intent intent = new Intent(olcumKaydetActivity.this,vucutSicakligiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
