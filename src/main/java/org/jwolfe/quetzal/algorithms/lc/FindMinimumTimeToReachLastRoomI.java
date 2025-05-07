package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinimumTimeToReachLastRoomI {
    class Solution {
        private int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        public int minTimeToReach(int[][] moveTime) {
            if (moveTime == null || moveTime.length == 0 || moveTime[0].length == 0) {
                return 0;
            }

            int m = moveTime.length;
            int n = moveTime[0].length;

            int[][] distance = new int[m][n];
            for (int[] row : distance) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            boolean[][] visited = new boolean[m][n];

            distance[0][0] = 0;

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            heap.offer(new int[]{0, 0, 0});

            while (!heap.isEmpty()) {
                int[] node = heap.poll();
                int r = node[0];
                int c = node[1];
                int d = node[2];

                if (visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    int nd = Math.max(moveTime[nr][nc], distance[r][c]) + 1;
                    if (distance[nr][nc] > nd) {
                        distance[nr][nc] = nd;
                        heap.offer(new int[]{nr, nc, nd});
                    }
                }
            }

            return distance[m - 1][n - 1];
        }
    }
}

//    3341. Find Minimum Time to Reach Last Room I
//    Medium
//    There is a dungeon with n x m rooms arranged as a grid.
//
//    You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
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
//    Output: 6
//
//    Explanation:
//
//    The minimum time required is 6 seconds.
//
//    At time t == 4, move from room (0, 0) to room (1, 0) in one second.
//    At time t == 5, move from room (1, 0) to room (1, 1) in one second.
//    Example 2:
//
//    Input: moveTime = [[0,0,0],[0,0,0]]
//
//    Output: 3
//
//    Explanation:
//
//    The minimum time required is 3 seconds.
//
//    At time t == 0, move from room (0, 0) to room (1, 0) in one second.
//    At time t == 1, move from room (1, 0) to room (1, 1) in one second.
//    At time t == 2, move from room (1, 1) to room (1, 2) in one second.
//    Example 3:
//
//    Input: moveTime = [[0,1],[1,2]]
//
//    Output: 3
//
//
//
//    Constraints:
//
//    2 <= n == moveTime.length <= 50
//    2 <= m == moveTime[i].length <= 50
//    0 <= moveTime[i][j] <= 109