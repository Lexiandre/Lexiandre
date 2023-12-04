package ratings;
import java.util.*;

public class DoS {


    public class DegOfSep {

        private final ArrayList<Movie> movies;
        private final HashMap<String, Integer> memoizationTable;

        public DegOfSep(ArrayList<Movie> movies) {
            this.movies = movies;
            this.memoizationTable = new HashMap<>(); // Memoization table to avoid recalculations
        }

        public int degreesOfSeparation(String actor1, String actor2) {
            // Check if either actor is not in the database
            if (!movies.stream().anyMatch(movie -> movie.getCast().contains(actor1))) {
                return -1;
            }

            if (!movies.stream().anyMatch(movie -> movie.getCast().contains(actor2))) {
                return -1;
            }

            // Check if the DoS for this pair of actors has already been calculated
            if (memoizationTable.containsKey(actor1 + "," + actor2)) {
                return memoizationTable.get(actor1 + "," + actor2);
            }

            // Create a queue to store the nodes to visit
            Queue<Node> queue = new LinkedList<>();

            // Add the start node to the queue
            queue.add(new Node(actor1, 0));

            // Create a hash set to store the visited nodes
            HashSet<String> visited = new HashSet<>();

            // Perform a BFS to find the shortest path between the two actors
            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();

                if (currentNode.getActor().equals(actor2)) {
                    // We have found the shortest path between the two actors
                    memoizationTable.put(actor1 + "," + actor2, currentNode.getDistance());
                    return currentNode.getDistance();
                }

                visited.add(currentNode.getActor());

                for (String neighbor : getNeighbors(currentNode.getActor())) {
                    if (!visited.contains(neighbor)) {
                        queue.add(new Node(neighbor, currentNode.getDistance() + 1));
                    }
                }
            }

            // If we reach this point, it means that the two actors are not connected
            memoizationTable.put(actor1 + "," + actor2, -1);
            return -1;
        }

        // Get adjacent neighbors
        private ArrayList<String> getNeighbors(String actor) {
            ArrayList<String> neighbors = new ArrayList<>();

            for (Movie movie : movies) {
                if (movie.getCast().contains(actor)) {
                    for (String otherActor : movie.getCast()) {
                        if (!actor.equals(otherActor)) {
                            neighbors.add(otherActor);
                        }
                    }
                }
            }

            return neighbors;
        }

        private static class Node {
            private final String actor;
            private final int distance;

            public Node(String actor, int distance) {
                this.actor = actor;
                this.distance = distance;
            }

            public String getActor() {
                return actor;
            }

            public int getDistance() {
                return distance;
            }
        }
    }

}
