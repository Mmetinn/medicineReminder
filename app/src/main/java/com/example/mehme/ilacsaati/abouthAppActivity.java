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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class abouthAppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    BottomNavigationView bottomNavigationView;
    NavigationView nv;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Animation fromLeft,fromRight;
    ImageView iv1,iv2,iv3,iv4;
    boolean acikmi=false;
    @SuppressLint({"ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abouth_app);

        drawerLayout=(DrawerLayout)findViewById(R.id.draweAbouth);
        iv1=(ImageView)findViewById(R.id.haberimYok);
        iv2=(ImageView)findViewById(R.id.seker);
        iv3=(ImageView)findViewById(R.id.ilacUnuttum);
        iv4=(ImageView)findViewById(R.id.randevuIv);


        fromRight= AnimationUtils.loadAnimation(this,R.anim.fromright);
        fromLeft= AnimationUtils.loadAnimation(this,R.anim.fromleft);
        iv1.setAnimation(fromRight);
        iv2.setAnimation(fromLeft);
        iv3.setAnimation(fromRight);
        iv4.setAnimation(fromLeft);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.ac,R.string.kapa);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout.requestLayout();

        nv=(NavigationView)findViewById(R.id.naviSearch);
        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();

        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);
        nv.bringToFront();


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_menu_abouth);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.uygulama_hakkında);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.ana_sayfa:
                        Intent i = new Intent(abouthAppActivity.this,girisActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.search:
                        Intent i2 = new Intent(abouthAppActivity.this,searchActivity.class);
                        startActivity(i2);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.sosyal_medya:
                        Intent i3 = new Intent(abouthAppActivity.this,sosyalMedyaActivity.class);
                        startActivity(i3);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.istekleriniz:
                        Intent i5 = new Intent(abouthAppActivity.this,claimActivity.class);
                        startActivity(i5);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        break;
                }
                return true;
            }
        });
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.uygulama_hakkında).setIcon(R.drawable.ic_about_app);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.kayitli_ilaclar:
                Intent intent = new Intent(abouthAppActivity.this,ilacListeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.kayitli_alarmlar:
                Intent intent4=new Intent(abouthAppActivity.this,alarmListActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.gunu_bitiyor:
                Intent intent5=new Intent(abouthAppActivity.this,ilacGunuBitiyorActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.olcumler:
                Intent intent9=new Intent(abouthAppActivity.this,olcumKaydetActivity.class);
                startActivity(intent9);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu:
                Intent intent6=new Intent(abouthAppActivity.this,HastaneRandevuActivity.class);
                startActivity(intent6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.alarm_ayarla:
                Intent intent2 = new Intent(abouthAppActivity.this,MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ilac_kaydet:
                Intent intent3 = new Intent(abouthAppActivity.this,ilacKaydetActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.acil_numara:
                Intent intent7 = new Intent(abouthAppActivity.this,acilNumberActivity.class);
                startActivity(intent7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu_list:
                Intent intent8 = new Intent(abouthAppActivity.this,randevuListActivity.class);
                startActivity(intent8);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    public void shareAppClicked(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Merhaba ;)\n İlaç Saati Hatırlatıcı uygulamasını aşağıdaki bağlantıya tıklayarak indirebilirsin:\n\nhttps://play.google.com/store/apps/details?id=com.benim.ilacsaati.uygulamam");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void txtClicked(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Merhaba ;)\n İlaç Saati Hatırlatıcı uygulamasını aşağıdaki bağlantıya tıklayarak indirebilirsin:\n\nhttps://play.google.com/store/apps/details?id=com.benim.ilacsaati.uygulamam");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.right_top_button,menu);

        return super.onCreateOptionsMenu(menu);
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
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(abouthAppActivity.this);
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
