package org.jwolfe.quetzal.algorithms.lc;

public class SpecialPositionsInABinaryMatrix {
    class Solution {
        public int numSpecial(int[][] mat) {
            if(mat == null || mat.length == 0 || mat[0].length == 0) {
                return 0;
            }

            int m = mat.length;
            int n = mat[0].length;

            int[] rowSums = new int[m];
            int[] colSums = new int[n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    rowSums[i] += mat[i][j];
                    colSums[j] += mat[i][j];
                }
            }

            int count = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(mat[i][j] == 1
                            && rowSums[i] == 1
                            && colSums[j] == 1) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int numSpecial(int[][] mat) {
            if(mat == null || mat.length == 0 || mat[0].length == 0) {
                return 0;
            }

            int m = mat.length;
            int n = mat[0].length;

            int[] rowSums = new int[m];
            int[] colSums = new int[n];

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    rowSums[r] += mat[r][c];
                    colSums[c] += mat[r][c];
                }
            }

            int specialCount = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(mat[r][c] == 1
                            && rowSums[r] == 1 && colSums[c] == 1) {
                        specialCount++;
                    }
                }
            }

            return specialCount;
        }
    }

// [[1,0,0],[0,0,1],[1,0,0]]
}

//    1582. Special Positions in a Binary Matrix
//    Easy
//    Given an m x n binary matrix mat, return the number of special positions in mat.
//
//    A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
//
//
//
//    Example 1:
//
//
//    Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
//    Output: 1
//    Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
//    Example 2:
//
//
//    Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
//    Output: 3
//    Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= m, n <= 100
//    mat[i][j] is either 0 or 1.
