package com.example.mehme.ilacsaati;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HastaneRandevuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hastane_randevu);
    }
    public void hastaneRandevuClicked(View v){
        Uri uri = Uri.parse("https://www.mhrs.gov.tr/Vatandas/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void randevuKaydet(View v){
        Intent intent = new Intent(HastaneRandevuActivity.this,RandevuKaydetActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
