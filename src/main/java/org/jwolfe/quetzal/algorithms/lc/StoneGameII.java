package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class StoneGameII {
    class Solution {
        public int stoneGameII(int[] piles) {
            if (piles == null || piles.length == 0) {
                return 0;
            }

            int n = piles.length;
            int[] suffixSums = new int[n];
            suffixSums[n - 1] = piles[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suffixSums[i] = piles[i] + suffixSums[i + 1];
            }

            Integer[][] memo = new Integer[n][n];

            return stoneGameII(piles, 0, 1, suffixSums, memo);
        }

        private int stoneGameII(int[] piles, int index, int m, int[] suffixSums, Integer[][] memo) {
            int maxRange = index + 2 * m;
            System.out.println(maxRange + ", " + index);
            if (maxRange >= piles.length) {
                // Can take all remaining
                return suffixSums[index];
            }

            if (memo[index][m] != null) {
                return memo[index][m];
            }

            int minOpponentScore = Integer.MAX_VALUE;
            for (int x = 1; x <= 2 * m; x++) {
                int i = index + x - 1;
                int opponentScore = stoneGameII(piles, i + 1, Math.max(x, m), suffixSums, memo);
                minOpponentScore = Math.min(minOpponentScore, opponentScore);
            }

            int maxScore = suffixSums[index] - minOpponentScore;
            return memo[index][m] = maxScore;
        }
    }

    class Solution_Recursive_TLE {
        public int stoneGameII(int[] piles) {
            if (piles == null || piles.length == 0) {
                return 0;
            }

            int n = piles.length;
            int[] suffixSums = new int[n];
            suffixSums[n - 1] = piles[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suffixSums[i] = piles[i] + suffixSums[i + 1];
            }

            return stoneGameII(piles, 0, 1, suffixSums);
        }

        private int stoneGameII(int[] piles, int index, int m, int[] suffixSums) {
            int maxRange = index + 2 * m;
            System.out.println(maxRange + ", " + index);
            if (maxRange >= piles.length) {
                // Can take all remaining
                return suffixSums[index];
            }

            int minOpponentScore = Integer.MAX_VALUE;
            for (int x = 1; x <= 2 * m; x++) {
                int i = index + x - 1;
                int opponentScore = stoneGameII(piles, i + 1, Math.max(x, m), suffixSums);
                minOpponentScore = Math.min(minOpponentScore, opponentScore);
            }

            int maxScore = suffixSums[index] - minOpponentScore;
            return maxScore;
        }
    }

    class Solution_Correct_1 {
        public int stoneGameII(int[] piles) {
            if (piles == null || piles.length == 0) {
                return 0;
            }

            int n = piles.length;
            int[] suffixSums = Arrays.copyOf(piles, n);
            for (int i = n - 2; i >= 0; i--) {
                suffixSums[i] += suffixSums[i + 1];
            }

            int[][] memo = new int[n][n];
            return stoneGameII(suffixSums, 0, 1, memo);
        }

        private int stoneGameII(int[] suffixSums, int index, int m, int[][] memo) {
            int maxIndex = index + 2 * m;
            if (maxIndex >= suffixSums.length) {
                return suffixSums[index];
            }

            if (memo[index][m] != 0) {
                return memo[index][m];
            }

            int minNextScore = Integer.MAX_VALUE;
            for (int x = 1; x <= 2 * m; x++) {
                int nextScore = stoneGameII(suffixSums, index + x, Math.max(x, m), memo);
                minNextScore = Math.min(minNextScore, nextScore);
            }

            int maxCurrScore = suffixSums[index] - minNextScore;
            return memo[index][m] = maxCurrScore;
        }
    }
}

//    1140. Stone Game II
//    Medium
//    Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
//
//    Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
//
//    On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
//
//    The game continues until all the stones have been taken.
//
//    Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
//
//
//
//    Example 1:
//
//    Input: piles = [2,7,9,4,4]
//    Output: 10
//    Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
//    Example 2:
//
//    Input: piles = [1,2,3,4,5,100]
//    Output: 104
//
//
//    Constraints:
//
//    1 <= piles.length <= 100
//    1 <= piles[i] <= 104