package com.example.restservice.resource;


public class Onregelmatig {

    private String infinitief;
    private String imperfectum;
    private String perfectum;
    private String meaning;

    public Onregelmatig() {
    }

    public Onregelmatig(String infinitief, String imperfectum, String perfectum, String meaning) {
        this.infinitief = infinitief;
        this.imperfectum = imperfectum;
        this.perfectum = perfectum;
        this.meaning = meaning;
    }

    public String getInfinitief() {
        return infinitief;
    }

    public String getImperfectum() {
        return imperfectum;
    }

    public String getPerfectum() {
        return perfectum;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setInfinitief(String infinitief) {
        this.infinitief = infinitief;
    }

    public void setImperfectum(String imperfectum) {
        this.imperfectum = imperfectum;
    }

    public void setPerfectum(String perfectum) {
        this.perfectum = perfectum;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
