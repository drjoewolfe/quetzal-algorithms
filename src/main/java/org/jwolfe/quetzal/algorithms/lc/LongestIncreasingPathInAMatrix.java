package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class LongestIncreasingPathInAMatrix {
    class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int longestPath = Integer.MIN_VALUE;
            int[][] dp = new int[m][n];
            for(int[] row : dp) {
                Arrays.fill(row, -1);
            }

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int path = longestIncreasingPath(matrix, m, n, r, c, dp);
                    longestPath = Math.max(longestPath, path);
                }
            }

            return longestPath;
        }

        private int longestIncreasingPath(int[][] matrix, int m, int n, int r, int c, int[][] dp) {
            if(dp[r][c] != -1) {
                return dp[r][c];
            }

            int path = 1;
            int longestPath = path;

            // Left
            if(c > 0 && matrix[r][c] < matrix[r][c - 1]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r, c - 1, dp));
            }

            // Right
            if(c < n - 1 && matrix[r][c] < matrix[r][c + 1]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r, c + 1, dp));
            }

            // Top
            if(r > 0 && matrix[r][c] < matrix[r - 1][c]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r - 1, c, dp));
            }

            // Bottom
            if(r < m - 1 && matrix[r][c] < matrix[r + 1][c]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r + 1, c, dp));
            }

            dp[r][c] = longestPath;
            return longestPath;
        }
    }

    class Solution_Memoized {
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int longestPath = Integer.MIN_VALUE;
            Map<Integer, Integer> memo = new HashMap<>();
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int path = longestIncreasingPath(matrix, m, n, r, c, memo);
                    longestPath = Math.max(longestPath, path);
                }
            }

            return longestPath;
        }

        private int longestIncreasingPath(int[][] matrix, int m, int n, int r, int c, Map<Integer, Integer> memo) {
            int memento = getMemento(m, n, r, c);

            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            int path = 1;
            int longestPath = path;

            // Left
            if(c > 0 && matrix[r][c] < matrix[r][c - 1]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r, c - 1, memo));
            }

            // Right
            if(c < n - 1 && matrix[r][c] < matrix[r][c + 1]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r, c + 1, memo));
            }

            // Top
            if(r > 0 && matrix[r][c] < matrix[r - 1][c]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r - 1, c, memo));
            }

            // Bottom
            if(r < m - 1 && matrix[r][c] < matrix[r + 1][c]) {
                longestPath = Math.max(longestPath,
                        path + longestIncreasingPath(matrix, m, n, r + 1, c, memo));
            }

            memo.put(memento, longestPath);
            return longestPath;
        }

        private int getMemento(int m, int n, int r, int c) {
            return (r * n) +  (c + 1);
        }
    }

    class Solution_Recursive {
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int longestPath = Integer.MIN_VALUE;
            Set<Integer> currentPath = new HashSet<>();
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int path = longestIncreasingPath(matrix, m, n, r, c, 0, currentPath);
                    longestPath = Math.max(longestPath, path);
                }
            }

            return longestPath;
        }

        private int longestIncreasingPath(int[][] matrix, int m, int n, int r, int c, int current, Set<Integer> currentPath) {
            int memento = getMemento(m, n, r, c);
            if(currentPath.contains(memento)) {
                return 0;
            }

            currentPath.add(memento);
            current++;
            int longestPath = current;

            // Left
            if(c > 0 && matrix[r][c] < matrix[r][c - 1]) {
                longestPath = Math.max(longestPath,
                        longestIncreasingPath(matrix, m, n, r, c - 1, current, currentPath));
            }

            // Right
            if(c < n - 1 && matrix[r][c] < matrix[r][c + 1]) {
                longestPath = Math.max(longestPath,
                        longestIncreasingPath(matrix, m, n, r, c + 1, current, currentPath));
            }

            // Top
            if(r > 0 && matrix[r][c] < matrix[r - 1][c]) {
                longestPath = Math.max(longestPath,
                        longestIncreasingPath(matrix, m, n, r - 1, c, current, currentPath));
            }

            // Bottom
            if(r < m - 1 && matrix[r][c] < matrix[r + 1][c]) {
                longestPath = Math.max(longestPath,
                        longestIncreasingPath(matrix, m, n, r + 1, c, current, currentPath));
            }

            currentPath.remove(memento);
            return longestPath;
        }

        private int getMemento(int m, int n, int r, int c) {
            return (r * m) +  (c + 1);
        }
    }

// [[9,9,4],[6,6,8],[2,1,1]]
// [[3,4,5],[3,2,6],[2,2,1]]
// [[7,8,9],[9,7,6],[7,2,3]]
// [[17,4,6,11,4,0,17,12,19,12,12,0],[0,6,4,4,5,9,15,1,11,13,18,0],[4,2,13,1,2,7,15,5,14,6,8,6]]
// [[0,1,2,3,4,5,6,7,8,9],[19,18,17,16,15,14,13,12,11,10],[20,21,22,23,24,25,26,27,28,29],[39,38,37,36,35,34,33,32,31,30],[40,41,42,43,44,45,46,47,48,49],[59,58,57,56,55,54,53,52,51,50],[60,61,62,63,64,65,66,67,68,69],[79,78,77,76,75,74,73,72,71,70],[80,81,82,83,84,85,86,87,88,89],[99,98,97,96,95,94,93,92,91,90],[100,101,102,103,104,105,106,107,108,109],[119,118,117,116,115,114,113,112,111,110],[120,121,122,123,124,125,126,127,128,129],[139,138,137,136,135,134,133,132,131,130],[0,0,0,0,0,0,0,0,0,0]]
}

//    329. Longest Increasing Path in a Matrix
//    Hard
//    Given an m x n integers matrix, return the length of the longest increasing path in matrix.
//
//    From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
//    Output: 4
//    Explanation: The longest increasing path is [1, 2, 6, 9].
//    Example 2:
//
//
//    Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
//    Output: 4
//    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
//    Example 3:
//
//    Input: matrix = [[1]]
//    Output: 1
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 200
//    0 <= matrix[i][j] <= 231 - 1