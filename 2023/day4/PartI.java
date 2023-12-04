import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PartI {
    public static void main(String[] args) {
        readFromFile("input.txt");
    }

    private static List<String> readFromFile(String filename) {
        List<String> input = new ArrayList<String>();
        Integer sumOfPoints = 0;
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                Integer calculatePoints = parseLine(scanner.nextLine());
                sumOfPoints += calculatePoints;
            }
            scanner.close();
            System.out.println("Total points: " + sumOfPoints);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return input;
    }

    private static Integer parseLine(String line) {
        Integer points = 0;
        List<String> listOfGames = List.of(line.split(": ")[1].split("[|]"));
        List<String> winningNumbers = List.of(listOfGames.get(0).trim().split(" "));
        List<String> myNumbers = List.of(listOfGames.get(1).trim().split(" "));
        for (String number : myNumbers) {
            if (number.equals("")) {
                continue;
            }
            if (winningNumbers.contains(number)) {
                if (points.equals(0)) {
                    points = 1;
                } else {
                    points *= 2;
                }
            }
        }

        return points;
    }
}
