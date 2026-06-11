package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfWaysToAssignEdgeWeightsI {
    class Solution {
        private int MOD = 1_000_000_007;

        public int assignEdgeWeights(int[][] edges) {
            if (edges == null || edges.length == 0) {
                return 0;
            }

            int n = edges.length + 1;
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 1; u <= n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int maxDepth = getMaxDepth(graph);
            return qPow(2, maxDepth - 1);
        }

        private int getMaxDepth(Map<Integer, Set<Integer>> graph) {
            return getMaxDepth(graph, 1, 0) - 1;
        }

        private int getMaxDepth(Map<Integer, Set<Integer>> graph, int u, int p) {
            int subTreeMaxDepth = 0;
            for (int v : graph.get(u)) {
                if (v == p) {
                    continue;
                }

                int depth = getMaxDepth(graph, v, u);
                subTreeMaxDepth = Math.max(subTreeMaxDepth, depth);
            }

            return subTreeMaxDepth + 1;
        }

        private int qPow(int x, int y) {
            long res = 1;
            long base = x;

            while (y > 0) {
                if ((y & 1) == 1) {
                    res = (res * base) % MOD;
                }

                base = (base * base) % MOD;
                y >>= 1;
            }

            return (int) res;
        }
    }
}

//    3558. Number of Ways to Assign Edge Weights I
//    Medium
//    There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.
//
//    Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
//
//    The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
//
//    Select any one node x at the maximum depth. Return the number of ways to assign edge weights in the path from node 1 to x such that its total cost is odd.
//
//    Since the answer may be large, return it modulo 109 + 7.
//
//    Note: Ignore all edges not in the path from node 1 to x.
//
//
//
//    Example 1:
//
//
//
//    Input: edges = [[1,2]]
//
//    Output: 1
//
//    Explanation:
//
//    The path from Node 1 to Node 2 consists of one edge (1 → 2).
//    Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.
//    Example 2:
//
//
//
//    Input: edges = [[1,2],[1,3],[3,4],[3,5]]
//
//    Output: 2
//
//    Explanation:
//
//    The maximum depth is 2, with nodes 4 and 5 at the same depth. Either node can be selected for processing.
//    For example, the path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4).
//    Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    edges.length == n - 1
//    edges[i] == [ui, vi]
//    1 <= ui, vi <= n
//    edges represents a valid tree.