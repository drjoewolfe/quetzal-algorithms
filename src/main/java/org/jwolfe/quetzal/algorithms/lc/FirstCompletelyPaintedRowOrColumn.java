package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;

public class FirstCompletelyPaintedRowOrColumn {
    class Solution {
        public int firstCompleteIndex(int[] arr, int[][] mat) {
            if (arr == null || arr.length == 0 || mat == null || mat.length == 0 || mat[0].length == 0) {
                return 0;
            }

            int m = mat.length;
            int n = mat[0].length;

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int cell = (r * n) + c;
                    int val = mat[r][c];

                    map.put(val, cell);
                }
            }

            int[] rowFrequency = new int[m];
            int[] colFrequency = new int[n];

            for (int i = 0; i < arr.length; i++) {
                int val = arr[i];
                int cell = map.get(val);

                int r = cell / n;
                int c = cell % n;

                rowFrequency[r]++;
                colFrequency[c]++;

                if (rowFrequency[r] == n || colFrequency[c] == m) {
                    return i;
                }
            }

            return -1;
        }
    }
}

//    2661. First Completely Painted Row or Column
//    Medium
//    You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].
//
//    Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
//
//    Return the smallest index i at which either a row or a column will be completely painted in mat.
//
//
//
//    Example 1:
//
//    image explanation for example 1
//    Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
//    Output: 2
//    Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
//    Example 2:
//
//    image explanation for example 2
//    Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
//    Output: 3
//    Explanation: The second column becomes fully painted at arr[3].
//
//
//    Constraints:
//
//    m == mat.length
//    n = mat[i].length
//    arr.length == m * n
//    1 <= m, n <= 105
//    1 <= m * n <= 105
//    1 <= arr[i], mat[r][c] <= m * n
//    All the integers of arr are unique.
//    All the integers of mat are unique.