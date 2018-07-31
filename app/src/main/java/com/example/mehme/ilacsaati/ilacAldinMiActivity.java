package com.example.mehme.ilacsaati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ilacAldinMiActivity extends AppCompatActivity {
    Button iptal_btn,al_btn,ertele_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_aldin_mi);
        iptal_btn=(Button)findViewById(R.id.iptal);
        al_btn=(Button)findViewById(R.id.al);
        ertele_btn=(Button)findViewById(R.id.ertele);

    }

    public void iptalClicked(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                iptal_btn.setBackgroundResource(R.mipmap.ic_launcher_iptal_beyaz);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.finish();
    }

    public void alClicked(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                al_btn.setBackgroundResource(R.mipmap.ic_launcher_tamam_beyaz);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.finish();
    }

    public void erteleClicked(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ertele_btn.setBackgroundResource(R.mipmap.ic_launcher_ertele_beyaz);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.finish();
    }
}
