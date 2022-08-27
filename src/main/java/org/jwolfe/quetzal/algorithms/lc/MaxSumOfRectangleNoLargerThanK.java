package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeSet;

public class MaxSumOfRectangleNoLargerThanK {
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return Integer.MIN_VALUE;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int maxSum = Integer.MIN_VALUE;
            for(int j = 0; j < n; j++) {

                int[] rowSums = new int[m];
                for(int l = j; l < n; l++) {

                    for(int i = 0; i < m; i++) {
                        rowSums[i] += matrix[i][l];
                    }

                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(0);

                    int currentSum = 0;
                    for(int i = 0; i < m; i++) {
                        currentSum += rowSums[i];

                        Integer startSum = set.ceiling(currentSum - k);
                        if(startSum != null) {
                            int sum = currentSum - startSum;
                            maxSum = Math.max(maxSum, sum);
                        }

                        set.add(currentSum);
                    }
                }
            }

            return maxSum;
        }
    }

    class Solution_Correct_2 {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return Integer.MIN_VALUE;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] sums = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int sum = matrix[i][j];
                    if(i > 0) {
                        sum += sums[i - 1][j];
                    }

                    if(j > 0) {
                        sum += sums[i][j - 1];
                    }

                    if(i > 0 && j > 0) {
                        sum -= sums[i - 1][j - 1];
                    }

                    sums[i][j] = sum;
                }
            }

            int maxSum = Integer.MIN_VALUE;
            for(int top = 0; top < m; top++) {
                for(int left = 0; left < n; left++) {
                    for(int bottom = top; bottom < m; bottom++) {
                        for(int right = left; right < n; right++) {
                            int sum = sums[bottom][right];

                            if(top > 0) {
                                sum -= sums[top - 1][right];
                            }

                            if(left > 0) {
                                sum -= sums[bottom][left - 1];
                            }

                            if(top > 0 && left > 0) {
                                sum += sums[top - 1][left - 1];
                            }

                            if(sum <= k) {
                                maxSum = Math.max(maxSum, sum);
                            }
                        }
                    }

                }
            }

            return maxSum;
        }

        private void print(int[][] matrix) {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return Integer.MIN_VALUE;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int maxSum = Integer.MIN_VALUE;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    maxSum = Math.max(maxSum,
                            getMaxSumFromSubmatrix(matrix, i, j, m, n, k));
                }
            }

            return maxSum;
        }

        private int getMaxSumFromSubmatrix(int[][] matrix, int top, int left, int m, int n, int k) {
            int maxSum = Integer.MIN_VALUE;

            for(int i = top; i < m; i++) {
                for(int j = left; j < n; j++) {
                    int sum = getMatrixSum(matrix, top, left, i, j);
                    if(sum <= k) {
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }

            return maxSum;
        }

        private int getMatrixSum(int[][] matrix, int top, int left, int bottom, int right) {
            int sum = 0;
            for(int i = top; i <= bottom; i++) {
                for(int j = left; j <= right; j++) {
                    sum += matrix[i][j];
                }
            }

            return sum;
        }
    }

    class Solution_Correct_1 {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] sums = new int[m][n];
            sums[0][0] = matrix[0][0];
            for(int i = 1; i < m; i++) {
                sums[i][0] = sums[i - 1][0] + matrix[i][0];
            }

            for(int j = 1; j < n; j++) {
                sums[0][j] = sums[0][j - 1] + matrix[0][j];
            }

            for(int i = 1; i < m; i++) {
                for(int j = 1; j < n; j++) {
                    sums[i][j] = matrix[i][j] + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
                }
            }

            int maxSum = Integer.MIN_VALUE;
            for(int top = 0; top < m; top++) {
                for(int left = 0; left < n; left++) {
                    for(int bottom = top; bottom < m; bottom++) {
                        for(int right = left; right < n; right++) {
                            int sum = sums[bottom][right];
                            if(top > 0) {
                                sum -= sums[top - 1][right];
                            }

                            if(left > 0) {
                                sum -= sums[bottom][left - 1];
                            }

                            if(top > 0 && left > 0) {
                                sum += sums[top - 1][left - 1];
                            }

                            if(sum <= k) {
                                maxSum = Math.max(maxSum, sum);
                            }
                        }
                    }

                }
            }

            return maxSum;
        }
    }

    class Solution_Brute {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int maxSum = Integer.MIN_VALUE;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    maxSum = Math.max(maxSum,
                            maxSumSubmatrix(matrix, k, m, n, i, j));
                }
            }

            return maxSum;
        }

        private int maxSumSubmatrix(int[][] matrix, int k, int m, int n, int top, int left) {
            int maxSum = Integer.MIN_VALUE;

            for(int i = top; i < m; i++) {
                for(int j = left; j < n; j++) {
                    int sum = getSum(matrix, top, left, i, j);
                    if(sum <= k) {
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }

            return maxSum;
        }

        private int getSum(int[][] matrix, int top, int left, int bottom, int right) {
            int sum = 0;
            for(int i = top; i <= bottom; i++) {
                for(int j = left; j <= right; j++) {
                    sum += matrix[i][j];
                }
            }

            return sum;
        }
    }

// [[1,0,1],[0,-2,3]]
// 2

// [[2,2,-1]]
// 3

// [[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]
// 10

// [[9,-10,-3,-1,1,7,-6,-2,1,-4,-6,-8,-1,2,-9,-7,-9,-1,-1,5,-4,5,-8,3,4,2,9,4,5,4,-8,5,4,-9,-10,3,-2,-2,9,0,-4,3,5,-10,8,-10,9,-7,-6,1,2,6,-8,1,7,3,0,-5,7,-6,1,9,-8,4,-7,-9,1,8,-2,6,-1,0,8,4,-9,8,-3,7,-4,3,-6,2,-1,-2,-10,-10,-10,-3,8,2,-4,3,-6,-3,1,9,-9,7,-6,8],[8,-10,-4,-5,7,2,-6,7,-9,0,-8,9,-4,-5,-2,3,2,7,3,3,0,-3,-10,8,-9,3,-6,-9,3,-10,9,9,-3,6,-8,-7,5,5,9,5,-6,6,1,4,4,-5,-1,2,9,-8,8,-9,-9,-6,8,-3,1,0,-7,9,8,-3,-9,-4,8,-2,-9,2,7,-3,0,6,-7,3,3,-8,1,-2,-6,-5,3,6,0,-9,-6,-4,-10,-6,8,3,1,0,-1,-5,-10,5,-2,-5,-10,6],[-3,-5,-1,-8,-7,-6,-6,0,7,0,3,-6,-9,7,-5,-7,8,4,-4,2,7,-4,-6,4,-9,-8,-1,-9,-1,8,3,8,-2,2,-2,5,9,-1,3,-6,8,2,-6,-2,2,2,-8,-2,-2,0,6,2,2,4,-1,-3,-3,4,-2,4,-6,-7,3,2,-6,3,5,-10,-6,7,-4,-4,-3,-5,-8,-7,-9,-8,-7,5,-9,7,8,-10,7,6,6,-1,-3,4,4,-2,3,-1,-9,-10,-5,-7,8,-1],[-8,-3,3,7,9,-5,3,5,-1,-9,5,8,5,2,6,6,-5,-2,-6,5,-2,-5,3,2,-1,-7,5,-3,-6,0,4,-4,-6,9,-6,-9,-10,7,9,3,6,-6,4,-3,-7,-9,-3,6,-3,0,7,9,-7,-3,9,-8,2,7,-9,6,2,8,-5,5,-7,0,-5,3,8,-6,8,-5,-9,-6,-5,-7,5,-3,7,2,3,5,-2,-3,-3,3,4,-4,0...
// 292
}

//    363. Max Sum of Rectangle No Larger Than K
//    Hard
//    Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
//
//    It is guaranteed that there will be a rectangle with a sum no larger than k.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,0,1],[0,-2,3]], k = 2
//    Output: 2
//    Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
//    Example 2:
//
//    Input: matrix = [[2,2,-1]], k = 3
//    Output: 3
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 100
//    -100 <= matrix[i][j] <= 100
//    -105 <= k <= 105
//
//
//    Follow up: What if the number of rows is much larger than the number of columns?