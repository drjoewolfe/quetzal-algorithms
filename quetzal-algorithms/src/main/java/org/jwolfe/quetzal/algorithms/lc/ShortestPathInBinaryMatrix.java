package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        boolean[][] visited = new boolean[n][n];

        int[][] directions = new int[][] {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        visited[0][0] = true;
        int level = 1;

        while(!queue.isEmpty()) {
            for(int l = queue.size(); l > 0; l--) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];

                if(x == n - 1 && y == n - 1) {
                    return level;
                }

                for(int[] step : directions) {
                    int dx = step[0];
                    int dy = step[1];

                    int nr = x + dx;
                    int nc = y + dy;

                    if(nr < 0 || nc < 0 || nr == n || nc == n || grid[nr][nc] == 1 || visited[nr][nc]) {
                        continue;
                    }

                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }

            level++;
        }

        return -1;
    }
}

//    1091. Shortest Path in Binary Matrix
//    Medium

//    In an N by N square grid, each cell is either empty (0) or blocked (1).
//
//    A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
//
//    Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
//    C_1 is at location (0, 0) (ie. has value grid[0][0])
//    C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
//    If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
//    Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
//
//
//
//    Example 1:
//
//    Input: [[0,1],[1,0]]
//
//
//    Output: 2
//
//    Example 2:
//
//    Input: [[0,0,0],[1,1,0],[1,1,0]]
//
//
//    Output: 4
//
//
//
//    Note:
//
//    1 <= grid.length == grid[0].length <= 100
//    grid[r][c] is 0 or 1