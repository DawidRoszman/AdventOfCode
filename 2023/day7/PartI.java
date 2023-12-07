import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PartI {
    public static void main(String[] args) {
        HashMap<Character, Integer> cardValues = getCardValues();

        List<String> lines = readFromFile("input.txt");
        List<Pair<String, Integer>> pairs = parseInput(lines);
        pairs.sort((p1, p2) -> sort(cardValues, p1, p2));
        Long sum = 0L;
        for (Integer i = 0; i < pairs.size(); i++) {
            sum += (i + 1) * pairs.get(i).getR();
        }
        System.out.println("Sum: " + sum);

    }

    private static int sort(HashMap<Character, Integer> cardValues, Pair<String, Integer> p1,
            Pair<String, Integer> p2) {
        if (checkHand(p1.getL()) > checkHand(p2.getL())) {
            return -1;
        }
        if (checkHand(p1.getL()) < checkHand(p2.getL())) {
            return 1;
        }
        if (checkHand(p1.getL()) == checkHand(p2.getL())) {
            for (int i = 0; i < p1.getL().length(); i++) {
                if (cardValues.get(p1.getL().charAt(i)) > cardValues.get(p2.getL().charAt(i))) {
                    return 1;
                }
                if (cardValues.get(p1.getL().charAt(i)) < cardValues.get(p2.getL().charAt(i))) {
                    return -1;
                }
            }
        }
        return 0;
    }

    private static HashMap<Character, Integer> getCardValues() {
        HashMap<Character, Integer> cardValues = new HashMap<Character, Integer>();
        cardValues.put('T', 10);
        cardValues.put('J', 11);
        cardValues.put('Q', 12);
        cardValues.put('K', 13);
        cardValues.put('A', 14);
        cardValues.put('2', 2);
        cardValues.put('3', 3);
        cardValues.put('4', 4);
        cardValues.put('5', 5);
        cardValues.put('6', 6);
        cardValues.put('7', 7);
        cardValues.put('8', 8);
        cardValues.put('9', 9);
        return cardValues;
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

    public static List<Pair<String, Integer>> parseInput(List<String> lines) {
        List<Pair<String, Integer>> pairs = new ArrayList<Pair<String, Integer>>();
        for (String line : lines) {
            String[] words = line.split(" ");
            pairs.add(new Pair<String, Integer>(words[0], Integer.parseInt(words[1])));
        }
        return pairs;
    }

    public static Integer checkHand(String hand) {
        if (isFiveOfKind(hand)) {
            return 1;
        } else if (isFourOfKind(hand)) {
            return 2;
        } else if (isFullHouse(hand)) {
            return 3;
        } else if (isThreeOfKind(hand)) {
            return 4;
        } else if (isTwoPair(hand)) {
            return 5;
        } else if (isOnePair(hand)) {
            return 6;
        } else if (isHighCard(hand)) {
            return 7;
        }
        return 0;
    }

    public static Boolean isFiveOfKind(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Integer i = 0; i < hand.length(); i++) {
            map.put(hand.charAt(i), i);
        }
        return map.size() == 1;
    }

    public static Boolean isFourOfKind(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        for (Character key : map.keySet()) {
            if (map.get(key) == 4) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isFullHouse(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        return map.size() == 2;
    }

    public static Boolean isThreeOfKind(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        for (Character key : map.keySet()) {
            if (map.get(key) == 3) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isTwoPair(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        Integer numPairs = 0;
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        for (Character key : map.keySet()) {
            if (map.get(key) == 2) {
                numPairs++;
            }
        }
        return numPairs == 2;
    }

    public static Boolean isOnePair(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        Integer numPairs = 0;
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        for (Character key : map.keySet()) {
            if (map.get(key) == 2) {
                numPairs++;
            }
        }
        return numPairs == 1;
    }

    public static Boolean isHighCard(String hand) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Integer i = 0; i < hand.length(); i++) {
            if (map.containsKey(hand.charAt(i))) {
                map.put(hand.charAt(i), map.get(hand.charAt(i)) + 1);
            } else {
                map.put(hand.charAt(i), 1);
            }
        }
        return map.size() == 5;
    }

}

class Pair<L, R> {
    private L l;
    private R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    public void setL(L l) {
        this.l = l;
    }

    public void setR(R r) {
        this.r = r;
    }
}
