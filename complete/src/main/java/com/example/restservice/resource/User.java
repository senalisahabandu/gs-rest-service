package com.example.restservice.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    String id;
    Map<String, Onregelmatig> wordMap;
    List<String> keys;
    int imperfectumScore;
    int perfectumScore;
    int meaningScore;
    int count;
    int start;
    int end;

    public User(String id) {
        this.id = id;
        this.wordMap = new HashMap<>();
        this.keys = new ArrayList<>();
        this.imperfectumScore = 0;
        this.perfectumScore = 0;
        this.meaningScore = 0;
        this.count = 0;
        this.start = 0;
        this.end = 0;
    }

    public String getId() {
        return id;
    }

    public Map<String, Onregelmatig> getWordMap() {
        return wordMap;
    }

    public List<String> getKeys() {
        return keys;
    }

    public int getImperfectumScore() {
        return imperfectumScore;
    }

    public int getPerfectumScore() {
        return perfectumScore;
    }

    public int getMeaningScore() {
        return meaningScore;
    }

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWordMap(Map<String, Onregelmatig> wordMap) {
        this.wordMap = wordMap;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public void setImperfectumScore(int imperfectumScore) {
        this.imperfectumScore = imperfectumScore;
    }

    public void setPerfectumScore(int perfectumScore) {
        this.perfectumScore = perfectumScore;
    }

    public void setMeaningScore(int meaningScore) {
        this.meaningScore = meaningScore;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
