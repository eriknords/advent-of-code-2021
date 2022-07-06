package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HydrothermalVenture {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> inputMatrix = new ArrayList<ArrayList<Integer>>();
        inputMatrix.add(new ArrayList<Integer>());


        int i = 0;
        int j = 0;
        String line;
        int[] row = new int[4];

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            row = Arrays.stream(line.strip().split(",| -> ")).mapToInt(Integer::parseInt).toArray();

            inputMatrix.get(j).add(row[0]);
            inputMatrix.get(j).add(row[1]);
            inputMatrix.get(j).add(row[2]);
            inputMatrix.get(j).add(row[3]);

            inputMatrix.add(new ArrayList<Integer>());
            j++;
        }

        int[][] boardMatrix = new int[1000][1000];

        for (j = 0; j < inputMatrix.size() -1; j ++) {

            int x1 = inputMatrix.get(j).get(0);
            int x2 = inputMatrix.get(j).get(2);
            int y1 = inputMatrix.get(j).get(1);
            int y2 = inputMatrix.get(j).get(3);

            int xDiff = x1 - x2;
            int yDiff = y1 - y2;

            if (xDiff != 0 && yDiff != 0) {
                if (xDiff == yDiff || xDiff == -yDiff) {
                    int y = 0;
                    if (x1 < x2 && y1 < y2) {
                        y = y1;
                        for (int x = x1; x <= x2; x++) {
                            boardMatrix[x][y]++;
                            y++;
                        }
                    } else if (x1 > x2 && y1 < y2) {
                        y = y1;
                        for (int x = x1; x >= x2; x--) {
                            boardMatrix[x][y]++;
                            y++;
                        }
                    } else if (x1 < x2 && y1 > y2) {
                        y = y1;
                        for (int x = x1; x <= x2; x++) {
                            boardMatrix[x][y]++;
                            y--;
                        }
                    } else if (x1 > x2 && y1 > y2) {
                        y = y1;
                        for (int x = x1; x >= x2; x--) {
                            boardMatrix[x][y]++;
                            y--;
                        }
                    }
                    continue;
                }
            }
            if (x1 < x2) {
                for (int x = x1; x <= x2; x++) {
                    boardMatrix[x][y1]++;
                }
            } else if (x1 > x2) {
                for (int x = x2; x <= x1; x++) {
                    boardMatrix[x][y1]++;
                }
            } else if (y1 < y2) {
                for (int y = y1; y <= y2; y++) {
                    boardMatrix[x1][y]++;
                }
            } else if (y1 > y2) {
                for (int y = y2; y <= y1; y++) {
                    boardMatrix[x1][y]++;
                }
            }
        }
        System.out.println();
        int z = 0;
        int numberOfTimes = 0;

        for (i = 0; i < 1000; i++) {
            for (j = 0; j < 1000; j++) {
                if (boardMatrix[i][j] > 1) {
                    numberOfTimes++;
                }
                /*if (z == 10) {
                    System.out.println();
                    z=0;
                }
                z++;

                System.out.print(boardMatrix[j][i] + " ");*/
            }
        }
        System.out.println("NUMBER OF TIMES: " + numberOfTimes);
    }
}
