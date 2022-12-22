package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SumOfDistancesInTree {
    class Solution {
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            if(n < 1) {
                return new int[0];
            }

            if(edges == null || edges.length == 0) {
                return new int[n];
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] distances = new int[n];
            int[] subTreeNodeCounts = new int[n];

            computeSubTreeNodeCounts(graph, 0, subTreeNodeCounts, distances, new HashSet<>());
            computeDistances(graph, n, 0, -1, subTreeNodeCounts, distances);

            return distances;
        }

        private void computeSubTreeNodeCounts(Map<Integer, Set<Integer>> graph, int u, int[] subTreeNodeCounts, int[] distances, Set<Integer> visited) {
            visited.add(u);

            subTreeNodeCounts[u] = 1;
            distances[u] = 0;

            for(var v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                computeSubTreeNodeCounts(graph, v, subTreeNodeCounts, distances, visited);
                subTreeNodeCounts[u] += subTreeNodeCounts[v];
                distances[u] += (distances[v] + subTreeNodeCounts[v]);
            }
        }

        private void computeDistances(Map<Integer, Set<Integer>> graph, int n, int u, int p, int[] subTreeNodeCounts, int[] distances) {
            for(var v : graph.get(u)) {
                if(v == p) {
                    continue;
                }

                distances[v] = distances[u] - subTreeNodeCounts[v] + (n - subTreeNodeCounts[v]);
                computeDistances(graph, n, v, u, subTreeNodeCounts, distances);
            }
        }
    }

    class Solution_Correct_1 {
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            if(n < 1) {
                return new int[0];
            }

            if(edges == null || edges.length == 0) {
                return new int[n];
            }


            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] subTreeNodeCounts = new int[n];
            int[] distances = new int[n];
            computeSubTreeStatistics(graph, 0, subTreeNodeCounts, distances, new HashSet<>());
            computeDistances(graph, n, 0, -1, distances, subTreeNodeCounts);

            return distances;
        }

        private void computeSubTreeStatistics(Map<Integer, Set<Integer>> graph, int u, int[] subTreeNodeCounts, int[] distances, Set<Integer> visited) {
            visited.add(u);
            subTreeNodeCounts[u] = 1;
            distances[u] = 0;

            for(Integer v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                computeSubTreeStatistics(graph, v, subTreeNodeCounts, distances, visited);

                subTreeNodeCounts[u] += subTreeNodeCounts[v];
                distances[u] += (distances[v] + subTreeNodeCounts[v]);
            }
        }

        private void computeDistances(Map<Integer, Set<Integer>> graph, int n, int u, int parent, int[] distances, int[] subTreeNodeCounts) {
            for(Integer v : graph.get(u)) {
                if(v == parent) {
                    continue;
                }

                distances[v] = distances[u] - subTreeNodeCounts[v] + n - subTreeNodeCounts[v];
                computeDistances(graph, n, v, u, distances, subTreeNodeCounts);
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    834. Sum of Distances in Tree
//    Hard
//    There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
//
//    You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
//
//    Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
//
//
//
//    Example 1:
//
//
//    Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//    Output: [8,12,6,10,10,10]
//    Explanation: The tree is shown above.
//    We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
//    equals 1 + 1 + 2 + 2 + 2 = 8.
//    Hence, answer[0] = 8, and so on.
//    Example 2:
//
//
//    Input: n = 1, edges = []
//    Output: [0]
//    Example 3:
//
//
//    Input: n = 2, edges = [[1,0]]
//    Output: [1,1]
//
//
//    Constraints:
//
//    1 <= n <= 3 * 104
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    The given input represents a valid tree.