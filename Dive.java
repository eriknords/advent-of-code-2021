package adventofcode2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Dive {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int depth = 0;
        int forward = 0;
        int aim = 0;

        ArrayList<String> commandList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            commandList.add(scanner.nextLine());
        }

        for (String line : commandList) {
            int value = Integer.parseInt(line.replaceAll("[^0-9]", ""));

            if (line.startsWith("f")) {
                forward += value;
                depth += (aim * value);
            } else if (line.startsWith("d")) {
                aim += value;
            } else if (line.startsWith("u")) {
                aim -= value;
            }
        }
        System.out.println("Answer: depth = " + depth + " and forward = " + forward + " Ans = " + forward * depth);
    }
}