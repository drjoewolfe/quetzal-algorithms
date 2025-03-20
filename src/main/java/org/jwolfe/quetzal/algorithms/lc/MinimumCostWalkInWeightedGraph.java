package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumCostWalkInWeightedGraph {
    class Solution {
        public int[] minimumCost(int n, int[][] edges, int[][] query) {
            if (query == null || query.length == 0) {
                return new int[0];
            }

            int[] results = new int[query.length];
            DisjointSets sets = new DisjointSets(n);
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                sets.union(u, v);
            }

            int[] componentCosts = new int[n];
            for (int u = 0; u < n; u++) {
                componentCosts[u] = Integer.MAX_VALUE;
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                int up = sets.find(u);
                componentCosts[up] &= w;
            }

            for (int i = 0; i < query.length; i++) {
                int[] q = query[i];
                int u = q[0];
                int v = q[1];

                int up = sets.find(u);
                int vp = sets.find(v);

                if (up != vp) {
                    results[i] = -1;
                } else {
                    results[i] = componentCosts[up];
                }
            }

            return results;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;

            public DisjointSets(int n) {
                parent = new int[n];
                rank = new int[n];

                for (int u = 0; u < n; u++) {
                    parent[u] = u;
                }

                Arrays.fill(rank, 1);
            }

            private void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if (up == vp) {
                    return;
                }

                if (rank[up] > rank[vp]) {
                    parent[vp] = up;
                    rank[up]++;
                } else if (rank[up] < rank[vp]) {
                    parent[up] = vp;
                    rank[vp]++;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }

            private int find(int u) {
                int up = parent[u];
                if (up == u) {
                    return u;
                }

                return parent[u] = find(parent[u]);
            }
        }
    }
}

//    3108. Minimum Cost Walk in Weighted Graph
//    Hard
//    There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
//
//    You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.
//
//    A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk may visit the same edge or vertex more than once.
//
//    The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.
//
//    You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.
//
//    Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.
//
//
//
//    Example 1:
//
//    Input: n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]
//
//    Output: [1,-1]
//
//    Explanation:
//
//
//    To achieve the cost of 1 in the first query, we need to move on the following edges: 0->1 (weight 7), 1->2 (weight 1), 2->1 (weight 1), 1->3 (weight 7).
//
//    In the second query, there is no walk between nodes 3 and 4, so the answer is -1.
//
//    Example 2:
//
//    Input: n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]
//
//    Output: [0]
//
//    Explanation:
//
//
//    To achieve the cost of 0 in the first query, we need to move on the following edges: 1->2 (weight 1), 2->1 (weight 6), 1->2 (weight 1).
//
//
//
//    Constraints:
//
//    2 <= n <= 105
//    0 <= edges.length <= 105
//    edges[i].length == 3
//    0 <= ui, vi <= n - 1
//    ui != vi
//    0 <= wi <= 105
//    1 <= query.length <= 105
//    query[i].length == 2
//    0 <= si, ti <= n - 1
//    si != ti