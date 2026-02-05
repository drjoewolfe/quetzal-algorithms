package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumCostPathWithEdgeReversals {
    class Solution {
        public int minCost(int n, int[][] edges) {
            if (n < 1) {
                return 0;
            }

            if (edges == null || edges.length == 0) {
                return -1;
            }

            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                graph.get(u).add(new int[]{v, w});
                graph.get(v).add(new int[]{u, 2 * w});
            }

            // Dijkstra
            boolean[] visited = new boolean[n];
            int[] distance = new int[n];

            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[0] = 0;

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            heap.offer(new int[]{0, 0});

            int target = n - 1;
            while (!heap.isEmpty()) {
                int[] element = heap.poll();
                int u = element[0];
                int d = element[1];

                if (u == target) {
                    return d;
                }

                if (visited[u]) {
                    continue;
                }

                visited[u] = true;
                for (int[] neighbour : graph.get(u)) {
                    int v = neighbour[0];
                    int w = neighbour[1];

                    int vd = d + w;
                    if (distance[v] > vd) {
                        distance[v] = vd;
                        heap.offer(new int[]{v, vd});
                    }
                }
            }

            return -1;
        }
    }
}

//    3650. Minimum Cost Path with Edge Reversals
//    Medium
//    You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.
//
//    Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch, you may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.
//
//    The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.
//
//    Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.
//
//
//
//    Example 1:
//
//    Input: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
//
//    Output: 5
//
//    Explanation:
//
//
//
//    Use the path 0 → 1 (cost 3).
//    At node 1 reverse the original edge 3 → 1 into 1 → 3 and traverse it at cost 2 * 1 = 2.
//    Total cost is 3 + 2 = 5.
//    Example 2:
//
//    Input: n = 4, edges = [[0,2,1],[2,1,1],[1,3,1],[2,3,3]]
//
//    Output: 3
//
//    Explanation:
//
//    No reversal is needed. Take the path 0 → 2 (cost 1), then 2 → 1 (cost 1), then 1 → 3 (cost 1).
//    Total cost is 1 + 1 + 1 = 3.
//
//
//    Constraints:
//
//    2 <= n <= 5 * 104
//    1 <= edges.length <= 105
//    edges[i] = [ui, vi, wi]
//    0 <= ui, vi <= n - 1
//    1 <= wi <= 1000