package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreList implements Serializable {
    ArrayList<HighScoreEntry> list;
    static HighScoreList instance;

    public void add(String name, int score) {
        list.add(new HighScoreEntry(name, score));
    }

    public void sort() {
        Collections.sort(list, Collections.reverseOrder());
    }

    public ArrayList<HighScoreEntry> getList() {
        return list;
    }

    public static HighScoreList getInstance() {
        if (instance == null) {
            instance = new HighScoreList();
        }
        return instance;
    }

    public static void setInstance(HighScoreList newInstance) {
        instance = newInstance;
    }

    private HighScoreList() {
        list = new ArrayList<HighScoreEntry>();
    }
}
