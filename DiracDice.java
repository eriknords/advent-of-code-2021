/*
package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DiracDice {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Integer> startingPositions = new ArrayList<>();

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            String[] partsOfLine = line.split(":");
            int lastDigit = Integer.parseInt(partsOfLine[partsOfLine.length -1].trim());

            startingPositions.add(lastDigit);
        }

        int maximumScore = 0;
        ArrayList<Integer> player1Score = new ArrayList<>();
        ArrayList<Integer> player2Score = new ArrayList<>();
        int player = 0;

        ArrayList<Integer> player1Position = new ArrayList<>();
        player1Position.add(startingPositions.get(0));
        player1Position.add(startingPositions.get(0));
        player1Position.add(startingPositions.get(0));
        ArrayList<Integer> player2Position = new ArrayList<>();
        player2Position.add(startingPositions.get(1));
        player2Position.add(startingPositions.get(1));
        player2Position.add(startingPositions.get(1));

        System.out.println();

        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            dice.add(i);
        }

        int diceRolls = 1;
        int universesPlayer1 = 1;
        int universesPlayer2 = 1;

        int i = 0;
        while (maximumScore < 21) {


            if (player % 2 == 0) {
                for (int j = 0; j < universesPlayer1; j++) {
                    player1Position.add( (player1Position.get(j) + dice.get(j%3)) % 10);
                    player1Score.add(0);
                }
                universesPlayer1 = player1Position.size();

                for (int j = 0; j < player1Position.size(); j++) {
                    if (player1Position.get(j) == 0) {
                        player1Position.set(j, 10);
                    }
                    player1Score.set(j, player1Position.get(j))
                }





player1Position = (player1Position + (dice.get(i%100) + dice.get((i+1)%100) + dice.get((i+2)%100))) % 10;
                if (player1Position == 0) {
                    player1Position = 10;
                }
                player1Score += player1Position;
            } else if (player % 2 == 1) {
                player2Position = (player2Position + (dice.get(i%100) + dice.get((i+1)%100) + dice.get((i+2)%100))) % 10;
                if (player2Position == 0) {
                    player2Position = 10;
                }
                player2Score += player2Position;

            }
            player++;
            i += 3;

            maximumScore = player1Score > player2Score ? player1Score : player2Score;
        }
        int lowestScore = player1Score > player2Score ? player2Score : player1Score;
        System.out.println(" player 1: " + player1Score);
        System.out.println(" player 2: " + player2Score);
        System.out.println(maximumScore);
        System.out.println("Ans = " + lowestScore * player*3);

    }

    private static int randomNumberGenerator() {
        return ThreadLocalRandom.current().nextInt(1, 10 + 1);
    }
}
*/
