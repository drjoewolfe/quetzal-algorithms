package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class NumberOfGoodPaths {
    class Solution {
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            if(vals == null || vals.length == 0) {
                return 0;
            }

            int n = vals.length;

            if(edges == null || edges.length == 0) {
                return n;
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

            TreeMap<Integer, Set<Integer>> nodesByVal = new TreeMap<>();
            for(int u = 0; u < n; u++) {
                int val = vals[u];
                if(!nodesByVal.containsKey(val)) {
                    nodesByVal.put(val, new HashSet<>());
                }

                nodesByVal.get(val).add(u);
            }

            int goodPathCount = 0;

            UnionFind unionFind = new UnionFind(n);
            for(var entry : nodesByVal.entrySet()) {
                var nodes = entry.getValue();
                for(var u : nodes) {
                    for(var v : graph.get(u)) {
                        if(vals[v] <= vals[u]) {
                            unionFind.union(u, v);
                        }
                    }
                }

                Map<Integer, Integer> group = new HashMap<>();
                for(var u : nodes) {
                    int up = unionFind.find(u);
                    group.put(up, group.getOrDefault(up, 0) + 1);
                }

                for(var ge : group.entrySet()) {
                    int c = ge.getValue();
                    goodPathCount +=  (c * (c + 1) / 2);
                }
            }

            return goodPathCount;
        }

        private class UnionFind {
            int n;
            int[] parent;
            int[] rank;

            public UnionFind(int n) {
                this.n = n;
                parent = new int[n];
                rank = new int[n];

                for(int u = 0; u < n; u++) {
                    parent[u] = u;
                }
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if(up == vp) {
                    return;
                } else if(rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else if(rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else {
                    parent[vp] = up;
                    rank[up]++;
                }
            }

            private int find(int u) {
                if(parent[u] != u) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }
        }
    }

    class Solution_TLE {
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            if(vals == null || vals.length == 0) {
                return 0;
            }

            int n = vals.length;

            if(edges == null || edges.length == 0) {
                return n;
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

            int goodPathCount = 0;
            for(int u = 0; u < n; u++) {
                goodPathCount += computeGoodPathCounts(graph, vals, u, u, new HashSet<>());
            }

            goodPathCount = n + (goodPathCount - n) / 2;
            return goodPathCount;
        }

        private int computeGoodPathCounts(Map<Integer, Set<Integer>> graph, int[] vals, Integer u, Integer start, Set<Integer> visited) {
            int count = 0;
            if(vals[u] == vals[start]) {
                count++;
            }

            visited.add(u);

            for(var v : graph.get(u)) {
                if(visited.contains(v)
                        || vals[v] > vals[start]) {
                    continue;
                }

                count += computeGoodPathCounts(graph, vals, v, start, visited);
            }

            return count;
        }
    }

// [1,3,2,1,3]
// [[0,1],[0,2],[2,3],[2,4]]
}

//    2421. Number of Good Paths
//    Hard
//    There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
//
//    You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
//
//    A good path is a simple path that satisfies the following conditions:
//
//    The starting node and the ending node have the same value.
//    All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
//    Return the number of distinct good paths.
//
//    Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
//
//
//
//    Example 1:
//
//
//    Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
//    Output: 6
//    Explanation: There are 5 good paths consisting of a single node.
//    There is 1 additional good path: 1 -> 0 -> 2 -> 4.
//    (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
//    Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
//    Example 2:
//
//
//    Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
//    Output: 7
//    Explanation: There are 5 good paths consisting of a single node.
//    There are 2 additional good paths: 0 -> 1 and 2 -> 3.
//    Example 3:
//
//
//    Input: vals = [1], edges = []
//    Output: 1
//    Explanation: The tree consists of only one node, so there is one good path.
//
//
//    Constraints:
//
//    n == vals.length
//    1 <= n <= 3 * 104
//    0 <= vals[i] <= 105
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    edges represents a valid tree.