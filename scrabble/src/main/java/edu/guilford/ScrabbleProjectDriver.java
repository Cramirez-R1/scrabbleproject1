package edu.guilford;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class ScrabbleProjectDriver {

    public static void main(String[] args) {
        // Create a random ScrabbleSet instance
        ScrabbleSet randomSet = new ScrabbleSet();
        ScrabbleSet englishSet = new ScrabbleSet(true);
        // Words to calculate Scrabble values for
        System.out.println("Scrabble Scores for Words in the English Set:");
        String[] wordss = {"Banana", "Video", "Computer", "Phone", "Bottle"};
        for (String word : wordss) {
            int score = englishSet.getWordValue(word);
            System.out.println("Word: '" + word + "', Scrabble Score: " + score);
        }


        // Test words against the random ScrabbleSet
        System.out.println("");
        System.out.println("Words tested against the random Scrabble set");
        String[] words = {"Banana", "Video", "Computer", "Phone", "Bottle"};
        for (String word : words) {
            int score = randomSet.getWordScore(word);
boolean isAcceptable = score > 9;

            System.out.println("Word: '" + word + "', Acceptable: " + isAcceptable);
        }

        // Read words from the "Frankenstein" text file and find the highest Scrabble score
        File filePath = new File("../frankenstein.txt");
        String highestScoringWord = "";
        int highestScore = 0;

        // Shortest non-valid word
        String shortestNonValidWord = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");
                for (String word : wordsInLine) {
                    // Scrabble score calculation
                    int score = englishSet.getWordValue(word);

                    // Update highest scoring word if needed
                    if (score > highestScore) {
                        highestScore = score;
                        highestScoringWord = word;
                    }

                    // Update shortest non-valid word if needed
                    if (isNonValidWord(word) && (shortestNonValidWord == null || word.length() < shortestNonValidWord.length())) {
                        shortestNonValidWord = word;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (IOException e) {
            System.out.println("Error: IO Exception");
        }

        // Print the highest scoring word and its score
        System.out.println("");
        System.out.println("From the text file 'frankenstein'");
        System.out.println("Word with the Highest Scrabble Score: '" + highestScoringWord + "', Score: " + highestScore);

        // Print the shortest non-valid word
        System.out.println("Shortest Non-Valid Word: '" + shortestNonValidWord + "'");


    }

    // Helper method to check if a word is non-valid (contains digits or punctuation)
    private static boolean isNonValidWord(String word) {
        return word.matches("[a-zA-Z]+") && !word.matches(".*\\d.*");
    }
   
}


