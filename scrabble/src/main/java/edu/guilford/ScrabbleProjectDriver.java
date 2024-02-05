package edu.guilford;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class ScrabbleProjectDriver {
    public static void main(String[] args) {
        // Create a ScrabbleCalculator instance
        ScrabbleSet english = new ScrabbleSet(true);
        ScrabbleSet random = new ScrabbleSet();

        // Words to calculate Scrabble values for
        System.out.println("Scrabble Scores - Given Words");
        String[] words = {"Banana", "Video", "Computer", "Phone", "Bottle"};

        // Calculate and print Scrabble values for each word
        for (String word : words) {
            int score = english.getWordValue(word);
            System.out.println("Word: '" + word + "', Scrabble Score: " + score);
        }

        // test words against a random scrabbleSet object
        System.out.println("\nScrabble Scores - Random ScrabbleSet:");
        for (String word : words) {
            int score = random.getWordValue(word);
            System.out.println("Word: '" + word + "', Scrabble Score: " + score);
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
                    int score = english.getWordValue(word);

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
        System.out.println("Word with the Highest Scrabble Score: '" + highestScoringWord + "', Score: " + highestScore);

        // Print the shortest non-valid word
        System.out.println("Shortest Non-Valid Word: '" + shortestNonValidWord + "'");
    }

    // Helper method to check if a word is non-valid (contains digits or punctuation)
    private static boolean isNonValidWord(String word) {
        return word.matches("[a-zA-Z]+") && !word.matches(".*\\d.*");
    }

   
}


