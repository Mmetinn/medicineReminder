package com.example.mehme.ilacsaati;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ilacSaatiDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ilacDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="ilaclar";
    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_ADI="ilac_adi";
    private static final String COLOUMN_ACIKLAMA="ilac_aciklama";
    private static final String COLOUMN_ACTOK="aclik_tokluk";
    private static final String COLOUMN_KACDEFA="kac_defa";
    private static final String COLOUMN_SURE="ilac_sure";
    private static final String COLOUMN_IMAGEI="ilac_foto";

    private static final String query_ilac="CREATE TABLE "+TABLE_NAME+" ( "
            +COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLOUMN_ADI+" TEXT NOT NULL, "
            +COLOUMN_ACIKLAMA+" TEXT NOT NULL, "
            +COLOUMN_ACTOK+" TEXT NOT NULL, "
            +COLOUMN_KACDEFA+" TEXT NOT NULL, "
            +COLOUMN_SURE+" TEXT NOT NULL, "
            +COLOUMN_IMAGEI+" BLOB)";

    private static final String TABLENAME_ALARM="alarmlar";
    private static final String COLOUMN_ALARMID="id";
    private static final String COLOUMN_ILACID="ilac_id";
    private static final String COLOUMN_MILLIS="millis";
    private static final String COLOUMN_KACALARM="kacAlarm";


    private static final String query_alarm="CREATE TABLE "+TABLENAME_ALARM+" ( "
            +COLOUMN_ALARMID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLOUMN_ILACID+" INTEGER NOT NULL, "
            +COLOUMN_KACALARM+" TEXT NOT NULL, "
            +COLOUMN_MILLIS+" TEXT)";

    private static final String TABLENAME_RANDEVU="randevular";
    private static final String COLOUMN_IDRANDEVU="id";
    private static final String COLOUMN_HASTANEAD="hastane_adi";
    private static final String COLOUMN_POLIKLINIKAD="poliklinik_adi";
    private static final String COLOUMN_DOKTORAD="doktor_adi";
    private static final String COLOUMN_TARIH="tarih";
    private static final String COLOUMN_SAAT="saat";

    private static final String query_randevu="CREATE TABLE "+TABLENAME_RANDEVU+" ( "
            +COLOUMN_IDRANDEVU+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLOUMN_HASTANEAD+" TEXT NOT NULL, "
            +COLOUMN_POLIKLINIKAD+" TEXT NOT NULL, "
            +COLOUMN_DOKTORAD+" TEXT NOT NULL, "
            +COLOUMN_TARIH+" TEXT NOT NULL, "
            +COLOUMN_SAAT+" TEXT NOT NULL)";

    private static final String TABLENAME_OLCUMTUR="olcum_turleri";
    private static final String COLOUMN_OLCUMTURID="id";
    private static final String COLOUMN_OLCUMDEGER="olcum_deger";
    private static final String COLOUMN_OLCUMACIKLAMA="olcum_aciklama";
    private static final String COLOUMN_OLCUMGRUP="olcum_grup";
    private static final String COLOUMN_OLCUMTARIH="olcum_tarih";

    private static final String query_olcumtur="CREATE TABLE "+TABLENAME_OLCUMTUR+" ( "
            +COLOUMN_OLCUMTURID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLOUMN_OLCUMDEGER+" TEXT NOT NULL,"
            +COLOUMN_OLCUMACIKLAMA+" TEXT NOT NULL,"
            +COLOUMN_OLCUMGRUP+" TEXT NOT NULL,"
            +COLOUMN_OLCUMTARIH+" TEXT NOT NULL)";

    private static final String TABLENAME_ALDINMI="aldin_mi";
    private static final String COLOUMN_ALDINMIID="id";
    private static final String COLOUMN_ALDINMIILACID="ilac_id";
    private static final String COLOUMN_ALDINMI="aldinmi_deger";


    private static final String query_aldinmi="CREATE TABLE "+TABLENAME_ALDINMI+" ( "
            +COLOUMN_ALDINMIID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLOUMN_ALDINMIILACID+" TEXT NOT NULL, "
            +COLOUMN_ALDINMI+" TEXT NOT NULL)";

    public ilacSaatiDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_ilac);
        db.execSQL(query_alarm);
        db.execSQL(query_randevu);
        db.execSQL(query_olcumtur);
        db.execSQL(query_aldinmi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryIlac="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(queryIlac);
        String queryAlarm="DROP TABLE IF EXISTS "+TABLENAME_ALARM;
        db.execSQL(queryAlarm);
        String queryRandevu="DROP TABLE IF EXISTS "+TABLENAME_RANDEVU;
        db.execSQL(queryRandevu);
        String queryOlcum="DROP TABLE IF EXISTS "+TABLENAME_OLCUMTUR;
        db.execSQL(queryOlcum);
        String queryAldinmi="DROP TABLE IF EXISTS "+TABLENAME_ALDINMI;
        db.execSQL(queryAldinmi);
        onCreate(db);
    }



    public void aldinmi_ekle(aldinmi_class aldinmi){
        ContentValues cv = new ContentValues();

        cv.put(COLOUMN_ALDINMIILACID,aldinmi.getIlac_id());
        cv.put(COLOUMN_ALDINMI,aldinmi.getAldinmi());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLENAME_ALDINMI,null,cv);
        DB.close();
    }
    public ArrayList<String> DBAldinMi(){
        ArrayList<String> val = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;

        cursor=DB.rawQuery("SELECT * FROM "+TABLENAME_ALDINMI+" WHERE "+COLOUMN_ALDINMI+" = "+"'0'",null);

        while(cursor.moveToNext()){
            val.add(cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2));
        }
        return val;
    }

    public void olcum_ekle(olcum_class olcum){
        ContentValues cv = new ContentValues();

        cv.put(COLOUMN_OLCUMDEGER,olcum.getDeger());
        cv.put(COLOUMN_OLCUMACIKLAMA,olcum.getAciklama());
        cv.put(COLOUMN_OLCUMGRUP,olcum.getGroup_id());
        cv.put(COLOUMN_OLCUMTARIH,olcum.getTarih());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLENAME_OLCUMTUR,null,cv);
        DB.close();
    }

    public ArrayList<String> DBArrayOlcum(String groupId){
        ArrayList<String> olcumlist = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;

        cursor=DB.rawQuery("SELECT "+COLOUMN_OLCUMDEGER+" , "+COLOUMN_OLCUMACIKLAMA+" , "+COLOUMN_OLCUMTARIH+" FROM "+TABLENAME_OLCUMTUR+" WHERE "+COLOUMN_OLCUMGRUP+" = '"+groupId+"'",null);
        while (cursor.moveToNext()){
            olcumlist.add(cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2));
        }
        return olcumlist;
    }

    public DataPoint[] DBArrayOlcumXY(String groupId){
        ArrayList<String> olcumlist = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = null;

        cursor=DB.rawQuery("SELECT "+COLOUMN_OLCUMDEGER+" , "+COLOUMN_OLCUMTARIH+" FROM "+TABLENAME_OLCUMTUR+" WHERE "+COLOUMN_OLCUMGRUP+" = '"+groupId+"'",null);
        DataPoint []dataPoint= new DataPoint[cursor.getCount()];
        int i=0;
        DateFormat df = new SimpleDateFormat("dd MMM HH:MM");
        Date startDate = null;

        while (cursor.moveToNext()){
            try {
                startDate = df.parse(cursor.getString(1));
                String newDateString = df.format(startDate);
                System.out.println(newDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dataPoint[i]=new DataPoint(startDate,cursor.getLong(0));
            i++;
        }
        return dataPoint;
    }


    public void randevu_ekle(randevu_class randevu){
        ContentValues cv = new ContentValues();

        cv.put(COLOUMN_HASTANEAD,randevu.getHastaneAd());
        cv.put(COLOUMN_POLIKLINIKAD,randevu.getPoliklinikAd());
        cv.put(COLOUMN_DOKTORAD,randevu.getDoktorAd());
        cv.put(COLOUMN_TARIH,randevu.getTarih());
        cv.put(COLOUMN_SAAT,randevu.getSaat());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLENAME_RANDEVU,null,cv);
        DB.close();
    }

    public ArrayList<String> DBArrayRandevu(){
        ArrayList<String> randevulist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String randevular[]={COLOUMN_HASTANEAD,COLOUMN_POLIKLINIKAD,COLOUMN_DOKTORAD,COLOUMN_TARIH,COLOUMN_SAAT};
        Cursor cursor = DB.query(TABLENAME_RANDEVU,randevular,null, null, null, null, null);
        while (cursor.moveToNext()){
            randevulist.add(cursor.getString(0)
                    +"--"+cursor.getString(1)
                    +"--"+cursor.getString(2)
                    +"--"+cursor.getString(3)
                    +"--"+cursor.getString(4));
        }
        return randevulist;
    }

    public void alarm_ekle(alarmlar_class alarm){
        ContentValues cv = new ContentValues();

        cv.put(COLOUMN_ILACID,alarm.getIlac_sira());
        cv.put(COLOUMN_MILLIS,alarm.getMillis());
        cv.put(COLOUMN_KACALARM,alarm.getKacinci_alarm());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLENAME_ALARM,null,cv);
        DB.close();
    }

    public int alarmsSay(int gelenAlarmId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        cursor=DB.rawQuery("SELECT COUNT("+COLOUMN_ILACID+") FROM "+TABLENAME_ALARM+" WHERE "+COLOUMN_ILACID+" = "+gelenAlarmId,null);
        int kac = 0;
        if(cursor.moveToFirst()){
            kac=cursor.getInt(0);
        }
        return kac;
    }

    public ArrayList<String> DBArrayAlarm(){
        ArrayList<String> alarmlist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String alarmlar[]={COLOUMN_ALARMID,COLOUMN_ILACID,COLOUMN_MILLIS};
        Cursor cursor = DB.query(TABLENAME_ALARM,alarmlar,null, null, null, null, null);
        while (cursor.moveToNext()){
            alarmlist.add(cursor.getInt(0)
                    +"--"+cursor.getString(1)
                    +"--"+cursor.getString(2));
        }
        return alarmlist;
    }



    public void alarmDelete(long millis){
        SQLiteDatabase DB = this.getWritableDatabase();
        String where=COLOUMN_MILLIS+"="+millis;
        DB.delete(TABLENAME_ALARM,where,null);
        DB.close();
    }


    public void ilacDelete(int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        String where=COLOUMN_ID+"="+id;
        DB.delete(TABLE_NAME,where,null);
        DB.close();
    }

    //Veritabanından alarm bilgilerini çekemek için alarmListActivity'den
    //aldığım alarmId değerlerini bu metoda gönderip alarm değerlerini çektim.
    //alarm tablosunda ilac_id kısmı da mevcut bu şekilde UpdateDeleteAlarmActivity ekranında
    //istediğim şekilde listeledim.
    public int DBArrayHangiAlarm(long alarmMillis){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        int hangi_alarm = 0;
        String m=String.valueOf(alarmMillis);
        cursor=DB.rawQuery("SELECT * FROM "+TABLENAME_ALARM+" WHERE "+COLOUMN_MILLIS+"='"+m+"'",null);
        if(cursor.moveToFirst()){
            hangi_alarm=cursor.getInt(0);
        }
        return hangi_alarm;
    }
    public String DBArrayHangiAlarmIlacID(long millis){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        String hangi_alarm_id = null;

        cursor=DB.rawQuery("SELECT "+COLOUMN_ILACID+" FROM "+TABLENAME_ALARM+" WHERE "+COLOUMN_MILLIS+"="+millis,null);
        if(cursor.moveToFirst()){
            hangi_alarm_id=cursor.getString(0);
        }
        return hangi_alarm_id;
    }

    public void ilac_ekle(ilaclar_class ilac){
        ContentValues cv = new ContentValues();
        cv.put(COLOUMN_ADI,ilac.getIlac_adi());
        cv.put(COLOUMN_ACIKLAMA,ilac.getIlac_aciklama());
        cv.put(COLOUMN_ACTOK,ilac.getAc_tok());
        cv.put(COLOUMN_KACDEFA,ilac.getKac_defa());
        cv.put(COLOUMN_SURE,ilac.getSure());
        cv.put(COLOUMN_IMAGEI,ilac.getData_image());

        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME,null,cv);
        DB.close();
    }
    public ArrayList<String> DBArray(){
        ArrayList<String> urunlist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String ilaclar[]={COLOUMN_ADI,COLOUMN_ACIKLAMA,COLOUMN_ACTOK,COLOUMN_KACDEFA,COLOUMN_SURE,COLOUMN_ID};
        Cursor cursor = DB.query(TABLE_NAME,ilaclar,null, null, null, null, null);
        while (cursor.moveToNext()){
            urunlist.add(cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"+cursor.getString(3)+"--"+cursor.getString(4)+"--"+cursor.getString(5));
        }
        return urunlist;
    }

    public ArrayList<String> DBArrayIdli(){
        ArrayList<String> urunlist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String ilaclar[]={COLOUMN_ID,COLOUMN_ADI,COLOUMN_ACIKLAMA,COLOUMN_ACTOK,COLOUMN_KACDEFA,COLOUMN_SURE};
        Cursor cursor = DB.query(TABLE_NAME,ilaclar,null, null, null, null, null);
        while (cursor.moveToNext()){
            urunlist.add(cursor.getInt(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"+cursor.getString(3)+"--"+cursor.getString(4)+"--"+cursor.getString(5));
        }
        return urunlist;
    }
    public ArrayList<String> DBArrayId(){
        ArrayList<String> urunlist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String ilaclar[]={COLOUMN_ID};
        Cursor cursor = DB.query(TABLE_NAME,ilaclar,null, null, null, null, null);
        while (cursor.moveToNext()){
            urunlist.add(cursor.getString(0));
        }
        return urunlist;
    }

    public String DBArrayHangiIlac(int ilacId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        String hangi_ilac = null;

        cursor=DB.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id="+ilacId,null);
        if(cursor.moveToFirst()){
            hangi_ilac=cursor.getInt(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"
                    +cursor.getString(3)+"--"+cursor.getString(4)+"--"+cursor.getString(5);
        }
        return hangi_ilac;
    }

    public String DBArrayHangiIlacIdsiz(int ilacId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        String hangi_ilac = null;

        cursor=DB.rawQuery("SELECT "+COLOUMN_ADI+","+COLOUMN_ACIKLAMA+","+COLOUMN_ACTOK+","+COLOUMN_KACDEFA+", "+COLOUMN_SURE+" FROM "+TABLE_NAME+" WHERE id="+ilacId,null);
        if(cursor.moveToFirst()){
            hangi_ilac=cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"
                    +cursor.getString(3)+"--"+cursor.getString(4);
        }
        return hangi_ilac;
    }

    public void alarmGuncelle(int id,long millis){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLOUMN_MILLIS,millis);

        String where=COLOUMN_ALARMID+"="+id;
        DB.update(TABLENAME_ALARM,cv,where,null);
        DB.close();
    }

    public void ilacGuncelle(ilaclar_class ilac){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLOUMN_ADI,ilac.getIlac_adi());
        cv.put(COLOUMN_ACIKLAMA,ilac.getIlac_aciklama());
        cv.put(COLOUMN_ACTOK,ilac.getAc_tok());
        cv.put(COLOUMN_KACDEFA,ilac.getKac_defa());
        cv.put(COLOUMN_SURE,ilac.getSure());
        cv.put(COLOUMN_IMAGEI,ilac.getData_image());

        String where=COLOUMN_ID+"="+ilac.getId();
        DB.update(TABLE_NAME,cv,where,null);
        DB.close();
    }

    public String DBArrayHangiIlacAd(int ilacId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        String hangi_ilac = null;

        cursor=DB.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id="+ilacId,null);
        if(cursor.moveToFirst()){
            hangi_ilac=cursor.getString(1)+"  "+cursor.getString(3);
        }
        return hangi_ilac;
    }
    public String DBArrayHangiIlacAd2(int ilacId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        String hangi_ilac = null;

        cursor=DB.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id="+ilacId,null);
        if(cursor.moveToFirst()){
            hangi_ilac=cursor.getString(1);
        }
        return hangi_ilac;
    }

    public byte[] ilac_foto(int gelen_id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        byte [] a=null;

        cursor=DB.rawQuery("SELECT "+COLOUMN_IMAGEI+" FROM "+TABLE_NAME+" WHERE "+COLOUMN_ID+" = "+gelen_id,null);
        //cursor=DB.rawQuery("SELECT * FROM ilaclar WHERE id=0",null);
        if(cursor.moveToFirst()){
            a=cursor.getBlob(0);
        }
        return a;
    }

    public int kacIlacVar(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        cursor=DB.rawQuery("SELECT COUNT("+COLOUMN_ID+") FROM "+TABLE_NAME,null);
        int kac = 0;
        if(cursor.moveToFirst()){
            kac=cursor.getInt(0);
        }
        return kac;
    }
}
