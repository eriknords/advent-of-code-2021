package adventofcode2021;

import java.util.Arrays;
import java.util.Scanner;

public class Day07 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int[] numbers = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int minNumber = Arrays.stream(numbers).min().getAsInt();
        int maxNumber = Arrays.stream(numbers).max().getAsInt();

        int[] fuelCost = new int[maxNumber+1];
        fuelCost[0] = 0;
        for (int i = 1; i < fuelCost.length; i++) {
            fuelCost[i] = fuelCost[i-1] + i;
        }

        int minimumDiff = Integer.MAX_VALUE;

        for (int i = minNumber; i <  maxNumber; i++) {
            int tempDiff = 0;
            for (int number : numbers) {
                int fuelCostIndex = Math.abs(i - number);
                tempDiff += fuelCost[fuelCostIndex];
            }
            if (tempDiff < minimumDiff) {
                minimumDiff = tempDiff;
            }
        }
        System.out.println("\n Ans: " + minimumDiff);
    }
}
