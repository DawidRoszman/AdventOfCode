import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class PartII {
    public static void main(String[] args) {
        List<String> input = readFromFile("input.txt");
        List<String> firstLine = List.of(input.get(0).split(": "));
        List<Long> seeds = List.of(firstLine.get(1).split(" ")).stream().map(Long::parseLong)
                .collect(Collectors.toList());
        List<List<List<Long>>> maps = parseInput(input.subList(2, input.size()));
        List<Long> minLocations = new ArrayList<>();
        for (Integer i = 0; i < seeds.size(); i += 2) {
            System.out.println("Range: " + seeds.get(i) + " " + seeds.get(i + 1));
            Long minLocation = minLocationForRangeOfSeeds(seeds.get(i), seeds.get(i + 1),
                    maps);
            minLocations.add(minLocation);
        }

        System.out.println("Location: " + minLocations.stream().mapToLong(Long::longValue).min().getAsLong());

    }

    private static Long minLocationForRangeOfSeeds(Long start, Long range, List<List<List<Long>>> maps) {
        Long minLocation = Long.MAX_VALUE;
        for (Long seed = start; seed < start + range; seed++) {
            Long location = findLocation(seed, maps);
            if (location < minLocation)
                minLocation = location;
        }
        return minLocation;
    }

    private static Long findLocation(Long seed, List<List<List<Long>>> maps) {
        Long nextIndex = seed;
        for (List<List<Long>> step : maps) {
            for (List<Long> list : step) {
                if (nextIndex >= list.get(1) && nextIndex < list.get(1) + list.get(2)) {
                    nextIndex = list.get(0) - list.get(1) + nextIndex;
                    break;
                }
            }
        }
        return nextIndex;
    }

    private static List<List<List<Long>>> parseInput(List<String> input) {
        List<List<List<Long>>> maps = new ArrayList<>();
        String tempKey = "";
        List<List<Long>> tempValue = new ArrayList<>();
        for (String line : input) {
            if (line == "") {
                maps.add(tempValue);
                tempKey = "";
                tempValue = new ArrayList<>();
                continue;
            }
            String[] parts = line.split(" ");
            if (tempKey == "") {
                tempKey = parts[0];
                continue;
            }
            tempValue.add(List.of(parts).stream().map(Long::parseLong).collect(Collectors.toList()));
        }
        maps.add(tempValue);
        return maps;
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

}
