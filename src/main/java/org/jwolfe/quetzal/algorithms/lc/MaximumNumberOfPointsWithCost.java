package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumNumberOfPointsWithCost {
    class Solution {
        public long maxPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }

            int m = points.length;
            int n = points[0].length;

            long[][] dp = new long[m][n];
            for (int c = 0; c < n; c++) {
                dp[m - 1][c] = points[m - 1][c];
            }

            long[] leftMax = new long[n];
            long[] rightMax = new long[n];

            for (int r = m - 2; r >= 0; r--) {

                leftMax[0] = dp[r + 1][0];
                for (int c = 1; c < n; c++) {
                    leftMax[c] = Math.max(leftMax[c - 1] - 1, dp[r + 1][c]);
                }

                rightMax[n - 1] = dp[r + 1][n - 1];
                for (int c = n - 2; c >= 0; c--) {
                    rightMax[c] = Math.max(rightMax[c + 1] - 1, dp[r + 1][c]);
                }

                for (int c = 0; c < n; c++) {
                    dp[r][c] = points[r][c] + Math.max(leftMax[c], rightMax[c]);
                }
            }

            long maxScore = 0;
            for (int c = 0; c < n; c++) {
                maxScore = Math.max(maxScore, dp[0][c]);
            }

            return maxScore;
        }

        private void print(long[][] arr) {
            for (long[] a : arr) {
                System.out.print("[");
                for (long b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

    class Solution_DP {
        public long maxPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }

            int m = points.length;
            int n = points[0].length;

            long[][] dp = new long[m][n];
            for (int c = 0; c < n; c++) {
                dp[m - 1][c] = points[m - 1][c];
            }

            for (int r = m - 2; r >= 0; r--) {
                for (int c = 0; c < n; c++) {

                    for (int nc = 0; nc < n; nc++) {
                        long ns = dp[r + 1][nc] - Math.abs(c - nc);
                        dp[r][c] = Math.max(dp[r][c], ns);
                    }

                    dp[r][c] += points[r][c];
                }
            }


            long maxScore = 0;
            for (int c = 0; c < n; c++) {
                maxScore = Math.max(maxScore, dp[0][c]);
            }

            return maxScore;
        }

        private void print(long[][] arr) {
            for (long[] a : arr) {
                System.out.print("[");
                for (long b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

    class Solution_Memoized {
        public long maxPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }

            int m = points.length;
            int n = points[0].length;

            long maxScore = 0;
            long[][] memo = new long[m][n];
            for (long[] row : memo) {
                Arrays.fill(row, -1);
            }

            for (int j = 0; j < n; j++) {
                long score = maxPoints(points, m, n, 0, j, memo);
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        }

        private long maxPoints(int[][] points, int m, int n, int row, int col, long[][] memo) {
            if (row == m - 1) {
                return points[row][col];
            }

            if (memo[row][col] != -1) {
                return memo[row][col];
            }

            long maxNextScore = 0;
            for (int j = 0; j < n; j++) {
                long nextScore = maxPoints(points, m, n, row + 1, j, memo) - Math.abs(col - j);
                maxNextScore = Math.max(maxNextScore, nextScore);
            }

            long maxScore = points[row][col] + maxNextScore;
            return memo[row][col] = maxScore;
        }
    }

    class Solution_TLE {
        public long maxPoints(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }

            int m = points.length;
            int n = points[0].length;

            int maxScore = 0;

            for (int j = 0; j < n; j++) {
                int score = maxPoints(points, m, n, 0, j);
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        }

        private int maxPoints(int[][] points, int m, int n, int row, int col) {
            if (row == m - 1) {
                return points[row][col];
            }

            int maxNextScore = 0;
            for (int j = 0; j < n; j++) {
                int nextScore = maxPoints(points, m, n, row + 1, j) - Math.abs(col - j);
                maxNextScore = Math.max(maxNextScore, nextScore);
            }

            int maxScore = points[row][col] + maxNextScore;
            return maxScore;
        }
    }

// [[1,2,3],[1,5,1],[3,1,1]]
// [[4,9,8,9,4,1,3,4],[3,5,2,4,0,8,2,4],[0,0,8,4,2,10,7,1],[0,9,4,7,7,7,5,2],[0,4,7,1,1,6,7,1],[9,10,10,0,2,2,9,4],[1,3,1,8,6,9,9,9],[3,8,6,7,7,4,9,6],[4,9,9,0,9,1,3,10],[3,4,10,1,2,3,2,8]]
}

//    1937. Maximum Number of Points with Cost
//    Medium
//    You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
//
//    To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
//
//    However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
//
//    Return the maximum number of points you can achieve.
//
//    abs(x) is defined as:
//
//    x for x >= 0.
//    -x for x < 0.
//
//
//    Example 1:
//
//
//    Input: points = [[1,2,3],[1,5,1],[3,1,1]]
//    Output: 9
//    Explanation:
//    The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
//    You add 3 + 5 + 3 = 11 to your score.
//    However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
//    Your final score is 11 - 2 = 9.
//    Example 2:
//
//
//    Input: points = [[1,5],[2,3],[4,2]]
//    Output: 11
//    Explanation:
//    The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
//    You add 5 + 3 + 4 = 12 to your score.
//    However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
//    Your final score is 12 - 1 = 11.
//
//
//    Constraints:
//
//    m == points.length
//    n == points[r].length
//    1 <= m, n <= 105
//    1 <= m * n <= 105
//    0 <= points[r][c] <= 105