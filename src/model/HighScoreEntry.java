package model;

import java.io.Serializable;

public class HighScoreEntry
        implements Comparable<HighScoreEntry>, Serializable {
    public String name;
    public int score;

    public HighScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(HighScoreEntry other) {
        return ((Integer)score).compareTo(other.score);
    }
}
