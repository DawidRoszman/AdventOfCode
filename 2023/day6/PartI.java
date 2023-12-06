import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PartI {
    public static void main(String[] args) {
        List<String> lines = readFromFile("input.txt");
        List<List<Integer>> parsedLines = parseLines(lines);
        System.out.println("Part I: " + parsedLines);
        Integer productOfRaces = 1;
        for (Integer i = 0; i < parsedLines.get(0).size(); i++) {
            Integer raceScore = beatRace(parsedLines.get(0).get(i), parsedLines.get(1).get(i));
            System.out.println("Race " + i + ": " + raceScore);
            productOfRaces *= raceScore;
        }

        System.out.println(productOfRaces);

    }

    private static Integer beatRace(Integer time, Integer distance) {
        Integer waysToBeatRace = 0;
        Integer halfTime = time / 2;
        if (halfTime * (time - halfTime) > distance) {
            waysToBeatRace++;
            Integer lPTR = halfTime - 1;
            Integer rPTR = halfTime + 1;
            Integer foundLimits = 0;
            while (foundLimits < 2) {
                if (lPTR * (time - lPTR) > distance) {
                    waysToBeatRace++;
                    lPTR--;
                } else {
                    foundLimits++;
                }
                if (rPTR * (time - rPTR) > distance) {
                    waysToBeatRace++;
                    rPTR++;
                } else {
                    foundLimits++;
                }
            }
        } else {
            for (Integer i = 1; i < time; i++) {
                if (i * (time - i) > distance) {
                    waysToBeatRace++;
                }
            }
        }
        return waysToBeatRace;
    }

    public static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
        }
        return lines;
    }

    public static List<List<Integer>> parseLines(List<String> lines) {
        List<List<Integer>> parsedLines = new ArrayList<List<Integer>>();
        for (String line : lines) {
            List<Integer> parsedLine = new ArrayList<Integer>();
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                if (token.equals("") || token == tokens[0]) {
                    continue;
                }
                parsedLine.add(Integer.parseInt(token));
            }
            parsedLines.add(parsedLine);
        }
        return parsedLines;
    }
}
