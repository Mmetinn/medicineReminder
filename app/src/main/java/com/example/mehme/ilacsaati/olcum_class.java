package com.example.mehme.ilacsaati;

public class olcum_class {
    private int id;
    private String deger;
    private String aciklama;
    private String group_id;
    private String tarih;

    public olcum_class(int id, String deger, String aciklama, String group_id, String tarih) {
        this.id = id;
        this.deger = deger;
        this.aciklama = aciklama;
        this.group_id = group_id;
        this.tarih = tarih;
    }

    public olcum_class(String deger, String aciklama, String group_id, String tarih) {
        this.deger = deger;
        this.aciklama = aciklama;
        this.group_id = group_id;
        this.tarih = tarih;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
