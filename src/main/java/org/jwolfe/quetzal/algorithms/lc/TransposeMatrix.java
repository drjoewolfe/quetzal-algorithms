package org.jwolfe.quetzal.algorithms.lc;

public class TransposeMatrix {
    class Solution {
        public int[][] transpose(int[][] A) {
            if(A == null || A.length == 0 || A[0].length == 0) {
                return A;
            }

            int m = A.length;
            int n = A[0].length;

            int[][] transpose = new int[n][m];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    transpose[c][r] = A[r][c];
                }
            }

            return transpose;
        }
    }

    class Solution_Correct_1 {
        public int[][] transpose(int[][] A) {
            if(A == null || A.length == 0 || A[0].length == 0) {
                return new int[0][0];
            }

            int rowCount = A.length;
            int columnCount = A[0].length;

            int[][] transpose = new int[columnCount][rowCount];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    transpose[c][r] = A[r][c];
                }
            }

            return transpose;
        }
    }
}

//    867. Transpose Matrix
//    Easy
//    Given a 2D integer array matrix, return the transpose of matrix.
//
//    The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
//
//
//
//
//
//    Example 1:
//
//    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    Output: [[1,4,7],[2,5,8],[3,6,9]]
//    Example 2:
//
//    Input: matrix = [[1,2,3],[4,5,6]]
//    Output: [[1,4],[2,5],[3,6]]
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 1000
//    1 <= m * n <= 105
//    -109 <= matrix[i][j] <= 109