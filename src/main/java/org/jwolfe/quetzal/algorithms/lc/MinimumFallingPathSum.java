package org.jwolfe.quetzal.algorithms.lc;

public class MinimumFallingPathSum {
    class Solution {
        public int minFallingPathSum(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] dp = new int[m][n];
            for(int j = 0; j < n; j++) {
                dp[m - 1][j] = matrix[m - 1][j];
            }

            for(int i = m - 2; i >= 0; i--) {
                for(int j = 0; j < n; j++) {
                    int minPath = dp[i + 1][j];
                    if(j > 0) {
                        minPath = Math.min(minPath, dp[i + 1][j - 1]);
                    }

                    if(j < n - 1) {
                        minPath = Math.min(minPath, dp[i + 1][j + 1]);
                    }

                    dp[i][j] = matrix[i][j] + minPath;
                }
            }

            int minSum = dp[0][0];
            for(int j = 1; j < n; j++) {
                minSum = Math.min(minSum, dp[0][j]);
            }

            return minSum;
        }
    }

    class Solution_Correct_2 {
        public int minFallingPathSum(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] dp = new int[m][n];
            for(int c = 0; c < n; c++) {
                dp[m - 1][c] = matrix[m - 1][c];
            }

            for(int r = m - 2; r >= 0; r--) {
                for(int c = 0; c < n; c++) {
                    int minSum = dp[r + 1][c];

                    if(c > 0) {
                        minSum = Math.min(minSum, dp[r + 1][c - 1]);
                    }

                    if(c < n - 1) {
                        minSum = Math.min(minSum, dp[r + 1][c + 1]);
                    }

                    dp[r][c] = matrix[r][c] + minSum;
                }
            }

            int result = dp[0][0];
            for(int c = 1; c < n; c++) {
                result = Math.min(result, dp[0][c]);
            }

            return result;
        }

        private void print(int[][] matrix) {
            for(int[] row : matrix) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int minFallingPathSum(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] dp = new int[m][n];
            for(int j = 0; j < n; j++) {
                dp[m - 1][j] = matrix[m - 1][j];
            }

            for(int i = m - 2; i >= 0; i--) {
                for(int j = 0; j < n; j++) {
                    int minSum = dp[i + 1][j];

                    if(j > 0) {
                        minSum = Math.min(minSum,
                                dp[i + 1][j - 1]);
                    }

                    if(j < n - 1) {
                        minSum = Math.min(minSum,
                                dp[i + 1][j + 1]);
                    }

                    dp[i][j] = matrix[i][j] + minSum;
                }
            }

            int minPathSum = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                minPathSum = Math.min(minPathSum,
                        dp[0][j]);
            }

            return minPathSum;
        }

        private void print(int[][] matrix) {
            for(int[] row : matrix) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Recursive {
        public int minFallingPathSum(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int minSum = Integer.MAX_VALUE;

            for(int j = 0; j < n; j++) {
                minSum = Math.min(minSum,
                        minFallingPathSum(matrix, m, n, 0, j));
            }

            return minSum;
        }

        private int minFallingPathSum(int[][] matrix, int m, int n, int i, int j) {
            if(i == m - 1) {
                return matrix[i][j];
            }

            int minSum = minFallingPathSum(matrix, m, n, i + 1, j);

            if(j > 0) {
                minSum = Math.min(minSum,
                        minFallingPathSum(matrix, m, n, i + 1, j - 1));
            }

            if(j < n - 1) {
                minSum = Math.min(minSum,
                        minFallingPathSum(matrix, m, n, i + 1, j + 1));
            }

            return minSum + matrix[i][j];
        }
    }

// [[2,1,3],[6,5,4],[7,8,9]]
}

//    931. Minimum Falling Path Sum
//    Medium
//    Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
//
//    A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
//    Output: 13
//    Explanation: There are two falling paths with a minimum sum as shown.
//    Example 2:
//
//
//    Input: matrix = [[-19,57],[-40,-5]]
//    Output: -59
//    Explanation: The falling path with a minimum sum is shown.
//
//
//    Constraints:
//
//    n == matrix.length == matrix[i].length
//    1 <= n <= 100
//    -100 <= matrix[i][j] <= 100