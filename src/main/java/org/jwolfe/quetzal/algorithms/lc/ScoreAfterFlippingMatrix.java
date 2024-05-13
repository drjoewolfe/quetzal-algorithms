package org.jwolfe.quetzal.algorithms.lc;

public class ScoreAfterFlippingMatrix {
    class Solution {
        public int matrixScore(int[][] A) {
            if(A == null || A.length == 0 || A[0].length == 0) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length;

            for(int r = 0; r < m; r++) {
                if(A[r][0] == 0) {
                    for(int c = 0; c < n; c++) {
                        A[r][c] = 1 - A[r][c];
                    }
                }
            }

            for(int c = 1; c < n; c++) {
                int zeroCount = 0;
                for(int r = 0; r < m; r++) {
                    if(A[r][c] == 0) {
                        zeroCount++;
                    }
                }

                int oneCount = m - zeroCount;

                if(zeroCount > oneCount) {
                    for(int r = 0; r < m; r++) {
                        A[r][c] = 1 - A[r][c];
                    }
                }
            }

            int score = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int columnScore = A[r][c] << (n - c - 1);
                    score += columnScore;
                }
            }

            return score;
        }
    }
}

//    861. Score After Flipping Matrix
//    Medium
//    You are given an m x n binary matrix grid.
//
//    A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
//
//    Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
//
//    Return the highest possible score after making any number of moves (including zero moves).
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
//    Output: 39
//    Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
//    Example 2:
//
//    Input: grid = [[0]]
//    Output: 1
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 20
//    grid[i][j] is either 0 or 1.