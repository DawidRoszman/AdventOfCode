import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PartII {
    public static void main(String[] args) {
        List<String> input = readFromFile("input.txt");
        List<List<Integer>> parsedInput = parseInput(input);

        Integer sum = 0;
        for (List<Integer> row : parsedInput) {
            sum += extrapolate(listOfDifferences(row));
        }
        System.out.println(sum);
    }

    public static Integer extrapolate(List<List<Integer>> differences) {
        List<List<Integer>> reversed = differences.reversed();
        int currVal = 0;
        for (int i = 1; i < reversed.size(); i++) {
            currVal = reversed.get(i).getFirst() - currVal;
        }

        return currVal;
    }

    public static List<List<Integer>> listOfDifferences(List<Integer> history) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        output.add(history);
        List<Integer> current = differences(history);
        while (true) {
            output.add(current);
            if (current.stream().allMatch(i -> i == 0)) {
                break;
            }

            current = differences(current);
        }

        return output;
    }

    public static List<Integer> differences(List<Integer> history) {
        List<Integer> output = new ArrayList<Integer>();
        for (int i = 1; i < history.size(); i++) {
            output.add(history.get(i) - history.get(i - 1));
        }
        return output;
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

    public static List<List<Integer>> parseInput(List<String> input) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        for (String line : input) {
            List<Integer> row = new ArrayList<Integer>();
            for (String num : line.split(" ")) {
                row.add(Integer.parseInt(num));
            }
            output.add(row);
        }
        return output;
    }

}
