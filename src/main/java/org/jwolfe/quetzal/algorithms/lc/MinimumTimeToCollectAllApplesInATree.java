package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumTimeToCollectAllApplesInATree {
    class Solution {
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            if(n < 1 || edges == null || edges.length == 0 || hasApple == null || hasApple.size() == 0) {
                return 0;
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

            int time = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            for(Integer v : graph.get(0)) {
                time += dfs(graph, v, hasApple, visited);
            }

            return time;
        }

        private int dfs(Map<Integer, Set<Integer>> graph, int u, List<Boolean> hasApple, Set<Integer> visited) {
            visited.add(u);

            int time = 0;

            for(Integer v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                time += dfs(graph, v, hasApple, visited);
            }

            if(time > 0 || hasApple.get(u)) {
                time += 2;
            }

            return time;
        }
    }

    class Solution_Correct_1 {
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            if(n == 0 || edges == null || hasApple == null || hasApple.size() == 0) {
                return 0;
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if(!graph.containsKey(u)) {
                    graph.put(u, new ArrayList<>());
                }

                if(!graph.containsKey(v)) {
                    graph.put(v, new ArrayList<>());
                }

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int time = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            for(int v : graph.get(0)) {
                time += dfs(graph, v, visited, hasApple);
            }

            return time;
        }

        private int dfs(Map<Integer, List<Integer>> graph, int u, Set<Integer> visited, List<Boolean> hasApple) {
            int time = 0;
            if(!graph.containsKey(u)) {
                return time;
            }

            visited.add(u);
            for(int v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                time += dfs(graph, v, visited, hasApple);
            }

            if(time > 0 || hasApple.get(u)) {
                time += 2;
            }

            return time;
        }
    }
}

//    1443. Minimum Time to Collect All Apples in a Tree
//    Medium
//    Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
//
//    The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
//
//
//
//    Example 1:
//
//
//    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
//    Output: 8
//    Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
//    Example 2:
//
//
//    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
//    Output: 6
//    Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
//    Example 3:
//
//    Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= n <= 105
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai < bi <= n - 1
//    fromi < toi
//    hasApple.length == n