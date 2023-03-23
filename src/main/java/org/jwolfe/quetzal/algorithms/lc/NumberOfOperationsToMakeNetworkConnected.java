package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class NumberOfOperationsToMakeNetworkConnected {
    class Solution {
        public int makeConnected(int n, int[][] connections) {
            if(n < 1 || connections.length < n - 1) {
                return -1;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new HashSet<>());
            }

            for(int[] connection : connections) {
                int u = connection[0];
                int v = connection[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int components = 0;
            Set<Integer> visited = new HashSet<>();
            for(int i = 0; i < n; i++) {
                if(!visited.contains(i)) {
                    components++;

                    dfs(graph, i, visited);
                }
            }

            return components - 1;
        }

        private void dfs(Map<Integer, Set<Integer>> graph, int u, Set<Integer> visited) {
            visited.add(u);

            for(var v : graph.get(u)) {
                if(!visited.contains(v)) {
                    dfs(graph, v, visited);
                }
            }
        }
    }

    class Solution_Correct_1 {
        public int makeConnected(int n, int[][] connections) {
            if(n == 0 || connections.length < n - 1) {
                return -1;
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int[] edge : connections) {
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

            int components = 0;
            boolean[] visited = new boolean[n];
            for(int u = 0; u < n; u++) {
                if(!visited[u]) {
                    components++;

                    Set<Integer> stack = new HashSet<>();
                    stack.add(u);
                    dfs(graph, u, visited, stack);
                }
            }

            return components - 1;
        }

        private void dfs(Map<Integer, List<Integer>> graph, int u, boolean[] visited, Set<Integer> stack) {
            visited[u] = true;
            if(!graph.containsKey(u)) {
                return;
            }

            for(Integer v : graph.get(u)) {
                if(visited[v] || stack.contains(v)) {
                    continue;
                }

                stack.add(v);
                dfs(graph, v, visited, stack);
                stack.remove(v);
            }
        }
    }
}

//    1319. Number of Operations to Make Network Connected
//    Medium
//    There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
//
//    You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
//
//    Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
//
//
//
//    Example 1:
//
//
//    Input: n = 4, connections = [[0,1],[0,2],[1,2]]
//    Output: 1
//    Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
//    Example 2:
//
//
//    Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
//    Output: 2
//    Example 3:
//
//    Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
//    Output: -1
//    Explanation: There are not enough cables.
//
//
//    Constraints:
//
//    1 <= n <= 105
//    1 <= connections.length <= min(n * (n - 1) / 2, 105)
//    connections[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    There are no repeated connections.
//    No two computers are connected by more than one cable.