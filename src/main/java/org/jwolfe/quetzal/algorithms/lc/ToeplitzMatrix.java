package org.jwolfe.quetzal.algorithms.lc;

public class ToeplitzMatrix {
    class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return true;
            }

            for(int i = 1; i < matrix.length; i++) {
                for(int j = 1; j < matrix[0].length; j++) {
                    if(matrix[i][j] != matrix[i - 1][j - 1]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean isToeplitzMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            int rows = matrix.length;
            int columns = matrix[0].length;

            for(int r = 1; r < rows; r++) {
                for(int c = 1; c < columns; c++) {
                    if(matrix[r][c] != matrix[r - 1][c - 1]) {
                        return false;
                    }
                }
            }

            return true;
        }

        public boolean isToeplitzMatrix_Approach1(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            int rows = matrix.length;
            int columns = matrix[0].length;

            for(int k = 0; k < columns; k++) {
                int val = matrix[0][k];
                for(int i = 1, j = k + 1; i < rows && j < columns; i++, j++) {
                    if(matrix[i][j] != val) {
                        return false;
                    }
                }
            }

            for(int k = 1; k < rows; k++) {
                int val = matrix[k][0];
                for(int i = k + 1, j = 1; i < rows && j < columns; i++, j++) {
                    if(matrix[i][j] != val) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}

//    766. Toeplitz Matrix
//    Easy
//    Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
//
//    A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
//    Output: true
//    Explanation:
//    In the above grid, the diagonals are:
//    "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
//    In each diagonal all elements are the same, so the answer is True.
//    Example 2:
//
//
//    Input: matrix = [[1,2],[2,2]]
//    Output: false
//    Explanation:
//    The diagonal "[1, 2]" has different elements.
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 20
//    0 <= matrix[i][j] <= 99
//
//
//    Follow up:
//
//    What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
//    What if the matrix is so large that you can only load up a partial row into the memory at once?