package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class CheckIfEveryRowAndColumnContainsAllNumbers {
    class Solution {
        public boolean checkValid(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length != matrix.length) {
                return false;
            }

            int n = matrix.length;

            // Check rows
            for(int i = 0; i < n; i++) {
                Set<Integer> set = new HashSet<>();
                for(int j = 0; j < n; j++) {
                    int val = matrix[i][j];

                    if(val < 1 || val > n) {
                        return false;
                    }

                    set.add(val);
                }

                if(set.size() != n) {
                    return false;
                }
            }

            // Check columns
            for(int j = 0; j < n; j++) {
                Set<Integer> set = new HashSet<>();
                for(int i = 0; i < n; i++) {
                    int val = matrix[i][j];

                    if(val < 1 || val > n) {
                        return false;
                    }

                    set.add(val);
                }

                if(set.size() != n) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    2133. Check if Every Row and Column Contains All Numbers
//    Easy
//    An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
//
//    Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
//    Output: true
//    Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
//    Hence, we return true.
//    Example 2:
//
//
//    Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
//    Output: false
//    Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
//    Hence, we return false.
//
//
//    Constraints:
//
//    n == matrix.length == matrix[i].length
//    1 <= n <= 100
//    1 <= matrix[i][j] <= n