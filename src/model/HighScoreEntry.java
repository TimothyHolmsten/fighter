package model;

import java.io.Serializable;

/**
 * The <code>HighScoreEntry</code> class represents an entry in a
 * {@link HighScoreList}.
 */

public class HighScoreEntry
        implements Comparable<HighScoreEntry>, Serializable {
    public String name;
    public int score;

    /**
     * Constructs a highscore entry with specified name and score.
     */
    public HighScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Compares the score of this to the score of another highscore
     * entry.
     *
     * @param other the other highscore entry
     * @return &lt;0 if this is less than other, 0 if they are equal
     * and &gt;0 if this is greater than other
     */
    @Override
    public int compareTo(HighScoreEntry other) {
        return ((Integer) score).compareTo(other.score);
    }
}
