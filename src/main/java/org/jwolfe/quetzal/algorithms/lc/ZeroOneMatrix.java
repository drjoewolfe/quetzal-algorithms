package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    class Solution {
        public int[][] updateMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return new int[0][0];
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int[][] distances = new int[m][n];
            for(int i = 0; i < m; i++) {
                Arrays.fill(distances[i], Integer.MAX_VALUE);
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == 0) {
                        distances[i][j] = 0;
                    } else {
                        int top = i > 0 && distances[i - 1][j] != Integer.MAX_VALUE ? distances[i - 1][j] + 1 : Integer.MAX_VALUE;
                        int left = j > 0 && distances[i][j - 1] != Integer.MAX_VALUE? distances[i][j - 1] + 1 : Integer.MAX_VALUE;

                        distances[i][j] = Math.min(distances[i][j],
                                Math.min(left, top));
                    }
                }
            }

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    if(matrix[i][j] == 1) {
                        int bottom = i < m - 1 && distances[i + 1][j] != Integer.MAX_VALUE ? distances[i + 1][j] + 1 : Integer.MAX_VALUE;
                        int right = j < n - 1 && distances[i][j + 1] != Integer.MAX_VALUE ? distances[i][j + 1] + 1 : Integer.MAX_VALUE;

                        distances[i][j] = Math.min(distances[i][j],
                                Math.min(right, bottom));
                    }
                }
            }

            return distances;
        }
    }

    class Solution_BFS {
        public int[][] updateMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return new int[0][0];
            }

            int m = matrix.length;
            int n = matrix[0].length;

            Queue<int[]> queue = new LinkedList<>();

            int[][] distances = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == 1) {
                        distances[i][j] = Integer.MAX_VALUE;
                    } else {
                        int[] point = new int[] {i, j, 0};
                        queue.offer(point);
                    }
                }
            }

            while(!queue.isEmpty()) {
                int[] point = queue.poll();

                int i = point[0];
                int j = point[1];
                int d = point[2];

                if(i > 0 && matrix[i - 1][j] == 1
                        && distances[i - 1][j] > d + 1) {
                    distances[i - 1][j] = d + 1;
                    queue.offer(new int[] {i - 1, j, d + 1});
                }

                if(i < m - 1 && matrix[i + 1][j] == 1
                        && distances[i + 1][j] > d + 1) {
                    distances[i + 1][j] = d + 1;
                    queue.offer(new int[] {i + 1, j, d + 1});
                }

                if(j > 0 && matrix[i][j - 1] == 1
                        && distances[i][j - 1] > d + 1) {
                    distances[i][j - 1] = d + 1;
                    queue.offer(new int[] {i, j - 1, d + 1});
                }

                if(j < n - 1 && matrix[i][j + 1] == 1
                        && distances[i][j + 1] > d + 1) {
                    distances[i][j + 1] = d + 1;
                    queue.offer(new int[] {i, j + 1, d + 1});
                }
            }

            return distances;
        }
    }

// [[0,0,0],[0,1,0],[0,0,0]]
// [[0,1,0,1,1],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]
}

//    542. 01 Matrix
//    Medium
//    Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
//
//    The distance between two adjacent cells is 1.
//
//
//
//    Example 1:
//
//
//    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
//    Output: [[0,0,0],[0,1,0],[0,0,0]]
//    Example 2:
//
//
//    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
//    Output: [[0,0,0],[0,1,0],[1,2,1]]
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= m, n <= 104
//    1 <= m * n <= 104
//    mat[i][j] is either 0 or 1.
//    There is at least one 0 in mat.
