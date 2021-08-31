package org.jwolfe.quetzal.algorithms.lc;

public class RangeAdditionII {
    class Solution {
        public int maxCount(int m, int n, int[][] ops) {
            if(m < 1 || n < 1) {
                return 0;
            }

            int minRows = m;
            int minColumns = n;

            if(ops != null) {
                for(int[] op : ops) {
                    minRows = Math.min(minRows, op[0]);
                    minColumns = Math.min(minColumns, op[1]);
                }
            }

            return minRows * minColumns;
        }
    }

    class Solution_Brute {
        public int maxCount(int m, int n, int[][] ops) {
            if(m < 1 || n < 1) {
                return 0;
            }

            int[][] matrix = new int[m][n];

            if(ops != null) {
                for(int[] op : ops) {
                    int a = op[0];
                    int b = op[1];
                    for(int r = 0; r < a; r++) {
                        for(int c = 0; c < b; c++) {
                            matrix[r][c]++;
                        }
                    }

                }
            }

            int max = Integer.MIN_VALUE;
            int maxCount = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int v = matrix[r][c];
                    if(v > max) {
                        max = v;
                        maxCount = 1;
                    } else if(v == max) {
                        maxCount++;
                    }
                }
            }

            return maxCount;
        }
    }

    class Solution_Correct_2 {
        public int maxCount(int m, int n, int[][] ops) {
            if(m == 0 || n == 0) {
                return 0;
            }

            if(ops == null || ops.length == 0) {
                return m * n;
            }

            int row = m;
            int col = n;
            for(int[] op : ops) {
                int r = op[0];
                int c = op[1];

                row = Math.min(row, r);
                col = Math.min(col, c);
            }

            return row * col;
        }
    }
}

//    598. Range Addition II
//    Easy
//    You are given an m x n matrix M initialized with all 0's and an array of operations ops, where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
//
//    Count and return the number of maximum integers in the matrix after performing all the operations.
//
//
//
//    Example 1:
//
//
//    Input: m = 3, n = 3, ops = [[2,2],[3,3]]
//    Output: 4
//    Explanation: The maximum integer in M is 2, and there are four of it in M. So return 4.
//    Example 2:
//
//    Input: m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
//    Output: 4
//    Example 3:
//
//    Input: m = 3, n = 3, ops = []
//    Output: 9
//
//
//    Constraints:
//
//    1 <= m, n <= 4 * 104
//    0 <= ops.length <= 104
//    ops[i].length == 2
//    1 <= ai <= m
//    1 <= bi <= n