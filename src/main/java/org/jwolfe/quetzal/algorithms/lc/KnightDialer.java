package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class KnightDialer {
    class Solution {
        private int MOD = 1_000_000_007;

        public int knightDialer(int n) {
            if(n < 1) {
                return 0;
            }

            int[][] jumps = new int[][] {
                    {4, 6},
                    {6, 8},
                    {7, 9},
                    {4, 8},
                    {3, 9, 0},
                    {},
                    {1, 7, 0},
                    {2, 6},
                    {1, 3},
                    {2, 4}
            };

            int[][] dp = new int[n + 1][10];
            for(int j = 0; j < 10; j++) {
                dp[1][j] = 1;
            }

            for(int i = 2; i <= n; i++) {
                for(int j = 0; j < 10; j++) {
                    for(int jump : jumps[j]) {
                        dp[i][j] += dp[i - 1][jump];
                        dp[i][j] %= MOD;
                    }
                }
            }

            int count = 0;
            for(int i = 0; i < 10; i++) {
                count += dp[n][i];
                count %= MOD;
            }

            return count;
        }
    }

    class Solution_Correct_Memo {
        private int MOD = 1_000_000_007;
        public int knightDialer(int n) {
            if(n < 1) {
                return 0;
            }

            if(n == 1) {
                return 10;
            }

            int[][] moves = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};

            long[][] memo = new long[n][10];
            for(long[] row : memo) {
                Arrays.fill(row, -1);
            }

            for(int i = 0; i < 10; i++) {
                memo[0][i] = 1;
            }

            long count = 0;
            for(int i = 0; i < 10; i++) {
                count += computeKnightDialerNumbers(n - 1, i, moves, memo);
            }

            return (int) (count % MOD);
        }

        private long computeKnightDialerNumbers(int n, int number, int[][] moves, long[][] memo) {
            if(memo[n][number] != -1) {
                return memo[n][number];
            }

            int count = 0;
            for(int next : moves[number]) {
                count += computeKnightDialerNumbers(n - 1, next, moves, memo);
                count %= MOD;
            }

            memo[n][number] = count;
            return count;
        }
    }

    class Solution_TLE {
        public int knightDialer(int n) {
            if(n < 1) {
                return 0;
            }

            if(n == 1) {
                return 10;
            }

            int[][] moves = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};

            int count = 0;
            for(int i = 0; i < 10; i++) {
                count += computeKnightDialerNumbers(n - 1, i, moves);
            }

            return count;
        }

        private int computeKnightDialerNumbers(int n, int number, int[][] moves) {
            if(n == 0) {
                return 1;
            }

            int count = 0;
            for(int next : moves[number]) {
                count += computeKnightDialerNumbers(n - 1, next, moves);
            }

            return count;
        }
    }

// 1
// 2
// 3
// 4
// 5
// 3131
}

//    935. Knight Dialer
//    Medium
//    The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:
//
//    A chess knight can move as indicated in the chess diagram below:
//
//
//    We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
//
//
//    Given an integer n, return how many distinct phone numbers of length n we can dial.
//
//    You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
//
//    As the answer may be very large, return the answer modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 1
//    Output: 10
//    Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
//    Example 2:
//
//    Input: n = 2
//    Output: 20
//    Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
//    Example 3:
//
//    Input: n = 3131
//    Output: 136006598
//    Explanation: Please take care of the mod.
//
//
//    Constraints:
//
//    1 <= n <= 5000