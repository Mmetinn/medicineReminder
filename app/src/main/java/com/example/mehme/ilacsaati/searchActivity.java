package com.example.mehme.ilacsaati;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class searchActivity extends AppCompatActivity  {
    BottomNavigationView bottomNavigationView;
    NavigationView nv;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Animation fromBottom;
    EditText etSearch;
    String []items;
    ArrayList<String> olasiArananlarList;
    ArrayAdapter<String>adapter;
    ListView listView;
    boolean acikmi=false;
    @SuppressLint({"ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerSearch);
        etSearch=(EditText)findViewById(R.id.etS);
        listView=(ListView)findViewById(R.id.listSearch);


        items=new String[]{"kayıtlı ilaçlar","kayıtlı alarmlar","günü bitiyor","ölçüm gir",
                "randevu al","alarmı ayarla","ilaç kaydet","acil numaralar","kayıtlı randevular",
                "ağırlık ekle","kan şekeri ekle","adım sayısı ekle","nabız ekle","kan basıncı ekle",
                "alınan kalori ekle","harcanan kalori ekle","kolestrol ekle","vucut sıcaklığı ekle",
                "randevu ekle","bizi takip et","uygulama hakkında","talep belirtin"};

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.ac,R.string.kapa);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nv=(NavigationView)findViewById(R.id.naviSearch);
        nv.setItemIconTintList(null);
//        nv.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        nv.bringToFront();



        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.requestLayout();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.kayitli_ilaclar:
                        Intent intent = new Intent(searchActivity.this,ilacListeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.kayitli_alarmlar:
                        Intent intent4=new Intent(searchActivity.this,alarmListActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.gunu_bitiyor:
                        Intent intent5=new Intent(searchActivity.this,ilacGunuBitiyorActivity.class);
                        startActivity(intent5);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.olcumler:
                        Intent intent9=new Intent(searchActivity.this,olcumKaydetActivity.class);
                        startActivity(intent9);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.randevu:
                        Intent intent6=new Intent(searchActivity.this,HastaneRandevuActivity.class);
                        startActivity(intent6);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.alarm_ayarla:
                        Intent intent2 = new Intent(searchActivity.this,MainActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.ilac_kaydet:
                        Intent intent3 = new Intent(searchActivity.this,ilacKaydetActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.acil_numara:
                        Intent intent7 = new Intent(searchActivity.this,acilNumberActivity.class);
                        startActivity(intent7);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.randevu_list:
                        Intent intent8 = new Intent(searchActivity.this,randevuListActivity.class);
                        startActivity(intent8);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                drawerLayout.closeDrawers();

                return true;
            }
        });

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_menu_search);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.ana_sayfa:
                        Intent i = new Intent(searchActivity.this,girisActivity.class);
                        startActivity(i);

                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.sosyal_medya:
                        Intent i3 = new Intent(searchActivity.this,sosyalMedyaActivity.class);
                        startActivity(i3);
                        item.setChecked(true);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.uygulama_hakkında:
                        Intent i4 = new Intent(searchActivity.this,abouthAppActivity.class);
                        startActivity(i4);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.istekleriniz:
                        Intent i5 = new Intent(searchActivity.this,claimActivity.class);
                        startActivity(i5);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;

                }
                return true;
            }
        });

        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.search).setIcon(R.drawable.ic_search_bold);
        fromBottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        etSearch.setAnimation(fromBottom);

        initListView();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                    initListView();
                else
                    searchItem(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                switch (selectedItem){
                    case "kayıtlı ilaçlar":
                        Intent i1=new Intent(searchActivity.this,ilacListeActivity.class);
                        startActivity(i1);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "kayıtlı alarmlar":
                        Intent i2=new Intent(searchActivity.this,alarmListActivity.class);
                        startActivity(i2);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "günü bitiyor":
                        Intent i3=new Intent(searchActivity.this,ilacGunuBitiyorActivity.class);
                        startActivity(i3);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "ölçüm gir":
                        Intent i4=new Intent(searchActivity.this,olcumKaydetActivity.class);
                        startActivity(i4);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "randevu al":
                        Intent i5=new Intent(searchActivity.this,HastaneRandevuActivity.class);
                        startActivity(i5);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "alarmı ayarla":
                        Intent i6=new Intent(searchActivity.this,MainActivity.class);
                        startActivity(i6);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "ilaç kaydet":
                        Intent i7=new Intent(searchActivity.this,ilacKaydetActivity.class);
                        startActivity(i7);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "acil numaralar":
                        Intent i8=new Intent(searchActivity.this,acilNumberActivity.class);
                        startActivity(i8);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "kayıtlı randevular":
                        Intent i9=new Intent(searchActivity.this,randevuListActivity.class);
                        startActivity(i9);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "ağırlık ekle":
                        Intent i10=new Intent(searchActivity.this,agirlikOlcumActivity.class);
                        startActivity(i10);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "kan şekeri ekle":
                        Intent i11=new Intent(searchActivity.this,kanSekeriActivity.class);
                        startActivity(i11);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "adım sayısı ekle":
                        Intent i12=new Intent(searchActivity.this,adimSayActivity.class);
                        startActivity(i12);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "nabız ekle":
                        Intent i13=new Intent(searchActivity.this,nabizSayActivity.class);
                        startActivity(i13);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "kan basıncı ekle":
                        Intent i14=new Intent(searchActivity.this,kanBasinciActivity.class);
                        startActivity(i14);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "alınan kalori ekle":
                        Intent i15=new Intent(searchActivity.this,tuketilenKaloriActivity.class);
                        startActivity(i15);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "harcanan kalori ekle":
                        Intent i16=new Intent(searchActivity.this,harcananKaloriActivity.class);
                        startActivity(i16);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "kolestrol ekle":
                        Intent i17=new Intent(searchActivity.this,kolestrolActivity.class);
                        startActivity(i17);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "vucut sıcaklığı ekle":
                        Intent i18=new Intent(searchActivity.this,vucutSicakligiActivity.class);
                        startActivity(i18);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "randevu ekle":
                        Intent i19=new Intent(searchActivity.this,RandevuKaydetActivity.class);
                        startActivity(i19);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "bizi takip et":
                        Intent i20=new Intent(searchActivity.this,sosyalMedyaActivity.class);
                        startActivity(i20);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "uygulama hakkında":
                        Intent i21=new Intent(searchActivity.this,abouthAppActivity.class);
                        startActivity(i21);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case "talep belirtin":
                        Intent i22=new Intent(searchActivity.this,claimActivity.class);
                        startActivity(i22);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                }
            }
        });

    }

    public void initListView(){

        olasiArananlarList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,olasiArananlarList);

        listView.setAdapter(adapter);
    }

    public void searchItem(String textToSearch){
        for (String item:items){
            if(!item.contains(textToSearch)){
                olasiArananlarList.remove(item);
            }

        }
        adapter.notifyDataSetChanged();
    }
    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.kayitli_ilaclar:
                Intent intent = new Intent(searchActivity.this,ilacListeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.kayitli_alarmlar:
                Intent intent4=new Intent(searchActivity.this,alarmListActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.gunu_bitiyor:
                Intent intent5=new Intent(searchActivity.this,ilacGunuBitiyorActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.olcumler:
                Intent intent9=new Intent(searchActivity.this,olcumKaydetActivity.class);
                startActivity(intent9);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu:
                Intent intent6=new Intent(searchActivity.this,HastaneRandevuActivity.class);
                startActivity(intent6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.alarm_ayarla:
                Intent intent2 = new Intent(searchActivity.this,MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.ilac_kaydet:
                Intent intent3 = new Intent(searchActivity.this,ilacKaydetActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.acil_numara:
                Intent intent7 = new Intent(searchActivity.this,acilNumberActivity.class);
                startActivity(intent7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.randevu_list:
                Intent intent8 = new Intent(searchActivity.this,randevuListActivity.class);
                startActivity(intent8);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }*/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.right_top_button,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
//16908332
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
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(searchActivity.this);
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
