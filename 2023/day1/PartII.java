import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PartII {

    public static boolean IsInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean keyStartsWith(String word, HashMap<String, String> wordToDigit) {
        for (String key : wordToDigit.keySet()) {
            if (key.startsWith(word)) {
                return true;
            }
        }
        return false;
    }

    public static boolean keyEndsWith(String word, HashMap<String, String> wordToDigit) {
        for (String key : wordToDigit.keySet()) {
            if (key.endsWith(word)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Getting input from a file
        File f = new File("input.txt");
        List<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }
        scanner.close();

        int res = 0;

        HashMap<String, String> wordToDigit = new HashMap<String, String>();

        wordToDigit.put("zero", "0");
        wordToDigit.put("one", "1");
        wordToDigit.put("two", "2");
        wordToDigit.put("three", "3");
        wordToDigit.put("four", "4");
        wordToDigit.put("five", "5");
        wordToDigit.put("six", "6");
        wordToDigit.put("seven", "7");
        wordToDigit.put("eight", "8");
        wordToDigit.put("nine", "9");

        for (String s : input) {
            String[] sArr = s.split("");

            // using Two pointers
            String foundL = "";
            String foundR = "";
            int l = 0;
            int r = s.length() - 1;
            while (foundL == "" || foundR == "") {
                if (keyStartsWith(sArr[l], wordToDigit)) {
                    String word = sArr[l];
                    for (int i = l + 1; i < sArr.length; i++) {
                        word += sArr[i];
                        if (keyStartsWith(word, wordToDigit)) {
                            if (wordToDigit.containsKey(word)) {
                                foundL = wordToDigit.get(word);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }

                if (keyEndsWith(sArr[r], wordToDigit)) {
                    String word = sArr[r];
                    for (int i = r - 1; i >= 0; i--) {
                        word = sArr[i] + word;
                        if (keyEndsWith(word, wordToDigit)) {
                            if (wordToDigit.containsKey(word)) {
                                foundR = wordToDigit.get(word);
                                break;
                            }
                        } else {
                            break;
                        }

                    }

                }

                if (IsInt(sArr[l]))
                    foundL = sArr[l];
                if (IsInt(sArr[r]))
                    foundR = sArr[r];

                if (foundL == "")
                    l += 1;
                if (foundR == "")
                    r -= 1;

            }
            int sum = Integer.parseInt(foundL + foundR);
            res += sum;

        }
        System.out.println(String.format("%d", res));

    }

}
