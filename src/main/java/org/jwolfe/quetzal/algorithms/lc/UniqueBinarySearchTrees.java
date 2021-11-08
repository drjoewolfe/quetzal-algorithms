package org.jwolfe.quetzal.algorithms.lc;

public class UniqueBinarySearchTrees {
    class Solution {
        public int numTrees(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 3) {
                return n;
            }

            int[] ways = new int[n + 1];
            ways[0] = 1;
            ways[1] = 1;
            ways[2] = 2;

            for(int i = 3; i <= n; i++) {
                for(int root = 1; root <= i; root++) {
                    int left = root - 1;
                    int right = i - root;

                    int leftWays = ways[left];
                    int rightWays = ways[right];

                    ways[i] += (leftWays * rightWays);
                }

            }

            return ways[n];
        }
    }

    class Solution_Correct_1 {
        public int numTrees(int n) {
            if(n < 3) {
                return n;
            }

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;

            for(int i = 3; i <= n; i++) {
                int orientations = 0;
                for(int j = 1; j <= i; j++) {
                    int left = j - 1;
                    int right = i - j;

                    orientations += (dp[left] * dp[right]);
                }

                dp[i] = orientations;
            }

            return dp[n];
        }
    }
}

//    96. Unique Binary Search Trees
//    Medium
//    Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
//
//
//
//    Example 1:
//
//
//    Input: n = 3
//    Output: 5
//    Example 2:
//
//    Input: n = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= n <= 19