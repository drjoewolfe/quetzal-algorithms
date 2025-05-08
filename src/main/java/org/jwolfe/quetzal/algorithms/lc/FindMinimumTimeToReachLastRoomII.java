package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinimumTimeToReachLastRoomII {
    class Solution {
        private int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        public int minTimeToReach(int[][] moveTime) {
            if (moveTime == null || moveTime.length == 0 || moveTime[0].length == 0) {
                return 0;
            }

            int m = moveTime.length;
            int n = moveTime[0].length;

            int[][] time = new int[m][n];
            for (int[] row : time) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            boolean[][] visited = new boolean[m][n];
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

            time[0][0] = 0;
            heap.offer(new int[]{0, 0, 0, 0});

            while (!heap.isEmpty()) {
                int[] node = heap.poll();

                int r = node[0];
                int c = node[1];

                if (visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;
                int flag = node[3];

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    int t = Math.max(time[r][c], moveTime[nr][nc]) + ((flag == 0) ? 1 : 2);
                    if (time[nr][nc] > t) {
                        time[nr][nc] = t;
                        heap.offer(new int[]{nr, nc, t, ((flag == 0) ? 1 : 0)});
                    }
                }
            }

            return time[m - 1][n - 1];
        }

        private void print(int[][] matrix) {
            for (int[] row : matrix) {
                for (int val : row) {
                    System.out.print(val + " ");
                }

                System.out.println();
            }
        }
    }
}

//    3342. Find Minimum Time to Reach Last Room II
//    Medium
//    There is a dungeon with n x m rooms arranged as a grid.
//
//    You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.
//
//    Return the minimum time to reach the room (n - 1, m - 1).
//
//    Two rooms are adjacent if they share a common wall, either horizontally or vertically.
//
//
//
//    Example 1:
//
//    Input: moveTime = [[0,4],[4,4]]
//
//    Output: 7
//
//    Explanation:
//
//    The minimum time required is 7 seconds.
//
//    At time t == 4, move from room (0, 0) to room (1, 0) in one second.
//    At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.
//    Example 2:
//
//    Input: moveTime = [[0,0,0,0],[0,0,0,0]]
//
//    Output: 6
//
//    Explanation:
//
//    The minimum time required is 6 seconds.
//
//    At time t == 0, move from room (0, 0) to room (1, 0) in one second.
//    At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
//    At time t == 3, move from room (1, 1) to room (1, 2) in one second.
//    At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.
//    Example 3:
//
//    Input: moveTime = [[0,1],[1,2]]
//
//    Output: 4
//
//
//
//    Constraints:
//
//    2 <= n == moveTime.length <= 750
//    2 <= m == moveTime[i].length <= 750
//    0 <= moveTime[i][j] <= 109