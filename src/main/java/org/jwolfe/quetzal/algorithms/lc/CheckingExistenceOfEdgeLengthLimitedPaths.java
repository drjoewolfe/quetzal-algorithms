package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class CheckingExistenceOfEdgeLengthLimitedPaths {
    class Solution {
        public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            if(queries == null || queries.length == 0) {
                return new boolean[0];
            }

            boolean[] answers = new boolean[queries.length];
            if(edgeList == null || edgeList.length == 0 || n < 1) {
                return answers;
            }

            DisjointSets sets = new DisjointSets(n);
            Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);

            List<Pair<int[], Integer>> queriesWithIndex = new ArrayList<>();
            for(int i = 0; i < queries.length; i++) {
                int[] query = queries[i];

                queriesWithIndex.add(new Pair<>(query, i));
            }

            Collections.sort(queriesWithIndex, (a, b) -> a.getKey()[2] - b.getKey()[2]);

            int edgeIndex = 0;
            for(var queryWithIndex : queriesWithIndex) {
                int[] query = queryWithIndex.getKey();
                int p = query[0];
                int q = query[1];
                int limit = query[2];
                int index = queryWithIndex.getValue();

                while(edgeIndex < edgeList.length
                        && edgeList[edgeIndex][2] < limit) {
                    int[] edge = edgeList[edgeIndex];
                    int u = edge[0];
                    int v = edge[1];

                    sets.union(u, v);

                    edgeIndex++;;
                }

                int rp = sets.find(p);
                int rq = sets.find(q);
                answers[index] = (rp == rq);
            }

            return answers;
        }

        private class DisjointSets {
            int[] representative;
            int[] rank;

            public DisjointSets(int n) {
                representative = new int[n];
                rank = new int[n];

                for(int i = 0; i < n; i++) {
                    representative[i] = i;
                }
            }

            public void union(int u, int v) {
                int ru = find(u);
                int rv = find(v);

                if(rank[ru] < rank[rv]) {
                    representative[ru] = rv;
                } else if(rank[ru] > rank[rv]) {
                    representative[rv] = ru;
                } else {
                    rank[rv]++;
                    representative[ru] = rv;
                }
            }

            public int find(int u) {
                if(representative[u] != u) {
                    representative[u] = find(representative[u]);
                }

                return representative[u];
            }
        }
    }

    class Solution_TLE {
        public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            if(queries == null || queries.length == 0) {
                return new boolean[0];
            }

            boolean[] answers = new boolean[queries.length];
            if(edgeList == null || edgeList.length == 0 || n < 1) {
                return answers;
            }

            Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashMap<>());
            }

            for(int[] edge : edgeList) {
                int u = edge[0];
                int v = edge[1];
                int d = edge[2];

                if(graph.get(u).containsKey(v)) {
                    graph.get(u).put(v, Math.min(graph.get(u).get(v), d));
                } else {
                    graph.get(u).put(v, d);
                }

                if(graph.get(v).containsKey(u)) {
                    graph.get(v).put(u, Math.min(graph.get(v).get(u), d));
                } else {
                    graph.get(v).put(u, d);
                }
            }

            for(int i = 0; i < queries.length; i++) {
                int[] query = queries[i];
                int u = query[0];
                int v = query[1];
                int limit = query[2];

                answers[i] = dfs(graph, u, v, limit, new HashSet<>());
            }

            return answers;
        }

        private boolean dfs(Map<Integer, Map<Integer, Integer>> graph, int u, int target, int limit, Set<Integer> visited) {
            if(u == target) {
                return true;
            }

            visited.add(u);

            var neighbours = graph.get(u);
            for(var v : neighbours.keySet()) {
                if(!visited.contains(v)
                        && neighbours.get(v) < limit) {
                    if(dfs(graph, v, target, limit, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// 3
// [[0,1,2],[1,2,4],[2,0,8],[1,0,16]]
// [[0,1,2],[0,2,5]]
}

//    1697. Checking Existence of Edge Length Limited Paths
//    Hard
//    An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
//
//    Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
//
//    Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
//    Output: [false,true]
//    Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
//    For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
//    For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
//    Example 2:
//
//
//    Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
//    Output: [true,false]
//    Exaplanation: The above figure shows the given graph.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= edgeList.length, queries.length <= 105
//    edgeList[i].length == 3
//    queries[j].length == 3
//    0 <= ui, vi, pj, qj <= n - 1
//    ui != vi
//    pj != qj
//    1 <= disi, limitj <= 109
//    There may be multiple edges between two nodes.