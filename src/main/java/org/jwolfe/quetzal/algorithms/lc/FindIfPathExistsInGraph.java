package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindIfPathExistsInGraph {
    class Solution {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            Set<Integer> visited = new HashSet<>();
            while(!queue.isEmpty()) {
                Integer u = queue.poll();
                if(u == destination) {
                    return true;
                }

                visited.add(u);
                for(Integer v : graph.get(u)) {
                    if(!visited.contains(v)) {
                        queue.offer(v);
                    }
                }
            }

            return false;
        }
    }

    class Solution_Iterative_DFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(source);

            Set<Integer> visited = new HashSet<>();
            while(!stack.isEmpty()) {
                Integer u = stack.pop();
                if(u == destination) {
                    return true;
                }

                visited.add(u);
                for(Integer v : graph.get(u)) {
                    if(!visited.contains(v)) {
                        stack.push(v);
                    }
                }
            }

            return false;
        }
    }

    class Solution_Recursive_DFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            if(n < 1 || source < 0 || source >= n || destination < 0 || destination >= n) {
                return false;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            return dfs(graph, source, destination, new HashSet<>());
        }

        private boolean dfs(Map<Integer, Set<Integer>> graph, Integer u, int destination, Set<Integer> visited) {
            if(u == destination) {
                return true;
            }

            visited.add(u);
            for(Integer v : graph.get(u)) {
                if(!visited.contains(v)
                        && dfs(graph, v, destination, visited)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    1971. Find if Path Exists in Graph
//    Easy
//    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
//
//    You want to determine if there is a valid path that exists from vertex source to vertex destination.
//
//    Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//    Output: true
//    Explanation: There are two paths from vertex 0 to vertex 2:
//    - 0 → 1 → 2
//    - 0 → 2
//    Example 2:
//
//
//    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
//    Output: false
//    Explanation: There is no path from vertex 0 to vertex 5.
//
//
//    Constraints:
//
//    1 <= n <= 2 * 105
//    0 <= edges.length <= 2 * 105
//    edges[i].length == 2
//    0 <= ui, vi <= n - 1
//    ui != vi
//    0 <= source, destination <= n - 1
//    There are no duplicate edges.
//    There are no self edges.