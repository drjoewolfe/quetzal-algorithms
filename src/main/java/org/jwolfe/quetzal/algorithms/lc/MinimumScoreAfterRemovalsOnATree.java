package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumScoreAfterRemovalsOnATree {
    class Solution {
        private int timer;

        public int minimumScore(int[] nums, int[][] edges) {
            if (nums == null || nums.length == 0 || edges == null || edges.length == 0) {
                return 0;
            }

            int n = nums.length;

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int u = 0; u < n; u++) {
                graph.put(u, new ArrayList<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int totalXor = 0;
            for (int val : nums) {
                totalXor ^= val;
            }

            int[] subTreeXor = new int[n];
            int[] inTime = new int[n];
            int[] outTime = new int[n];

            timer = 0;
            dfs(graph, nums, 0, -1, subTreeXor, inTime, outTime);

            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < edges.length; i++) {
                for (int j = i + 1; j < edges.length; j++) {
                    int[] edge1 = edges[i];
                    int[] edge2 = edges[j];

                    int u1 = edge1[0];
                    int v1 = edge1[1];

                    int u2 = edge2[0];
                    int v2 = edge2[1];

                    if (inTime[u1] > inTime[v1]) {
                        int temp = u1;
                        u1 = v1;
                        v1 = temp;
                    }

                    if (inTime[u2] > inTime[v2]) {
                        int temp = u2;
                        u2 = v2;
                        v2 = temp;
                    }

                    int xor1 = 0;
                    int xor2 = 0;
                    int xor3 = 0;

                    if (isAncestor(v1, v2, inTime, outTime)) {
                        xor1 = subTreeXor[v2];
                        xor2 = subTreeXor[v1] ^ subTreeXor[v2];
                        xor3 = totalXor ^ subTreeXor[v1];

                    } else if (isAncestor(v2, v1, inTime, outTime)) {
                        xor1 = subTreeXor[v1];
                        xor2 = subTreeXor[v2] ^ subTreeXor[v1];
                        xor3 = totalXor ^ subTreeXor[v2];
                    } else {
                        xor1 = subTreeXor[v1];
                        xor2 = subTreeXor[v2];
                        xor3 = totalXor ^ subTreeXor[v1] ^ subTreeXor[v2];
                    }

                    int maxXor = Math.max(xor1, Math.max(xor2, xor3));
                    int minXor = Math.min(xor1, Math.min(xor2, xor3));
                    int score = maxXor - minXor;
                    minScore = Math.min(minScore, score);
                }
            }

            return minScore;
        }

        private void dfs(Map<Integer, List<Integer>> graph, int[] nums, int u, int p, int[] subTreeXor, int[] inTime, int[] outTime) {
            inTime[u] = timer++;
            subTreeXor[u] = nums[u];

            for (int v : graph.get(u)) {
                if (v == p) {
                    continue;
                }

                dfs(graph, nums, v, u, subTreeXor, inTime, outTime);
                subTreeXor[u] ^= subTreeXor[v];
            }

            outTime[u] = timer++;
        }

        private boolean isAncestor(int u, int v, int[] inTime, int[] outTime) {
            return (inTime[u] < inTime[v]) && (outTime[u] > outTime[v]);
        }
    }
}

//    2322. Minimum Score After Removals on a Tree
//    Hard
//    There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
//
//    You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
//
//    Remove two distinct edges of the tree to form three connected components. For a pair of removed edges, the following steps are defined:
//
//    Get the XOR of all the values of the nodes for each of the three components respectively.
//    The difference between the largest XOR value and the smallest XOR value is the score of the pair.
//    For example, say the three components have the node values: [4,5,7], [1,9], and [3,3,3]. The three XOR values are 4 ^ 5 ^ 7 = 6, 1 ^ 9 = 8, and 3 ^ 3 ^ 3 = 3. The largest XOR value is 8 and the smallest XOR value is 3. The score is then 8 - 3 = 5.
//    Return the minimum score of any possible pair of edge removals on the given tree.
//
//
//
//    Example 1:
//
//
//    Input: nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
//    Output: 9
//    Explanation: The diagram above shows a way to make a pair of removals.
//    - The 1st component has nodes [1,3,4] with values [5,4,11]. Its XOR value is 5 ^ 4 ^ 11 = 10.
//    - The 2nd component has node [0] with value [1]. Its XOR value is 1 = 1.
//    - The 3rd component has node [2] with value [5]. Its XOR value is 5 = 5.
//    The score is the difference between the largest and smallest XOR value which is 10 - 1 = 9.
//    It can be shown that no other pair of removals will obtain a smaller score than 9.
//    Example 2:
//
//
//    Input: nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
//    Output: 0
//    Explanation: The diagram above shows a way to make a pair of removals.
//    - The 1st component has nodes [3,4] with values [4,4]. Its XOR value is 4 ^ 4 = 0.
//    - The 2nd component has nodes [1,0] with values [5,5]. Its XOR value is 5 ^ 5 = 0.
//    - The 3rd component has nodes [2,5] with values [2,2]. Its XOR value is 2 ^ 2 = 0.
//    The score is the difference between the largest and smallest XOR value which is 0 - 0 = 0.
//    We cannot obtain a smaller score than 0.
//
//
//    Constraints:
//
//    n == nums.length
//    3 <= n <= 1000
//    1 <= nums[i] <= 108
//    edges.length == n - 1
//    edges[i].length == 2
//    0 <= ai, bi < n
//    ai != bi
//    edges represents a valid tree.