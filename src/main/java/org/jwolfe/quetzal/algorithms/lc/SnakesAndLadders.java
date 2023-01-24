package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    class Solution {
        public int snakesAndLadders(int[][] board) {
            if(board == null || board.length == 0 || board[0].length != board.length) {
                return -1;
            }

            int n = board[0].length;
            int count = n * n;

            int[][] coordinates = new int[count + 1][2];

            Integer[] columns = new Integer[n];
            for(int i = 0; i < n; i++) {
                columns[i] = i;
            }

            int cell = 1;
            for(int row = n - 1; row >= 0; row--) {
                for(int col : columns) {
                    coordinates[cell][0] = row;
                    coordinates[cell][1] = col;

                    cell++;
                }

                Collections.reverse(Arrays.asList(columns));
            }

            int[] distances = new int[count + 1];
            Arrays.fill(distances, -1);
            distances[1] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);

            while(!queue.isEmpty()) {
                cell = queue.poll();

                for(int next = cell + 1; next <= Math.min(cell + 6, count); next++) {
                    int row = coordinates[next][0];
                    int col = coordinates[next][1];

                    int destination = (board[row][col] == -1 ? next : board[row][col]);
                    if(distances[destination] == -1) {
                        distances[destination] = 1 + distances[cell];
                        queue.offer(destination);
                    }
                }
            }

            return distances[count];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(int[][] arr) {
            for(int[] a : arr) {
                System.out.print("[");
                for(int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

    class Solution_Attempt_2 {
        public int snakesAndLadders(int[][] board) {
            if(board == null || board.length == 0 || board[0].length != board.length) {
                return -1;
            }

            int n = board[0].length;
            int count = n * n;

            boolean[] visited = new boolean[count + 1];
            visited[1] = true;

            return snakesAndLadders(board, n, 1, count, visited);
        }

        private int snakesAndLadders(int[][] board, int n, int curr, int end, boolean[] visited) {
            if(curr == end) {
                return 0;
            }

            visited[curr] = true;

            int moves = Integer.MAX_VALUE;
            for(int next = curr + 1; next <= Math.min(curr + 6, end); next++) {
                if(visited[next]) {
                    continue;
                }

                System.out.println("\t" + next + " : " + (1 + snakesAndLadders(board, n, next, end, visited)));
                moves = Math.min(moves, 1 + snakesAndLadders(board, n, next, end, visited));
            }

            System.out.println(curr + ", " + moves);
            return moves;
        }
    }

    class Solution_Attempt_1 {
        public int snakesAndLadders(int[][] board) {
            if(board == null || board.length == 0 || board[0].length != board.length) {
                return -1;
            }

            int n = board.length;
            int count = n * n;

            boolean[] visited = new boolean[count + 1];
            visited[1] = true;
            return snakesAndLadders(board, n, 1, count, visited);
        }

        private int snakesAndLadders(int[][] board, int n, int position, int destination, boolean[] visited) {
            if(position == destination) {
                return 0;
            }

            for(int i = 1; i <= 6; i++) {
                int newPosition = position + i;

                if(newPosition > destination) {
                    break;
                }

                if(visited[newPosition]) {
                    continue;
                }

            }

            return -1;
        }
    }

// [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1]]
// [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
}

//    909. Snakes and Ladders
//    Medium
//    You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
//
//    You start on square 1 of the board. In each move, starting from square curr, do the following:
//
//    Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
//    This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
//    If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
//    The game ends when you reach the square n2.
//    A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
//
//    Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
//
//    For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
//    Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.
//
//
//
//    Example 1:
//
//
//    Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
//    Output: 4
//    Explanation:
//    In the beginning, you start at square 1 (at row 5, column 0).
//    You decide to move to square 2 and must take the ladder to square 15.
//    You then decide to move to square 17 and must take the snake to square 13.
//    You then decide to move to square 14 and must take the ladder to square 35.
//    You then decide to move to square 36, ending the game.
//    This is the lowest possible number of moves to reach the last square, so return 4.
//    Example 2:
//
//    Input: board = [[-1,-1],[-1,3]]
//    Output: 1
//
//
//    Constraints:
//
//    n == board.length == board[i].length
//    2 <= n <= 20
//    grid[i][j] is either -1 or in the range [1, n2].
//    The squares labeled 1 and n2 do not have any ladders or snakes.