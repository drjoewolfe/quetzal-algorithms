package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfWaysToPaintNx3Grid {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numOfWays(int n) {
            // base case for n = 1
            long aba = 6;
            long abc = 6;

            for (int i = 1; i < n; i++) {
                long nextAba = (3 * aba + 2 * abc) % MOD;
                long nextAbc = (2 * aba + 2 * abc) % MOD;

                aba = nextAba;
                abc = nextAbc;
            }

            return (int) (aba + abc) % MOD;
        }
    }
}

//    1411. Number of Ways to Paint N × 3 Grid
//    Hard
//    You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).
//
//    Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.
//
//
//
//    Example 1:
//
//
//    Input: n = 1
//    Output: 12
//    Explanation: There are 12 possible way to paint the grid as shown.
//    Example 2:
//
//    Input: n = 5000
//    Output: 30228214
//
//
//    Constraints:
//
//    n == grid.length
//    1 <= n <= 5000
