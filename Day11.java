package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day11 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> boardsList = new ArrayList<ArrayList<Integer>>();

        int j = 0;
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boardsList.add(new ArrayList<Integer>());

            for (i = 0; i < line.length(); i++) {
                boardsList.get(j).add(line.charAt(i) - '0');
            }
            j++;
        }

        int lineLength = boardsList.get(0).size();

        int[][] boards = new int[boardsList.size()][lineLength*2];

        for (j = 0; j < boardsList.size(); j++) {
            for (i = 0; i < lineLength; i++) {
                boards[j][i] = boardsList.get(j).get(i);
            }
        }


        int flashes = 0;
        for (int step = 1; step <= 216; step++) {
            int flashesThisStep = 0;


            //First
            for (int row = 0; row < boardsList.size(); row++) {
                for (i = 0; i < lineLength; i++) {
                    boards[row][i] = boards[row][i] + 1;
                    boards[row][i+lineLength] = 0; // reset flash sign
                }
            }

            //Then
            int reactionFlashes = 10; // size determine convergence of answer -> bigger is more accurate but more demanding
            while (reactionFlashes > 0) {
                for (int row = 0; row < boardsList.size(); row++) {
                    for (i = 0; i < lineLength; i++) {
                        if (boards[row][i] > 9 &&  boards[row][i+lineLength] == 0) {
                            boards = neighbourReactions(boards, row, i);
                            boards[row][i+lineLength] = 1; //flash sign
                            flashesThisStep++;
                        }
                    }
                }
                reactionFlashes--;
            }

            //Finally

            for (int row = 0; row < boardsList.size(); row++) {
                for (i = 0; i < lineLength; i++) {
                    if (boards[row][i] > 9) {
                        boards[row][i] = 0;
                        flashes++;
                    }
                }
            }
            if (flashesThisStep == boardsList.size() * lineLength) {
                System.out.println("Ans Part 2 : Step " + step +" has " + flashesThisStep + " flashes.");
            }

            if (step == 100) {
                System.out.println("\nAns Part 1 = " + flashes);
            }
        }

    }

    private static int[][] neighbourReactions(int[][] boards, int row, int i) {
        int lastRow = boards.length - 1;
        int lastColumn = (boards[0].length/2) - 1;

        if (row == 0) { // top boundary
            if (i == 0) {
                boards[row][i+1]++;
                boards[row+1][i]++;
                boards[row+1][i+1]++;
            } else if (i == lastColumn) {
                boards[row][i-1]++;
                boards[row+1][i]++;
                boards[row+1][i-1]++;
            } else {
                boards[row][i-1]++;
                boards[row][i+1]++;
                boards[row+1][i]++;
                boards[row+1][i-1]++;
                boards[row+1][i+1]++;
            }
        } else if (row == lastRow) { // bottom boundary
            if (i == 0) {
                boards[row][i+1]++;
                boards[row-1][i]++;
                boards[row-1][i+1]++;
            } else if (i == lastColumn) {
                boards[row][i-1]++;
                boards[row-1][i]++;
                boards[row-1][i-1]++;
            } else {
                boards[row][i-1]++;
                boards[row][i+1]++;
                boards[row-1][i]++;
                boards[row-1][i-1]++;
                boards[row-1][i+1]++;
            }
        } else if (i == 0) { // left boundary
            boards[row-1][i]++;
            boards[row-1][i+1]++;
            boards[row][i+1]++;
            boards[row+1][i]++;
            boards[row+1][i+1]++;
        } else if (i == lastColumn) { // right boundary
            boards[row-1][i]++;
            boards[row-1][i-1]++;
            boards[row][i-1]++;
            boards[row+1][i]++;
            boards[row+1][i-1]++;
        } else { // within boundaries
            boards[row-1][i-1]++;
            boards[row-1][i]++;
            boards[row-1][i+1]++;
            boards[row][i-1]++;
            boards[row][i+1]++;
            boards[row+1][i-1]++;
            boards[row+1][i]++;
            boards[row+1][i+1]++;
        }
        return boards;
    }
}
