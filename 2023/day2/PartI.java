import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class PartI {

    public static void main(String[] args) {
        List<String> games = readFromFile("input.txt");
        HashMap<String, Integer> maxScores = getMaxScores();
        HashMap<Integer, List<HashMap<String, Integer>>> separetedGames = separeteGamesIntoSubsets(games);
        Integer sumOfIds = calculateSumOfIds(maxScores, separetedGames);

        System.out.println(sumOfIds);
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

    private static HashMap<String, Integer> getMaxScores() {
        HashMap<String, Integer> maxScores = new HashMap<String, Integer>();
        maxScores.put("red", 12);
        maxScores.put("green", 13);
        maxScores.put("blue", 14);
        return maxScores;

    }

    private static HashMap<Integer, List<HashMap<String, Integer>>> separeteGamesIntoSubsets(List<String> games) {
        HashMap<Integer, List<HashMap<String, Integer>>> gamesMap = new HashMap<Integer, List<HashMap<String, Integer>>>();

        games.stream().forEach(game -> {
            String[] separatedGame = game.split(":");
            Integer gameNumber = Integer.parseInt(separatedGame[0].split(" ")[1]);
            List<HashMap<String, Integer>> subsets = separeteSubset(separatedGame[1]);

            gamesMap.put(gameNumber, subsets);

        });
        return gamesMap;
    }

    private static List<HashMap<String, Integer>> separeteSubset(String subset) {
        List<HashMap<String, Integer>> subsets = new ArrayList<HashMap<String, Integer>>();

        List<String> separatedSubsets = Arrays.asList(subset.split(";"));
        separatedSubsets.stream().forEach(subsetElement -> {
            HashMap<String, Integer> subsetMap = new HashMap<String, Integer>();
            List<String> separatedSubset = Arrays.asList(subsetElement.split(","));
            separatedSubset.stream().forEach(score -> {
                String[] scores = score.trim().split(" ");
                subsetMap.put(scores[1], Integer.parseInt(scores[0]));
            });
            subsets.add(subsetMap);
        });
        return subsets;

    }

    private static Integer calculateSumOfIds(HashMap<String, Integer> maxScores,
            HashMap<Integer, List<HashMap<String, Integer>>> games) {
        List<Integer> validGames = new ArrayList<Integer>();

        games.forEach((key, value) -> {
            final boolean[] isSubsetValid = { true };

            value.forEach(subset -> {
                subset.forEach((color, score) -> {
                    if (score > maxScores.get(color)) {
                        isSubsetValid[0] = false;
                    }
                });
            });
            if (isSubsetValid[0]) {
                validGames.add(key);
            }
        });
        return validGames.stream().mapToInt(Integer::intValue).sum();
    }
}
