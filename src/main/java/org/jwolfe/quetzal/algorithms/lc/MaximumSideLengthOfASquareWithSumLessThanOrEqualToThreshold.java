package org.jwolfe.quetzal.algorithms.lc;

public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
    class Solution {
        public int maxSideLength(int[][] mat, int threshold) {
            if (mat == null || mat.length == 0 || mat[0].length == 0 || threshold < 0) {
                return 0;
            }

            int m = mat.length;
            int n = mat[0].length;

            int[][] prefixSum = new int[m + 1][n + 1];
            for (int r = 1; r <= m; r++) {
                for (int c = 1; c <= n; c++) {
                    prefixSum[r][c] = prefixSum[r - 1][c]
                            + prefixSum[r][c - 1]
                            - prefixSum[r - 1][c - 1]
                            + mat[r - 1][c - 1];
                }
            }

            int ml = Math.min(m, n);
            int ans = 0;
            for (int r = 1; r <= m; r++) {
                for (int c = 1; c <= n; c++) {
                    for (int l = ans + 1; l <= ml; l++) {
                        if (r + l - 1 <= m
                                && c + l - 1 <= n
                                && sum(prefixSum, r, c, r + l - 1, c + l - 1) <= threshold) {
                            ans++;
                        } else {
                            break;
                        }
                    }
                }
            }

            return ans;
        }

        private int sum(int[][] prefixSum, int x1, int y1, int x2, int y2) {
            return prefixSum[x2][y2]
                    - prefixSum[x2][y1 - 1]
                    - prefixSum[x1 - 1][y2]
                    + prefixSum[x1 - 1][y1 - 1];
        }
    }
}

//    1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
//    Medium
//    Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
//
//
//
//    Example 1:
//
//
//    Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//    Output: 2
//    Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
//    Example 2:
//
//    Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
//    Output: 0
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= m, n <= 300
//    0 <= mat[i][j] <= 104
//    0 <= threshold <= 105