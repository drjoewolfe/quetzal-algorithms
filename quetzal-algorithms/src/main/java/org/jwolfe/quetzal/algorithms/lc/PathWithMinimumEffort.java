package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        if(heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        int m = heights.length;
        int n = heights[0].length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[] {0, 0, 0});

        int[][] costs = new int[m][n];
        for(int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        boolean[][] visited = new boolean[m][n];
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()) {
            int[] element = queue.poll();
            int i = element[0];
            int j = element[1];
            int c = element[2];

            visited[i][j] = true;
            if(i == m - 1 && j == n - 1) {
                return c;
            }

            for(int[] direction : directions) {
                int x = i + direction[0];
                int y = j + direction[1];

                if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }

                int heightDifference = Math.abs(heights[x][y] - heights[i][j]);
                int maxHeightDifference = Math.max(c, heightDifference);

                if(maxHeightDifference < costs[x][y]) {
                    costs[x][y] = maxHeightDifference;
                    queue.offer(new int[] {x, y, costs[x][y]});
                }
            }
        }

        return costs[m - 1][n - 1];
    }
}

//    1631. Path With Minimum Effort
//    You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
//
//    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
//
//
//    Example 1:
//
//    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//    Output: 2
//    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
//
//    Example 2:
//    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//    Output: 1
//    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
//
//    Example 3:
//    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//    Output: 0
//    Explanation: This route does not require any effort.
//
//
//    Constraints:
//
//    rows == heights.length
//    columns == heights[i].length
//    1 <= rows, columns <= 100
//    1 <= heights[i][j] <= 106