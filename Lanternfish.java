package adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lanternfish {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String line = scanner.nextLine();
        int[] timeArray = Arrays.stream(line.strip().split(",")).mapToInt(Integer::parseInt).toArray();

        long[] timerValues = new long[9];
        Arrays.fill(timerValues, 0);
        for (int age : timeArray) {
            timerValues[age]++;
        }

        for (int day = 1; day <= 256; day++) {
            long nrOfFishesToCreateNewFish = timerValues[0];
            for (int n = 1; n < timerValues.length; n++) {
                timerValues[n - 1] = timerValues[n];
            }
            timerValues[6] += nrOfFishesToCreateNewFish;
            timerValues[8] = nrOfFishesToCreateNewFish;
            if (day == 80) {
                System.out.println("\nAns part 1: " + Arrays.stream(timerValues).sum());
            }
        }
        System.out.println("Ans part 2: " + Arrays.stream(timerValues).sum());    }
}
