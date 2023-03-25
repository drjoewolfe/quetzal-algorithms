package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class CountUnreachablePairsOfNodesInAnUndirectedGraph {
    class Solution {
        public long countPairs(int n, int[][] edges) {
            if(n < 1) {
                return 0;
            }

            DisjointSets sets = new DisjointSets(n);
            for(int[] edge : edges) {
                sets.union(edge[0], edge[1]);
            }

            Map<Integer, Integer> componentSizes = new HashMap<>();
            for(int u = 0; u < n; u++) {
                int pu = sets.find(u);
                componentSizes.put(pu, componentSizes.getOrDefault(pu, 0) + 1);
            }

            long pairCounts = 0;
            int alreadyCounted = 0;

            for(var entry : componentSizes.entrySet()) {
                int nodesInComponent = entry.getValue();
                int nodesOutsideComponent = n - nodesInComponent - alreadyCounted;

                pairCounts += (1L * nodesInComponent * nodesOutsideComponent);
                alreadyCounted += nodesInComponent;
            }

            return pairCounts;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;

            public DisjointSets(int n) {
                parent = new int[n];
                rank = new int[n];

                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 1;
                }
            }

            public void union(int u, int v) {
                int pu = find(u);
                int pv = find(v);

                if(rank[pu] < rank[pv]) {
                    parent[pu] = pv;
                } else if(rank[pu] > rank[pv]) {
                    parent[pv] = pu;
                } else {
                    parent[pu] = pv;
                    rank[pv]++;
                }
            }

            public int find(int u) {
                if(u != parent[u]) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }
        }
    }

    class Solution_Correct_DFS_1 {
        public long countPairs(int n, int[][] edges) {
            if(n < 0) {
                return 0;
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            List<Integer> componentSizes = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            for(int u = 0; u < n; u++) {
                if(!visited.contains(u)) {
                    int size = dfs(graph, u, visited);
                    componentSizes.add(size);
                }
            }

            long unreachablePairs = 0;
            int counted = 0;
            for(int i = 0; i < componentSizes.size() - 1; i++) {
                int s1 = componentSizes.get(i);
                int s2 = n - s1 - counted;
                unreachablePairs += (1L * s1 * s2);

                counted += s1;
            }

            return unreachablePairs;
        }

        private int dfs(Map<Integer, List<Integer>> graph, int u, Set<Integer> visited) {
            visited.add(u);

            int count = 1;
            for(int v : graph.get(u)) {
                if(!visited.contains(v)) {
                    count += dfs(graph, v, visited);
                }
            }

            return count;
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 3
// [[0,1],[0,2],[1,2]]

// 7
// [[0,2],[0,5],[2,4],[1,6],[5,4]]
}

//    2316. Count Unreachable Pairs of Nodes in an Undirected Graph
//    Medium
//
//    483
//
//    14
//
//    Add to List
//
//    Share
//    You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
//
//    Return the number of pairs of different nodes that are unreachable from each other.
//
//
//
//    Example 1:
//
//
//    Input: n = 3, edges = [[0,1],[0,2],[1,2]]
//    Output: 0
//    Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
//    Example 2:
//
//
//    Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
//    Output: 14
//    Explanation: There are 14 pairs of nodes that are unreachable from each other:
//    [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
//    Therefore, we return 14.
//
//
//    Constraints:
//
//    1 <= n <= 105
//    0 <= edges.length <= 2 * 105
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    There are no repeated edges.