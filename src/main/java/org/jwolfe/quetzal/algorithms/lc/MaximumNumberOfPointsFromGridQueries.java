package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberOfPointsFromGridQueries {
    class Solution {
        private int[][] DIRECTIONS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        public int[] maxPoints(int[][] grid, int[] queries) {
            if (grid == null || grid.length == 0 || grid[0].length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int k = queries.length;
            int m = grid.length;
            int n = grid[0].length;

            int[][] sortedQueries = new int[k][2];
            for (int i = 0; i < k; i++) {
                sortedQueries[i] = new int[]{queries[i], i};
            }

            Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            minHeap.offer(new int[]{grid[0][0], 0, 0});

            int[] results = new int[k];
            int points = 0;

            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;

            for (int i = 0; i < k; i++) {
                int[] query = sortedQueries[i];
                int value = query[0];
                int index = query[1];
                while (!minHeap.isEmpty()
                        && minHeap.peek()[0] < value) {
                    points++;

                    int[] element = minHeap.poll();
                    int r = element[1];
                    int c = element[2];

                    for (int[] direction : DIRECTIONS) {
                        int nr = r + direction[0];
                        int nc = c + direction[1];

                        if (nr >= 0 && nr < m
                                && nc >= 0 && nc < n
                                && !visited[nr][nc]) {
                            minHeap.offer(new int[]{grid[nr][nc], nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }

                results[index] = points;
            }

            return results;
        }
    }
}

//    2503. Maximum Number of Points From Grid Queries
//    Hard
//    You are given an m x n integer matrix grid and an array queries of size k.
//
//    Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:
//
//    If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
//    Otherwise, you do not get any points, and you end this process.
//    After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.
//
//    Return the resulting array answer.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
//    Output: [5,8,1]
//    Explanation: The diagrams above show which cells we visit to get points for each query.
//    Example 2:
//
//
//    Input: grid = [[5,2,1],[1,1,2]], queries = [3]
//    Output: [0]
//    Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    2 <= m, n <= 1000
//    4 <= m * n <= 105
//    k == queries.length
//    1 <= k <= 104
//    1 <= grid[i][j], queries[i] <= 106