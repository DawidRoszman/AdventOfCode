import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class PartII {
    public static void main(String[] args) {
        Integer sumOfPoints = 0;
        List<String> input = readFromFile("input.txt");
        HashMap<Integer, Integer> repetitionsOfIds = new HashMap<Integer, Integer>();
        for (int i = 0; i < input.size(); i++) {
            if (!repetitionsOfIds.containsKey(i)) {
                repetitionsOfIds.put(i, 1);
            }
            List<List<Integer>> parsedInput = parseLine(input.get(i));
            Integer points = calculatePoints(parsedInput.get(0), parsedInput.get(1));
            for (int j = i + 1; j < i + points + 1; j++) {
                if (!repetitionsOfIds.containsKey(j)) {
                    repetitionsOfIds.put(j, 1 + repetitionsOfIds.get(i));
                } else {
                    repetitionsOfIds.put(j, repetitionsOfIds.get(j) + repetitionsOfIds.get(i));
                }
            }
        }
        for (Integer id : repetitionsOfIds.keySet()) {
            System.out.println("ID: " + id + " repetitions: " + repetitionsOfIds.get(id));
            sumOfPoints += repetitionsOfIds.get(id);
        }
        System.out.println("Total points: " + sumOfPoints);
    }

    private static Integer calculatePoints(List<Integer> winning, List<Integer> given) {
        Integer points = 0;
        for (Integer number : given) {
            if (winning.contains(number)) {
                points++;
            }
        }
        return points;
    }

    private static List<String> readFromFile(String filename) {
        List<String> input = new ArrayList<String>();
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return input;
    }

    private static List<List<Integer>> parseLine(String line) {
        List<List<Integer>> games = new ArrayList<List<Integer>>();
        List<String> listOfGames = List.of(line.split(": ")[1].split("[|]"));
        List<Integer> winningNumbers = List.of(listOfGames.get(0).trim().split(" ")).stream()
                .filter(s -> !s.equals(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> myNumbers = List.of(listOfGames.get(1).trim().split(" ")).stream()
                .filter(s -> !s.equals(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        games.add(winningNumbers);
        games.add(myNumbers);

        return games;
    }
}
