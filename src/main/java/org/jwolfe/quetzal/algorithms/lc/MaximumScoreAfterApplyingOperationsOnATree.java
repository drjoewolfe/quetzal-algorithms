package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumScoreAfterApplyingOperationsOnATree {
    class Solution {
        public long maximumScoreAfterOperations(int[][] edges, int[] values) {
            if(edges == null || edges.length == 0 || values == null || values.length == 0) {
                return 0;
            }

            int n = values.length;

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for(int u = 0; u < n; u++) {
                graph.put(u, new HashSet<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            long[] maxScores = new long[n];
            dfs(graph, values, maxScores, 0, -1);

            return maxScores[0];
        }

        private long[] dfs(Map<Integer, Set<Integer>> graph, int[] values, long[] maxScores, int u, int p) {
            int nodeScore = values[u];

            long minSubTreeScore = 0;
            long totalTreeScore = nodeScore;
            for(int v : graph.get(u)) {
                if(v == p) {
                    continue;
                }

                long[] scores = dfs(graph, values, maxScores, v, u);
                minSubTreeScore += scores[0];
                totalTreeScore += scores[1];
            }

            if(minSubTreeScore == 0) {
                // Leaf node.
                // Cannot take, as tree rooted here will be unhealthy
                maxScores[u] = 0;

                // return -> {minSubTreeScore, totalTreeScore}
                return new long[] {nodeScore, nodeScore};
            }


            if(nodeScore > minSubTreeScore) {
                // Take the node; min nodes from subtree will maintain health
                maxScores[u] = totalTreeScore - minSubTreeScore;
            } else {
                // Better to keep the node & take the min nodes from the subtree
                maxScores[u] = totalTreeScore - nodeScore;
            }

            return new long[] {Math.min(nodeScore, minSubTreeScore), totalTreeScore};
        }
    }

// [[0,1],[0,2],[0,3],[2,4],[4,5]]
// [5,2,5,2,1,1]

// [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
// [20,10,9,7,4,3,5]

// [[0,1],[0,2],[0,3]]
// [1000000000,1000000000,1000000000,1000000000]

// [[7,0],[3,1],[6,2],[4,3],[4,5],[4,6],[4,7]]
// [2,16,23,17,22,21,8,6]
}

//    2925. Maximum Score After Applying Operations on a Tree
//    Medium
//    There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
//
//    You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node.
//
//    You start with a score of 0. In one operation, you can:
//
//    Pick any node i.
//    Add values[i] to your score.
//    Set values[i] to 0.
//    A tree is healthy if the sum of values on the path from the root to any leaf node is different than zero.
//
//    Return the maximum score you can obtain after performing these operations on the tree any number of times so that it remains healthy.
//
//
//
//    Example 1:
//
//
//    Input: edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
//    Output: 11
//    Explanation: We can choose nodes 1, 2, 3, 4, and 5. The value of the root is non-zero. Hence, the sum of values on the path from the root to any leaf is different than zero. Therefore, the tree is healthy and the score is values[1] + values[2] + values[3] + values[4] + values[5] = 11.
//    It can be shown that 11 is the maximum score obtainable after any number of operations on the tree.
//    Example 2:
//
//
//    Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
//    Output: 40
//    Explanation: We can choose nodes 0, 2, 3, and 4.
//    - The sum of values on the path from 0 to 4 is equal to 10.
//    - The sum of values on the path from 0 to 3 is equal to 10.
//    - The sum of values on the path from 0 to 5 is equal to 3.
//    - The sum of values on the path from 0 to 6 is equal to 5.
//    Therefore, the tree is healthy and the score is values[0] + values[2] + values[3] + values[4] = 40.
//    It can be shown that 40 is the maximum score obtainable after any number of operations on the tree.
//
//
//    Constraints:
//
//    2 <= n <= 2 * 104
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    values.length == n
//    1 <= values[i] <= 109
//    The input is generated such that edges represents a valid tree.