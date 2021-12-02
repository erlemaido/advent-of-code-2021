package exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dive {

    public static List<String[]> readFile(File file) throws FileNotFoundException {
        List<String[]> commands = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] command = line.split(" ");
                commands.add(command);
            }
        }

        return commands;
    }

    public static int calculatePosition(List<String[]> commands) {
        int x = 0;
        int y = 0;
        for (String[] command : commands) {
            switch (command[0]) {
                case "forward" -> x += Integer.parseInt(command[1]);
                case "down" -> y -= Integer.parseInt(command[1]);
                case "up" -> y += Integer.parseInt(command[1]);
            }
        }

        if (y < 0) {
            y = y * (-1);
        }

        return y * x;
    }

    public static int calculatePositionWithAim(List<String[]> commands) {
        int x = 0;
        int y = 0;
        int a = 0;
        for (String[] command : commands) {
            switch (command[0]) {
                case "forward":
                    x += Integer.parseInt(command[1]);
                    y += Math.abs(a) * Integer.parseInt(command[1]);
                    break;
                case "down":
                    a += Integer.parseInt(command[1]);
                    break;
                case "up":
                    a -= Integer.parseInt(command[1]);
                    break;
            }
        }

        if (y < 0) {
            y = y * (-1);
        }

        return y * x;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(calculatePositionWithAim(readFile(new File("02/src/exercise/input.txt"))));
    }
}
