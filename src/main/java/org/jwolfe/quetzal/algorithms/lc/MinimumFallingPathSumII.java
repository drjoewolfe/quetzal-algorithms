package org.jwolfe.quetzal.algorithms.lc;

public class MinimumFallingPathSumII {
    class Solution {
        public int minFallingPathSum(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] minPathSums = new int[m][n];
            for(int j = 0; j < n; j++) {
                minPathSums[0][j] = grid[0][j];
            }

            for(int i = 1; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int minPathTillJ = Integer.MAX_VALUE;
                    for(int aj = 0; aj < n; aj++) {
                        if(j == aj) {
                            continue;
                        }

                        minPathTillJ = Math.min(minPathTillJ, minPathSums[i  - 1][aj]);
                    }

                    minPathSums[i][j] = minPathTillJ + grid[i][j];
                }
            }

            int minPathSum = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                minPathSum = Math.min(minPathSum, minPathSums[m - 1][j]);
            }

            return minPathSum;
        }

        private void print(int[][] arr) {
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}

//    1289. Minimum Falling Path Sum II
//    Hard
//    Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
//
//    A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
//    Output: 13
//    Explanation:
//    The possible falling paths are:
//    [1,5,9], [1,5,7], [1,6,7], [1,6,8],
//    [2,4,8], [2,4,9], [2,6,7], [2,6,8],
//    [3,4,8], [3,4,9], [3,5,7], [3,5,9]
//    The falling path with the smallest sum is [1,5,7], so the answer is 13.
//    Example 2:
//
//    Input: grid = [[7]]
//    Output: 7
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    1 <= n <= 200
//    -99 <= grid[i][j] <= 99