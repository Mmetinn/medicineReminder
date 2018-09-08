package com.example.mehme.ilacsaati;

public class randevu_class {
    private String hastaneAd;
    private String poliklinikAd;
    private String doktorAd;
    private String tarih;
    private String saat;
    private int id;
    private long millis;
    public randevu_class(String hastaneAd, String poliklinikAd, String doktorAd, String tarih, String saat, int id) {
        this.hastaneAd = hastaneAd;
        this.poliklinikAd = poliklinikAd;
        this.doktorAd = doktorAd;
        this.tarih = tarih;
        this.saat = saat;
        this.id = id;
    }

    public randevu_class(String hastaneAd, String poliklinikAd, String doktorAd, String tarih, String saat,long millis) {
        this.hastaneAd = hastaneAd;
        this.poliklinikAd = poliklinikAd;
        this.doktorAd = doktorAd;
        this.tarih = tarih;
        this.saat = saat;
        this.millis=millis;
    }

    public String getHastaneAd() {
        return hastaneAd;
    }

    public void setHastaneAd(String hastaneAd) {
        this.hastaneAd = hastaneAd;
    }

    public String getPoliklinikAd() {
        return poliklinikAd;
    }

    public void setPoliklinikAd(String poliklinikAd) {
        this.poliklinikAd = poliklinikAd;
    }

    public String getDoktorAd() {
        return doktorAd;
    }

    public void setDoktorAd(String doktorAd) {
        this.doktorAd = doktorAd;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
