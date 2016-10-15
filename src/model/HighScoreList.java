package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The <code>HighScoreList</code> class is a singleton and represents
 * a highscore list. It has a list of {@link HighScoreEntry} objects.
 */
public class HighScoreList implements Serializable {
    ArrayList<HighScoreEntry> list;
    static HighScoreList instance;

    /**
     * Creates a new {@link HighScoreEntry} and adds it to the list.
     * @param name the name of the highscore entry
     * @param score the score of the highscore entry
     */
    public void add(String name, int score) {
        list.add(new HighScoreEntry(name, score));
    }

    /**
     * Sorts the list by score in decreasing order.
     */
    public void sort() {
        Collections.sort(list, Collections.reverseOrder());
    }

    /**
     * Returns the list of highscore entries.
     * @return the list of highscore entries
     */
    public ArrayList<HighScoreEntry> getList() {
        return list;
    }

    /**
     * Returns the instance of this class. Creates a new instance if
     * there is no instance.
     * @return the instance of this class
     */
    public static HighScoreList getInstance() {
        if (instance == null) {
            instance = new HighScoreList();
        }
        return instance;
    }

    /**
     * Sets the instance.
     * @param newInstance the new instance that will replace the old
     * one (if one exists)
     */
    public static void setInstance(HighScoreList newInstance) {
        instance = newInstance;
    }

    private HighScoreList() {
        list = new ArrayList<HighScoreEntry>();
    }
}
