package com.example.mehme.ilacsaati;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ilacKaydetActivity extends AppCompatActivity {
    final StringBuilder builder=new StringBuilder();
    Spinner sureSp,kacDefaSp,acTokSp;
    EditText ilacAdiEt;
    EditText ilacAciklamaEt;
    String []sayiArray={"1","2","3","4","5"};
    String []actokArray={"Açlık","Tokluk"};
    String []bitisArray={"1 Haftalık","2 Haftalık","3 Haftalık","4 Haftalık","1 Aylık","2 Aylık","3 Aylık","6 Aylık"};
    ArrayList<String> listeSure=new ArrayList<>();


    String ilac_adi,ilac_aciklama,ilac_sure,ilac_kacdefa,ilac_actok;
    ilaclar_class ilacClass;
    ilacSaatiDB DB = new ilacSaatiDB(ilacKaydetActivity.this);
    PopupMenu popup;
    Button kaydetButton;
    ImageView ilacFoto;
    Bitmap thumbnail;
    TextView txD;
    String[] arrayIlac=new String[1000];
    byte[] data;
    boolean devam;
    private static int RESULT_LOAD_IMAGE_GALERI=101;
    private static int RESULT_LOAD_IMAGE_KAMERA=102;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_kaydet);
        kacDefaSp=(Spinner)findViewById(R.id.kacDefaSpinner);
        sureSp=(Spinner)findViewById(R.id.kacTaneSpinner);
        acTokSp = (Spinner)findViewById(R.id.acTokSpinner);
        ilacAdiEt=(EditText)findViewById(R.id.ilacAdiEt);
        ilacAciklamaEt=(EditText)findViewById(R.id.ilacAciklamaEt);
        kaydetButton=(Button)findViewById(R.id.kaydetBtn);
//        kameraGaleri=(Button)findViewById(R.id.ilacFotoBtn);
        ilacFoto=(ImageView)findViewById(R.id.ilaccImage);
        listeSure.add("0 Hafta");
        listeSure.add("1 Hafta");
        listeSure.add("2 Hafta");
        listeSure.add("3 Hafta");
        listeSure.add("1 Ay");
        listeSure.add("2 Ay");
        listeSure.add("3 Ay");
        listeSure.add("6 Ay");
        listeSure.add("1 Yıl");

        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sayiArray);
        ArrayAdapter<String> adapter2  = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bitisArray);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,actokArray);
        sureSp.setAdapter(adapter2);
        kacDefaSp.setAdapter(adapter);
        acTokSp.setAdapter(adapter1);
        popup = new PopupMenu(ilacKaydetActivity.this, ilacFoto);
        popup.getMenuInflater().inflate(R.menu.kamera_galeri, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.kameraAc:
                        Intent intentKamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intentKamera,RESULT_LOAD_IMAGE_KAMERA);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return  true;
                    case R.id.galeriAc:
                        Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentGaleri,RESULT_LOAD_IMAGE_GALERI);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;
                    default :
                        return false;
                }
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("ilac_id")){
            kaydetButton.setText("GÜNCELLE");
            ilacSaatiDB db = new ilacSaatiDB(this);
            Bundle extras = getIntent().getExtras();
            int id=Integer.valueOf(extras.getString("ilac_id"));
            String ilac=db.DBArrayHangiIlac(id);
            String dizi[]=ilac.split("--");
            ilacAdiEt.setText(dizi[1]);
            ilacAciklamaEt.setText(dizi[2]);
            int positionAcTok=0;
            if(dizi[3].equals("Tokluk"))
                positionAcTok=1;
            acTokSp.setSelection(positionAcTok);
            kacDefaSp.setSelection(Integer.valueOf(dizi[4]));
            sureSp.setSelection(listeSure.indexOf(dizi[5]));
            byte[]a=db.ilac_foto(id);
            Bitmap bMap = BitmapFactory.decodeByteArray(a, 0, a.length);
            ilacFoto.setImageBitmap(bMap);
        }
int l=77;
    }
    public void ilacKaydiClicked(View v){
        /********************/
        Intent intent = getIntent();



        ilac_adi=ilacAdiEt.getText().toString();
        ilac_aciklama=ilacAciklamaEt.getText().toString();
        ilac_actok=acTokSp.getSelectedItem().toString();
        ilac_kacdefa= kacDefaSp.getSelectedItem().toString();
        ilac_sure=sureSp.getSelectedItem().toString();

        ilacFoto.setDrawingCacheEnabled(true);
        ilacFoto.buildDrawingCache();
        Bitmap bitmap = ilacFoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        data = baos.toByteArray();

        if(!intent.hasExtra("ilac_id")){

            ilacClass = new ilaclar_class(ilac_adi, ilac_aciklama, ilac_actok, ilac_kacdefa, ilac_sure, data);
            DB.ilac_ekle(ilacClass);


            Toast.makeText(this, R.string.kayit_tamamlandi_toast, Toast.LENGTH_SHORT).show();
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            devam=true;
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            devam=false;
                            break;
                        default:
                            devam=false;
                            break;
                    }
                    if(devam==true){
                        devam=false;
                   /* ilacSaatiDB DB = new ilacSaatiDB(ilacKaydetActivity.this);
                    ilaclar_class ilac=new ilaclar_class(ilac_adi,ilac_aciklama,ilac_actok,ilac_kacdefa,ilac_sure);
                    DB.hangi_ilac(ilac);*/
                        Intent intent = new Intent(ilacKaydetActivity.this,MainActivity.class);
                        intent.putExtra("ilac_adi",ilac_adi);
                        intent.putExtra("ilac_aciklama",ilac_aciklama);
                        intent.putExtra("ilac_actok",ilac_actok);
                        intent.putExtra("ilac_kacdefa",ilac_kacdefa);
                        intent.putExtra("ilac_sure",ilac_sure);
                        intent.putExtra("ilac_foto",data);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(ilacKaydetActivity.this.getString(R.string.set_want_alarm)).setPositiveButton(ilacKaydetActivity.this.getString(R.string.yes_text),dialogClickListener).setNegativeButton(ilacKaydetActivity.this.getString(R.string.no_text),dialogClickListener).show();
            ilacAdiEt.setText("");
            ilacAciklamaEt.setText("");
            acTokSp.setSelection(0);
            kacDefaSp.setSelection(0);
            sureSp.setSelection(0);
        }else{
            Bundle extras = getIntent().getExtras();
            int id=Integer.valueOf(extras.getString("ilac_id"));
            ilaclar_class ilac=new ilaclar_class(ilac_adi,ilac_aciklama,ilac_actok,ilac_kacdefa,ilac_sure,data,id);
            ilacSaatiDB db = new ilacSaatiDB(this);
            db.ilacGuncelle(ilac);
            ilacAdiEt.setText("");
            ilacAciklamaEt.setText("");
            acTokSp.setSelection(0);
            kacDefaSp.setSelection(0);
            sureSp.setSelection(0);
            Toast.makeText(getApplicationContext(),R.string.ilac_guncelle_toast,Toast.LENGTH_SHORT).show();
        }

    }
    public void ilacFotoClicked(View v){
        popup.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE_GALERI){
            if(resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    ilacFoto.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else if(requestCode == RESULT_LOAD_IMAGE_KAMERA){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        ilacFoto.setMaxWidth(200);
        ilacFoto.setImageBitmap(thumbnail);
    }
    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sag_ustmenu,menu);
        return true;
    }
*/
    public void qrClicked(View view){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Document doc =  Jsoup.connect("https://patient.info/medicine").get();
                    String title = doc.title();
                    Elements data = doc.select("div[class=az]");
                    builder.append(title).append("\n");
                    for (Element link:data){
                        builder.append("--").append(link.attr("div")).append("--").append(link.text());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ilacAciklamaEt.setText(builder.toString());
                    }
                });
            }
        });
        t.start();
        arrayIlac=builder.toString().split("--");
        if(arrayIlac.length!=1) {
            Toast.makeText(getApplicationContext(), arrayIlac[15] + String.valueOf(builder.length()), Toast.LENGTH_LONG).show();
            ilacAciklamaEt.setText(arrayIlac[15]);
        }
    }
}
