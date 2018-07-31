package com.example.mehme.ilacsaati;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ilacKaydetActivity extends MainActivity {
    Spinner sureSp,kacDefaSp,acTokSp;
    EditText ilacAdiEt;
    EditText ilacAciklamaEt;
    String []sayiArray={"1","2","3","4","5"};
    String []actokArray={"Açlık","Tokluk"};
    String []bitisArray={"Süresiz","1 Hafta","2 Hafta","3 Hafta","1 Ay","2 Ay","3 Ay","6 Ay","1 Yıl"};
    String ilac_adi,ilac_aciklama,ilac_sure,ilac_kacdefa,ilac_actok;
    ilaclar_class ilacClass;
    ilacSaatiDB DB = new ilacSaatiDB(ilacKaydetActivity.this);
    PopupMenu popup;
    Button kameraGaleri;
    ImageView ilacFoto;
    Bitmap thumbnail;
    byte[] data;
    boolean devam;
    private static int RESULT_LOAD_IMAGE_GALERI=101;
    private static int RESULT_LOAD_IMAGE_KAMERA=102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac_kaydet);
        kacDefaSp=(Spinner)findViewById(R.id.kacDefaSpinner);
        sureSp=(Spinner)findViewById(R.id.kacTaneSpinner);
        acTokSp = (Spinner)findViewById(R.id.acTokSpinner);
        ilacAdiEt=(EditText)findViewById(R.id.ilacAdiEt);
        ilacAciklamaEt=(EditText)findViewById(R.id.ilacAciklamaEt);
//        kameraGaleri=(Button)findViewById(R.id.ilacFotoBtn);
        ilacFoto=(ImageView)findViewById(R.id.ilaccImage);

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
                        return  true;
                    case R.id.galeriAc:
                        Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentGaleri,RESULT_LOAD_IMAGE_GALERI);
                        return true;
                    default :
                        return false;
                }
            }
        });

    }
    public void ilacKaydiClicked(View v){
        /********************/
        ilacFoto.setDrawingCacheEnabled(true);
        ilacFoto.buildDrawingCache();
        Bitmap bitmap = ilacFoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        data = baos.toByteArray();
        //data= ByteBuffer.allocate(4).putInt(1905).array();

        /*Drawable drawable = ilacImage.getDrawable();
        if(drawable!=null) {
            BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
            data = new Data(ilacKaydetActivity.this, bitmapDrawable.getBitmap());
        }*/
        ilac_adi=ilacAdiEt.getText().toString();
        ilac_aciklama=ilacAciklamaEt.getText().toString();
        ilac_actok=acTokSp.getSelectedItem().toString();
        ilac_kacdefa= kacDefaSp.getSelectedItem().toString();
        ilac_sure=sureSp.getSelectedItem().toString();

        ilacClass = new ilaclar_class(ilac_adi, ilac_aciklama, ilac_actok, ilac_kacdefa, ilac_sure, data);
        DB.ilac_ekle(ilacClass);
        ilacAdiEt.setText("");
        ilacAciklamaEt.setText("");
        acTokSp.setSelection(0);
        kacDefaSp.setSelection(0);
        sureSp.setSelection(0);

        Toast.makeText(this, "Kayıt başarıyla tamamlandı", Toast.LENGTH_SHORT).show();
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
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Kaydettiğiniz ilaç için alarm ayarlamak ister misiniz misiniz?").setPositiveButton("evet",dialogClickListener).setNegativeButton("hayır",dialogClickListener).show();
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sag_ustmenu,menu);
        return true;
    }

}
