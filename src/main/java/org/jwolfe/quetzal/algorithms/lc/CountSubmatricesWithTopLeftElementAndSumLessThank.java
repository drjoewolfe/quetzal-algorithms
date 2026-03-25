package org.jwolfe.quetzal.algorithms.lc;

public class CountSubmatricesWithTopLeftElementAndSumLessThank {
    class Solution {
        public int countSubmatrices(int[][] grid, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] sums = new int[m][n];
            sums[0][0] = grid[0][0];

            for (int r = 1; r < m; r++) {
                sums[r][0] = sums[r - 1][0] + grid[r][0];
            }

            for (int c = 1; c < n; c++) {
                sums[0][c] = sums[0][c - 1] + grid[0][c];
            }

            for (int r = 1; r < m; r++) {
                for (int c = 1; c < n; c++) {
                    sums[r][c] = grid[r][c] + sums[r - 1][c] + sums[r][c - 1] - sums[r - 1][c - 1];
                }
            }

            int count = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (sums[r][c] <= k) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    3070. Count Submatrices with Top-Left Element and Sum Less Than k
//    Medium
//    You are given a 0-indexed integer matrix grid and an integer k.
//
//    Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[7,6,3],[6,6,1]], k = 18
//    Output: 4
//    Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.
//    Example 2:
//
//
//    Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
//    Output: 6
//    Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 20.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= n, m <= 1000
//    0 <= grid[i][j] <= 1000
//    1 <= k <= 109