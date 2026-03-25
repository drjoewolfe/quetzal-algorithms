package org.jwolfe.quetzal.algorithms.lc;

public class FlipSquareSubmatrixVertically {
    class Solution {
        public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return grid;
            }

            int m = grid.length;
            int n = grid[0].length;

            if (x < 0 || x >= m || y < 0 || y >= n || k < 1) {
                return grid;
            }

            int r1 = x;
            int c1 = y;

            int r2 = x + k - 1;
            int c2 = y + k - 1;

            while (r1 < r2) {
                swapRows(grid, r1, c1, r2, c2);

                r1++;
                r2--;
            }

            return grid;
        }

        private void swapRows(int[][] grid, int r1, int c1, int r2, int c2) {
            for (int c = c1; c <= c2; c++) {
                int temp = grid[r1][c];
                grid[r1][c] = grid[r2][c];
                grid[r2][c] = temp;
            }
        }
    }
}

//    3643. Flip Square Submatrix Vertically
//    Easy
//    You are given an m x n integer matrix grid, and three integers x, y, and k.
//
//    The integers x and y represent the row and column indices of the top-left corner of a square submatrix and the integer k represents the size (side length) of the square submatrix.
//
//    Your task is to flip the submatrix by reversing the order of its rows vertically.
//
//    Return the updated matrix.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], x = 1, y = 0, k = 3
//
//    Output: [[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]
//
//    Explanation:
//
//    The diagram above shows the grid before and after the transformation.
//
//    Example 2:
//
//    ​​​​​​​
//    Input: grid = [[3,4,2,3],[2,3,4,2]], x = 0, y = 2, k = 2
//
//    Output: [[3,4,4,2],[2,3,2,3]]
//
//    Explanation:
//
//    The diagram above shows the grid before and after the transformation.
//
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 50
//    1 <= grid[i][j] <= 100
//    0 <= x < m
//    0 <= y < n
//    1 <= k <= min(m - x, n - y)