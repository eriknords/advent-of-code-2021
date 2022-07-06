package adventofcode2021;

import java.util.*;

public class Day09 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int riskLevel = 0;

        int[][] boards;
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

        boards = new int[boardsList.size()][lineLength];

        for (j = 0; j < boardsList.size(); j++) {
            for (i = 0; i < lineLength; i++) {
                boards[j][i] = boardsList.get(j).get(i);
            }
        }
        ArrayList<int[]> lowestPoints = new ArrayList<>();

        for (int row = 0; row < boardsList.size(); row++) {
            for (i = 0; i < lineLength; i++) {
                if (row == 0) { // top boundary
                    if (i == 0) {
                        if (boards[row][i] < boards[row+1][i] &&
                                boards[row][i] < boards[row][i+1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    } else if (i == lineLength-1) {
                        if (boards[row][i] < boards[row+1][i] &&
                                boards[row][i] < boards[row][i-1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    } else {
                        if (boards[row][i] < boards[row+1][i] &&
                                boards[row][i] < boards[row][i+1] &&
                                boards[row][i] < boards[row][i-1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    }
                } else if (row == boardsList.size() - 1) { // bottom boundary
                    if (i == 0) {
                        if (boards[row][i] < boards[row-1][i] &&
                                boards[row][i] < boards[row][i+1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    } else if (i == lineLength-1) {
                        if (boards[row][i] < boards[row-1][i] &&
                                boards[row][i] < boards[row][i-1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    } else {
                        if (boards[row][i] < boards[row-1][i] &&
                                boards[row][i] < boards[row][i+1] &&
                                boards[row][i] < boards[row][i-1]) {
                            riskLevel += boards[row][i] + 1;
                            lowestPoints.add(new int[]{row,i});
                        }
                    }
                } else if (i == 0) { // left boundary
                    if (boards[row][i] < boards[row - 1][i] &&
                            boards[row][i] < boards[row + 1][i] &&
                            boards[row][i] < boards[row][i + 1]) {
                        riskLevel += boards[row][i] + 1;
                        lowestPoints.add(new int[]{row,i});
                    }
                } else if (i == lineLength - 1) { // right boundary
                    if (boards[row][i] < boards[row - 1][i] &&
                            boards[row][i] < boards[row + 1][i] &&
                            boards[row][i] < boards[row][i - 1]) {
                        riskLevel += boards[row][i] + 1;
                        lowestPoints.add(new int[]{row,i});
                    }
                } else { // within boundaries
                    if (boards[row][i] < boards[row-1][i] &&
                            boards[row][i] < boards[row+1][i] &&
                            boards[row][i] < boards[row][i-1] &&
                            boards[row][i] < boards[row][i+1]) {
                        riskLevel += boards[row][i] + 1;
                        lowestPoints.add(new int[]{row,i});
                    }
                }
            }
        }
        System.out.println("Ans Part 1 = " + riskLevel);

        int[][] basinMatrix = new int[boardsList.size()][lineLength];
        for (int row = 0; row < boardsList.size(); row++) {
            for (i = 0; i < lineLength; i++) {
                if (boards[row][i] != 9) {
                    basinMatrix[row][i] = 1;
                } else {
                    basinMatrix[row][i] = 0;
                }
            }
        }

        ArrayList<Integer> basinList = new ArrayList<>();
        for (int[] lowestPoint : lowestPoints) {
            int row = lowestPoint[0];
            i = lowestPoint[1];

            ArrayList<int[]> neighboursPositionList = new ArrayList<>();
            neighboursPositionList.add(new int[]{row, i});
            basinList.add(findNeighboursInBasin(basinMatrix, boardsList, neighboursPositionList, 0));
            }

        int highest = 0;
        int secondHighest = 0;
        int thirdHighest = 0;
        for (int basins : basinList) {
            if (basins > highest) {
                thirdHighest = secondHighest;
                secondHighest = highest;
                highest = basins;
            } else if (basins > secondHighest) {
                thirdHighest = secondHighest;
                secondHighest = basins;
            } else if (basins > thirdHighest) {
                thirdHighest = basins;
            }
        }

        System.out.println("Basin list: " + basinList);
        System.out.println("highest : " + highest + "\n second highest : " + secondHighest + "\n third highest : " + thirdHighest);
        System.out.println("Ans Part 2 = " + highest * secondHighest * thirdHighest);
    }
    private static int findNeighboursInBasin(int[][] boards, ArrayList<ArrayList<Integer>> boardsList,
                                             ArrayList<int[]> neighboursPositionList, int basinSize){
        int row = neighboursPositionList.get(0)[0];
        int i = neighboursPositionList.get(0)[1];

        int lineLength = boardsList.get(0).size();
        basinSize++;

        if (row == 0) { // top boundary
            if (i == 0) {
                if (boards[row+1][i] == 1) {
                    neighboursPositionList.add(new int[]{row+1,i});
                }
                if (boards[row][i+1] == 1) {
                    neighboursPositionList.add(new int[]{row,i+1});
                }
            } else if (i == lineLength-1) {
                if (boards[row+1][i] == 1) {
                    neighboursPositionList.add(new int[]{row+1,i});
                }
                if (boards[row][i-1] == 1) {
                    neighboursPositionList.add(new int[]{row,i-1});
                }
            } else {
                if (boards[row+1][i] == 1) {
                    neighboursPositionList.add(new int[]{row+1,i});
                }
                if (boards[row][i-1] == 1) {
                    neighboursPositionList.add(new int[]{row,i-1});
                }
                if (boards[row][i+1] == 1) {
                    neighboursPositionList.add(new int[]{row,i+1});
                }
            }
        } else if (row == boardsList.size() - 1) { // bottom boundary
            if (i == 0) {
                if (boards[row-1][i] == 1) {
                    neighboursPositionList.add(new int[]{row-1,i});
                }
                if (boards[row][i+1] == 1) {
                    neighboursPositionList.add(new int[]{row,i+1});
                }
            } else if (i == lineLength-1) {
                if (boards[row-1][i] == 1) {
                    neighboursPositionList.add(new int[]{row-1,i});
                }
                if (boards[row][i-1] == 1) {
                    neighboursPositionList.add(new int[]{row,i-1});
                }
            } else {
                if (boards[row-1][i] == 1) {
                    neighboursPositionList.add(new int[]{row-1,i});
                }
                if (boards[row][i-1] == 1) {
                    neighboursPositionList.add(new int[]{row,i-1});
                }
                if (boards[row][i+1] == 1) {
                    neighboursPositionList.add(new int[]{row,i+1});
                }
            }
        } else if (i == 0) { // left boundary
            if (boards[row-1][i] == 1) {
                neighboursPositionList.add(new int[]{row-1,i});
            }
            if (boards[row+1][i] == 1) {
                neighboursPositionList.add(new int[]{row+1,i});
            }
            if (boards[row][i+1] == 1) {
                neighboursPositionList.add(new int[]{row,i+1});
            }
        } else if (i == lineLength - 1) { // right boundary
            if (boards[row-1][i] == 1) {
                neighboursPositionList.add(new int[]{row-1,i});
            }
            if (boards[row+1][i] == 1) {
                neighboursPositionList.add(new int[]{row+1,i});
            }
            if (boards[row][i-1] == 1) {
                neighboursPositionList.add(new int[]{row,i-1});
            }
        } else { // within boundaries
            if (boards[row+1][i] == 1) {
                neighboursPositionList.add(new int[]{row+1,i});
            }
            if (boards[row-1][i] == 1) {
                neighboursPositionList.add(new int[]{row-1,i});
            }
            if (boards[row][i+1] == 1) {
                neighboursPositionList.add(new int[]{row,i+1});
            }
            if (boards[row][i-1] == 1) {
                neighboursPositionList.add(new int[]{row,i-1});
            }
        }

        // Check for duplicates
        int magic = 0;
        ArrayList<Integer> listsToBeRemoved = new ArrayList<>();
        for (int j = 0; j < neighboursPositionList.size(); j++) {
            for (int k = 0; k < neighboursPositionList.size(); k++) {
                if (j != k) {
                    if (Arrays.equals(neighboursPositionList.get(j), neighboursPositionList.get(k))) {
                        if (magic % 2 == 0) {
                            listsToBeRemoved.add(k);
                            magic++;
                        }
                    }
                }
            }
        }
        listsToBeRemoved.sort(Collections.reverseOrder());
        for (int index : listsToBeRemoved) {
            neighboursPositionList.remove(index);
        }

        while (neighboursPositionList.size() > 1) {
            neighboursPositionList.remove(0);
            boards[row][i] = 0;

            basinSize = findNeighboursInBasin(boards, boardsList, neighboursPositionList, basinSize);
        }
        return basinSize;
    }
}
