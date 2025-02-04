/*

      DATE: 02/03/2025
      ASSIGNMENT: High Scores from a File
      VERSION: 1.0.0

 */

/////////////////////////////////////////
//                 Main
/////////////////////////////////////////

import java.io.*;
import java.util.*;

class CSVReader {
    private static final String HighScoreCSV = "HighScores.csv";

    public static void main(String[] args) {
        List<Player> highScores = readHighScores();

        //Add a new player with a high score between #8 and #9
        Player newPlayer = new Player("NEW", 2500000); // Score between 3000000 (#8) and 2000000 (#9)
        highScores.add(newPlayer);

        //Sort list by score (descending order)
        highScores.sort(Comparator.comparingInt(Player::getScore).reversed());

        //Remove the lowest-ranked player (new #11)
        if (highScores.size() > 10) {
            highScores.remove(10);
        }

        // Display updated scores
        System.out.println("----------------------------");
        displayHighScores(highScores);
    }

    private static List<Player> readHighScores() {
        List<Player> scores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(HighScoreCSV))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Skip header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Split and parse data
                String[] values = line.split(",");
                if (values.length == 3) {
                    scores.add(new Player(values[1].trim(), Integer.parseInt(values[2].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        }

        return scores;
    }

    private static void displayHighScores(List<Player> scores) {
        System.out.printf("%-10s %-10s%n", "Initials", "Score");
        System.out.println("----------------------------");
        for (Player player : scores) {
            System.out.printf("%-10s %-10d%n", player.getInitials(), player.getScore());
        }
    }
}

// Player class to store initials and score
class Player {
    private String initials;
    private int score;

    public Player(String initials, int score) {
        this.initials = initials;
        this.score = score;
    }

    public String getInitials() {
        return initials;
    }

    public int getScore() {
        return score;
    }
}
