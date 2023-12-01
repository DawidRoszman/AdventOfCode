import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean IsInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
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

        for (String s : input) {
            String[] sArr = s.split("");

            // using Two pointers
            boolean foundL = false;
            boolean foundR = false;
            int l = 0;
            int r = s.length() - 1;
            while (!foundL || !foundR) {
                if (IsInt(sArr[l])) {
                    foundL = true;
                } else {
                    if (!foundL)
                        l += 1;
                }
                if (IsInt(sArr[r])) {
                    foundR = true;
                } else {
                    if (!foundR)
                        r -= 1;
                }

            }
            int sum = Integer.parseInt(sArr[l] + sArr[r]);
            res += sum;

        }
        System.out.println(String.format("%d", res));

    }

}
