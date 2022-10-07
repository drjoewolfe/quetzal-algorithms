package org.jwolfe.quetzal.algorithms.lc;

public class DetermineWhetherMatrixCanBeObtainedByRotation {
    class Solution {
        public boolean findRotation(int[][] mat, int[][] target) {
            if(mat == null || target == null || mat.length == 0 || mat[0].length == 0 || mat.length != target.length) {
                return false;
            }

            if(equals(mat, target)) {
                return true;
            }

            // try for 3 rotation - 90, 180, 270
            for(int i = 0; i < 3; i++) {
                rotate(mat);
                if(equals(mat, target)) {
                    return true;
                }
            }

            return false;
        }

        private void rotate(int[][] mat) {
            int n = mat.length;

            // transpose
            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[j][i];
                    mat[j][i] = temp;
                }
            }

            // swap
            int left = 0;
            int right = n - 1;
            while(left < right) {
                for(int i = 0; i < n; i++) {
                    int temp = mat[i][left];
                    mat[i][left] = mat[i][right];
                    mat[i][right] = temp;
                }

                left++;
                right--;
            }
        }

        private boolean equals(int[][] mat, int[][] target) {
            int n = mat.length;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(mat[i][j] != target[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

// [[0,1],[1,0]]
// [[1,0],[0,1]]
}

//    1886. Determine Whether Matrix Can Be Obtained By Rotation
//    Easy
//    Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
//    Output: true
//    Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
//    Example 2:
//
//
//    Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
//    Output: false
//    Explanation: It is impossible to make mat equal to target by rotating mat.
//    Example 3:
//
//
//    Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
//    Output: true
//    Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
//
//
//    Constraints:
//
//    n == mat.length == target.length
//    n == mat[i].length == target[i].length
//    1 <= n <= 10
//    mat[i][j] and target[i][j] are either 0 or 1.