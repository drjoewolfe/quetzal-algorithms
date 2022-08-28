package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortTheMatrixDiagonally {
    class Solution {
        public int[][] diagonalSort(int[][] mat) {
            if(mat == null || mat.length == 0 || mat[0].length == 0) {
                return mat;
            }

            int m = mat.length;
            int n = mat[0].length;

            Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int diagonal = i - j;

                    if(!map.containsKey(diagonal)) {
                        map.put(diagonal, new PriorityQueue<>());
                    }

                    var heap = map.get(diagonal);
                    heap.offer(mat[i][j]);
                }
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int diagonal = i - j;
                    var heap = map.get(diagonal);

                    mat[i][j] = heap.poll();
                }
            }

            return mat;
        }
    }

    class Solution_Correct_1 {
        public int[][] diagonalSort(int[][] mat) {
            if(mat == null || mat.length == 0 || mat[0].length == 0) {
                return new int[0][0];
            }

            int m = mat.length;
            int n = mat[0].length;

            Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int diagonal = i - j;
                    map.putIfAbsent(diagonal, new PriorityQueue<>());
                    map.get(diagonal).offer(mat[i][j]);
                }
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int diagonal = i - j;
                    mat[i][j] = map.get(diagonal).poll();
                }
            }

            return mat;
        }

        private void print(int[][] mat) {
            for(int[] row : mat) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Classic {
        public int[][] diagonalSort(int[][] mat) {
            if(mat == null || mat.length == 0 || mat[0].length == 0) {
                return new int[0][0];
            }

            int m = mat.length;
            int n = mat[0].length;

            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for(int c = n - 1; c >= 0; c--) {
                int j = c;
                for(int i = 0; i < m && j < n; i++, j++) {
                    queue.offer(mat[i][j]);
                }

                j = c;
                for(int i = 0; i < m && j < n; i++, j++) {
                    mat[i][j] = queue.poll();
                }
            }

            for(int r = 1; r < m; r++) {
                int i = r;
                for(int j = 0; i < m && j < n; i++, j++) {
                    queue.offer(mat[i][j]);
                }

                i = r;
                for(int j = 0; i < m && j < n; i++, j++) {
                    mat[i][j] = queue.poll();
                }
            }

            return mat;
        }

        private void print(int[][] mat) {
            for(int[] row : mat) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

// [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
}

//    1329. Sort the Matrix Diagonally
//    Medium
//    A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
//
//    Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
//
//
//
//    Example 1:
//
//
//    Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
//    Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
//    Example 2:
//
//    Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
//    Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= m, n <= 100
//    1 <= mat[i][j] <= 100