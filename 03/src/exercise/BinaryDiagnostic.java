package exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BinaryDiagnostic {
    public static List<List<String>> readFile(File file) throws FileNotFoundException {
        List<List<String>> numsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                List<String> numbers = List.of(line);
                numsList.add(numbers);
            }
        }

        return numsList;
    }

    public static List<String> transpose(List<List<String>> list) {
        List<String> transposedList = new ArrayList<>();
        String oldNum = list.get(0).get(0);
        for (int i = 0; i < oldNum.length(); i++) {
            StringBuilder newNumString = new StringBuilder();
            for (List<String> strings : list) {
                String oldNumList = strings.get(0);
                String newNum = oldNumList.substring(i, i + 1);
                newNumString.append(newNum);
            }
            transposedList.add(newNumString.toString());
        }
        return transposedList;
    }

    public static String getGammaRate(List<String> numbers) {
        StringBuilder gammaRate = new StringBuilder();
        for (String binaryNumber : numbers) {
            char arr[] = new char[binaryNumber.length()];
            int countZero = 0;
            int countOne = 0;
            for (int i = 0; i < binaryNumber.length(); i++) {
                arr[i] = binaryNumber.charAt(i);
                if (arr[i] == '0') {
                    countZero++;
                } else {
                    countOne++;
                }
            }
            if (countZero > countOne) {
                gammaRate.append("0");
            } else {
                gammaRate.append("1");
            }
        }
        return gammaRate.toString();
    }

    public static String getEpsilonRate(String gammaRate) {
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < gammaRate.length(); i++) {
            if (gammaRate.charAt(i) == '0') {
                epsilonRate.append('1');
            } else {
                epsilonRate.append('0');
            }
        }

        return epsilonRate.toString();
    }

    public static int getAnswer(String gamma, String epsilon) {
        int gammaDecimal = Integer.parseInt(gamma, 2);
        int epsilonDecimal = Integer.parseInt(epsilon, 2);

        return gammaDecimal * epsilonDecimal;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String gamma = getGammaRate(transpose(readFile(new File("03/src/exercise/input.txt"))));
        String epsilon = getEpsilonRate(gamma);
        System.out.println(getAnswer(gamma, epsilon));
    }
}
