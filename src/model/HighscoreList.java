package model;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreList {
    ArrayList<HighscoreEntry> list;
    static HighscoreList instance;

    public void add(String name, int score) {
        list.add(new HighscoreEntry(name, score));
    }

    public void sort() {
        Collections.sort(list, Collections.reverseOrder());
    }

    public ArrayList<HighscoreEntry> getList() {
        return list;
    }

    public static HighscoreList getInstance() {
        if (instance == null) {
            instance = new HighscoreList();
        }
        return instance;
    }

    public static void setInstance(HighscoreList newInstance) {
        instance = newInstance;
    }

    private HighscoreList() {
        list = new ArrayList<HighscoreEntry>();
    }
}
