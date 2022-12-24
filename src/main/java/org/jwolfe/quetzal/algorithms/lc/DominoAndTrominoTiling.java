package org.jwolfe.quetzal.algorithms.lc;

public class DominoAndTrominoTiling {
    class Solution {
        int MOD = 1_000_000_007;

        public int numTilings(int n) {
            if(n < 0) {
                return 0;
            }

            if(n < 3) {
                return n;
            }

            long[] dp = new long[n + 1];

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 5;

            for(int i = 4; i <= n; i++) {
                dp[i] = 2 * dp[i - 1] + dp[i - 3];
                dp[i] %= MOD;
            }

            return (int) (dp[n] % MOD);
        }
    }

    class Solution_Incorrect {
        public int numTilings(int n) {
            if(n <= 0) {
                return 0;
            }

            if(n < 3) {
                return n;
            }

            if(n == 3) {
                return 5;
            }

            int ways = 0;

            // One
            ways += numTilings(n - 1);

            // Two
            ways += 2 * numTilings(n - 2);

            // Three
            ways += 5 * numTilings(n - 3);

            return ways;
        }
    }
}

//    790. Domino and Tromino Tiling
//    Medium
//    You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
//
//
//    Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
//
//    In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
//
//
//
//    Example 1:
//
//
//    Input: n = 3
//    Output: 5
//    Explanation: The five different ways are show above.
//    Example 2:
//
//    Input: n = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= n <= 1000
