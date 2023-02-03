package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaximumStarSumOfAGraph {
    class Solution {
        public int maxStarSum(int[] vals, int[][] edges, int k) {
            if(vals == null || vals.length == 0) {
                return 0;
            }

            int n = vals.length;
            Map<Integer, PriorityQueue<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                graph.put(i, new PriorityQueue<>((a, b) -> vals[b] - vals[a]));
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).offer(v);
                graph.get(v).offer(u);
            }

            int maximumSum = Integer.MIN_VALUE;
            for(var entry : graph.entrySet()) {
                int u = entry.getKey();
                var neighbours = entry.getValue();

                int sum = vals[u];
                maximumSum = Math.max(maximumSum, sum);

                for(int count = 1; count <= k && !neighbours.isEmpty(); count++) {
                    int v = neighbours.poll();
                    sum += vals[v];
                    maximumSum = Math.max(maximumSum, sum);
                }
            }

            return maximumSum;
        }
    }

// [1,2,3,4,10,-10,-20]
// [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]]
// 2

// [-5]
// []
// 0

// [6,-27,-44,-64,59,-7,21,-57]
// [[7,5],[4,0],[4,3],[6,1],[0,2],[7,3],[0,3],[0,5],[1,3],[4,2],[3,6],[0,1],[2,1],[5,4],[5,6],[5,2],[6,4],[5,1],[6,0],[6,7],[0,7],[3,5]]
// 5
}

//    2497. Maximum Star Sum of a Graph
//    Medium
//    There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
//
//    You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
//
//    A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
//
//    The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
//
//
//    The star sum is the sum of the values of all the nodes present in the star graph.
//
//    Given an integer k, return the maximum star sum of a star graph containing at most k edges.
//
//
//
//    Example 1:
//
//
//    Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
//    Output: 16
//    Explanation: The above diagram represents the input graph.
//    The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
//    It can be shown it is not possible to get a star graph with a sum greater than 16.
//    Example 2:
//
//    Input: vals = [-5], edges = [], k = 0
//    Output: -5
//    Explanation: There is only one possible star graph, which is node 0 itself.
//    Hence, we return -5.
//
//
//    Constraints:
//
//    n == vals.length
//    1 <= n <= 105
//    -104 <= vals[i] <= 104
//    0 <= edges.length <= min(n * (n - 1) / 2, 105)
//    edges[i].length == 2
//    0 <= ai, bi <= n - 1
//    ai != bi
//    0 <= k <= n - 1