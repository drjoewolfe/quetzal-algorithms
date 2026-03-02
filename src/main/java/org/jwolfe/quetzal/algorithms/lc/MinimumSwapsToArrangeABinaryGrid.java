package org.jwolfe.quetzal.algorithms.lc;

public class MinimumSwapsToArrangeABinaryGrid {
    class Solution {
        public int minSwaps(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int n = grid.length;

            int[] endZeros = new int[n];
            for (int r = 0; r < n; r++) {
                for (int c = n - 1; c >= 0; c--) {
                    if (grid[r][c] == 0) {
                        endZeros[r]++;
                    } else {
                        break;
                    }
                }
            }

            int steps = 0;
            for (int i = 0; i < n; i++) {
                int need = n - i - 1;

                int j = i;
                while (j < n && endZeros[j] < need) {
                    j++;
                }

                if (j == n) {
                    // No upstream rows to satisfy need
                    return -1;
                }

                steps += (j - i);

                while (j > i) {
                    int temp = endZeros[j];
                    endZeros[j] = endZeros[j - 1];
                    endZeros[j - 1] = temp;

                    j--;
                }
            }

            return steps;
        }
    }
}

//    1536. Minimum Swaps to Arrange a Binary Grid
//    Medium
//    Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
//
//    A grid is said to be valid if all the cells above the main diagonal are zeros.
//
//    Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
//
//    The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
//    Output: 3
//    Example 2:
//
//
//    Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
//    Output: -1
//    Explanation: All rows are similar, swaps have no effect on the grid.
//    Example 3:
//
//
//    Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
//    Output: 0
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    1 <= n <= 200
//    grid[i][j] is either 0 or 1