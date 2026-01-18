package org.jwolfe.quetzal.algorithms.lc;

public class LargestMagicSquare {
    class Solution {
        public int largestMagicSquare(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            for (int l = Math.min(m, n); l > 1; l--) {
                for (int r = 0; r + l <= m; r++) {
                    for (int c = 0; c + l <= n; c++) {
                        if (isMagicSquare(grid, r, c, l)) {
                            return l;
                        }
                    }
                }

            }

            return 1;
        }

        private boolean isMagicSquare(int[][] grid, int row, int col, int length) {
            int checkSum = 0;
            for (int c = col; c < col + length; c++) {
                checkSum += grid[row][c];
            }

            // Check Rows
            for (int r = row + 1; r < row + length; r++) {
                int sum = 0;
                for (int c = col; c < col + length; c++) {
                    sum += grid[r][c];
                }

                if (sum != checkSum) {
                    return false;
                }
            }

            // Check Columns
            for (int c = col; c < col + length; c++) {
                int sum = 0;
                for (int r = row; r < row + length; r++) {
                    sum += grid[r][c];
                }

                if (sum != checkSum) {
                    return false;
                }
            }

            // Check Diagonals
            int d1 = 0;
            int d2 = 0;

            for (int i = 0; i < length; i++) {
                d1 += grid[row + i][col + i];
                d2 += grid[row + i][col + length - 1 - i];
            }

            if (d1 != checkSum || d2 != checkSum) {
                return false;
            }

            return true;
        }
    }
}

//    1895. Largest Magic Square
//    Medium
//    A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.
//
//    Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
//    Output: 3
//    Explanation: The largest magic square has a size of 3.
//    Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
//    - Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
//    - Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
//    - Diagonal sums: 5+4+3 = 6+4+2 = 12
//    Example 2:
//
//
//    Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
//    Output: 2
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 50
//    1 <= grid[i][j] <= 106