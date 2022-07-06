package adventofcode2021;

import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDiagnostic {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> binaryLinesList = new ArrayList<>();
        ArrayList<String> oxygenGeneratorRating = new ArrayList<>();
        ArrayList<String> coTwoScrubberRating = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            binaryLinesList.add(nextLine);
            oxygenGeneratorRating.add(nextLine);
            coTwoScrubberRating.add(nextLine);
        }

        int lineLength = binaryLinesList.get(0).length();
        int onesOxygen;
        int zerosOxygen;
        int onesCoTwo;
        int zerosCoTwo;

        for (int i = 0; i < lineLength; i++) {
            onesOxygen = 0;
            zerosOxygen = 0;
            onesCoTwo = 0;
            zerosCoTwo = 0;

            for (String row : oxygenGeneratorRating) {

                int bit = Integer.parseInt(String.valueOf(row.toCharArray()[i]));

                if (bit == 1) {
                    onesOxygen++;
                } else if (bit == 0) {
                    zerosOxygen++;
                }
            }

            for (String row : coTwoScrubberRating) {

                int bit = Integer.parseInt(String.valueOf(row.toCharArray()[i]));

                if (bit == 1) {
                    onesCoTwo++;
                } else if (bit == 0) {
                    zerosCoTwo++;
                }
            }

            for (String row : binaryLinesList) {

                int bit = Integer.parseInt(String.valueOf(row.toCharArray()[i]));

                if (onesOxygen >= zerosOxygen) {
                    if (bit == 0) {
                        if (oxygenGeneratorRating.size() > 1) {
                            oxygenGeneratorRating.remove(row);
                        }
                    }
                } else if (onesOxygen < zerosOxygen) {
                    if (bit == 1) {
                        if (oxygenGeneratorRating.size() > 1) {
                            oxygenGeneratorRating.remove(row);
                        }
                    }
                }
                if (onesCoTwo >= zerosCoTwo) {
                    if (bit == 1) {
                        if (coTwoScrubberRating.size() > 1) {
                            coTwoScrubberRating.remove(row);
                        }
                    }
                } else if (onesCoTwo < zerosCoTwo) {
                    if (bit == 0) {
                        if (coTwoScrubberRating.size() > 1) {
                            coTwoScrubberRating.remove(row);
                        }
                    }
                }
            }
        }

        int j = 0;
        double oxygenDecimal = 0;
        double coTwoDecimal = 0;
        for (int i = lineLength - 1; i >= 0; i--) {

            oxygenDecimal += Integer.parseInt(String.valueOf(oxygenGeneratorRating.get(0).toCharArray()[i]))*Math.pow(2,j);
            coTwoDecimal += Integer.parseInt(String.valueOf(coTwoScrubberRating.get(0).toCharArray()[i]))*Math.pow(2,j);
            j++;
        }
        System.out.println("oxygen = " + oxygenGeneratorRating);
        System.out.println("co2 = " + coTwoScrubberRating);
        System.out.println("oxygen decimal: " + oxygenDecimal);
        System.out.println("co2 decimal: " + coTwoDecimal);
        System.out.println("Ans: " + (int) (oxygenDecimal * coTwoDecimal));

        /*

        Integer[] ones = new Integer[binaryLinesList.get(0).length()];
        Integer[] gamma = new Integer[binaryLinesList.get(0).length()];
        Integer[] epsilon = new Integer[binaryLinesList.get(0).length()];
        double decimalGamma = 0;
        double decimalEpsilon = 0;


        int lineLength = 0;

        for (String line : binaryLinesList) {

            var chars = line.toCharArray();
            lineLength = chars.length;

            for (int i = 0; i < chars.length; i++) {
                if (ones[i] == null) {
                    ones[i] = 0;
                }

                int bit = Integer.parseInt(String.valueOf(chars[i]));

                if (bit == 1) {
                    ones[i] = ones[i] + 1;
                }
            }
        }

        int j = 0;
        for (int i = ones.length - 1; i >= 0; i--) {
            double limit = Math.ceil(binaryLinesList.size()/2.0);
            if (ones[i] >= limit) {
                gamma[i] = 1;
                epsilon[i] = 0;
            } else {
                gamma[i] = 0;
                epsilon[i] = 1;
            }

            decimalGamma += gamma[i]*Math.pow(2,j);
            decimalEpsilon += epsilon[i]*Math.pow(2,j);
            j++;
        }

        //String oxygenGeneratorRating;





        System.out.println(binaryLinesList.size());
        System.out.println("Ones: " + Arrays.toString(ones));
        System.out.println("Gamma: " + Arrays.toString(gamma));
        System.out.println("Epsilon: " + Arrays.toString(epsilon));
        System.out.println("Gamma in decimal = " + decimalGamma);
        System.out.println("Epsilon in decimal = " + decimalEpsilon);
        System.out.println("multiplication Ans = " + decimalEpsilon * decimalGamma);*/
    }
}
