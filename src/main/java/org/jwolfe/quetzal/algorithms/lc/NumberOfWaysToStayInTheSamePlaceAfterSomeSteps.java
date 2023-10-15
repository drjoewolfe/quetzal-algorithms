package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    class Solution {
        int MOD = 1_000_000_007;

        public int numWays(int steps, int arrLen) {
            if(steps < 1 || arrLen < 1) {
                return 0;
            }

            arrLen = Math.min(steps, arrLen);
            long[] prev = new long[arrLen + 1];
            long[] curr = new long[arrLen + 1];

            prev[0] = 1;

            for(int step = 1; step <= steps; step++) {
                for(int index = 0; index < arrLen; index++) {
                    long leftWays = (index > 0) ? prev[index - 1] : 0;
                    long rightWays = (index < arrLen - 1) ? prev[index + 1] : 0;
                    long stayWays = prev[index];

                    long moveWays = leftWays + rightWays + stayWays;
                    curr[index] = moveWays % MOD;
                }

                // swap
                var temp = prev;
                prev = curr;
                curr = temp;
            }

            return (int) prev[0];
        }
    }

    class Solution_DP_MLE {
        int MOD = 1_000_000_007;

        public int numWays(int steps, int arrLen) {
            if(steps < 1 || arrLen < 1) {
                return 0;
            }

            long[][] dp = new long[steps + 1][arrLen + 1];
            dp[0][0] = 1;

            for(int step = 1; step <= steps; step++) {
                for(int index = 0; index < arrLen; index++) {
                    long leftWays = (index > 0) ? dp[step - 1][index - 1] : 0;
                    long rightWays = (index < arrLen - 1) ? dp[step - 1][index + 1] : 0;
                    long stayWays = dp[step - 1][index];

                    long moveWays = leftWays + rightWays + stayWays;
                    dp[step][index] = moveWays % MOD;
                }
            }

            return (int) dp[steps][0];
        }

        private void print(long[][] dp) {
            for(long[] row : dp) {
                for(long cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memoized_MLE {
        int MOD = 1_000_000_007;

        public int numWays(int steps, int arrLen) {
            if(steps < 1 || arrLen < 1) {
                return 0;
            }

            long[][] memo = new long[steps + 1][arrLen + 1];
            for(long[] row : memo) {
                Arrays.fill(row, -1);
            }

            return (int) numWays(steps, arrLen, 0, memo);
        }

        private long numWays(int steps, int arrLength, int index, long[][] memo) {
            if(steps == 0 && index == 0) {
                return 1;
            }

            if(steps == 0 || index < 0 || index >= arrLength) {
                return 0;
            }

            if(memo[steps][index] != -1) {
                return memo[steps][index];
            }

            long leftWays = numWays(steps - 1, arrLength, index - 1, memo) % MOD;
            long rightWays = numWays(steps - 1, arrLength, index + 1, memo) % MOD;
            long stayWays = numWays(steps - 1, arrLength, index, memo) % MOD;

            memo[steps][index] = (leftWays + rightWays + stayWays) % MOD;
            return memo[steps][index];
        }
    }

    class Solution_Recursive_TLE {
        int MOD = 1_000_000_007;

        public int numWays(int steps, int arrLen) {
            if(steps < 1 || arrLen < 1) {
                return 0;
            }

            return numWays(steps, arrLen, 0);
        }

        private int numWays(int steps, int arrLength, int index) {
            if(steps == 0 && index == 0) {
                return 1;
            }

            if(steps == 0 || index < 0 || index >= arrLength) {
                return 0;
            }

            int leftWays = numWays(steps - 1, arrLength, index - 1) % MOD;
            int rightWays = numWays(steps - 1, arrLength, index + 1) % MOD;
            int stayWays = numWays(steps - 1, arrLength, index) % MOD;

            return (leftWays + rightWays + stayWays) % MOD;
        }
    }

// 3
// 2

// 27
// 7

// 430
// 148488

// 500
// 969997
}

//    1269. Number of Ways to Stay in the Same Place After Some Steps
//    Hard
//    You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).
//
//    Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: steps = 3, arrLen = 2
//    Output: 4
//    Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
//    Right, Left, Stay
//    Stay, Right, Left
//    Right, Stay, Left
//    Stay, Stay, Stay
//    Example 2:
//
//    Input: steps = 2, arrLen = 4
//    Output: 2
//    Explanation: There are 2 differents ways to stay at index 0 after 2 steps
//    Right, Left
//    Stay, Stay
//    Example 3:
//
//    Input: steps = 4, arrLen = 2
//    Output: 8
//
//
//    Constraints:
//
//    1 <= steps <= 500
//    1 <= arrLen <= 106