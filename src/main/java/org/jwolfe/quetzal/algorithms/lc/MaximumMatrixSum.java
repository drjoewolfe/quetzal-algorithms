package org.jwolfe.quetzal.algorithms.lc;

public class MaximumMatrixSum {
    class Solution {
        public long maxMatrixSum(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            long totalAbsoluteSum = 0;
            int negativeCount = 0;
            int minAbsVal = Integer.MAX_VALUE;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int val = matrix[r][c];
                    totalAbsoluteSum += Math.abs(val);

                    if (val < 0) {
                        negativeCount++;
                    }

                    minAbsVal = Math.min(minAbsVal, Math.abs(val));
                }
            }

            if (negativeCount % 2 != 0) {
                totalAbsoluteSum -= (2 * minAbsVal);
            }

            return totalAbsoluteSum;
        }
    }
}

//    1975. Maximum Matrix Sum
//    Medium
//    Topics
//    You are given an n x n integer matrix. You can do the following operation any number of times:
//
//    Choose any two adjacent elements of matrix and multiply each of them by -1.
//    Two elements are considered adjacent if and only if they share a border.
//
//    Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,-1],[-1,1]]
//    Output: 4
//    Explanation: We can follow the following steps to reach sum equals 4:
//    - Multiply the 2 elements in the first row by -1.
//    - Multiply the 2 elements in the first column by -1.
//    Example 2:
//
//
//    Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
//    Output: 16
//    Explanation: We can follow the following step to reach sum equals 16:
//    - Multiply the 2 last elements in the second row by -1.
//
//
//    Constraints:
//
//    n == matrix.length == matrix[i].length
//    2 <= n <= 250
//    -105 <= matrix[i][j] <= 105