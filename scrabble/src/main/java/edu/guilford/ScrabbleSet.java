package edu.guilford;

import java.util.Arrays;
import java.util.Random;

public class ScrabbleSet {
    private String[] letters;
    private int[] letterCount;
    private int[] letterValues;  // Array to store the point values for each letter
    private Random randomValue;

    public ScrabbleSet(boolean isEnglish) {
        if (isEnglish) {
            letters = new String[]{"A", "A", "A", "A", "A", "A", "A", "A", "A", "B", "B", "C", "C", "D", "D", "D", "D", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "F", "F", "G", "G", "G", "H", "H", "I", "I", "I", "I", "I", "I", "I", "I", "I", "J", "K", "L", "L", "L", "L", "M", "M", "N", "N", "N", "N", "N", "N", "O", "O", "O", "O", "O", "O", "O", "O", "P", "P", "Q", "R", "R", "R", "R", "R", "R", "S", "S", "S", "S", "T", "T", "T", "T", "T", "T", "U", "U", "U", "U", "V", "V", "W", "W", "X", "Y", "Y", "Z", " ", ""};
            letterCount = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2};
            letterValues = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0};
        } else {
            System.out.println("Error: Only English is supported at this time");
        }
    }

    public ScrabbleSet() {
        letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                   "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
        letterCount = new int[27]; // letters and blank
        randomValue = new Random();

        int totalTiles = 100;
        // Assign random counts to each letter
        for (int i = 0; i < letters.length; i++) {
            int maxCount = Math.min(totalTiles, 3); // You can adjust the maximum count as needed
            int randomCount = randomValue.nextInt(maxCount) + 1;
            letterCount[i] = randomCount;
            totalTiles -= randomCount;
        }
        // Handle the blank tile separately
        letterCount[26] = totalTiles; // The last element in the array is the blank tile. Placed here so it would not get the initial value of 0.

        // Initialize letterValues array with random values
        letterValues = new int[26];
        for (int i = 0; i < letterValues.length; i++) {
            letterValues[i] = randomValue.nextInt(10) + 1; // You can adjust the range of random values as needed
        }
    }

    // Add a method to get the point value for a given letter
    public int getWordValue(String word) {
        int total = 0;
        int[] letterValues = createLetterValuesArray(); // Create an array of letter values

        for (char letter : word.toUpperCase().toCharArray()) {
            if (letter >= 'A' && letter <= 'Z') {
                int index = letter - 'A';
                total += (index >= 0 && index < letterValues.length) ? letterValues[index] : 0;
            } else {
                // Handle invalid letter in the word
                return 0;
            }
        }

        return total;
    }
    public int getWordScore(String word) {
        // Check if the word is acceptable based on letter counts in the ScrabbleSet
        int[] tempLetterCount = Arrays.copyOf(letterCount, letterCount.length);

        for (char letter : word.toUpperCase().toCharArray()) {
            if (letter == ' ') {
                continue;
            }

            int index = letter - 'A';

            if (index >= 0 && index < tempLetterCount.length && tempLetterCount[index] > 0) {
                tempLetterCount[index]--;
            } else {
                // Invalid letter in the word or not enough tiles for this letter
                return 0;
            }
        }

        // If we reach here, the word is valid, calculate the score
        return getWordValue(word);
    }


    private int[] createLetterValuesArray() {
        int[] letterValues = new int[26];
        // Assign your letter values to the array, for example:
        letterValues['A' - 'A'] = 1;
        letterValues['B' - 'A'] = 3;
        letterValues['C' - 'A'] = 3;
        letterValues['D' - 'A'] = 2;
        letterValues['E' - 'A'] = 1;
        letterValues['F' - 'A'] = 4;
        letterValues['G' - 'A'] = 2;
        letterValues['H' - 'A'] = 4;
        letterValues['I' - 'A'] = 1;
        letterValues['J' - 'A'] = 8;
        letterValues['K' - 'A'] = 5;
        letterValues['L' - 'A'] = 1;
        letterValues['M' - 'A'] = 3;
        letterValues['N' - 'A'] = 1;
        letterValues['O' - 'A'] = 1;
        letterValues['P' - 'A'] = 3;
        letterValues['Q' - 'A'] = 10;
        letterValues['R' - 'A'] = 1;
        letterValues['S' - 'A'] = 1;
        letterValues['T' - 'A'] = 1;
        letterValues['U' - 'A'] = 1;
        letterValues['V' - 'A'] = 4;
        letterValues['W' - 'A'] = 4;
        letterValues['X' - 'A'] = 8;
        letterValues['Y' - 'A'] = 4;
        letterValues['Z' - 'A'] = 10;

        return letterValues;
    }

    // Generate a random Scrabble set
    public static ScrabbleSet generateRandomScrabbleSet() {
        return new ScrabbleSet();
    }

    // Add a method to test words against a Scrabble set
    public boolean testWord(String word) {
        int wordScore = getWordValue(word);
        return wordScore >= 5;
    }

    @Override
    public String toString() {
        return "ScrabbleSet {\n" +
                "Letters: " + Arrays.toString(letters) + "\n" +
                "Letter Count: " + Arrays.toString(letterCount) + "\n" +
                "Letter Values: " + Arrays.toString(letterValues) + "\n" +
                "}\n";
    }
}
