package com.example.restservice.service;

import com.example.restservice.resource.Onregelmatig;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
public class OnregelmatigService {

    Map<String, Onregelmatig> wordMap;
    List<String> keys;

    int imperfectumScore = 0;
    int perfectumScore = 0;
    int meaningScore = 0;
    int count = 0;
    int start;
    int end;

    public void resetScores() {
        wordMap = new HashMap<>();
        keys = new ArrayList<>();
        imperfectumScore = 0;
        perfectumScore = 0;
        meaningScore = 0;
        count = 0;
    }

    public String processAnswer(Onregelmatig onregelmatig) {

        count++;
        String key = onregelmatig.getInfinitief();
        Onregelmatig answer = wordMap.get(key);

        System.out.println(key + ": ");

        if (onregelmatig.getImperfectum().equalsIgnoreCase(answer.getImperfectum())) {
            imperfectumScore++;
        }

        onregelmatig.setImperfectum(answer.getImperfectum() + "    " + onregelmatig.getImperfectum());

        if (onregelmatig.getPerfectum().equalsIgnoreCase(answer.getPerfectum())) {
            perfectumScore++;
        }

        onregelmatig.setPerfectum(answer.getPerfectum() + "    " + onregelmatig.getPerfectum());

        List<String> meanings = new LinkedList<String>(Arrays.asList(answer.getMeaning().split(",")));
        for (int i = 0; i < meanings.size(); i++) {
            if (meanings.get(i).contains("_")) {
                meanings.add(meanings.remove(i).replaceAll("_", " "));
            }
        }

        if (meanings.contains(onregelmatig.getMeaning().toLowerCase())) {
            meaningScore++;
        }
        onregelmatig.setMeaning(answer.getMeaning() + "    " + onregelmatig.getMeaning());

        return "Answered: " + count + " words\nScore imperfectum: "
                + imperfectumScore*100.0/count + "% \nperfectum: "
                + perfectumScore*100.0/count + "% \nmeaning: "
                + meaningScore*100.0/count;
    }

    public String getInfinitief() {
        return keys.remove(getRandomNumber(keys.size()));
    }

    public Onregelmatig getOnregelmatig(String key) {

        Onregelmatig answer = wordMap.get(key);

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

    public void readFile(Integer startNum, Integer endNum) throws IOException, NullPointerException {

        this.start = startNum;
        this.end = endNum;
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
        if (startNum != null && endNum != null)
            keys = keys.subList(startNum, endNum);
    }

    public Map<String, Onregelmatig> getWordMap() {
        return wordMap;
    }

    public List<String> getKeys() {
        return keys;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
