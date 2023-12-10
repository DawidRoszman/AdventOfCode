import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PartI {
    public static void main(String[] args) {
        List<String> input = readFromFile("example.txt");
        List<List<String>> matrix = createMatrix(input);
        System.out.println("Part I: " + matrix);
        Pair start = findStart(matrix);
        System.out.println("Start: " + start.getX() + ", " + start.getY());
        List<Pair> ends = findEndsOfLoop(matrix, start);
        for (Pair end : ends) {
            System.out.println("End: " + end.getX() + ", " + end.getY());
        }
    }

    private static List<Pair> findEndsOfLoop(List<List<String>> matrix, Pair start) {
        List<Pair> ends = new ArrayList<Pair>();
        Pair north = new Pair(start.getX() - 1, start.getY());
        Pair south = new Pair(start.getX() + 1, start.getY());
        Pair west = new Pair(start.getX(), start.getY() - 1);
        Pair east = new Pair(start.getX(), start.getY() + 1);
        if (north.getX() >= 0) {
            if (matrix.get(north.getX()).get(north.getY()).matches("[|7F]")) {
                ends.add(new Pair(start.getX() - 1, start.getY()));
            }
        }
        if (south.getX() < matrix.size()) {
            if (matrix.get(south.getX()).get(south.getY()).matches("[|JL]")) {
                ends.add(new Pair(start.getX() + 1, start.getY()));
            }
        }
        if (west.getY() >= 0) {
            if (matrix.get(west.getX()).get(west.getY()).matches("[-LF]")) {
                ends.add(new Pair(start.getX(), start.getY() - 1));
            }
        }
        if (east.getY() < matrix.get(start.getX()).size()) {
            if (matrix.get(east.getX()).get(east.getY()).matches("[-J7]")) {
                ends.add(new Pair(start.getX(), start.getY() + 1));
            }
        }

        return ends;

    }

    private static Pair findStart(List<List<String>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j).equals("S")) {
                    return new Pair(i, j);
                }
            }
        }
        return null;
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

    public static List<List<String>> createMatrix(List<String> input) {
        List<List<String>> matrix = new ArrayList<List<String>>();
        for (String line : input) {
            List<String> row = new ArrayList<String>();
            for (String c : line.split("")) {
                row.add(c);
            }
            matrix.add(row);
        }
        return matrix;
    }

}

class Pair {
    private int x;
    private int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
