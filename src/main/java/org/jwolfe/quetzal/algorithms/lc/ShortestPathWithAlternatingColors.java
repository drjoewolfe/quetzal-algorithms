package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ShortestPathWithAlternatingColors {
    class Solution {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            if(n < 1) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> redGraph = new HashMap<>();
            Map<Integer, Set<Integer>> blueGraph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                redGraph.put(i, new HashSet<>());
                blueGraph.put(i, new HashSet<>());
            }

            for(int[] edge : redEdges) {
                redGraph.get(edge[0]).add(edge[1]);
            }

            for(int[] edge : blueEdges) {
                blueGraph.get(edge[0]).add(edge[1]);
            }

            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[0] = 0;

            Queue<NodeTraversalInfo> queue = new LinkedList<>();

            for(var v : redGraph.get(0)) {
                queue.offer(new NodeTraversalInfo(v, true, 1));
            }

            for(var v : blueGraph.get(0)) {
                queue.offer(new NodeTraversalInfo(v, false, 1));
            }

            Set<Integer> visitedViaRedSet = new HashSet<>();
            Set<Integer> visitedViaBlueSet = new HashSet<>();

            while(!queue.isEmpty()) {
                var info = queue.poll();
                distances[info.vertex] = Math.min(distances[info.vertex], info.distance);

                var graph = info.visitedViaARedEdge ? blueGraph : redGraph;
                var visited = info.visitedViaARedEdge ? visitedViaRedSet : visitedViaBlueSet;

                for(var v : graph.get(info.vertex)) {
                    if(!visited.contains(v)) {
                        visited.add(v);
                        queue.offer(new NodeTraversalInfo(v, !info.visitedViaARedEdge, info.distance + 1));
                    }
                }

            }

            for(int i = 1; i < n; i++) {
                if(distances[i] == Integer.MAX_VALUE) {
                    distances[i] = -1;
                }
            }

            return distances;
        }

        private class NodeTraversalInfo {
            int vertex;
            boolean visitedViaARedEdge;
            int distance;

            public NodeTraversalInfo(int vertex, boolean visitedViaARedEdge, int distance) {
                this.vertex = vertex;
                this.visitedViaARedEdge = visitedViaARedEdge;
                this.distance = distance;
            }
        }
    }

    class Solution_Incorrect_2 {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            if(n < 1) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> redGraph = new HashMap<>();
            Map<Integer, Set<Integer>> blueGraph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                redGraph.put(i, new HashSet<>());
                blueGraph.put(i, new HashSet<>());
            }

            for(int[] edge : redEdges) {
                redGraph.get(edge[0]).add(edge[1]);
            }

            for(int[] edge : blueEdges) {
                blueGraph.get(edge[0]).add(edge[1]);
            }

            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[0] = 0;

            for(var v : redGraph.get(0)) {
                Set<Integer> visitedViaRed = new HashSet<>();
                Set<Integer> visitedViaBlue = new HashSet<>();

                dfs(redGraph, blueGraph, v, true, 1, distances, visitedViaRed, visitedViaBlue);
                print(distances);
            }

            for(var v : blueGraph.get(0)) {
                Set<Integer> visitedViaRed = new HashSet<>();
                Set<Integer> visitedViaBlue = new HashSet<>();

                dfs(redGraph, blueGraph, v, false, 1, distances, visitedViaRed, visitedViaBlue);
                print(distances);
            }

            for(int i = 1; i < n; i++) {
                if(distances[i] == Integer.MAX_VALUE) {
                    distances[i] = -1;
                }
            }

            return distances;
        }

        private void dfs(Map<Integer, Set<Integer>> redGraph, Map<Integer, Set<Integer>> blueGraph, int u, boolean prevRed, int currentDistance, int[] distances, Set<Integer> visitedViaRed, Set<Integer> visitedViaBlue) {

            distances[u] = Math.min(distances[u], currentDistance);

            var graph = prevRed ? blueGraph : redGraph;
            var visited = prevRed ? visitedViaBlue : visitedViaRed;
            for(var v : graph.get(u)) {
                if(!visited.contains(v)) {
                    visited.add(v);
                    dfs(redGraph, blueGraph, v, !prevRed, currentDistance + 1, distances, visitedViaRed, visitedViaBlue);
                }
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            if(n < 1) {
                return new int[0];
            }

            Map<Integer, Set<Integer>> redGraph = new HashMap<>();
            Map<Integer, Set<Integer>> blueGraph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                redGraph.put(i, new HashSet<>());
                blueGraph.put(i, new HashSet<>());
            }

            for(int[] edge : redEdges) {
                redGraph.get(edge[0]).add(edge[1]);
            }

            for(int[] edge : blueEdges) {
                blueGraph.get(edge[0]).add(edge[1]);
            }

            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[0] = 0;

            for(var v : redGraph.get(0)) {
                Set<Integer> visited = new HashSet<>();
                visited.add(0);

                dfs(redGraph, blueGraph, v, true, 1, distances, visited);
            }

            for(var v : blueGraph.get(0)) {
                Set<Integer> visited = new HashSet<>();
                visited.add(0);

                dfs(redGraph, blueGraph, v, false, 1, distances, visited);
            }

            for(int i = 1; i < n; i++) {
                if(distances[i] == Integer.MAX_VALUE) {
                    distances[i] = -1;
                }
            }

            return distances;
        }

        private void dfs(Map<Integer, Set<Integer>> redGraph, Map<Integer, Set<Integer>> blueGraph, int u, boolean prevRed, int currentDistance, int[] distances, Set<Integer> visited) {

            visited.add(u);
            distances[u] = Math.min(distances[u], currentDistance);

            var graph = prevRed ? blueGraph : redGraph;
            for(var v : graph.get(u)) {
                if(!visited.contains(v)) {
                    dfs(redGraph, blueGraph, v, !prevRed, currentDistance + 1, distances, visited);
                }
            }
        }
    }

// 3
// [[0,1],[1,2]]
// []

// 5
// [[0,1],[1,2],[2,3],[3,4]]
// [[1,2],[2,3],[3,1]]

// 6
// [[1,5],[2,2],[5,5],[3,0],[4,5],[2,4],[4,1],[1,0],[1,2],[5,2],[2,3],[0,1]]
// [[4,4],[2,5],[1,1],[5,4],[3,3]]
}

//    1129. Shortest Path with Alternating Colors
//    Medium
//    You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
//
//    You are given two arrays redEdges and blueEdges where:
//
//    redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
//    blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
//    Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
//
//
//
//    Example 1:
//
//    Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
//    Output: [0,1,-1]
//    Example 2:
//
//    Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
//    Output: [0,1,-1]
//
//
//    Constraints:
//
//    1 <= n <= 100
//    0 <= redEdges.length, blueEdges.length <= 400
//    redEdges[i].length == blueEdges[j].length == 2
//    0 <= ai, bi, uj, vj < n