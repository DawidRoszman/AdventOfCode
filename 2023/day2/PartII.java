import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class PartII {

    public static void main(String[] args) {
        List<String> games = readFromFile("input.txt");
        HashMap<Integer, List<HashMap<String, Integer>>> separetedGames = separeteGamesIntoSubsets(games);
        Integer sumOfIds = calculateSumOfPowers(separetedGames);

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

    private static Integer calculateSumOfPowers(HashMap<Integer, List<HashMap<String, Integer>>> games) {
        List<Integer> gamesPower = new ArrayList<Integer>();

        games.forEach((key, value) -> {
            HashMap<String, Integer> maxScores = new HashMap<String, Integer>();

            value.forEach(subset -> {
                subset.forEach((color, score) -> {
                    if (maxScores.containsKey(color)) {
                        if (score > maxScores.get(color)) {
                            maxScores.put(color, score);
                        }
                    } else {
                        maxScores.put(color, score);
                    }
                });
            });

            gamesPower.add(getPowerOfSet(maxScores));

        });
        return gamesPower.stream().mapToInt(Integer::intValue).sum();
    }

    private static Integer getPowerOfSet(HashMap<String, Integer> minScores) {
        Integer[] power = { 1 };
        minScores.values().stream().mapToInt(Integer::intValue).forEach(score -> power[0] *= score);
        return power[0];
    }
}
