package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuckyNumbersInAMatrix {
    class Solution {
        public List<Integer> luckyNumbers (int[][] matrix) {
            List<Integer> numbers = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return numbers;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int maxMinAcrossRows = Integer.MIN_VALUE;
            for(int r = 0; r < m; r++) {
                int minAcrossRows = Integer.MAX_VALUE;
                for(int c = 0; c < n; c++) {
                    minAcrossRows = Math.min(minAcrossRows, matrix[r][c]);
                }

                maxMinAcrossRows = Math.max(maxMinAcrossRows, minAcrossRows);
            }

            int minMaxAcrossColumns = Integer.MAX_VALUE;
            for(int c = 0; c < n; c++) {
                int maxAcrossColumns = Integer.MIN_VALUE;
                for(int r = 0; r < m; r++) {
                    maxAcrossColumns = Math.max(maxAcrossColumns, matrix[r][c]);
                }

                minMaxAcrossColumns = Math.min(minMaxAcrossColumns, maxAcrossColumns);
            }

            if(maxMinAcrossRows == minMaxAcrossColumns) {
                numbers.add(maxMinAcrossRows);
            }

            return numbers;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> luckyNumbers (int[][] matrix) {
            List<Integer> numbers = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return numbers;
            }

            int rowCount = matrix.length;
            int columnCount = matrix[0].length;

            int[] rowMinimums = new int[rowCount];
            int[] columnMaximums = new int[columnCount];

            Arrays.fill(rowMinimums, Integer.MAX_VALUE);
            Arrays.fill(columnMaximums, Integer.MIN_VALUE);

            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    int a = matrix[r][c];
                    rowMinimums[r] = Math.min(rowMinimums[r], a);
                    columnMaximums[c] = Math.max(columnMaximums[c], a);
                }
            }

            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    int a = matrix[r][c];

                    if(rowMinimums[r] == a && columnMaximums[c] == a) {
                        numbers.add(a);
                    }
                }
            }

            return numbers;
        }
    }

    class Solution_Brute {
        public List<Integer> luckyNumbers (int[][] matrix) {
            List<Integer> numbers = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return numbers;
            }

            int rowCount = matrix.length;
            int columnCount = matrix[0].length;

            for(int r = 0; r < rowCount; r++) {
                int rowMin = Integer.MAX_VALUE;
                int minColumn = -1;
                for(int c = 0; c < columnCount; c++) {
                    int a = matrix[r][c];
                    if(rowMin > a) {
                        rowMin = a;
                        minColumn = c;
                    }
                }

                boolean isLucky = true;
                for(int i = 0;  i < rowCount; i++) {
                    if(matrix[i][minColumn] > rowMin) {
                        isLucky = false;
                        break;
                    }
                }

                if(isLucky) {
                    numbers.add(rowMin);
                }
            }

            return numbers;
        }
    }

// [[3,7,8],[9,11,13],[15,16,17]]
// [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
}

//    1380. Lucky Numbers in a Matrix
//    Easy
//    Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
//
//    A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
//
//
//
//    Example 1:
//
//    Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
//    Output: [15]
//    Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
//    Example 2:
//
//    Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
//    Output: [12]
//    Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
//    Example 3:
//
//    Input: matrix = [[7,8],[1,2]]
//    Output: [7]
//    Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= n, m <= 50
//    1 <= matrix[i][j] <= 105.
//    All elements in the matrix are distinct.