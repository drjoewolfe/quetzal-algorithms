package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SetMatrixZeroes {
    class Solution {
        public void setZeroes(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            int rows = matrix.length;
            int columns = matrix[0].length;

            for(int[] row : matrix) {
                if(row.length != columns) {
                    return;
                }
            }

            boolean rowZero = false;
            boolean colZero = false;

            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < columns; c++) {
                    if(matrix[r][c] == 0) {
                        if(r == 0) {
                            rowZero = true;
                        }

                        if(c == 0) {
                            colZero = true;
                        }

                        if( r != 0 && c != 0) {
                            matrix[0][c] = 0;
                            matrix[r][0] = 0;
                        }
                    }
                }
            }

            for(int r = 1; r < rows; r++) {
                for(int c = 1; c < columns; c++) {
                    if(matrix[0][c] == 0 || matrix[r][0] == 0) {
                        matrix[r][c] = 0;
                    }
                }
            }

            if(rowZero) {
                Arrays.fill(matrix[0], 0);
            }

            if(colZero) {
                for(int r = 0; r < rows; r ++) {
                    matrix[r][0] = 0;
                }
            }
        }
    }

    class Solution_Correct_2 {
        public void setZeroes(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;


            boolean firstRowZero = false;
            boolean firstColZero = false;

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(matrix[r][c] == 0) {
                        if(r == 0) {
                            firstRowZero = true;
                        }

                        if(c == 0) {
                            firstColZero = true;
                        }

                        matrix[r][0] = 0;
                        matrix[0][c] = 0;
                    }
                }
            }

            for(int r = 1; r < m; r++) {
                for(int c = 1; c < n; c++) {
                    if(matrix[r][0] == 0 || matrix[0][c] == 0) {
                        matrix[r][c] = 0;
                    }
                }
            }

            for(int r = 0; r < m; r++) {
                if(firstColZero) {
                    matrix[r][0] = 0;
                }
            }

            for(int c = 0; c < n; c++) {
                if(firstRowZero) {
                    matrix[0][c] = 0;
                }
            }
        }
    }

    class Solution_Space {
        public void setZeroes(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[] rowMasks = new int[m];
            int[] colMasks = new int[n];

            Arrays.fill(rowMasks, 1);
            Arrays.fill(colMasks, 1);

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(matrix[r][c] == 0) {
                        rowMasks[r] = 0;
                        colMasks[c] = 0;
                    }
                }
            }

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(rowMasks[r] == 0 || colMasks[c] == 0) {
                        matrix[r][c] = 0;
                    }
                }
            }
        }
    }
}

//    73. Set Matrix Zeroes
//    Medium
//    Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
//
//    You must do it in place.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//    Output: [[1,0,1],[0,0,0],[1,0,1]]
//    Example 2:
//
//
//    Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//    Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[0].length
//    1 <= m, n <= 200
//    -231 <= matrix[i][j] <= 231 - 1
//
//
//    Follow up:
//
//    A straightforward solution using O(mn) space is probably a bad idea.
//    A simple improvement uses O(m + n) space, but still not the best solution.
//    Could you devise a constant space solution?
