import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PartII {
    public static void main(String[] args) {
        List<String> lines = readFromFile("input.txt");
        List<Long> parsedLines = parseLines(lines);
        System.out.println("Part I: " + parsedLines);
        Long raceScore = beatRace(parsedLines.get(0), parsedLines.get(1));

        System.out.println(raceScore);

    }

    private static Long beatRace(Long time, Long distance) {
        Long waysToBeatRace = 0L;
        Long halfTime = time / 2;
        if (halfTime * (time - halfTime) > distance) {
            waysToBeatRace++;
            Long lPTR = halfTime - 1;
            Long rPTR = halfTime + 1;
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
            for (Long i = 1L; i < time; i++) {
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

    public static List<Long> parseLines(List<String> lines) {
        List<Long> parsedLines = new ArrayList<Long>();
        for (String line : lines) {
            String num = "";
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                if (token.equals("") || token == tokens[0]) {
                    continue;
                }
                num += token;
            }
            parsedLines.add(Long.parseLong(num));
        }
        return parsedLines;
    }
}
