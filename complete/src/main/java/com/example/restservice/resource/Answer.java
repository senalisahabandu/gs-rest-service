package com.example.restservice.resource;

public class Answer {
    String answer;
    Boolean perfectum;
    Boolean imperfectum;
    Boolean meaning;

    public Answer() {
        this.answer = "";
        this.imperfectum = false;
        this.perfectum = false;
        this.meaning = false;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getPerfectum() {
        return perfectum;
    }

    public void setPerfectum(Boolean perfectum) {
        this.perfectum = perfectum;
    }

    public Boolean getImperfectum() {
        return imperfectum;
    }

    public void setImperfectum(Boolean imperfectum) {
        this.imperfectum = imperfectum;
    }

    public Boolean getMeaning() {
        return meaning;
    }

    public void setMeaning(Boolean meaning) {
        this.meaning = meaning;
    }
}
