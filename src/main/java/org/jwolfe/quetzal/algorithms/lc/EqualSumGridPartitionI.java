package org.jwolfe.quetzal.algorithms.lc;

public class EqualSumGridPartitionI {
    class Solution {
        public boolean canPartitionGrid(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            long[] rowSums = new long[m];
            long[] colSums = new long[n];
            long totalSum = 0;

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int cell = grid[r][c];
                    rowSums[r] += cell;
                    colSums[c] += cell;
                    totalSum += cell;
                }
            }

            // Try cut with rows
            long topSum = rowSums[0];
            for (int r = 1; r < m; r++) {
                long bottomSum = totalSum - topSum;
                if (topSum == bottomSum) {
                    return true;
                }

                topSum += rowSums[r];
            }

            // Try cut with columns
            long leftSum = colSums[0];
            for (int c = 1; c < n; c++) {
                long rightSum = totalSum - leftSum;
                if (leftSum == rightSum) {
                    return true;
                }

                leftSum += colSums[c];
            }

            return false;
        }
    }
}

//    3546. Equal Sum Grid Partition I
//    Medium
//    You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
//
//    Each of the two resulting sections formed by the cut is non-empty.
//    The sum of the elements in both sections is equal.
//    Return true if such a partition exists; otherwise return false.
//
//
//
//    Example 1:
//
//    Input: grid = [[1,4],[2,3]]
//
//    Output: true
//
//    Explanation:
//
//
//
//    A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.
//
//    Example 2:
//
//    Input: grid = [[1,3],[2,4]]
//
//    Output: false
//
//    Explanation:
//
//    No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.
//
//
//
//    Constraints:
//
//    1 <= m == grid.length <= 105
//    1 <= n == grid[i].length <= 105
//    2 <= m * n <= 105
//    1 <= grid[i][j] <= 105