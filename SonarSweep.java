package adventofcode2021;

import java.util.ArrayList;
import java.util.Scanner;

public class SonarSweep {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int numberOfIncreases = 0;
        int oldDepth;
        int newDepth;


        ArrayList<Integer> depthList = new ArrayList<>();

        while (scanner.hasNext()) {
            depthList.add(Integer.parseInt(scanner.next()));
        }

        for (int i = 3; i < depthList.size(); i++) {

            oldDepth = depthList.get(i-3) + depthList.get(i-2) + depthList.get(i-1);
            newDepth = depthList.get(i-2) + depthList.get(i-1) + depthList.get(i);

            if (oldDepth < newDepth) {
                numberOfIncreases++;
            }
        }
        System.out.println("Answer: " + numberOfIncreases);
    }
}
