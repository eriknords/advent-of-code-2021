package adventofcode2021;

import java.util.*;

public class Day15 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> boardList = new ArrayList<ArrayList<Integer>>();
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        int[][] board = new int[10][10];
        Stack<Position> dfsStack = new Stack<>();
        Stack<Position> visitedStack = new Stack<>();
        ArrayList<Position> visitedNodes = new ArrayList<>();


        int j = 0;
        int index = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boardList.add(new ArrayList<Integer>());

            for (int i = 0; i < line.length(); i++) {
                board[j][i] = line.charAt(i) - '0';
                boardList.get(j).add(line.charAt(i) - '0');
                hashtable.put(index, line.charAt(i) - '0');
                index++;
            }
            j++;
        }

        int totalRisk = 0;
        int test = 0;
        boolean isReachedGoal = false;
        dfsStack.push(new Position(0,0));

        while (!isReachedGoal || dfsStack.size() > 1 && test < 4) {
            Position currentNode = dfsStack.pop();
            System.out.println("current node: X = " + currentNode.getX() + ", Y = " + currentNode.getY());
            visitedNodes.add(currentNode);

            List<Position> unvisitedNeighbours = getUnvisitedNeighboursOnRightCourse(currentNode, visitedNodes);
            stackNeighbourWithLowestRisk(unvisitedNeighbours, dfsStack, boardList);

            if (unvisitedNeighbours.isEmpty() && !visitedStack.isEmpty()) {
                // return to node it came from
                dfsStack.push(visitedStack.pop());
            } else {
                // add current node to stack of visited nodes
                visitedStack.push(currentNode);
            }

            totalRisk += boardList.get(currentNode.getY()).get(currentNode.getX());
            System.out.println("risk at step is = " + boardList.get(currentNode.getY()).get(currentNode.getX()));

            test++;
            if (currentNode.getX() == 9 && currentNode.getY() == 9) {
                isReachedGoal = true;
                dfsStack.pop();
                System.out.println("\n\n\n\n\n\n total risk = " + totalRisk + "\n\n");
            }
        }
        System.out.println(visitedNodes.size());
        System.out.println(dfsStack.size());
        System.out.println(visitedStack.size());
        System.out.println(totalRisk);
    }

    private static List<Position> getUnvisitedNeighboursOnRightCourse(Position currentNode, ArrayList<Position> visitedNodes) {
        List<Position> unvisitedNodes = new ArrayList<>();

        for (Position neighbour : currentNode.getAllAdjacentOnRightCourse()) {
            if (isMovable(neighbour) && !visitedNodes.contains(neighbour)) {
                unvisitedNodes.add(neighbour);
            }
        }
        return unvisitedNodes;
    }

    private static boolean isMovable(Position position) {
        return position.getX() >= 0 && position.getX() < 10 &&
                position.getY() >= 0 && position.getY() < 10;
    }

    private static void stackNeighbourWithLowestRisk(List<Position> neighbours, Stack<Position> dfsStack, ArrayList<ArrayList<Integer>> boardList) {
        ArrayList<Position> nodesWithLowestRisk = new ArrayList<>();
        int lowestRisk = 1000;

        for (Position node : neighbours) {
            int risk = boardList.get(node.getY()).get(node.getX());
            if (risk <= lowestRisk) {
                nodesWithLowestRisk.add(node);
                lowestRisk = risk;
            }
        }
        for (Position nodeWithLowestRisk : nodesWithLowestRisk) {
            System.out.println("node with lowest risk: X = " + nodeWithLowestRisk.getX() + ", Y = " + nodeWithLowestRisk.getY());
            dfsStack.push(nodeWithLowestRisk);
        }
    }
}

class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPosToSouth() {
        return new Position(x, y + 1);
    }

    public Position getPosToNorth() {
        return new Position(x, y - 1);
    }

    public Position getPosToWest() {
        return new Position(x - 1, y);
    }

    public Position getPosToEast() {
        return new Position(x + 1, y);
    }


    public boolean isAdjacent(Position newPosition) {
        if (!this.equals(newPosition)) {
            return getAllAdjacentOnRightCourse().contains(newPosition);
        }
        return false;
    }

    public List<Position> getAllAdjacentOnRightCourse() {
        return Arrays.asList(
                new Position(x, y + 1),
                //new Position(x, y - 1),
                //new Position(x - 1, y),
                new Position(x + 1, y)
        );
    }
}


/*private static Hashtable<Integer, Integer> getNeighbours(Integer key, int rowLength, int colLength) {
        ArrayList<Integer> neighbourIndexes = new ArrayList<>();

        if (key % rowLength == 0) {
            if (key == 0) {
                neighbourIndexes.add(key+1);
                neighbourIndexes.add(key+rowLength);
            } else if (key == colLength) {
                neighbourIndexes.add(key+1);
                neighbourIndexes.add(key-rowLength);
            } else {
                neighbourIndexes.add(key+1);
                neighbourIndexes.add(key-rowLength);
                neighbourIndexes.add(key+rowLength);
            }
        } else if ((key - 1) % rowLength == rowLength - 1) {
            if (key == rowLength) {
                neighbourIndexes.add(key-1);
                neighbourIndexes.add(key+rowLength);
            } else if (key == colLength) {
                neighbourIndexes.add(key+1);
                neighbourIndexes.add(key-rowLength);
            } else {
                neighbourIndexes.add(key+1);
                neighbourIndexes.add(key-rowLength);
                neighbourIndexes.add(key+rowLength);
            }
        }
    }*/

   /* private static ArrayList<ArrayList<Integer>> getNeighbours(ArrayList<ArrayList<Integer>> board, int row, int i) {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();
        int lastRow = board.size() - 1;
        int lastColumn = board.get(0).size() - 1;

        if (row == 0) { // top boundary
            if (i == 0) {
                neighbours.add(board.get(row).get(i+1));
                board.get(row+1).get(i)++;
                board.get(row+1).get(i+1)++;
            } else if (i == lastColumn) {
                board.get(row).get(i-1)++;
                board.get(row+1).get(i)++;
                board.get(row+1).get(i-1)++;
            } else {
                board.get(row).get(i-1)++;
                board.get(row).get(i+1)++;
                board.get(row+1).get(i)++;
                board.get(row+1).get(i-1)++;
                board.get(row+1).get(i+1)++;
            }
        } else if (row == lastRow) { // bottom boundary
            if (i == 0) {
                board.get(row).get(i+1)++;
                board.get(row-1).get(i)++;
                board.get(row-1).get(i+1)++;
            } else if (i == lastColumn) {
                board.get(row).get(i-1)++;
                board.get(row-1).get(i)++;
                board.get(row-1).get(i-1)++;
            } else {
                board.get(row).get(i-1)++;
                board.get(row).get(i+1)++;
                board.get(row-1).get(i)++;
                board.get(row-1).get(i-1)++;
                board.get(row-1).get(i+1)++;
            }
        } else if (i == 0) { // left boundary
            board.get(row-1).get(i)++;
            board.get(row-1).get(i+1)++;
            board.get(row).get(i+1)++;
            board.get(row+1).get(i)++;
            board.get(row+1).get(i+1)++;
        } else if (i == lastColumn) { // right boundary
            board.get(row-1).get(i)++;
            board.get(row-1).get(i-1)++;
            board.get(row).get(i-1)++;
            board.get(row+1).get(i)++;
            board.get(row+1).get(i-1)++;
        } else { // within boundaries
            board.get(row-1).get(i-1)++;
            board.get(row-1).get(i)++;
            board.get(row-1).get(i+1)++;
            board.get(row).get(i-1)++;
            board.get(row).get(i+1)++;
            board.get(row+1).get(i-1)++;
            board.get(row+1).get(i)++;
            board.get(row+1).get(i+1)++;
        }
        return board;
    }*/


