package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day08 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        String signalLine;
        String digitLine;
        String[][] signalPatterns = new String[200][10];
        String[][] digits = new String[200][4];
        int[][] decodedDigits = new int[200][4];


        int numberOfApperances = 0;

        int row = 0;
        while (scanner.hasNextLine()) {
            signalLine = scanner.nextLine();
            //digitLine = scanner.nextLine();

            String[] sign = signalLine.strip().split(" ");
            //digits = digitLine.strip().split(" ");

            for (int i = 0; i <  10; i++) {
                signalPatterns[row][i] = sign[i];
            }
            for (int i = 0; i < 4; i++) {
                digits[row][i] = sign[i+11];
            }
            row++;
        }

        for (row = 0; row < digits.length; row++) {
            for (int i = 0; i < 4; i++) {
                if (digits[row][i].length() == 2 || digits[row][i].length() == 3 || digits[row][i].length() == 4 || digits[row][i].length() == 7) {
                    numberOfApperances++;
                }
            }
        }
        System.out.println("\n Ans Part 1: " + numberOfApperances);

        char charAtTop;
        char charAtTopLeft;
        char charAtTopRight;
        char charAtMiddle;
        char charAtBottomLeft;
        char charAtBottom;
        char charAtBottomRight;

        char charAtRight1;
        char charAtRight2;

        for (row = 0; row < signalPatterns.length; row++) {
            for (int i = 0; i < 10; i++) {
                char[][] charArray = new char[signalPatterns[row].length][signalPatterns[row][i].length()];
                for (int j = 0; j < charArray.length; j++) {
                    charArray[i][j] = signalPatterns[row][i].charAt(j);
                }

                if (signalPatterns[row][i].length() == 2) {
                    charAtRight1 = charArray[i][0];
                    charAtRight2 = charArray[i][1];
                } else if (signalPatterns[row][i].length() == 3) {
                    for (int j = 0; j < charArray.length; j++) {
                        // if (charArray[i][j] != charAtRight1 && charArray[i][j] != charAtRight2) {

                        }
                    }
                }

            }
            for (int i = 0; i < 4; i++) {
                if (digits[row][i].length() == 2) {
                    decodedDigits[row][i] = 1;
                } else if (digits[row][i].length() == 3) {
                    decodedDigits[row][i] = 7;
                } else if (digits[row][i].length() == 4) {
                    decodedDigits[row][i] = 4;
                } else if (digits[row][i].length() == 7) {
                    decodedDigits[row][i] = 8;
                }
            }
        }
}
