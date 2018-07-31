package com.example.mehme.ilacsaati;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ilacListeActivity extends AppCompatActivity {
    ListView ilacList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_liste);
        ilacList=(ListView)findViewById(R.id.list);
        listele();
    }

    public void listele(){
        ilacSaatiDB db = new ilacSaatiDB(ilacListeActivity.this);
        ArrayList<String> list = db.DBArray();
        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(ilacListeActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                if(position %2 == 1)
                {
                    view.setBackgroundColor(Color.parseColor("#FFB6B546"));
                }
                else
                {
                    view.setBackgroundColor(Color.parseColor("#FFCCCB4C"));
                }
                return view;
            }
        };
        ilacList.setAdapter(adaptor);
    }

}
