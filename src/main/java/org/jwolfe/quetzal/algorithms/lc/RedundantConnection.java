package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RedundantConnection {
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            if(edges == null || edges.length == 0) {
                return new int[0];
            }

            int n = edges.length;
            DisjointSet set = new DisjointSet(n + 1);
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if(set.find(u) == set.find(v)) {
                    // Already a connection exists between u & v
                    return edge;
                }

                set.union(u, v);
            }

            return new int[0];
        }

        private class DisjointSet {
            int[] parent;
            int[] rank;

            public DisjointSet(int n) {
                parent = new int[n];
                rank = new int[n];

                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }

                Arrays.fill(rank, 1);
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if(rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else if(rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }

            public int find(int u) {
                int p = u;
                if(parent[u] != u) {
                    p = find(parent[u]);
                }

                parent[u] = p;
                return p;
            }

            private void print() {
                for(int p : parent) {
                    System.out.print(p + " ");
                }

                System.out.println();
            }
        }
    }

    class Solution_UnionFind {
        public int[] findRedundantConnection(int[][] edges) {
            if(edges == null || edges.length == 0) {
                return new int[0];
            }

            int n = edges.length;
            DisjointSet set = new DisjointSet(n + 1);
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if(set.find(u) == set.find(v)) {
                    // Already a connection exists between u & v
                    return edge;
                }

                set.union(u, v);
            }

            return new int[0];
        }

        private class DisjointSet {
            int[] parent;

            public DisjointSet(int n) {
                parent = new int[n];

                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                parent[up] = vp;
            }

            public int find(int u) {
                if(parent[u] != u) {
                    return find(parent[u]);
                }

                return u;
            }
        }
    }

    class Solution_DFS {
        public int[] findRedundantConnection(int[][] edges) {
            if(edges == null || edges.length == 0) {
                return new int[0];
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if(canReach(graph, u, v)) {
                    // Already a connection exists between u & v
                    return edge;
                }

                if(!graph.containsKey(u)) {
                    graph.put(u, new ArrayList<>());
                }

                if(!graph.containsKey(v)) {
                    graph.put(v, new ArrayList<>());
                }

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return new int[0];
        }

        private boolean canReach(Map<Integer, List<Integer>> graph, int source, int destination) {
            Set<Integer> visited = new HashSet<>();
            return canReach(graph, source, destination, visited);
        }

        private boolean canReach(Map<Integer, List<Integer>> graph, int u, int destination, Set<Integer> visited) {
            if(u == destination) {
                return true;
            }

            if(visited.contains(u)) {
                return false;
            }

            visited.add(u);
            if(graph.containsKey(u)) {
                for(int v : graph.get(u)) {
                    if(canReach(graph, v, destination, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// [[1,2],[1,3],[2,3]]
}

//    684. Redundant Connection
//    Medium
//    In this problem, a tree is an undirected graph that is connected and has no cycles.
//
//    You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
//
//    Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
//
//
//
//    Example 1:
//
//
//    Input: edges = [[1,2],[1,3],[2,3]]
//    Output: [2,3]
//    Example 2:
//
//
//    Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
//    Output: [1,4]
//
//
//    Constraints:
//
//    n == edges.length
//    3 <= n <= 1000
//    edges[i].length == 2
//    1 <= ai < bi <= edges.length
//    ai != bi
//    There are no repeated edges.
//    The given graph is connected.
