package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortTheMatrixDiagonally {
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
}
