package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NearestExitFromEntranceInMaze {
    class Solution {
        public int nearestExit(char[][] maze, int[] entrance) {
            if(maze == null || maze.length == 0 || entrance == null) {
                return -1;
            }

            int m = maze.length;
            int n = maze[0].length;

            Point start = new Point(entrance);

            Queue<Point> queue = new LinkedList<>();
            queue.offer(start);

            Set<Point> visited = new HashSet<>();
            visited.add(start);

            int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            int steps = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    Point point = queue.poll();

                    if((point.x == 0 || point.y == 0 || point.x == m - 1 || point.y == n - 1)
                            && point != start) {
                        return steps;
                    }

                    for(int[] direction : directions) {
                        Point next = new Point(point.x + direction[0], point.y + direction[1]);

                        if(next.x >= 0 && next.x < m && next.y >= 0 && next.y < n
                                && maze[next.x][next.y] == '.'
                                && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }

                steps++;
            }

            return -1;
        }

        private class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Point(int[] ordinate) {
                this.x = ordinate[0];
                this.y = ordinate[1];
            }

            public boolean equals(Object other) {
                if(other instanceof Point) {
                    Point o = (Point) other;
                    return this.x == o.x && this.y == o.y;
                }

                return false;
            }

            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }

            public String toString() {
                return "(" + this.x + ", " + this.y + ")";
            }
        }

        private void print(int[] point) {
            System.out.println(point[0] + ", " + point[1]);
        }
    }

// [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]]
// [1,2]

// [["+","+","+"],[".",".","."],["+","+","+"]]
// [1,0]

// [["+",".","+","+","+","+","+"],["+",".","+",".",".",".","+"],["+",".","+",".","+",".","+"],["+",".",".",".",".",".","+"],["+","+","+","+",".","+","."]]
// [0,1]
}

//    1926. Nearest Exit from Entrance in Maze
//    Medium
//    You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
//
//    In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
//
//    Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
//
//
//
//    Example 1:
//
//
//    Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
//    Output: 1
//    Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
//    Initially, you are at the entrance cell [1,2].
//    - You can reach [1,0] by moving 2 steps left.
//    - You can reach [0,2] by moving 1 step up.
//    It is impossible to reach [2,3] from the entrance.
//    Thus, the nearest exit is [0,2], which is 1 step away.
//    Example 2:
//
//
//    Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
//    Output: 2
//    Explanation: There is 1 exit in this maze at [1,2].
//    [1,0] does not count as an exit since it is the entrance cell.
//    Initially, you are at the entrance cell [1,0].
//    - You can reach [1,2] by moving 2 steps right.
//    Thus, the nearest exit is [1,2], which is 2 steps away.
//    Example 3:
//
//
//    Input: maze = [[".","+"]], entrance = [0,0]
//    Output: -1
//    Explanation: There are no exits in this maze.
//
//
//    Constraints:
//
//    maze.length == m
//    maze[i].length == n
//    1 <= m, n <= 100
//    maze[i][j] is either '.' or '+'.
//    entrance.length == 2
//    0 <= entrancerow < m
//    0 <= entrancecol < n
//    entrance will always be an empty cell.