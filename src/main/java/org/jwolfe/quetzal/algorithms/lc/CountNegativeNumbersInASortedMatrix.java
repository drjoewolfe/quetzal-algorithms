package org.jwolfe.quetzal.algorithms.lc;

public class CountNegativeNumbersInASortedMatrix {
    class Solution {
        public int countNegatives(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int positiveCount = 0;
            int c = n - 1;
            for (int r = 0; r < m; r++) {
                while (c >= 0 && grid[r][c] < 0) {
                    c--;
                }

                positiveCount += (c + 1);
            }

            return m * n - positiveCount;
        }
    }

    class Solution_Brute_1 {
        public int countNegatives(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int count = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] < 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    1351. Count Negative Numbers in a Sorted Matrix
//    Easy
//    Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
//
//
//
//    Example 1:
//
//    Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
//    Output: 8
//    Explanation: There are 8 negatives number in the matrix.
//    Example 2:
//
//    Input: grid = [[3,2],[1,0]]
//    Output: 0
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 100
//    -100 <= grid[i][j] <= 100
//
//
//    Follow up: Could you find an O(n + m) solution?