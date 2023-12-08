import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class PartI {
    public static void main(String[] args) {
        List<String> input = readFromFile("input.txt");
        String moves = input.get(0);
        input.remove(0);
        input.remove(0);
        HashMap<String, List<String>> nodes = getNodes(input);
        Integer index = 0;
        Long steps = 0L;
        String currNode = "AAA";
        while (true) {
            Character currMove = moves.charAt(index % moves.length());
            if (currMove.equals('L')) {
                currNode = nodes.get(currNode).get(0);
            } else
                currNode = nodes.get(currNode).get(1);
            index++;
            steps++;
            if (currNode.equals("ZZZ")) {
                break;
            }
        }
        System.out.println("Steps: " + steps);
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
