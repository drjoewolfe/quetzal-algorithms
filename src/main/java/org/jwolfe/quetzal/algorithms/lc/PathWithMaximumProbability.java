package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathWithMaximumProbability {
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            if (n < 1 || edges == null || edges.length == 0 || succProb == null || succProb.length != edges.length || start < 0 || start >= n || end < 0 || end >= n) {
                return 0d;
            }

            // Bellman Ford
            double[] maxProbabilities = new double[n];
            maxProbabilities[start] = 1d;

            for (int i = 0; i < n - 1; i++) {

                for (int j = 0; j < edges.length; j++) {
                    int[] edge = edges[j];

                    int u = edge[0];
                    int v = edge[1];
                    double p = succProb[j];

                    if (maxProbabilities[u] * p > maxProbabilities[v]) {
                        maxProbabilities[v] = maxProbabilities[u] * p;
                    }

                    if (maxProbabilities[v] * p > maxProbabilities[u]) {
                        maxProbabilities[u] = maxProbabilities[v] * p;
                    }
                }

            }

            return maxProbabilities[end];
        }
    }

    class Solution_Correct_1 {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            if (n < 1 || edges == null || edges.length == 0 || succProb == null || succProb.length != edges.length || start < 0 || start >= n || end < 0 || end >= n) {
                return 0d;
            }

            Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for (int i = 0; i < edges.length; i++) {
                int[] edge = edges[i];
                int u = edge[0];
                int v = edge[1];
                double p = succProb[i];

                graph.get(u).add(new Pair<>(v, p));
                graph.get(v).add(new Pair<>(u, p));
            }

            double[] probabilities = dijkstra(graph, n, start);
            return probabilities[end] == Integer.MIN_VALUE ? 0 : probabilities[end];
        }

        private double[] dijkstra(Map<Integer, List<Pair<Integer, Double>>> graph, int n, int start) {
            double[] probabilities = new double[n];
            boolean[] seen = new boolean[n];

            for (int u = 0; u < n; u++) {
                probabilities[u] = Integer.MIN_VALUE;
            }

            probabilities[start] = 1;

            for (int i = 0; i < n; i++) {
                int u = getNext(n, probabilities, seen);
                if (u < 0) {
                    return probabilities;
                }

                double up = probabilities[u];
                seen[u] = true;

                // Relax
                for (var neighbour : graph.get(u)) {
                    int v = neighbour.getKey();
                    double vp = neighbour.getValue();

                    double uvp = up * vp;
                    if (probabilities[v] < uvp) {
                        probabilities[v] = uvp;
                    }
                }
            }

            return probabilities;
        }

        private int getNext(int n, double[] probabilities, boolean[] seen) {
            double maxProbability = 0;
            int next = -1;

            for (int u = 0; u < n; u++) {
                if (maxProbability < probabilities[u] && !seen[u]) {
                    maxProbability = probabilities[u];
                    next = u;
                }
            }

            return next;
        }
    }

// 3
// [[0,1],[1,2],[0,2]]
// [0.5,0.5,0.2]
// 0
// 2

// 3
// [[0,1]]
// [0.5]
// 0
// 2
}

//    1514. Path with Maximum Probability
//    Medium
//    You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
//
//    Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
//
//    If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
//
//
//
//    Example 1:
//
//
//
//    Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
//    Output: 0.25000
//    Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
//    Example 2:
//
//
//
//    Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
//    Output: 0.30000
//    Example 3:
//
//
//
//    Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//    Output: 0.00000
//    Explanation: There is no path between 0 and 2.
//
//
//    Constraints:
//
//    2 <= n <= 10^4
//    0 <= start, end < n
//    start != end
//    0 <= a, b < n
//    a != b
//    0 <= succProb.length == edges.length <= 2*10^4
//    0 <= succProb[i] <= 1
//    There is at most one edge between every two nodes.