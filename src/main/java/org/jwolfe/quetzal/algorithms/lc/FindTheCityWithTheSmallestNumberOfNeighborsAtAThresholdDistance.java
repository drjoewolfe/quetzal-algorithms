package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
    class Solution {
        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            if (n < 1 || distanceThreshold < 1) {
                return 0;
            }

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new HashMap<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                graph.get(u).put(v, w);
                graph.get(v).put(u, w);
            }

            Map<Integer, Map<Integer, Integer>> distanceMap = new HashMap<>();
            for (int u = 0; u < n; u++) {
                Map<Integer, Integer> shortestDistances = dijkstra(graph, n, u);
                distanceMap.put(u, shortestDistances);
            }

            int minCount = Integer.MAX_VALUE;
            int city = -1;
            for (var entry : distanceMap.entrySet()) {
                int u = entry.getKey();

                var distances = entry.getValue();

                int count = 0;
                for (var distanceEntry : distances.entrySet()) {
                    int v = distanceEntry.getKey();
                    if (u == v) {
                        continue;
                    }

                    int d = distanceEntry.getValue();
                    if (d <= distanceThreshold) {
                        count++;
                    }
                }

                if (minCount >= count) {
                    minCount = count;
                    city = u;
                }
            }

            return city;
        }

        private Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int n, int s) {
            Map<Integer, Integer> shortestDistances = new HashMap<>();

            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[s] = 0;

            boolean[] done = new boolean[n];
            for (int i = 0; i < n; i++) {
                int u = getShortestVertex(distances, done);
                if (u == -1) {
                    return shortestDistances;
                }

                shortestDistances.put(u, distances[u]);
                done[u] = true;

                // Relax
                for (var entry : graph.get(u).entrySet()) {
                    int v = entry.getKey();
                    int w = entry.getValue();

                    distances[v] = Math.min(distances[v], distances[u] + w);
                }
            }

            return shortestDistances;
        }

        private int getShortestVertex(int[] distances, boolean[] done) {
            int u = -1;
            int n = distances.length;
            int minDistance = Integer.MAX_VALUE;

            for (int v = 0; v < n; v++) {
                if (done[v]) {
                    continue;
                }

                int d = distances[v];
                if (d < minDistance) {
                    minDistance = d;
                    u = v;
                }
            }

            return u;
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(Map<Integer, Integer> map) {
            for (var entry : map.entrySet()) {
                System.out.print("[" + entry.getKey() + " : " + entry.getValue() + "] ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int findTheCity(int n, int[][] edges, int distanceThreshold) {
            if (n < 1 || edges == null || edges.length == 0 || distanceThreshold < 1) {
                return 0;
            }

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new HashMap<>());
            }

            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int w = e[2];

                graph.get(u).put(v, w);
                graph.get(v).put(u, w);
            }

            Map<Integer, Map<Integer, Integer>> distanceMap = new TreeMap<>();
            for (int s = 0; s < n; s++) {
                distanceMap.put(s, dijkstraShortestPaths(graph, s, n));
            }

            int minCount = Integer.MAX_VALUE;
            int city = -1;
            for (var entry : distanceMap.entrySet()) {
                int u = entry.getKey();

                int count = 0;
                for (var pair : entry.getValue().entrySet()) {
                    int v = pair.getKey();
                    if (u == v) {
                        continue;
                    }

                    int d = pair.getValue();

                    if (d <= distanceThreshold) {
                        count++;
                    }
                }

                if (minCount >= count) {
                    minCount = count;
                    city = u;
                }
            }

            return city;
        }

        private Map<Integer, Integer> dijkstraShortestPaths(Map<Integer, Map<Integer, Integer>> graph, int s, int n) {
            int[] distances = new int[n];
            boolean[] done = new boolean[n];
            for (int u = 0; u < n; u++) {
                distances[u] = Integer.MAX_VALUE;
                done[u] = false;
            }

            distances[s] = 0;

            Map<Integer, Integer> shortestDistances = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int u = getShortestDistanceVertex(distances, done);
                if (u == -1) {
                    // No more vertices
                    return shortestDistances;
                }

                shortestDistances.put(u, distances[u]);
                done[u] = true;

                // Relax
                var adjacents = graph.get(u);
                for (var entry : adjacents.entrySet()) {
                    int v = entry.getKey();
                    int w = entry.getValue();

                    distances[v] = Math.min(distances[v], distances[u] + w);
                }
            }

            return shortestDistances;
        }

        private int getShortestDistanceVertex(int[] distances, boolean[] done) {
            int u = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int v = 0; v < distances.length; v++) {
                if (!done[v]
                        && distances[v] < minDistance) {
                    minDistance = distances[v];
                    u = v;
                }
            }

            return u;
        }

//     private Map<Integer, Integer> dijkstraShortestPaths(Map<Integer, Map<Integer, Integer>> graph, int s, int n) {
//         // Initialize distances
//         int[][] distances = new int[n][2];
//         for(int u = 0; u < n; u++) {
//             distances[u] = new int[] {u, Integer.MAX_VALUE};
//         }

//         distances[s][1] = 0;

//         // Initialize queue
//         PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
//         for(int u = 0; u < n; u++) {
//             minHeap.offer(distances[u]);
//         }

//         Map<Integer, Integer> shortestDistances = new HashMap<>();
//         while(!minHeap.isEmpty()) {
//             int[] pair = minHeap.poll();
//             int u = pair[0];
//             int d = pair[1];

//             shortestDistances.put(u, d);

//             // Relax
//             var adjacents = graph.get(u);
//             for(var entry : adjacents.entrySet()) {
//                 int v = entry.getKey();
//                 int w = entry.getValue();

//                 distances[v][1] = Math.min(distances[v][1], d + w);
//             }
//         }

//         return shortestDistances;
//     }
    }

// 4
// [[0,1,3],[1,2,1],[1,3,4],[2,3,1]]
// 4

// 5
// [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]
// 2
}

//    1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
//    Medium
//    There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
//
//    Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
//
//    Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
//
//
//
//    Example 1:
//
//
//    Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
//    Output: 3
//    Explanation: The figure above describes the graph.
//    The neighboring cities at a distanceThreshold = 4 for each city are:
//    City 0 -> [City 1, City 2]
//    City 1 -> [City 0, City 2, City 3]
//    City 2 -> [City 0, City 1, City 3]
//    City 3 -> [City 1, City 2]
//    Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
//    Example 2:
//
//
//    Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
//    Output: 0
//    Explanation: The figure above describes the graph.
//    The neighboring cities at a distanceThreshold = 2 for each city are:
//    City 0 -> [City 1]
//    City 1 -> [City 0, City 4]
//    City 2 -> [City 3, City 4]
//    City 3 -> [City 2, City 4]
//    City 4 -> [City 1, City 2, City 3]
//    The city 0 has 1 neighboring city at a distanceThreshold = 2.
//
//
//    Constraints:
//
//    2 <= n <= 100
//    1 <= edges.length <= n * (n - 1) / 2
//    edges[i].length == 3
//    0 <= fromi < toi < n
//    1 <= weighti, distanceThreshold <= 10^4
//    All pairs (fromi, toi) are distinct.