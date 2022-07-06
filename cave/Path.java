package adventofcode2021.cave;

import java.util.HashSet;
import java.util.Set;

public class Path {

    private final Set<String> visitedSmallCaves = new HashSet<>();
    private final String lastVisited;
    private final boolean atEnd;
    private boolean visitedSmallCaveTwice = false;

    public Path(Path path, String lastVisited, boolean visitedASmallCaveTwice) {
        this(path,lastVisited);
        this.visitedSmallCaveTwice = visitedASmallCaveTwice;
    }

    public Path(Path path, String lastVisited) {
        this.visitedSmallCaves.addAll(path.visitedSmallCaves);
        if (lastVisited.toLowerCase().equals(lastVisited)) {
            this.visitedSmallCaves.add(lastVisited);
        }
        this.lastVisited = lastVisited;
        atEnd = lastVisited.equals("end");
        this.visitedSmallCaveTwice = path.visitedSmallCaveTwice;
    }

    public Path(String lastVisited) {
        if (lastVisited.toLowerCase().equals(lastVisited)) {
            this.visitedSmallCaves.add(lastVisited);
        }
        this.lastVisited = lastVisited;
        atEnd = lastVisited.equals("end");
    }

    public boolean canVisit(String cave) {
        if (cave.toLowerCase().equals(cave) &&
                visitedSmallCaves.contains(cave)) {

            return !cave.equals("start") &&
                    !cave.equals("end") &&
                    !visitedSmallCaveTwice;
        }
        return true;
    }

    public boolean isVisitingSmallCaveAgain(String cave) {
        return !cave.equals("start") &&
                !cave.equals("end") &&
                !visitedSmallCaveTwice &&
                visitedSmallCaves.contains(cave);
    }

    public String getLastVisited() {
        return lastVisited;
    }

    public boolean isAtEnd() {
        return atEnd;
    }
}
