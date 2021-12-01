package exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SonarSweep {
    public static int getCount(File file) throws FileNotFoundException {
        int line;
        Integer prevLine = null;
        int count = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                line = scanner.nextInt();
                if (prevLine != null && line - prevLine > 0) {
                    count++;
                }
                prevLine = line;
            }
        }

        return count;
    }

    public static List<Integer> readFile(File file) throws FileNotFoundException {
        List<Integer> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        }

        return numbers;
    }

    public static int getSumCount(List<Integer> numbers) {
        int count = 0;

        for (int i = 0; i < numbers.size() - 3; i++) {
            int firstSum = numbers.get(i) + numbers.get(i + 1) + numbers.get(i + 2);
            int secondSum = numbers.get(i + 1) + numbers.get(i + 2) + numbers.get(i + 3);

            if (secondSum - firstSum > 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(getCount(new File("01/src/exercise/input.txt")));
        System.out.println(getSumCount(readFile(new File("01/src/exercise/input.txt"))));
    }
}