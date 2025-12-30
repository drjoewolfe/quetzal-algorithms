package org.jwolfe.quetzal.algorithms.lc;

public class MagicSquaresInGrid {
    class Solution {
        public int numMagicSquaresInside(int[][] grid) {
            if (grid == null || grid.length < 3 || grid[0].length < 3) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int count = 0;
            for (int r = 0; r < m - 2; r++) {
                for (int c = 0; c < n - 2; c++) {
                    if (isMagicSquare(grid, r, c)) {
                        count++;
                    }
                }
            }

            return count;
        }

        private boolean isMagicSquare(int[][] grid, int row, int col) {
            boolean[] seen = new boolean[10];
            for (int r = row; r < row + 3; r++) {
                for (int c = col; c < col + 3; c++) {
                    int val = grid[r][c];

                    if (val < 1 || val > 9) {
                        return false;
                    }

                    if (seen[val]) {
                        return false;
                    }

                    seen[val] = true;
                }
            }

            int row1 = grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
            int row2 = grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 1][col + 2];
            int row3 = grid[row + 2][col] + grid[row + 2][col + 1] + grid[row + 2][col + 2];

            int col1 = grid[row][col] + grid[row + 1][col] + grid[row + 2][col];
            int col2 = grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 2][col + 1];
            int col3 = grid[row][col + 2] + grid[row + 1][col + 2] + grid[row + 2][col + 2];

            int diag1 = grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2];
            int diag2 = grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col];

            return (row1 == row2)
                    && (row1 == row3)
                    && (row1 == col1)
                    && (row1 == col2)
                    && (row1 == col3)
                    && (row1 == diag1)
                    && (row1 == diag2);
        }
    }
}

//    840. Magic Squares In Grid
//    Medium
//    A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
//
//    Given a row x col grid of integers, how many 3 x 3 magic square subgrids are there?
//
//    Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
//    Output: 1
//    Explanation:
//    The following subgrid is a 3 x 3 magic square:
//
//    while this one is not:
//
//    In total, there is only one magic square inside the given grid.
//    Example 2:
//
//    Input: grid = [[8]]
//    Output: 0
//
//
//    Constraints:
//
//    row == grid.length
//    col == grid[i].length
//    1 <= row, col <= 10
//    0 <= grid[i][j] <= 15