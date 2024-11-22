package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FlipColumnsForMaximumNumberOfEqualRows {
    class Solution {
        public int maxEqualRowsAfterFlips(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            Map<String, Integer> patternFrequencies = new HashMap<>();
            for (int[] row : matrix) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (row[i] == row[0]) {
                        sb.append('T');
                    } else {
                        sb.append('F');
                    }
                }

                String pattern = sb.toString();
                patternFrequencies.put(pattern, patternFrequencies.getOrDefault(pattern, 0) + 1);
            }

            int maxEqualRows = 0;
            for (var entry : patternFrequencies.entrySet()) {
                int count = entry.getValue();
                maxEqualRows = Math.max(maxEqualRows, count);
            }

            return maxEqualRows;
        }
    }
}

//    1072. Flip Columns For Maximum Number of Equal Rows
//    Solved
//    Medium
//    Topics
//    Companies
//    Hint
//    You are given an m x n binary matrix matrix.
//
//    You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
//
//    Return the maximum number of rows that have all values equal after some number of flips.
//
//
//
//    Example 1:
//
//    Input: matrix = [[0,1],[1,1]]
//    Output: 1
//    Explanation: After flipping no values, 1 row has all values equal.
//    Example 2:
//
//    Input: matrix = [[0,1],[1,0]]
//    Output: 2
//    Explanation: After flipping values in the first column, both rows have equal values.
//    Example 3:
//
//    Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
//    Output: 2
//    Explanation: After flipping values in the first two columns, the last two rows have equal values.
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 300
//    matrix[i][j] is either 0 or 1.