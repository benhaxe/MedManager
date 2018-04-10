package com.android.benhaxe.medmanager.adapter;

/**
 * Created by Benjamin Masebinu on 30-Mar-18.
 * Lead Organizer GDG FUTA
 * Android & CMS Developer at Planet Nest
 * haxeboom@gmail.com
 */

public class MedicinePojo {
    String drugs, dosage, stop_date;

    public MedicinePojo() {
    }

    public MedicinePojo(String drugs, String dosage, String stop_date) {
        this.drugs = drugs;
        this.dosage = dosage;
        this.stop_date = stop_date;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getStop_date() {
        return stop_date;
    }

    public void setStop_date(String stop_date) {
        this.stop_date = stop_date;
    }
}
