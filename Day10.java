package adventofcode2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<char[]> linesList = new ArrayList<>();

        ArrayList<Stack<Character>> arrayStack = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            linesList.add(line.toCharArray());
        }

        char previous;
        int syntaxErrorScore = 0;
        ArrayList<Long> syntaxRepairScoreArray = new ArrayList<>();
        int repairRow = 0;

        for (int i = 0; i < linesList.size(); i++) {
            Stack<Character> openers = new Stack<>();
            Stack<Character> closers = new Stack<>();
            int previousSyntaxErrorScore = syntaxErrorScore;

            for (int j = 0; j < linesList.get(i).length; j++) {
                char symbol = linesList.get(i)[j];
                if (symbol == '(' || symbol == '[' || symbol == '{' || symbol == '<') {
                    openers.push(symbol);
                } else if (symbol == ')') {
                    closers.push(symbol);
                    previous = openers.pop();
                    if (previous != '(') {
                        syntaxErrorScore += 3;
                    } else {
                        closers.pop();
                    }
                } else if (symbol == ']') {
                    closers.push(symbol);
                    previous = openers.pop();
                    if (previous != '[') {
                        syntaxErrorScore += 57;
                    } else {
                        closers.pop();
                    }
                } else if (symbol == '}') {
                    closers.push(symbol);
                    previous = openers.pop();
                    if (previous != '{') {
                        syntaxErrorScore += 1197;
                    } else {
                        closers.pop();
                    }
                } else if (symbol == '>') {
                    closers.push(symbol);
                    previous = openers.pop();
                    if (previous != '<') {
                        syntaxErrorScore += 25137;
                    } else {
                        closers.pop();
                    }
                }
            }
            long syntaxRepairScore = 0;

            if (previousSyntaxErrorScore == syntaxErrorScore) {
                while (openers.size() > 0) {
                    char symbol = openers.pop();
                    if (symbol == '(') {
                        syntaxRepairScore = (syntaxRepairScore * 5) + 1;
                    } else if (symbol == '[') {
                        syntaxRepairScore = (syntaxRepairScore * 5) + 2;
                    } else if (symbol == '{') {
                        syntaxRepairScore = (syntaxRepairScore * 5) + 3;
                    } else if (symbol == '<') {
                        syntaxRepairScore = (syntaxRepairScore * 5) + 4;
                    }
                }
                syntaxRepairScoreArray.add(syntaxRepairScore);
            }
        }

        syntaxRepairScoreArray.sort(Collections.reverseOrder());

        // 527763145 is too low
        // 554210197 is too low


        System.out.println("\nAns Part 1 = " + syntaxErrorScore);
        System.out.println(syntaxRepairScoreArray);
        System.out.println("Ans Part 2  = " + syntaxRepairScoreArray.get(Math.floorDiv(syntaxRepairScoreArray.size()-1,2)));
        System.out.println(Math.floorDiv(syntaxRepairScoreArray.size()-1,2));
        System.out.println(syntaxRepairScoreArray.size());

    }

}
