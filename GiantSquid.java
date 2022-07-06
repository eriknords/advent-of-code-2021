package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GiantSquid {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int[] numbers = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int winningNumber;
        int[][] boards;
        ArrayList<ArrayList<Integer>> boardsList = new ArrayList<ArrayList<Integer>>();
        boardsList.add(new ArrayList<Integer>());


        int i = 0;
        int j = 0;
        int value;

        while (scanner.hasNextInt()) {
            value = scanner.nextInt();

            if (i == 25) {
                boardsList.add(new ArrayList<Integer>());
                j++;
                i = 0;
            }
            boardsList.get(j).add(value);
            i++;
        }

        boards = new int[boardsList.size()][51];

        for (j = 0; j < boardsList.size(); j++) {
            for (i = 0; i < 25; i++) {
                boards[j][i] = boardsList.get(j).get(i);
            }
        }

        int x = 0;

        for (int number : numbers) {
            for (j = 0; j < boardsList.size(); j++) {
                for (i = 0; i < 25; i++)
                    first:{
                        if (boards[j][50] == -1) {
                            break first;
                        }
                        if (boards[j][i] == number) {
                            boards[j][i + 25] = -1;
                        }
                        int rowPosition = i % 5;
                        if ((boards[j][i + 25 - rowPosition] == -1 &&
                                boards[j][i + 25 + 1 - rowPosition] == -1 &&
                                boards[j][i + 25 + 2 - rowPosition] == -1 &&
                                boards[j][i + 25 + 3 - rowPosition] == -1 &&
                                boards[j][i + 25 + 4 - rowPosition] == -1)) {
                            x++;
                            boards[j][50] = -1;

                            if (x == 100) {
                                winningNumber = sumOfAllunmarkedCalculatorIntMatrix(j, boards) * boards[j][i];
                                System.out.println("Ans: " + winningNumber);
                            }
                            break first;
                        }

                        int colPosition = Math.floorDiv(i, 5);
                        if ((boards[j][i + 25 - (5 * colPosition)] == -1 &&
                                boards[j][i + 25 + 5 - (5 * colPosition)] == -1 &&
                                boards[j][i + 25 + 10 - (5 * colPosition)] == -1 &&
                                boards[j][i + 25 + 15 - (5 * colPosition)] == -1 &&
                                boards[j][i + 25 + 20 - (5 * colPosition)] == -1)) {
                            x++;
                            boards[j][50] = -1;

                            if (x == 100) {
                                winningNumber = sumOfAllunmarkedCalculatorIntMatrix(j, boards) * boards[j][i];
                                System.out.println("Ans: " + winningNumber);
                            }
                        }
                    }
            }
        }
    }

    private static int sumOfAllunmarkedCalculatorIntMatrix(int boardNumber, int[][] boards) {
        int sum = 0;
        for (int i = 0; i < 25; i++) {
            if (boards[boardNumber][i+25] == 0)
                sum += boards[boardNumber][i];
        }
        return sum;
    }
}
