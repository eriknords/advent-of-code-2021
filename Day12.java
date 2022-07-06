package adventofcode2021;

import adventofcode2021.cave.Path;

import java.util.*;

public class Day12 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String line;
        HashMap<String, Set<String>> cavePaths = new HashMap<>();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            String[] passage = line.strip().split("-");

           if (!cavePaths.containsKey(passage[0])) {
               cavePaths.put(passage[0], new HashSet<>());
           }
           if (!cavePaths.containsKey(passage[1])) {
               cavePaths.put(passage[1], new HashSet<>());
           }
           cavePaths.get(passage[0]).add(passage[1]);
           cavePaths.get(passage[1]).add(passage[0]);
        }

        List<Path> allPaths = new ArrayList<>();
        allPaths.add(new Path("start"));

        boolean allIsAtEnd = false;
        while (!allIsAtEnd) {
            allIsAtEnd = true;

            List<Path> newPaths = new ArrayList<>();

            for (Path path :allPaths) {
                if (path.isAtEnd())  {
                    newPaths.add(path);
                    continue;
                }
                allIsAtEnd = false;

                for (String cave : cavePaths.get(path.getLastVisited())) {
                    if (path.canVisit(cave)) {
                        if (path.isVisitingSmallCaveAgain(cave)) {
                            newPaths.add(new Path(path, cave, true));
                        } else {
                            newPaths.add(new Path(path, cave));
                        }
                    }
                }
            }
            allPaths = newPaths;

        }
        System.out.println("\n Number of paths: " + allPaths.size());


    }
}
