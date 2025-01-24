package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindEventualSafeStates {
    class Solution {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> safeNodes = new ArrayList<>();
            if (graph == null || graph.length == 0) {
                return safeNodes;
            }

            Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();
            int n = graph.length;
            for (int u = 0; u < n; u++) {
                reverseGraph.put(u, new HashSet<>());
            }

            int[] outDegrees = new int[n];
            for (int u = 0; u < n; u++) {
                for (int v : graph[u]) {
                    reverseGraph.get(v).add(u);
                    outDegrees[u]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] safeNode = new boolean[n];
            for (int u = 0; u < n; u++) {
                if (outDegrees[u] == 0) {
                    queue.offer(u);
                    safeNode[u] = true;
                }
            }

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int v : reverseGraph.get(u)) {
                    outDegrees[v]--;

                    if (outDegrees[v] == 0) {
                        queue.offer(v);
                        safeNode[v] = true;
                    }
                }
            }

            for (int u = 0; u < n; u++) {
                if (safeNode[u]) {
                    safeNodes.add(u);
                }
            }

            return safeNodes;
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_2 {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> safeNodes = new ArrayList<>();

            int n = graph.length;
            Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                reverseGraph.put(u, new HashSet<>());
            }

            int[] outDegrees = new int[n];
            for (int u = 0; u < n; u++) {
                for (int v : graph[u]) {
                    reverseGraph.get(v).add(u);
                    outDegrees[u]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int u = 0; u < n; u++) {
                if (outDegrees[u] == 0) {
                    queue.offer(u);
                }
            }

            boolean[] isSafeNode = new boolean[n];

            while (!queue.isEmpty()) {
                var u = queue.poll();
                isSafeNode[u] = true;

                for (var v : reverseGraph.get(u)) {
                    outDegrees[v]--;
                    if (outDegrees[v] == 0) {
                        queue.offer(v);
                    }
                }
            }

            for (int u = 0; u < n; u++) {
                if (isSafeNode[u]) {
                    safeNodes.add(u);
                }
            }

            return safeNodes;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            Set<Integer> safeNodes = new TreeSet<>();
            if (graph == null || graph.length == 0) {
                return null;
            }

            int n = graph.length;
            Set<Integer> visited = new HashSet<>();
            for (int u = 0; u < n; u++) {
                if (!visited.contains(u)) {
                    processSafeNodes(graph, u, safeNodes, visited);
                }
            }

            return new ArrayList(safeNodes);
        }

        private boolean processSafeNodes(int[][] graph, int u, Set<Integer> safeNodes, Set<Integer> visited) {
            Set<Integer> stack = new HashSet<>();
            return processSafeNodes(graph, u, stack, safeNodes, visited);
        }

        private boolean processSafeNodes(int[][] graph, int u, Set<Integer> stack, Set<Integer> safeNodes, Set<Integer> visited) {
            if (visited.contains(u)) {
                return !safeNodes.contains(u);
            }

            stack.add(u);
            visited.add(u);
            for (int v : graph[u]) {
                if (stack.contains(v)) {
                    return true;
                }

                if (!safeNodes.contains(v)
                        && processSafeNodes(graph, v, stack, safeNodes, visited)) {
                    return true;
                }
            }

            stack.remove(u);
            safeNodes.add(u);
            return false;
        }
    }

    class Solution_Classic_DFS {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> safeNodes = new ArrayList<>();
            if (graph == null || graph.length == 0) {
                return safeNodes;
            }

            int n = graph.length;
            for (int u = 0; u < n; u++) {
                if (!hasLoop(graph, u)) {
                    safeNodes.add(u);
                }
            }

            return safeNodes;
        }

        private boolean hasLoop(int[][] graph, int u) {
            Set<Integer> stack = new HashSet<>();
            return hasLoop(graph, u, stack);
        }

        private boolean hasLoop(int[][] graph, int u, Set<Integer> stack) {
            stack.add(u);
            for (int v : graph[u]) {
                if (stack.contains(v)) {
                    return true;
                }

                if (hasLoop(graph, v, stack)) {
                    return true;
                }
            }

            stack.remove(u);
            return false;
        }
    }

// [[1,2],[2,3],[5],[0],[5],[],[]]
// [[1,2,3,4],[1,2],[3,4],[0,4],[]]
// [[],[0,2,3,4],[3],[4],[]]
// [[1,14],[6,9,11,16],[1,3,4,5,13,16,17],[9,10,12,13,17,18],[9,10,13,18],[7,9,11,19],[2,8,14,15,17,18],[8,12,15,16,18,19],[10,12,14,19],[10,12,14,15,19],[12,16],[12,16,17,18,19],[13,14,16,18,19],[10,14,15,16,17,19],[],[17,19],[],[18,19],[8,19],[]]
}

//    802. Find Eventual Safe States
//    Medium
//    There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
//
//    A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
//
//    Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
//
//
//
//    Example 1:
//
//    Illustration of graph
//    Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//    Output: [2,4,5,6]
//    Explanation: The given graph is shown above.
//    Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
//    Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
//    Example 2:
//
//    Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//    Output: [4]
//    Explanation:
//    Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
//
//
//    Constraints:
//
//    n == graph.length
//    1 <= n <= 104
//    0 <= graph[i].length <= n
//    0 <= graph[i][j] <= n - 1
//    graph[i] is sorted in a strictly increasing order.
//    The graph may contain self-loops.
//    The number of edges in the graph will be in the range [1, 4 * 104].