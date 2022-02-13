package Goodrich_Tamassia.Fundamental_DS.Arrays;

/**
 * Class for storing high scores in an array in non-decreasing order.
 */
public class ScoreBoard {
    private int entries;
    private final GameEntry[] board;


    /**
     * Constructs an empty scoreboard with the given capacity for storing entries.
     *
     * @param capacity the number of game records that would be in the score board
     */
    public ScoreBoard(int capacity) {
        board = new GameEntry[capacity];
    }

    /**
     * Attempt to add a new score to the collection (if it is high enough)
     *
     * @param e the GameEntry instance that would be added
     */
    public void add(GameEntry e) {
        if (entries == board.length) {
            // board is full check if the given entry has a higher score than the smallest in the board
            if (e.getScore() > board[entries - 1].getScore()) {
                board[entries - 1] = e;
                // move the recent added entry to its corresponding position base on its score
                swap_entries();
            } else {
                System.out.println("Sorry, your score is not enough to enter to the board");
            }
            return;
        }

        // the board has free space, add the given entry regardless of its score
        board[entries] = e;
        entries++;
        // move the recent added entry to its corresponding position base on its score
        swap_entries();
    }


    /**
     * Remove and return the high score at index i.
     *
     * @param i the index of the entry to be removed
     * @return the game entry removed
     * @throws IndexOutOfBoundsException if the index given if out of the board range
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= entries)
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        GameEntry temp = board[i];              // save the object to be removed

        for (int j = i; j < entries - 1; j++)   // count up from i (not down)
            board[j] = board[j + 1];            // move one cell to the left

        board[entries - 1] = null;              // null out the old last score
        entries--;
        //  return a reference to the removed entry, which no longer
        //  has any reference pointing to it within the board array
        return temp;
    }

    /**
     * Returns a string representation of the high scores list.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int j = 0; j < entries; j++) {
            if (j > 0)
                sb.append(", ");                   // separate entries by commas
            sb.append(board[j]);
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Utility method to rearrange the board array, so it maintains the game entries in decrease order after each addition
     */
    private void swap_entries() {
        int pointer = entries - 1;
        boolean stop = false;

        while (pointer > 0 && !stop) {
            if (board[pointer].getScore() > board[pointer - 1].getScore()) {
                // swap them
                GameEntry temp = board[pointer - 1];
                board[pointer - 1] = board[pointer];
                board[pointer] = temp;
                pointer--;
            } else {
                stop = true;
            }
        }
    }

    public static void main(String[] args) {
        // The main method
        ScoreBoard highScores = new ScoreBoard(5);
        String[] names = {"Rob", "Mike", "Rose", "Jill", "Jack", "Anna", "Paul", "Bob"};
        int[] scores = {750, 1105, 590, 740, 510, 660, 720, 400};

        for (int i = 0; i < names.length; i++) {
            GameEntry gE = new GameEntry(names[i], scores[i]);
            System.out.println("Adding " + gE);
            highScores.add(gE);
            System.out.println(" Scoreboard: " + highScores);
        }
        System.out.println("Removing score at index " + 3);
        highScores.remove(3);
        System.out.println(highScores);
        System.out.println("Removing score at index " + 0);
        highScores.remove(0);
        System.out.println(highScores);
        System.out.println("Removing score at index " + 1);
        highScores.remove(1);
        System.out.println(highScores);
        System.out.println("Removing score at index " + 1);
        highScores.remove(1);
        System.out.println(highScores);
        System.out.println("Removing score at index " + 0);
        highScores.remove(0);
        System.out.println(highScores);
    }

}
