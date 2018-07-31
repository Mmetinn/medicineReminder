package com.example.mehme.ilacsaati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;

public class sabahOgleAksamActivity extends AppCompatActivity {
    Button sabah_btn,ogle_btn,aksam_btn,gece_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabah_ogle_aksam);
        sabah_btn=(Button)findViewById(R.id.sabah);
        ogle_btn=(Button)findViewById(R.id.ogle);
        aksam_btn=(Button)findViewById(R.id.aksam);
        gece_btn=(Button)findViewById(R.id.gece);
    }
    public void sabahClicked(View view){
        Toast.makeText(getApplicationContext(),"sabah",Toast.LENGTH_SHORT).show();
    }
    public void ogleClicked(View view){
        Toast.makeText(getApplicationContext(),"ogle",Toast.LENGTH_SHORT).show();
    }
    public void aksamClicked(View view){
        Toast.makeText(getApplicationContext(),"aksam",Toast.LENGTH_SHORT).show();
    }
    public void geceClicked(View view){
        Toast.makeText(getApplicationContext(),"gece",Toast.LENGTH_SHORT).show();
    }
}
