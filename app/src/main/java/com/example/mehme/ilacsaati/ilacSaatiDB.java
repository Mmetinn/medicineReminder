package com.example.mehme.ilacsaati;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
    private static final String COLOUMN_ALARM1="alarm1";
    private static final String COLOUMN_ALARM2="alarm2";
    private static final String COLOUMN_ALARM3="alarm3";
    private static final String COLOUMN_ALARM4="alarm4";
    private static final String COLOUMN_ALARM5="alarm5";

    private static final String query_alarm="CREATE TABLE "+TABLENAME_ALARM+" ( "
            +COLOUMN_ALARMID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLOUMN_ILACID+" INTEGER NOT NULL, "
            +COLOUMN_ALARM1+" TEXT, "
            +COLOUMN_ALARM2+" TEXT, "
            +COLOUMN_ALARM3+" TEXT, "
            +COLOUMN_ALARM4+" TEXT, "
            +COLOUMN_ALARM5+" TEXT)";



    public ilacSaatiDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_ilac);
        db.execSQL(query_alarm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryIlac="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(queryIlac);
        String queryAlarm="DROP TABLE IF EXISTS "+TABLENAME_ALARM;
        db.execSQL(queryAlarm);
        onCreate(db);
    }

    public void alarm_ekle(alarmlar_class alarm,int kac){
        ContentValues cv = new ContentValues();

        switch (kac){
            case 1:
                cv.put(COLOUMN_ILACID,alarm.getIlac_id());
                cv.put(COLOUMN_ALARM1,alarm.getAlarm1());
                break;
            case 2:
                cv.put(COLOUMN_ILACID,alarm.getIlac_id());
                cv.put(COLOUMN_ALARM1,alarm.getAlarm1());
                cv.put(COLOUMN_ALARM2,alarm.getAlarm2());
                break;
            case 3:
                cv.put(COLOUMN_ILACID,alarm.getIlac_id());
                cv.put(COLOUMN_ALARM1,alarm.getAlarm1());
                cv.put(COLOUMN_ALARM2,alarm.getAlarm2());
                cv.put(COLOUMN_ALARM3,alarm.getAlarm3());
                break;
            case 4:
                cv.put(COLOUMN_ILACID,alarm.getIlac_id());
                cv.put(COLOUMN_ALARM1,alarm.getAlarm1());
                cv.put(COLOUMN_ALARM2,alarm.getAlarm2());
                cv.put(COLOUMN_ALARM3,alarm.getAlarm3());
                cv.put(COLOUMN_ALARM4,alarm.getAlarm4());
                break;
            case 5:
                cv.put(COLOUMN_ILACID,alarm.getIlac_id());
                cv.put(COLOUMN_ALARM1,alarm.getAlarm1());
                cv.put(COLOUMN_ALARM2,alarm.getAlarm2());
                cv.put(COLOUMN_ALARM3,alarm.getAlarm3());
                cv.put(COLOUMN_ALARM4,alarm.getAlarm4());
                cv.put(COLOUMN_ALARM5,alarm.getAlarm5());
                break;
        }





        SQLiteDatabase DB = this.getWritableDatabase();

        DB.insert(TABLENAME_ALARM,null,cv);
        DB.close();
    }

    public ArrayList<String> DBArrayAlarm(){
        ArrayList<String> alarmlist = new ArrayList<>();

        SQLiteDatabase DB = this.getReadableDatabase();

        String alarmlar[]={COLOUMN_ALARMID,COLOUMN_ILACID,COLOUMN_ALARM1,COLOUMN_ALARM2,COLOUMN_ALARM3,COLOUMN_ALARM4,COLOUMN_ALARM5};
        Cursor cursor = DB.query(TABLENAME_ALARM,alarmlar,null, null, null, null, null);
        while (cursor.moveToNext()){
            alarmlist.add(cursor.getInt(0)
                    +"--"+cursor.getInt(1)
                    +"--"+cursor.getString(2)
                    +"--"+cursor.getString(3)
                    +"--"+cursor.getString(4)
                    +"--"+cursor.getString(5)
                    +"--"+cursor.getString(6));
        }
        return alarmlist;
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

        String ilaclar[]={COLOUMN_ADI,COLOUMN_ACIKLAMA,COLOUMN_ACTOK,COLOUMN_KACDEFA,COLOUMN_SURE};
        Cursor cursor = DB.query(TABLE_NAME,ilaclar,null, null, null, null, null);
        while (cursor.moveToNext()){
            urunlist.add(cursor.getString(0)+"--"+cursor.getString(1)+"--"+cursor.getString(2)+"--"+cursor.getString(3)+"--"+cursor.getString(4));
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
