package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget {
    class Solution {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] prefixSums = new int[m][n];

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(c == 0) {
                        prefixSums[r][c] = matrix[r][c];
                    } else {
                        prefixSums[r][c] = prefixSums[r][c - 1] + matrix[r][c];
                    }
                }
            }

            int count = 0;

            for(int c1 = 0; c1 < n; c1++) {
                for(int c2 = c1; c2 < n; c2++) {
                    Map<Integer, Integer> sumCounts = new HashMap<>();
                    sumCounts.put(0, 1);

                    int matrixSum = 0;

                    for(int r = 0; r < m; r++) {
                        int rowSum = prefixSums[r][c2] - ((c1 > 0) ? prefixSums[r][c1 - 1] : 0);
                        matrixSum += rowSum;

                        int remainingSum = matrixSum - target;

                        if(sumCounts.containsKey(remainingSum)) {
                            count += sumCounts.get(remainingSum);
                        }

                        sumCounts.put(matrixSum, sumCounts.getOrDefault(matrixSum, 0) + 1);
                        System.out.println(sumCounts);
                    }
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] prefixSums = getPrefixSumMatrix(matrix, m, n);
            int count = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {

                    for(int i = r; i < m; i++) {
                        for(int j = c; j < n; j++) {
                            int sum = getSum(matrix, prefixSums, r, c, i, j);
                            if(sum == target) {
                                count++;
                            }
                        }
                    }
                }
            }

            return count;
        }

        private int getSum(int[][] matrix, int[][] prefixSums, int r, int c, int i, int j) {
            int sum = prefixSums[i][j];
            if(r > 0) {
                sum -= prefixSums[r - 1][j];
            }

            if(c > 0) {
                sum -= prefixSums[i][c - 1];
            }

            if(r > 0 && c > 0) {
                sum += prefixSums[r -1][c - 1];
            }

            return sum;
        }

        private int[][] getPrefixSumMatrix(int[][] matrix, int m, int n) {
            int[][] prefixSums = new int[m][n];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int sum = matrix[r][c];
                    if(r > 0) {
                        sum += prefixSums[r - 1][c];
                    }

                    if(c > 0) {
                        sum += prefixSums[r][c - 1];
                    }

                    if(r > 0 && c > 0) {
                        sum -= prefixSums[r - 1][c - 1];
                    }

                    prefixSums[r][c] = sum;
                }
            }

            return prefixSums;
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

    class Solution_Brute {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int count = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {

                    for(int i = r; i < m; i++) {
                        for(int j = c; j < n; j++) {
                            int sum = getSum(matrix, r, c, i, j);

                            if(sum == target) {
                                count++;
                            }
                        }
                    }
                }
            }

            return count;
        }

        private int getSum(int[][] matrix, int r, int c, int i, int j) {
            int sum = 0;
            for(int a = r; a <= i; a++) {
                for(int b = c; b <= j; b++) {
                    sum += matrix[a][b];
                }
            }

            return sum;
        }
    }

// [[0,1,0],[1,1,1],[0,1,0]]
// 0

// [[1,-1],[-1,1]]
// 0
}

//    1074. Number of Submatrices That Sum to Target
//    Hard
//    Given a matrix and a target, return the number of non-empty submatrices that sum to target.
//
//    A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
//
//    Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//    Output: 4
//    Explanation: The four 1x1 submatrices that only contain 0.
//    Example 2:
//
//    Input: matrix = [[1,-1],[-1,1]], target = 0
//    Output: 5
//    Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
//    Example 3:
//
//    Input: matrix = [[904]], target = 0
//    Output: 0
//
//
//    Constraints:
//
//    1 <= matrix.length <= 100
//    1 <= matrix[0].length <= 100
//    -1000 <= matrix[i] <= 1000
//    -10^8 <= target <= 10^8