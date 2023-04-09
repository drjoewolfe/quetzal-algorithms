package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class LargestColorValueInADirectedGraph {
    class Solution {
        public int largestPathValue(String colors, int[][] edges) {
            if(colors == null || colors.length() == 0) {
                return -1;
            }

            if(edges == null || edges.length == 0) {
                return 1;
            }

            int n = colors.length();

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            int[] indegrees = new int[n];
            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                indegrees[v]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            for(int u = 0; u < n; u++) {
                if(indegrees[u] == 0) {
                    queue.offer(u);
                }
            }

            // dp[u][x] => max count of color 'x' in a path in the graph that ends at u
            int[][] dp = new int[n][26];
            int nodeCount = 0;
            int maxColorValue = 1;

            while(!queue.isEmpty()) {
                int u = queue.poll();
                nodeCount++;

                char c = colors.charAt(u);
                int ci = c - 'a';

                dp[u][ci]++;
                maxColorValue = Math.max(maxColorValue, dp[u][ci]);

                for(var v : graph.get(u)) {
                    for(int i = 0; i < 26; i++) {
                        dp[v][i] = Math.max(dp[v][i], dp[u][i]);
                    }

                    indegrees[v]--;
                    if(indegrees[v] == 0) {
                        queue.offer(v);
                    }
                }
            }

            return nodeCount != n ? -1 : maxColorValue;
        }
    }

// "abaca"
// [[0,1],[0,2],[2,3],[3,4]]
}

//    1857. Largest Color Value in a Directed Graph
//    Hard
//    There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
//
//    You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
//
//    A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
//
//    Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
//
//
//
//    Example 1:
//
//
//
//    Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
//    Output: 3
//    Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
//    Example 2:
//
//
//
//    Input: colors = "a", edges = [[0,0]]
//    Output: -1
//    Explanation: There is a cycle from 0 to 0.
//
//
//    Constraints:
//
//    n == colors.length
//    m == edges.length
//    1 <= n <= 105
//    0 <= m <= 105
//    colors consists of lowercase English letters.
//    0 <= aj, bj < n