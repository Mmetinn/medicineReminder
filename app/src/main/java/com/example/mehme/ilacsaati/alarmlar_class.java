package com.example.mehme.ilacsaati;

public class alarmlar_class {
    private int id;
    private long millis;
    private String ilac_sira;
    private String kacinci_alarm;


    public alarmlar_class(int id, long millis, String ilac_sira,String kacinci_alarm) {
        this.id = id;
        this.millis = millis;
        this.ilac_sira = ilac_sira;
        this.kacinci_alarm=kacinci_alarm;
    }

    public alarmlar_class(long millis, String ilac_sira, String kacinci_alarm) {
        this.millis = millis;
        this.ilac_sira = ilac_sira;
        this.kacinci_alarm = kacinci_alarm;
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

    public String getIlac_sira() {
        return ilac_sira;
    }

    public void setIlac_sira(String ilac_sira) {
        this.ilac_sira = ilac_sira;
    }

    public String getKacinci_alarm() {
        return kacinci_alarm;
    }

    public void setKacinci_alarm(String kacinci_alarm) {
        this.kacinci_alarm = kacinci_alarm;
    }
}
