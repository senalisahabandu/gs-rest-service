package com.example.restservice.service;

import com.example.restservice.resource.Onregelmatig;
import com.example.restservice.resource.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
public class OnregelmatigService {

    Map<String, User> sessionMap = new HashMap<>();

    public void resetScores(String sessionId) {
        sessionMap.put(sessionId, new User(sessionId));
    }

    public String processAnswer(Onregelmatig onregelmatig, String sessionId) {

        User user = sessionMap.get(sessionId);
        user.setCount(user.getCount() + 1);
        String key = onregelmatig.getInfinitief();
        Onregelmatig answer = user.getWordMap().get(key);

        System.out.println(key + ": ");

        if (onregelmatig.getImperfectum().equalsIgnoreCase(answer.getImperfectum())) {
            user.setImperfectumScore(user.getImperfectumScore() + 1);
        }

        onregelmatig.setImperfectum(answer.getImperfectum() + "    " + onregelmatig.getImperfectum());

        if (onregelmatig.getPerfectum().equalsIgnoreCase(answer.getPerfectum())) {
            user.setPerfectumScore(user.getPerfectumScore() + 1);
        }

        onregelmatig.setPerfectum(answer.getPerfectum() + "    " + onregelmatig.getPerfectum());

        List<String> meanings = new LinkedList<>(Arrays.asList(answer.getMeaning().split(",")));
        for (int i = 0; i < meanings.size(); i++) {
            if (meanings.get(i).contains("_")) {
                meanings.add(meanings.remove(i).replaceAll("_", " "));
            }
        }

        if (meanings.contains(onregelmatig.getMeaning().toLowerCase())) {
            user.setMeaningScore(user.getMeaningScore() + 1);
        }
        onregelmatig.setMeaning(answer.getMeaning() + "    " + onregelmatig.getMeaning());

        return "Answered: " + user.getCount() + " words\nScore imperfectum: "
                + user.getImperfectumScore()*100.0/user.getCount() + "% \nperfectum: "
                + user.getPerfectumScore()*100.0/user.getCount() + "% \nmeaning: "
                + user.getMeaningScore()*100.0/user.getCount();
    }

    public String getInfinitief(String sessionId) {
        return sessionMap.get(sessionId).getKeys().remove(getRandomNumber(sessionMap.get(sessionId).getKeys().size()));
    }

    public Onregelmatig getOnregelmatig(String key, String sessionId) {

        Onregelmatig answer = sessionMap.get(sessionId).getWordMap().get(key);

        Onregelmatig onregelmatig = new Onregelmatig();
        onregelmatig.setInfinitief(key);
        onregelmatig.setImperfectum(answer.getImperfectum());
        onregelmatig.setPerfectum(answer.getPerfectum());
        onregelmatig.setMeaning(answer.getMeaning());

        return onregelmatig;
    }

    public int getRandomNumber(int size) {
        return (int) (Math.random() * size);
    }

    public void readFile(Integer startNum, Integer endNum, String sessionId) throws IOException {

        Map<String, Onregelmatig> wordMap = sessionMap.get(sessionId).getWordMap();
        List<String> keys = sessionMap.get(sessionId).getKeys();
        this.sessionMap.get(sessionId).setStart(startNum);
        this.sessionMap.get(sessionId).setEnd(endNum);

        try {
            File myObj = new File("resources/words");
            Scanner myReader = new Scanner(Paths.get(myObj.getAbsolutePath()));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] words = data.split(" ");
                Onregelmatig onregelmatig = new Onregelmatig(words[0], words[1], words[2], words[3]);
                wordMap.put(words[0], onregelmatig);
                keys.add(words[0]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (startNum != null && endNum != null) {
            sessionMap.get(sessionId).setKeys(keys.subList(startNum, endNum));
        }
    }

    public Map<String, User> getSessionMap() {
        return sessionMap;
    }

    public List<String> getKeys(String sessionId) {
        return sessionMap.get(sessionId).getKeys();
    }

    public int getStart(String sessionId) {
        return sessionMap.get(sessionId).getStart();
    }

    public int getEnd(String sessionId) {
        return sessionMap.get(sessionId).getEnd();
    }
}
