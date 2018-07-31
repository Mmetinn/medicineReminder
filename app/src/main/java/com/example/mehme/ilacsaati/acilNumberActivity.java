package com.example.mehme.ilacsaati;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class acilNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acil_number);
    }

    @SuppressLint("MissingPermission")
    public void itfaiyeClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:110"));
        startActivity(callIntent);
    }
    @SuppressLint("MissingPermission")
    public void ambulansClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:112"));
        startActivity(callIntent);
    }
    @SuppressLint("MissingPermission")
    public void polisClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:155"));
        startActivity(callIntent);
    }
    @SuppressLint("MissingPermission")
    public void doktorClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:113"));
        startActivity(callIntent);
    }
    @SuppressLint("MissingPermission")
    public void saglikDanismaClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:184"));
        startActivity(callIntent);
    }
}
