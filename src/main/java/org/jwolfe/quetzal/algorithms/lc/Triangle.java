package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if(triangle == null || triangle.size() == 0) {
                return 0;
            }

            int m = triangle.size();
            int n = triangle.get(m - 1).size();
            int[][] memo = new int[m][n];
            for(int[] row : memo) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            return minimumTotal(triangle, 0, 0, memo);
        }

        private int minimumTotal(List<List<Integer>> triangle, int i, int j, int[][] memo) {
            if(i == triangle.size()) {
                return 0;
            }

            var row = triangle.get(i);
            if(j == row.size()) {
                return 0;
            }

            if(memo[i][j] != Integer.MAX_VALUE) {
                return memo[i][j];
            }

            int left = minimumTotal(triangle, i + 1, j, memo);
            int right = minimumTotal(triangle, i + 1, j + 1, memo);

            int min = row.get(j) + Math.min(left, right);

            memo[i][j] = min;
            return min;
        }
    }

    class Solution_Standard {
        int minPathSum;

        public int minimumTotal(List<List<Integer>> triangle) {
            if(triangle == null || triangle.size() == 0) {
                return 0;
            }

            List<Integer> prevMinPathSumRow = null;
            List<Integer> minPathSumRow = new ArrayList<>();
            minPathSumRow.add(triangle.get(0).get(0));
            for(int i = 1; i < triangle.size(); i++) {
                prevMinPathSumRow = minPathSumRow;
                minPathSumRow = new ArrayList<>();

                List<Integer> row = triangle.get(i);
                for(int j = 0; j < row.size(); j++) {
                    int prev1 = j > 0 ? prevMinPathSumRow.get(j - 1) : Integer.MAX_VALUE;
                    int prev2 = j < row.size() - 1 ? prevMinPathSumRow.get(j) : Integer.MAX_VALUE;

                    int pathSum = row.get(j) + Math.min(prev1, prev2);
                    minPathSumRow.add(pathSum);
                }
            }

            minPathSum = Integer.MAX_VALUE;
            for(int i = 0; i < minPathSumRow.size(); i++) {
                minPathSum = Math.min(minPathSum, minPathSumRow.get(i));
            }

            return minPathSum;
        }
    }

    class Solution_Recursive {
        public int minimumTotal(List<List<Integer>> triangle) {
            if(triangle == null || triangle.size() == 0) {
                return 0;
            }

            return minimumTotal(triangle, 0, 0);
        }

        private int minimumTotal(List<List<Integer>> triangle, int i, int j) {
            if(i == triangle.size()) {
                return 0;
            }

            var row = triangle.get(i);
            if(j == row.size()) {
                return 0;
            }

            int left = minimumTotal(triangle, i + 1, j);
            int right = minimumTotal(triangle, i + 1, j + 1);

            int min = row.get(j) + Math.min(left, right);
            return min;
        }
    }

    class Solution_Recursive1 {
        int minPathSum;

        public int minimumTotal(List<List<Integer>> triangle) {
            if(triangle == null || triangle.size() == 0) {
                return 0;
            }

            minPathSum = Integer.MAX_VALUE;
            minimumTotal(triangle, 0, 0, 0);

            return minPathSum;
        }

        private void minimumTotal(List<List<Integer>> triangle, int r, int c, int sum) {
            if(r == triangle.size()) {
                minPathSum = Math.min(minPathSum, sum);
                return;
            }

            int val = triangle.get(r).get(c);
            minimumTotal(triangle, r + 1, c, sum + val);
            minimumTotal(triangle, r + 1, c + 1, sum + val);
        }
    }

// [[2],[3,4],[6,5,7],[4,1,8,3]]
}

//    120. Triangle
//    Medium
//    Given a triangle array, return the minimum path sum from top to bottom.
//
//    For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
//
//
//
//    Example 1:
//
//    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//    Output: 11
//    Explanation: The triangle looks like:
//    2
//    3 4
//    6 5 7
//    4 1 8 3
//    The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
//    Example 2:
//
//    Input: triangle = [[-10]]
//    Output: -10
//
//
//    Constraints:
//
//    1 <= triangle.length <= 200
//    triangle[0].length == 1
//    triangle[i].length == triangle[i - 1].length + 1
//    -104 <= triangle[i][j] <= 104
//
//
//    Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?