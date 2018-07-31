package com.example.mehme.ilacsaati;

public class aldinmi_class {
    private int id;
    private String ilac_id;
    private String aldinmi;

    public aldinmi_class(int id, String ilac_id, String aldinmi) {
        this.id = id;
        this.ilac_id = ilac_id;
        this.aldinmi = aldinmi;
    }

    public aldinmi_class(String ilac_id, String aldinmi) {
        this.ilac_id = ilac_id;
        this.aldinmi = aldinmi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIlac_id() {
        return ilac_id;
    }

    public void setIlac_id(String ilac_id) {
        this.ilac_id = ilac_id;
    }

    public String getAldinmi() {
        return aldinmi;
    }

    public void setAldinmi(String aldinmi) {
        this.aldinmi = aldinmi;
    }
}
