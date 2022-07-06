package adventofcode2021;

import java.math.BigInteger;
import java.util.*;

public class Day14 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Map<String, List<String>> insertionCombinations = new HashMap<>();
        Map<String, BigInteger> frequencies = new HashMap<>();

        String initialLine = scanner.nextLine();
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] pair = line.split(" -> ");
            String key = pair[0];
            String newCombo1 = pair[0].split("")[0] + pair[1];
            String newCombo2 = pair[1] + pair[0].split("")[1];

            insertionCombinations.put(key, List.of(newCombo1, newCombo2));
        }

        for (int i = 0; i < initialLine.length() - 1; i++) {
            String letterPair = initialLine.substring(i, i + 2);
            if (!frequencies.containsKey(letterPair)) {
                frequencies.put(letterPair, BigInteger.ZERO);
            }
            frequencies.put(letterPair, frequencies.get(letterPair).add(BigInteger.ONE));
        }

        int numberOfIterations = 40;

        for (int i = 0; i < numberOfIterations; i++) {
            Map<String, BigInteger> newFrequencies = new HashMap<>();

            for (Map.Entry<String, BigInteger> entrySet : frequencies.entrySet()) {
                List<String> combos = insertionCombinations.get(entrySet.getKey());
                for (String combo : combos) {
                    if (!newFrequencies.containsKey(combo)) {
                        newFrequencies.put(combo, BigInteger.ZERO);
                    }
                    newFrequencies.put(combo, newFrequencies.get(combo).add(entrySet.getValue()));
                }
            }
            frequencies = newFrequencies;
        }

        List<BigInteger> countedLetters = countLetters(frequencies, initialLine);
        Collections.sort(countedLetters);
        System.out.println("Ans = " + countedLetters.get(countedLetters.size()-1).subtract(countedLetters.get(0)));
    }

    private static List<BigInteger> countLetters(Map<String, BigInteger> frequencies, String initialLine) {

        Map<String, BigInteger> letterCountMap = new HashMap<>();
        for (Map.Entry<String, BigInteger> entrySet : frequencies.entrySet()) {
            String firstLetter = entrySet.getKey().split("")[0]; // extract first letter
            if (!letterCountMap.containsKey(firstLetter)) {
                letterCountMap.put(firstLetter, BigInteger.ZERO);
            }
            letterCountMap.put(firstLetter, letterCountMap.get(firstLetter).add(entrySet.getValue()));
        }

        // Add the last letter of the string
        String lastLetterKey = initialLine.substring(initialLine.length()-1);
        letterCountMap.put(lastLetterKey, letterCountMap.get(lastLetterKey).add(BigInteger.ONE));

        return new ArrayList<>(letterCountMap.values());
    }
}



       /* for (int i = 0; i < numberOfIterations; i++) {
            char[] chars = stringArray.get(i).toCharArray();
            char[] newChars = new char[2 * chars.length - 1];

            for (int j = 0; j < chars.length - 1; j++) {
                char[] charComboArray = new char[2];
                charComboArray[0] = chars[j];
                charComboArray[1] = chars[j+1];

                String charCombo = String.valueOf(charComboArray);
                if (hashtable.containsKey(charCombo)) {
                    newChars[2*j] = chars[j];
                    newChars[2*j + 1] = hashtable.get(charCombo).charAt(0);
                }
            }
            newChars[2* chars.length-2] = chars[chars.length-1];
            stringArray.add(new String(newChars));

            System.out.println((i+1));
            //System.out.println(getAns(stringArray.get(i)));
            //System.out.println("Ans for (i=" + (i+1) + ") = " + getAns(stringArray.get(i)));

        }

        *//*long oldDiff = 0;
        for (int j = 0; j < 19; j++) {
            long newDiff = (getAns(stringArray.get(j+1)) - getAns(stringArray.get(j)));
            //System.out.println(newDiff);
            System.out.println(newDiff-oldDiff);
            oldDiff = newDiff;

        }*//*
        //System.out.println(stringArray);
        //System.out.println("Ans Part 1/2 : " + getAns(stringArray.get(numberOfIterations)));
    }

    private static long getAns(String stringCombination) {
        Hashtable<Character, MutableInt> frequency = new Hashtable<>();

        for (int i = 0; i < stringCombination.length(); i++) {
            char charAt = stringCombination.charAt(i);

            MutableInt count = frequency.get(charAt);
            if (count == null) {
                frequency.put(charAt, new MutableInt());
            } else {
                count.increment();
            }
        }

        MutableInt maxValue = new MutableInt();
        maxValue.setValue(0);
        MutableInt minValue = new MutableInt();
        minValue.setValue(9999);

        for (Map.Entry<Character, MutableInt> set : frequency.entrySet()) {
            long setValue = set.getValue().value;
            if (setValue > maxValue.value) {
                maxValue.setValue(setValue);
            }
            if (setValue < minValue.value) {
                minValue.setValue(setValue);
            }
        }
        return maxValue.value - minValue.value;
    }
}
class MutableInt {
    long value = 1; // note that we start at 1 since we're counting
    public void increment () { ++value;      }
    public long  get ()       { return value; }
    public void setValue(long value) {this.value = value;}

}*/