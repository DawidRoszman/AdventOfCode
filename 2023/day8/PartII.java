import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class PartII {
    public static void main(String[] args) {
        List<String> input = readFromFile("input.txt");
        String moves = input.get(0);
        input.remove(0);
        input.remove(0);
        HashMap<String, List<String>> nodes = getNodes(input);
        List<String> startingNodes = nodes.keySet().stream().filter(s -> s.endsWith("A"))
                .collect(Collectors.toList());
        List<Integer> steps = new ArrayList<Integer>();
        for (String node : startingNodes) {
            String curr = node;
            Integer numOfSteps = 0;
            Integer index = 0;
            while (true) {
                if (curr.endsWith("Z")) {
                    break;
                }
                if (moves.charAt(index) == 'L') {
                    curr = nodes.get(curr).get(0);
                } else
                    curr = nodes.get(curr).get(1);
                if (index + 1 == moves.length())
                    index = 0;
                else
                    index++;
                numOfSteps++;
            }
            steps.add(numOfSteps);
        }

        long lcm = findLCM(steps);
        System.out.println(lcm);

    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to find the LCM (Least Common Multiple) of two numbers
    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    // Function to find the LCM of a list of numbers
    public static long findLCM(List<Integer> numbers) {
        if (numbers.size() == 0) {
            throw new IllegalArgumentException("Input array is empty");
        }

        long result = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }

        return result;
    }

    private static HashMap<String, List<String>> getNodes(List<String> input) {
        HashMap<String, List<String>> nodes = new HashMap<String, List<String>>();
        for (String line : input) {
            List<String> parts = List.of(line.split("[^a-zA-Z0-9]")).stream().filter(s -> s != "")
                    .collect(Collectors.toList());
            nodes.put(parts.get(0), parts.subList(1, parts.size()));
        }
        return nodes;
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

}
