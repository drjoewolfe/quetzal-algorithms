package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class EqualSumGridPartitionII {
    class Solution {
        public boolean canPartitionGrid(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            long totalSum = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    totalSum += grid[r][c];
                }
            }

            if (checkHorizontalCuts(grid, totalSum)) {
                return true;
            }

            reverse(grid);

            if (checkHorizontalCuts(grid, totalSum)) {
                return true;
            }

            grid = transpose(grid);

            if (checkHorizontalCuts(grid, totalSum)) {
                return true;
            }

            reverse(grid);

            if (checkHorizontalCuts(grid, totalSum)) {
                return true;
            }

            return false;
        }

        private void reverse(int[][] grid) {
            int m = grid.length;

            for (int r = 0; r < m / 2; r++) {
                int br = m - r - 1;

                swap(grid, r, br);
            }
        }

        private void swap(int[][] grid, int row1, int row2) {
            int n = grid[0].length;

            int[] temp = grid[row1];
            grid[row1] = grid[row2];
            grid[row2] = temp;
        }

        private int[][] transpose(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[][] transpose = new int[n][m];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    transpose[c][r] = grid[r][c];
                }
            }

            return transpose;
        }

        private boolean checkHorizontalCuts(int[][] grid, long totalSum) {
            int m = grid.length;
            int n = grid[0].length;

            Set<Long> set = new HashSet<>();
            long topSum = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    long val = grid[r][c];
                    set.add(val);
                    topSum += val;
                }

                long bottomSum = totalSum - topSum;
                long diff = topSum - bottomSum;

                if (diff == 0) {
                    return true;
                }

                if (set.contains(diff) && r > 0 && n > 1) {
                    return true;
                }

                if (diff == grid[0][0]) {
                    return true;
                }

                if (diff == grid[0][n - 1]) {
                    return true;
                }

                if (diff == grid[r][0]) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    3548. Equal Sum Grid Partition II
//    Hard
//    You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
//
//    Each of the two resulting sections formed by the cut is non-empty.
//    The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
//    If a cell is discounted, the rest of the section must remain connected.
//    Return true if such a partition exists; otherwise, return false.
//
//    Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.
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
//    A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.
//    Example 2:
//
//    Input: grid = [[1,2],[3,4]]
//
//    Output: true
//
//    Explanation:
//
//
//
//    A vertical cut after the first column gives sums 1 + 3 = 4 and 2 + 4 = 6.
//    By discounting 2 from the right section (6 - 2 = 4), both sections have equal sums and remain connected. Thus, the answer is true.
//    Example 3:
//
//    Input: grid = [[1,2,4],[2,3,5]]
//
//    Output: false
//
//    Explanation:
//
//
//
//    A horizontal cut after the first row gives 1 + 2 + 4 = 7 and 2 + 3 + 5 = 10.
//    By discounting 3 from the bottom section (10 - 3 = 7), both sections have equal sums, but they do not remain connected as it splits the bottom section into two parts ([2] and [5]). Thus, the answer is false.
//    Example 4:
//
//    Input: grid = [[4,1,8],[3,2,6]]
//
//    Output: false
//
//    Explanation:
//
//    No valid cut exists, so the answer is false.
//
//
//
//    Constraints:
//
//    1 <= m == grid.length <= 105
//    1 <= n == grid[i].length <= 105
//    2 <= m * n <= 105
//    1 <= grid[i][j] <= 105