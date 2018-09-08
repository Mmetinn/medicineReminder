package com.example.mehme.ilacsaati;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class sosyalMedyaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    NavigationView nv;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    boolean acikmi=false;
    @SuppressLint({"ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosyal_medya);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerSosyal);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.ac,R.string.kapa);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout.requestLayout();

        nv=(NavigationView)findViewById(R.id.naviSearch);
        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener( this);
        nv.bringToFront();

        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_menu_sosyal);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.sosyal_medya);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.ana_sayfa:
                        Intent i = new Intent(sosyalMedyaActivity.this,girisActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.search:
                        Intent i2 = new Intent(sosyalMedyaActivity.this,searchActivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.uygulama_hakkında:
                        Intent i4 = new Intent(sosyalMedyaActivity.this,abouthAppActivity.class);
                        startActivity(i4);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.istekleriniz:
                        Intent i5 = new Intent(sosyalMedyaActivity.this,claimActivity.class);
                        startActivity(i5);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;

                }
                return true;
            }
        });

        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.sosyal_medya).setIcon(R.drawable.ic_network_bold);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.kayitli_ilaclar:
                Intent intent = new Intent(sosyalMedyaActivity.this,ilacListeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.kayitli_alarmlar:
                Intent intent4=new Intent(sosyalMedyaActivity.this,alarmListActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.gunu_bitiyor:
                Intent intent5=new Intent(sosyalMedyaActivity.this,ilacGunuBitiyorActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.olcumler:
                Intent intent9=new Intent(sosyalMedyaActivity.this,olcumKaydetActivity.class);
                startActivity(intent9);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu:
                Intent intent6=new Intent(sosyalMedyaActivity.this,HastaneRandevuActivity.class);
                startActivity(intent6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.alarm_ayarla:
                Intent intent2 = new Intent(sosyalMedyaActivity.this,MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ilac_kaydet:
                Intent intent3 = new Intent(sosyalMedyaActivity.this,ilacKaydetActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.acil_numara:
                Intent intent7 = new Intent(sosyalMedyaActivity.this,acilNumberActivity.class);
                startActivity(intent7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu_list:
                Intent intent8 = new Intent(sosyalMedyaActivity.this,randevuListActivity.class);
                startActivity(intent8);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.right_top_button,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void twitterClick(View view){
        Uri uri = Uri.parse("https://twitter.com/saati_c");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void instangramClick(View view){
        Uri uri = Uri.parse("https://www.instagram.com/ilacsaati/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void gmailClick(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","ilacsaatihatirlatici@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "İLAÇ SAATİ HATIRLATICI");
        intent.putExtra(Intent.EXTRA_TEXT, "İlaç Saati Hatırlatıcı");
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));

    }
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.benim.ilacsaati.uygulamam");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;

                    }
                }
            };
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(sosyalMedyaActivity.this);
            builder.setMessage(R.string.app_star).setPositiveButton(R.string.yes_text,dialogClickListener).setNegativeButton(R.string.no_text,dialogClickListener).show();
        }
        if(id==android.R.id.home && !acikmi){
            acikmi=true;
            drawerLayout.openDrawer(GravityCompat.START);
        }
        else if(id==android.R.id.home && acikmi){
            acikmi=false;
            drawerLayout.closeDrawers();
        }

        return super.onOptionsItemSelected(item);
    }
}
