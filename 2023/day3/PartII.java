import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class PartII {
    public static void main(String[] args) {
        List<String> input = readFromFile("input.txt");
        List<List<String>> parsedInput = parseInput(input);

        HashSet<List<Integer>> numbersWithIdx = findNumbers(parsedInput);
        HashMap<String, List<Integer>> adjecantToAstrix = finaAllSymbolsAdjecent(parsedInput, numbersWithIdx);

        Integer sum = getSum(adjecantToAstrix);
        System.out.println(sum);
    }

    private static Integer getSum(HashMap<String, List<Integer>> adjecantToAstrix) {
        Integer sum = 0;
        for (String adjecant : adjecantToAstrix.keySet()) {
            List<Integer> number = adjecantToAstrix.get(adjecant);
            if (number.size() > 1) {
                Integer product = 1;
                for (Integer n : number) {
                    product *= n;
                }
                sum += product;
            }
        }
        return sum;
    }

    private static HashMap<String, List<Integer>> finaAllSymbolsAdjecent(List<List<String>> parsedInput,
            HashSet<List<Integer>> numbersWithIdx) {
        HashMap<String, List<Integer>> adjecantToAstrix = new HashMap<String, List<Integer>>();
        for (List<Integer> number : numbersWithIdx) {
            String adjecant = symbolIsAdjacent(parsedInput, number);
            if (adjecant != "") {
                if (adjecantToAstrix.containsKey(adjecant)) {
                    adjecantToAstrix.get(adjecant).add(number.get(3));
                } else {
                    adjecantToAstrix.put(adjecant, new ArrayList<Integer>(List.of(number.get(3))));
                }
            }
        }
        return adjecantToAstrix;
    }

    private static String symbolIsAdjacent(List<List<String>> parsedInput, List<Integer> number) {
        Integer i = number.get(0);
        if (i > 0) {
            i -= 1;
        }
        Integer end = number.get(0) + 2;
        if (end > parsedInput.size() - 1) {
            end = parsedInput.size();
        }
        Integer l = number.get(1);
        if (l > 0) {
            l -= 1;
        }
        Integer r = number.get(2);
        if (r < parsedInput.get(i).size() - 1) {
            r += 1;
        }

        for (int j = i; j < end; j++) {
            for (int k = l; k <= r; k++) {
                if (parsedInput.get(j).get(k).matches("[*]")) {
                    return String.format("%d%d", j, k);
                }
            }
        }
        return "";
    }

    private static HashSet<List<Integer>> findNumbers(List<List<String>> parsedInput) {
        HashSet<List<Integer>> numbersWithIdx = new HashSet<List<Integer>>();
        for (int i = 0; i < parsedInput.size(); i++) {
            for (int j = 0; j < parsedInput.get(i).size(); j++) {
                if (parsedInput.get(i).get(j).matches("[0-9]")) {
                    List<Integer> number = findWholeNumber(parsedInput, i, j);
                    numbersWithIdx.add(number);
                }
            }
        }
        return numbersWithIdx;
    }

    private static List<Integer> findWholeNumber(List<List<String>> parsedInput, int i, int j) {
        String number = parsedInput.get(i).get(j);
        Integer l = j - 1;
        Integer r = j + 1;
        while (l >= 0 && parsedInput.get(i).get(l).matches("[0-9]")) {
            number = parsedInput.get(i).get(l) + number;
            l--;
        }
        while (r < parsedInput.get(i).size() && parsedInput.get(i).get(r).matches("[0-9]")) {
            number = number + parsedInput.get(i).get(r);
            r++;
        }

        return List.of(i, l + 1, r - 1, Integer.parseInt(number));
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

    private static List<List<String>> parseInput(List<String> input) {
        List<List<String>> parsedInput = new ArrayList<List<String>>();
        for (String line : input) {
            List<String> parsedLine = new ArrayList<String>();
            for (String word : line.split("")) {
                parsedLine.add(word);
            }
            parsedInput.add(parsedLine);
        }
        return parsedInput;
    }

}
