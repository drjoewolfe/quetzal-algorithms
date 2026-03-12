package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class MaximizeSpanningTreeStabilityWithUpgrades {
    class Solution {
        public int maxStability(int n, int[][] edges, int k) {
            DisjointSet ds = new DisjointSet(n);

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int s = edge[2];
                int m = edge[3];

                if (m == 1) {
                    // Must be included in the spanning tree
                    if (ds.find(u) == ds.find(v)) {
                        // Including this edge will result in a cycle
                        return -1;
                    }

                    ds.union(u, v);
                }
            }

            int result = -1;
            int left = 1;
            int right = 2 * 100000;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (check(n, edges, k, mid)) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return result;
        }

        private boolean check(int n, int[][] edges, int k, int mid) {
            DisjointSet ds = new DisjointSet(n);

            List<int[]> upgradeCandidates = new ArrayList<>();
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int s = edge[2];
                int m = edge[3];

                if (m == 1) {
                    if (s < mid) {
                        return false;
                    }

                    ds.union(u, v);
                } else {
                    if (s >= mid) {
                        // No need to upgrade
                        ds.union(u, v);
                    } else if (2 * s >= mid) {
                        upgradeCandidates.add(edge);
                    }
                }
            }

            for (int[] edge : upgradeCandidates) {
                int u = edge[0];
                int v = edge[1];

                if (ds.find(u) != ds.find(v)) {
                    if (k <= 0) {
                        return false;
                    }

                    ds.union(u, v);
                    k--;
                }
            }

            int root = ds.find(0);
            for (int u = 1; u < n; u++) {
                if (ds.find(u) != root) {
                    return false;
                }
            }

            return true;
        }

        class DisjointSet {
            int[] parent;
            int[] rank;

            public DisjointSet(int n) {
                this.parent = new int[n];
                this.rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int x) {
                if (x == parent[x]) {
                    return x;
                }

                return parent[x] = find(parent[x]);
            }

            public void union(int x, int y) {
                int xp = find(x);
                int yp = find(y);

                if (xp == yp) {
                    return;
                }

                if (rank[xp] > rank[yp]) {
                    parent[yp] = xp;
                } else if (rank[xp] < rank[yp]) {
                    parent[xp] = yp;
                } else {
                    parent[xp] = yp;
                    rank[yp]++;
                }
            }
        }
    }
}

//    3600. Maximize Spanning Tree Stability with Upgrades
//    Hard
//    You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:
//
//    ui and vi indicates an undirected edge between nodes ui and vi.
//    si is the strength of the edge.
//    musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.
//    You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.
//
//    The stability of a spanning tree is defined as the minimum strength score among all edges included in it.
//
//    Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.
//
//    Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.
//
//
//
//    Example 1:
//
//    Input: n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1
//
//    Output: 2
//
//    Explanation:
//
//    Edge [0,1] with strength = 2 must be included in the spanning tree.
//    Edge [1,2] is optional and can be upgraded from 3 to 6 using one upgrade.
//    The resulting spanning tree includes these two edges with strengths 2 and 6.
//    The minimum strength in the spanning tree is 2, which is the maximum possible stability.
//    Example 2:
//
//    Input: n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2
//
//    Output: 6
//
//    Explanation:
//
//    Since all edges are optional and up to k = 2 upgrades are allowed.
//    Upgrade edges [0,1] from 4 to 8 and [1,2] from 3 to 6.
//    The resulting spanning tree includes these two edges with strengths 8 and 6.
//    The minimum strength in the tree is 6, which is the maximum possible stability.
//    Example 3:
//
//    Input: n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0
//
//    Output: -1
//
//    Explanation:
//
//    All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= edges.length <= 105
//    edges[i] = [ui, vi, si, musti]
//    0 <= ui, vi < n
//    ui != vi
//    1 <= si <= 105
//    musti is either 0 or 1.
//    0 <= k <= n
//    There are no duplicate edges.