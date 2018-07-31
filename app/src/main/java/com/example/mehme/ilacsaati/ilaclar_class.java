package com.example.mehme.ilacsaati;

import java.lang.ref.SoftReference;

public class ilaclar_class {
    private String ilac_adi;
    private String ilac_aciklama;
    private String ac_tok;
    private String kac_defa;
    private String sure;
    private byte []data_image;
    private int id;
    public ilaclar_class(){}
    public ilaclar_class(String ilac_adi,String ilac_aciklama,String ac_tok,String kac_defa,String sure,byte []data_image){
        this.ilac_adi=ilac_adi;
        this.ilac_aciklama=ilac_aciklama;
        this.ac_tok=ac_tok;
        this.kac_defa=kac_defa;
        this.sure=sure;
        this.data_image=data_image;
    }

    public ilaclar_class(String ilac_adi,String ilac_aciklama,String ac_tok,String kac_defa,String sure){
        this.ilac_adi=ilac_adi;
        this.ilac_aciklama=ilac_aciklama;
        this.ac_tok=ac_tok;
        this.kac_defa=kac_defa;
        this.sure=sure;
    }

    public String getIlac_adi() {
        return ilac_adi;
    }

    public void setIlac_adi(String ilac_adi) {
        this.ilac_adi = ilac_adi;
    }

    public String getIlac_aciklama() {
        return ilac_aciklama;
    }

    public void setIlac_aciklama(String ilac_aciklama) {
        this.ilac_aciklama = ilac_aciklama;
    }

    public String getAc_tok() {
        return ac_tok;
    }

    public void setAc_tok(String ac_tok) {
        this.ac_tok = ac_tok;
    }

    public String getKac_defa() {
        return kac_defa;
    }

    public void setKac_defa(String kac_defa) {
        this.kac_defa = kac_defa;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public byte[] getData_image() {
        return data_image;
    }

    public void setData_image(byte[] data_image) {
        this.data_image = data_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
