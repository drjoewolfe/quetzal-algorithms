package org.jwolfe.quetzal.algorithms.lc;

public class CountSquareSubmatricesWithAllOnes {
    class Solution {
        public int countSquares(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int count = 0;
            int[][] dp = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = matrix[i][j];
                    } else if (matrix[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1],
                                Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }

                    count += dp[i][j];
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int countSquares(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }

            int rowCount = matrix.length;
            int colCount = matrix[0].length;

            for (int[] row : matrix) {
                if (row.length != colCount) {
                    return 0;
                }
            }

            int[][] sizes = new int[2][colCount];
            int flag = 0;
            int count = 0;
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (i == 0 || j == 0) {
                        sizes[flag][j] = matrix[i][j];
                    } else if (matrix[i][j] == 1) {
                        sizes[flag][j] = Math.min(sizes[flag ^ 1][j - 1],
                                Math.min(sizes[flag][j - 1], sizes[flag ^ 1][j])) + 1;
                    } else {
                        sizes[flag][j] = 0;
                    }

                    count += sizes[flag][j];
                }

                flag ^= 1;
            }

            return count;
        }

        private void print(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Standard {
        public int countSquares(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }

            int rowCount = matrix.length;
            int colCount = matrix[0].length;

            for (int[] row : matrix) {
                if (row.length != colCount) {
                    return 0;
                }
            }

            int[][] sizes = new int[rowCount][colCount];
            int count = 0;
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (i == 0 || j == 0) {
                        sizes[i][j] = matrix[i][j];
                    } else if (matrix[i][j] == 1) {
                        sizes[i][j] = Math.min(sizes[i - 1][j - 1],
                                Math.min(sizes[i][j - 1], sizes[i - 1][j])) + 1;
                    }

                    count += sizes[i][j];
                }
            }

            return count;
        }
    }

// [[0,1,1,1],[1,1,1,1],[0,1,1,1]]
}

//    1277. Count Square Submatrices with All Ones
//    Medium
//    Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
//
//
//
//    Example 1:
//
//    Input: matrix =
//    [
//    [0,1,1,1],
//    [1,1,1,1],
//    [0,1,1,1]
//    ]
//    Output: 15
//    Explanation:
//    There are 10 squares of side 1.
//    There are 4 squares of side 2.
//    There is  1 square of side 3.
//    Total number of squares = 10 + 4 + 1 = 15.
//    Example 2:
//
//    Input: matrix =
//    [
//    [1,0,1],
//    [1,1,0],
//    [1,1,0]
//    ]
//    Output: 7
//    Explanation:
//    There are 6 squares of side 1.
//    There is 1 square of side 2.
//    Total number of squares = 6 + 1 = 7.
//
//
//    Constraints:
//
//    1 <= arr.length <= 300
//    1 <= arr[0].length <= 300
//    0 <= arr[i][j] <= 1