package ratings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class DegreesOfSeparation {

        private final ArrayList<Movie> movies;
        private final HashMap<String, Integer> memoizationTable;

        public DegreesOfSeparation(ArrayList<Movie> movies) {
            this.movies = movies;
            this.memoizationTable = new HashMap<>();//memo saves time+no recalculations, ik its resource intensive
        }

        public int degreesOfSeparation(String actor1, String actor2) {
            // Check if either actor is not in the database. using java's stream class cos its fast af..i think..right?
            //or the old flag way cse115 stuff
            //yhup, lambda expressions are better
            boolean actor1Found = false;
            boolean actor2Found = false;
            for (Movie movie : movies) {
                if (movie.getCast().contains(actor1)) {
                    actor1Found = true;
                }if (movie.getCast().contains(actor2)) {
                    actor2Found = true;
                }
            }
            if (!actor1Found) {
                return -1;
            }if (!actor2Found) {
                return -1;
            }

            // now Check if the DoS for this pair of actors has already been calculated.
            //dis is 2 avoid recalculations
            if (memoizationTable.containsKey(actor1 + "," + actor2)) {
                return memoizationTable.get(actor1 + "," + actor2);
            }

            // Create a priority queue to store the nodes to visit, similar to jesse's linked list
            //First come, First Served :FcFs, FiFo can eat sheet
            PriorityQueue<Node> queue = new PriorityQueue<>((node1, node2) -> node1.getDistance() - node2.getDistance());
            //lambda expressions go brr

            // Add the start node to the queue.
            queue.add(new Node(actor1, 0));

            // Create a hash set to store the visited nodes.
            HashSet<String> visited = new HashSet<>();

            // Perform a BFS to find the shortest path between the two actors.
            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();//for the head operations, get and remove

                if (currentNode.getActor().equals(actor2)) {
                    // We have found the shortest path between the two actors. Ez money...
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

            // If we reach this point, it means that the two actors are not connected. therefore -1.
            memoizationTable.put(actor1 + "," + actor2, -1);
            return -1;
        }

        //adjacent neighbors
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


