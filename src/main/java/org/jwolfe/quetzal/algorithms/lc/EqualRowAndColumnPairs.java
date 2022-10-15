package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EqualRowAndColumnPairs {
    class Solution {
        public int equalPairs(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int n = grid.length;
            int count = 0;

            Map<String, Integer> map = new HashMap<>();
            for(int[] row : grid) {
                String memento = Arrays.toString(row);
                map.put(memento, map.getOrDefault(memento, 0) + 1);
            }

            for(int c = 0; c < n; c++) {
                int[] col = new int[n];
                for(int r = 0; r < n; r++) {
                    col[r] = grid[r][c];
                }

                String memento = Arrays.toString(col);
                if(map.containsKey(memento)) {
                    count += map.get(memento);
                }
            }

            return count;
        }
    }

    class Solution_N3 {
        public int equalPairs(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int n = grid.length;
            int count = 0;

            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    boolean equal = true;
                    for(int i = 0; i < n; i++) {
                        if(grid[r][i] != grid[i][c]) {
                            equal = false;
                            break;
                        }
                    }

                    if(equal) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

// [[3,2,1],[1,7,6],[2,7,7]]
}

//    2352. Equal Row and Column Pairs
//    Medium
//    Given a 0-indexed n x n integer matrix grid, return the number of pairs (Ri, Cj) such that row Ri and column Cj are equal.
//
//    A row and column pair is considered equal if they contain the same elements in the same order (i.e. an equal array).
//
//
//
//    Example 1:
//
//
//    Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
//    Output: 1
//    Explanation: There is 1 equal row and column pair:
//    - (Row 2, Column 1): [2,7,7]
//    Example 2:
//
//
//    Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
//    Output: 3
//    Explanation: There are 3 equal row and column pairs:
//    - (Row 0, Column 0): [3,1,2,2]
//    - (Row 2, Column 2): [2,4,2,2]
//    - (Row 3, Column 2): [2,4,2,2]
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    1 <= n <= 200
//    1 <= grid[i][j] <= 105
