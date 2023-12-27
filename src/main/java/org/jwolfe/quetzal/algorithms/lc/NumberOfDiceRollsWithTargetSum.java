package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NumberOfDiceRollsWithTargetSum {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numRollsToTarget(int n, int k, int target) {
            if(n < 1 || k < 1 || target < n) {
                return 0;
            }

            int[][] dp = new int[n + 1][target + 1];

            // 1 Dice
            for(int sum = 1; sum <= Math.min(k, target); sum++) {
                dp[1][sum] = 1;
            }

            // 2+ Dices
            for(int dice = 2; dice <= n; dice++) {
                for(int sum = 1; sum <= target; sum++) {

                    for(int face = 1; face <= k; face++) {
                        if(sum >= face) {
                            dp[dice][sum] += dp[dice - 1][sum - face];
                            dp[dice][sum] %= MOD;
                        }
                    }
                }
            }

            return dp[n][target];
        }
    }

    class Solution_Correct_2 {

        private int MOD = 1_000_000_007;

        public int numRollsToTarget(int d, int f, int target) {
            if(d < 1 || f < 1 || target < 1) {
                return 0;
            }

            int[][] dp = new int[d + 1][target + 1];
            dp[0][0] = 1;

            // one dice
            for(int j = 1; j <= Math.min(f, target); j++) {
                dp[1][j] = 1;
            }

            // remaining dices
            for(int dice = 2; dice <= d; dice++) {
                for(int t = 1; t <= target; t++) {

                    for(int a = 1; a <= f; a++) {
                        if(t >= a) {
                            dp[dice][t] += dp[dice - 1][t - a];
                            dp[dice][t] %= MOD;
                        }
                    }

                }
            }

            return dp[d][target] % MOD;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        int mod = 1_000_000_007;

        public int numRollsToTarget(int d, int f, int target) {
            if(d < 1 || f < 1 || target < 1) {
                return 0;
            }

            int[][] memo = new int[d + 1][target + 1];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return numRollsToTarget(d, f, target, 1, memo);
        }

        private int numRollsToTarget(int d, int f, int target, int dice, int[][] memo) {
            if(dice == d) {
                if(target <= f) {
                    return 1;
                }

                return 0;
            }

            if(memo[dice][target] != -1) {
                return memo[dice][target];
            }

            int ways = 0;
            for(int v = 1; v <= f; v++) {
                if(v > target) {
                    break;
                }

                // ways += (numRollsToTarget(d, f, target - v, dice + 1, memo) % mod);
                // ways %= mod;
                ways += (numRollsToTarget(d, f, target - v, dice + 1, memo));
            }

            memo[dice][target] = (int) ways;
            return memo[dice][target];
        }
    }

    class Solution_Recursive {
        int ways;
        int mod = 1_000_000_007;

        public int numRollsToTarget(int d, int f, int target) {
            if(d < 1 || f < 1 || target < 1) {
                return 0;
            }

            ways = 0;
            numRollsToTarget(d, f, target, 1);

            return ways;
        }

        private void numRollsToTarget(int d, int f, int target, int dice) {
            if(dice == d) {
                if(target <= f) {
                    ways++;
                    ways %= mod;
                }

                return;
            }


            for(int v = 1; v <= f; v++) {
                if(v > f) {
                    break;
                }

                numRollsToTarget(d, f, target - v, dice + 1);
            }
        }
    }

// 1
// 6
// 3

// 2
// 6
// 7

// 30
// 30
// 500
}

//    1155. Number of Dice Rolls With Target Sum
//    Medium
//    You have n dice and each die has k faces numbered from 1 to k.
//
//    Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 1, k = 6, target = 3
//    Output: 1
//    Explanation: You throw one die with 6 faces.
//    There is only one way to get a sum of 3.
//    Example 2:
//
//    Input: n = 2, k = 6, target = 7
//    Output: 6
//    Explanation: You throw two dice, each with 6 faces.
//    There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
//    Example 3:
//
//    Input: n = 30, k = 30, target = 500
//    Output: 222616187
//    Explanation: The answer must be returned modulo 109 + 7.
//
//
//    Constraints:
//
//    1 <= n, k <= 30
//    1 <= target <= 1000
