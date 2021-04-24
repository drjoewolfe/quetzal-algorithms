package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class CriticalConnectionsInANetwork {
    class Solution {
        private int time;
        private int[] discovery;
        private int[] low;
        private int[] parent;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            List<List<Integer>> criticalConnections = new ArrayList<>();
            if(n < 2 || connections == null || connections.size() == 0) {
                return criticalConnections;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int i = 0; i < connections.size(); i++) {
                var edge = connections.get(i);
                int u = edge.get(0);
                int v = edge.get(1);

                if(!graph.containsKey(u)) {
                    graph.put(u, new HashSet<>());
                }

                if(!graph.containsKey(v)) {
                    graph.put(v, new HashSet<>());
                }

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // Tarjan's algorithm for SCC
            // Critical connections are the cross edges
            Set<Integer> visited = new HashSet<>();
            for(int u = 0; u < n; u++) {
                if(!visited.contains(u)) {
                    tarjan(graph, n, u, visited);
                }
            }

            for(var edge : connections) {
                int u = edge.get(0);
                int v = edge.get(1);

                if(low[v] > discovery[u]
                        || low[u] > discovery[v]) {
                    // Bridge
                    criticalConnections.add(edge);
                }
            }

            return criticalConnections;
        }

        private void tarjan(Map<Integer, Set<Integer>> graph, int n, int u, Set<Integer> visited) {
            discovery = new int[n];
            low = new int[n];
            parent = new int[n];

            Stack<Integer> stack = new Stack<>();
            boolean[] inStack = new boolean[n];

            Arrays.fill(discovery, -1);
            Arrays.fill(low, -1);
            Arrays.fill(parent, -1);

            time = 0;
            tarjan(graph, n, u, -1, stack, inStack, visited);
        }

        private void tarjan(Map<Integer, Set<Integer>> graph, int n, int u, int p, Stack<Integer> stack, boolean[] inStack, Set<Integer> visited) {
            visited.add(u);

            discovery[u] = time;
            low[u] = time;
            parent[u] = p;

            stack.add(u);
            inStack[u] = true;

            time++;
            for(var v : graph.get(u)) {
                if(v == parent[u]) {
                    continue;
                }

                if(discovery[v] != -1) {
                    // Already discovered vertex
                    // Check if back edge or cross edge
                    if(inStack[v]) {
                        // Back edge
                        low[u] = Math.min(low[u], discovery[v]);
                    } else {
                        // Cross edge
                        // Ignore
                    }
                } else {
                    tarjan(graph, n, v, u, stack, inStack, visited);
                    low[u] = Math.min(low[u], low[v]);
                }
            }

            if(discovery[u] == low[u]) {
                // Head of a SCC.Can collect the SCC from the stack
                while(!stack.isEmpty()) {
                    int v = stack.pop();
                    inStack[v] = false;

                    // -- Collect --

                    if(v == u) {
                        break;
                    }
                }
            }
        }

        private void print(int[] A) {
            for(int a : A) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    1192. Critical Connections in a Network
//    Hard
//    There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
//
//    A critical connection is a connection that, if removed, will make some server unable to reach some other server.
//
//    Return all critical connections in the network in any order.
//
//
//
//    Example 1:
//
//
//
//    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
//    Output: [[1,3]]
//    Explanation: [[3,1]] is also accepted.
//
//
//    Constraints:
//
//    1 <= n <= 10^5
//    n-1 <= connections.length <= 10^5
//    connections[i][0] != connections[i][1]
//    There are no repeated connections.
