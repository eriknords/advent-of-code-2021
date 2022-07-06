package adventofcode2021;

import java.util.*;

public class Day13 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> boardList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Hashtable<String, Integer>> foldingInstructions = new ArrayList<>();

        int numberOfRows = 0;
        int numberOfColumns = 0;

        int j = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (Objects.equals(line, "")) {
                while (scanner.hasNextLine()) {
                    String foldIns = scanner.nextLine();

                    String keySplit = foldIns.split("=")[0];
                    String key = String.valueOf(keySplit.charAt(keySplit.length() - 1));
                    int value = Integer.parseInt(foldIns.split("=")[1]);

                    Hashtable<String, Integer> hashtable = new Hashtable<>();
                    hashtable.put(key, value);
                    foldingInstructions.add(hashtable);
                }
            } else {

                int[] number = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

                boardList.add(new ArrayList<Integer>());

                boardList.get(j).add(number[0]);
                boardList.get(j).add(number[1]);

                if (number[0] + 1 > numberOfColumns) numberOfColumns = number[0] + 1;
                if (number[1] + 1 > numberOfRows) numberOfRows = number[1] + 1;

                j++;
            }
        }

        int[][] board = new int[numberOfRows][numberOfColumns];
        for (j = 0; j < boardList.size(); j++) {
                int xVal = boardList.get(j).get(0);
                int yVal = boardList.get(j).get(1);
                board[yVal][xVal] = 1;
        }

        int[][] foldedBoard = foldBoard(board, foldingInstructions, 0);

        System.out.println( "\n rows: " + foldedBoard.length + " , columns: " + foldedBoard[0].length);

        int sumOfDots = 0;
        ArrayList<ArrayList<Integer>> finalBoards = new ArrayList<>();
        for (int row = 0; row < foldedBoard.length; row++) {
            finalBoards.add(new ArrayList<>());
            for (int i = 0; i < foldedBoard[0].length; i++) {
                finalBoards.get(row).add(foldedBoard[row][i]);
            }
        }

        for (int row = 0; row < foldedBoard.length; row++) {
            for (int i = 0; i < foldedBoard[0].length; i++) {
                sumOfDots += foldedBoard[row][i];
            }
        }
        System.out.println("Ans Part 1 = " + sumOfDots);
        System.out.println("Ans Part 2 = " + finalBoards);
    }

    private static int[][] foldBoard(int[][] board, ArrayList<Hashtable<String, Integer>> foldingInstructions, int i) {
        int nrOfRows = board.length;
        int nrOfColumns = board[0].length;

        String key = foldingInstructions.get(i).keys().nextElement();
        Integer value = foldingInstructions.get(i).elements().nextElement();
        int[][] newBoard;

        System.out.println("KEY : " + key);
        System.out.println("VALUE : " + value);
        if (key.equals("x")) {
            nrOfColumns = value;
            newBoard = foldBoardX(board, nrOfRows, nrOfColumns);
        } else {
            nrOfRows = value;
            newBoard = foldBoardY(board, nrOfRows, nrOfColumns);
        }

        if (i < foldingInstructions.size() - 1) {
            return foldBoard(newBoard, foldingInstructions, i+1);
        } else return newBoard;
    }
    
    private static int[][] foldBoardY(int[][] board, int yFold, int nrOfColumns) {
        int[][] yFoldedBoard = new int[yFold][nrOfColumns];

        for (int row = 0; row < yFold; row++) {
            int diffToFoldLine = yFold-row;
            for (int i = 0; i < nrOfColumns; i++) {
                if (board[row][i] + board[yFold + diffToFoldLine][i] > 0) {
                    yFoldedBoard[row][i] = 1;
                }
            }
        }
        return yFoldedBoard;
    }

    private static int[][] foldBoardX(int[][] board, int nrOfRows, int xFold) {
        int[][] xFoldedBoard = new int[nrOfRows][xFold];

        for (int row = 0; row < nrOfRows; row++) {
            for (int i = 0; i < xFold; i++) {
                int diffToFoldLine = xFold-i;
                if (board[row][i] + board[row][xFold+diffToFoldLine] > 0) {
                    xFoldedBoard[row][i] = 1;
                }
            }
        }
        return xFoldedBoard;
    }
}
