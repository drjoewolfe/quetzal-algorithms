package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ModifyGraphEdgeWeights {
    class Solution {
        private int MAX = 2_000_000_000;

        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
            if (n < 1 || edges == null || edges.length == 0 || source < 0 || source >= n || destination < 0 || destination >= n || target < 0) {
                return edges;
            }

            int[][] graph = new int[n][n];
            for (int[] row : graph) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (w > -1) {
                    graph[u][v] = w;
                    graph[v][u] = w;
                }

                if (w == -1) {
                    graph[u][v] = MAX;
                    graph[v][u] = MAX;
                }
            }

            long shortestPath = dijkstra(graph, source, destination);
            if (shortestPath < target) {
                return new int[0][0];
            }

            boolean match = (shortestPath == target);

            for (int[] edge : edges) {
                int w = edge[2];
                if (w > -1) {
                    continue;
                }

                int u = edge[0];
                int v = edge[1];

                if (match) {
                    edge[2] = MAX;
                } else {
                    edge[2] = 1;
                    graph[u][v] = 1;
                    graph[v][u] = 1;
                }

                if (!match) {
                    long newShortestPath = dijkstra(graph, source, destination);

                    if (newShortestPath <= target) {
                        match = true;
                        edge[2] += target - newShortestPath;
                    }
                }
            }

            return match ? edges : new int[0][0];
        }

        private long dijkstra(int[][] graph, int source, int destination) {
            int n = graph.length;
            long[] distance = new long[n];
            boolean[] visited = new boolean[n];

            Arrays.fill(distance, Long.MAX_VALUE);
            distance[source] = 0;

            for (int i = 0; i < n; i++) {
                int u = getNext(distance, visited);
                if (u == -1) {
                    break;
                }

                visited[u] = true;
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE) {
                        distance[v] = Math.min(
                                distance[v],
                                distance[u] + graph[u][v]);
                    }
                }
            }

            return distance[destination];
        }

        private int getNext(long[] distance, boolean[] visited) {
            int n = distance.length;
            int u = -1;
            long minDistance = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    continue;
                }

                if (minDistance > distance[i]) {
                    minDistance = distance[i];
                    u = i;
                }
            }

            return u;
        }

        private void print(long[] arr) {
            for (long a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 5
// [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]]
// 0
// 1
// 5

// 3
// [[0,1,-1],[0,2,5]]
// 0
// 2
// 6

// 4
// [[2,1,5],[0,1,3],[0,3,-1],[2,3,9]]
// 0
// 2
// 9
}

//    2699. Modify Graph Edge Weights
//    Hard
//    You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.
//
//    Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).
//
//    Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer target. If there are multiple modifications that make the shortest distance between source and destination equal to target, any of them will be considered correct.
//
//    Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from source to destination equal to target, or an empty array if it's impossible.
//
//    Note: You are not allowed to modify the weights of edges with initial positive weights.
//
//
//
//    Example 1:
//
//
//
//    Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
//    Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
//    Explanation: The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.
//    Example 2:
//
//
//
//    Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
//    Output: []
//    Explanation: The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal to 6 by modifying the edge with weight -1. So, an empty array is returned.
//    Example 3:
//
//
//
//    Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
//    Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
//    Explanation: The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.
//
//
//    Constraints:
//
//    1 <= n <= 100
//    1 <= edges.length <= n * (n - 1) / 2
//    edges[i].length == 3
//    0 <= ai, bi < n
//    wi = -1 or 1 <= wi <= 107
//    ai != bi
//    0 <= source, destination < n
//    source != destination
//    1 <= target <= 109
//    The graph is connected, and there are no self-loops or repeated edges