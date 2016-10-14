package model;

public class HighscoreEntry implements Comparable<HighscoreEntry> {
    public String name;
    public int score;

    public HighscoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(HighscoreEntry other) {
        if (score < other.score) {
            return -1;
        } else if (score > other.score) {
            return 1;
        } else {
            return 0;
        }
    }
}
