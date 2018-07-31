package com.example.mehme.ilacsaati;

public class alarmlar_class {
    private int id;
    private int ilac_id;
    private String alarm1;
    private String alarm2;
    private String alarm3;
    private String alarm4;
    private String alarm5;

    public alarmlar_class(int ilac_id, String alarm1, String alarm2, String alarm3, String alarm4, String alarm5) {
        this.ilac_id = ilac_id;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
        this.alarm3 = alarm3;
        this.alarm4 = alarm4;
        this.alarm5 = alarm5;
    }

    public alarmlar_class(int ilac_id, String alarm1, String alarm2, String alarm3, String alarm4) {
        this.ilac_id = ilac_id;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
        this.alarm3 = alarm3;
        this.alarm4 = alarm4;
    }
    public alarmlar_class(int ilac_id, String alarm1, String alarm2, String alarm3) {
        this.ilac_id = ilac_id;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
        this.alarm3 = alarm3;
    }

    public alarmlar_class(int ilac_id, String alarm1, String alarm2) {
        this.ilac_id = ilac_id;
        this.alarm1 = alarm1;
        this.alarm2 = alarm2;
    }

    public alarmlar_class(int ilac_id, String alarm1) {
        this.ilac_id = ilac_id;
        this.alarm1 = alarm1;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIlac_id() {
        return ilac_id;
    }

    public void setIlac_id(int ilac_id) {
        this.ilac_id = ilac_id;
    }

    public String getAlarm1() {
        return alarm1;
    }

    public void setAlarm1(String alarm1) {
        this.alarm1 = alarm1;
    }

    public String getAlarm2() {
        return alarm2;
    }

    public void setAlarm2(String alarm2) {
        this.alarm2 = alarm2;
    }

    public String getAlarm3() {
        return alarm3;
    }

    public void setAlarm3(String alarm3) {
        this.alarm3 = alarm3;
    }

    public String getAlarm4() {
        return alarm4;
    }

    public void setAlarm4(String alarm4) {
        this.alarm4 = alarm4;
    }

    public String getAlarm5() {
        return alarm5;
    }

    public void setAlarm5(String alarm5) {
        this.alarm5 = alarm5;
    }
}
