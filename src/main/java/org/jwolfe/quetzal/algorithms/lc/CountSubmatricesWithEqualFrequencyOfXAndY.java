package org.jwolfe.quetzal.algorithms.lc;

public class CountSubmatricesWithEqualFrequencyOfXAndY {
    class Solution {
        public int numberOfSubmatrices(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] xCounts = new int[m][n];
            int[][] yCounts = new int[m][n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (r > 0) {
                        xCounts[r][c] += xCounts[r - 1][c];
                        yCounts[r][c] += yCounts[r - 1][c];
                    }

                    if (c > 0) {
                        xCounts[r][c] += xCounts[r][c - 1];
                        yCounts[r][c] += yCounts[r][c - 1];
                    }

                    if (r > 0 && c > 0) {
                        xCounts[r][c] -= xCounts[r - 1][c - 1];
                        yCounts[r][c] -= yCounts[r - 1][c - 1];
                    }

                    if (grid[r][c] == 'X') {
                        xCounts[r][c]++;
                    } else if (grid[r][c] == 'Y') {
                        yCounts[r][c]++;
                    }
                }
            }

            int count = 0;

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int xCount = xCounts[r][c];
                    int yCount = yCounts[r][c];

                    if (xCount > 0 && xCount == yCount) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    3212. Count Submatrices With Equal Frequency of X and Y
//    Medium
//    Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:
//
//    grid[0][0]
//    an equal frequency of 'X' and 'Y'.
//    at least one 'X'.
//
//
//    Example 1:
//
//    Input: grid = [["X","Y","."],["Y",".","."]]
//
//    Output: 3
//
//    Explanation:
//
//
//
//    Example 2:
//
//    Input: grid = [["X","X"],["X","Y"]]
//
//    Output: 0
//
//    Explanation:
//
//    No submatrix has an equal frequency of 'X' and 'Y'.
//
//    Example 3:
//
//    Input: grid = [[".","."],[".","."]]
//
//    Output: 0
//
//    Explanation:
//
//    No submatrix has at least one 'X'.
//
//
//
//    Constraints:
//
//    1 <= grid.length, grid[i].length <= 1000
//    grid[i][j] is either 'X', 'Y', or '.'.