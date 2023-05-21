package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    class Solution {
        private int[][] DIRECTIONS = new int[][] { {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        public int shortestBridge(int[][] A) {
            if(A == null || A.length == 0 || A[0].length == 0) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length;

            Queue<int[]> queue = new LinkedList<>();
            boolean islandFound = false;

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int cell = A[r][c];
                    if(cell == 1) {
                        // Found the first island - mark & add the co-ordinates to queue
                        markIsland(A, m, n, r, c, queue);
                        islandFound = true;
                        break;
                    }
                }

                if(islandFound) {
                    break;
                }
            }

            int distance = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int[] coordinate = queue.poll();
                    int x = coordinate[0];
                    int y = coordinate[1];

                    for(int[] direction : DIRECTIONS) {
                        int nx = x + direction[0];
                        int ny = y + direction[1];

                        if(nx >= 0 && nx < m
                                && ny >= 0 && ny < n) {

                            if(A[nx][ny] == 1) {
                                return distance;
                            } else if(A[nx][ny] == 0) {
                                A[nx][ny] = -1;
                                queue.offer(new int[] {nx, ny});
                            }
                        }
                    }
                }

                distance++;
            }

            return distance;
        }

        private void markIsland(int[][] A, int m, int n, int x, int y, Queue<int[]> queue) {
            if(x < 0 || x == m || y < 0 || y == n || A[x][y] != 1) {
                return;
            }

            A[x][y] = -1;
            queue.offer(new int[] {x, y});
            for(int[] direction : DIRECTIONS) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                markIsland(A, m, n, nx, ny, queue);
            }
        }

        private void print(int[][] A) {
            for(int[] row : A) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

// [[0,1],[1,0]]
// [[0,1,0],[0,0,0],[0,0,1]]
}

//    934. Shortest Bridge
//    Medium
//    You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
//
//    An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
//
//    You may change 0's to 1's to connect the two islands to form one island.
//
//    Return the smallest number of 0's you must flip to connect the two islands.
//
//
//
//    Example 1:
//
//    Input: grid = [[0,1],[1,0]]
//    Output: 1
//    Example 2:
//
//    Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
//    Output: 2
//    Example 3:
//
//    Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//    Output: 1
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    2 <= n <= 100
//    grid[i][j] is either 0 or 1.
//    There are exactly two islands in grid.