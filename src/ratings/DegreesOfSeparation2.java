package ratings;

import java.util.*;

public class DegreesOfSeparation2 {

    private ArrayList<Movie> Movies;
    private HashMap<String, ArrayList<String>> movieCast;

    private Graph<String> graphOfMovies = new Graph();
    //nodes and edges


    public DegreesOfSeparation2(ArrayList<Movie> movies) {
        this.Movies = movies;
        this.movieCast = new HashMap<>();

        for(Movie mov: this.Movies){
            this.graphOfMovies.addEdge(mov.getTitle(), mov.getCast());
        }
    }

    public int degreesOfSeparation(String castA, String castB) {
        if (castA.equalsIgnoreCase(castB)) {
            return 0;
        }

        // Find the shortest path between castA and castB using the graph
        ArrayList<String> shortestPath = this.graphOfMovies.shortestPath(castA, castB);

        if (shortestPath == null) {
            // If there is no path between the two actors, return -1
            return -1;
        } else {
            // Return the length of the shortest path (number of edges)
            return shortestPath.size() - 1; // Subtract one to exclude the starting node
        }
    }



    ///////////////////////////////////JESSE'S GRAPH ///////////////////////////////

    public class Graph<N> {
        private HashMap<N, ArrayList<N>> adjacencyList;

        public Graph() {
            this.adjacencyList = new HashMap<>();
        }

        public void addEdge(N node, ArrayList<N> neighbors) {
            if (!this.adjacencyList.containsKey(node)) {
                addNode(node);
            }

            this.adjacencyList.get(node).addAll(neighbors);

            for (N neighbor : neighbors) {
                if (!this.adjacencyList.containsKey(neighbor)) {
                    addNode(neighbor);
                }

                this.adjacencyList.get(neighbor).add(node);
            }
        }


        private void addNode(N node) {
            if (!this.adjacencyList.containsKey(node)) {
                this.adjacencyList.put(node, new ArrayList<>());
            }
        }

        public boolean areConnect(N from, N to) {
            return this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).contains(to);

            //return this.adjacencyList.containsKey(from) && this.adjacencyList.get(from).contains(to);
        }

        public boolean validPath(ArrayList<N> path) {
            for (int i = 0; i < path.size() - 1; i++) {
                if (!this.areConnect(path.get(i), path.get(i + 1))) {
                    return false;
                }
            }
            return true;
        }

        public ArrayList<N> shortestPath(N from, N to) {
            if (!areConnect(from, to)) {
                return null; // Return null if there is no direct connection between from and to
            }

            Map<N, N> predecessors = new HashMap<>();
            Set<N> visited = new HashSet<>();
            LinkedList<N> priorityQueue = new LinkedList<>();

            // Initialization
            for (N node : adjacencyList.keySet()) {
                predecessors.put(node, null); // Initialize to null to indicate no predecessor yet
                priorityQueue.add(node);
            }

            // Set the distance of the starting node to 0
            predecessors.put(from, from);

            // Dijkstra's algorithm
            while (!priorityQueue.isEmpty()) {
                N current = priorityQueue.poll();
                if (!visited.contains(current)) {
                    visited.add(current);

                    for (N neighbor : adjacencyList.get(current)) {
                        if (predecessors.get(neighbor) == null || predecessors.get(neighbor) == current) {
                            predecessors.put(neighbor, current);
                            priorityQueue.add(neighbor);
                        }
                    }
                }
            }

            // Reconstruct the shortest path using predecessors
            ArrayList<N> path = new ArrayList<>();
            N current = to;
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }

            Collections.reverse(path); // Reverse the path to get the correct order
            return path;
        }


        private static class NodeDistance<N> {
            private final N node;
            private final int distance;

            public NodeDistance(N node, int distance) {
                this.node = node;
                this.distance = distance;
            }

            public N getNode() {
                return node;
            }

            public int getDistance() {
                return distance;
            }
        }
    }


}


