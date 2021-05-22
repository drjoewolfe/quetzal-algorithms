package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> solutions = new ArrayList<>();

            if(n < 1) {
                return solutions;
            }

            boolean[][] board = new boolean[n][n];
            solveNQueens(board, 0, n, solutions);

            return solutions;
        }

        private void solveNQueens(boolean[][] board, int x, int n, List<List<String>> solutions) {
            if(x == n) {
                // Solved
                addBoardToSolution(board, n, solutions);
                return;
            }

            for(int y = 0; y < n; y++) {
                if(isSafeForQueen(board, x, y, n)) {

                    board[x][y] = true;
                    solveNQueens(board, x + 1, n, solutions);

                    // Backtrack
                    board[x][y] = false;
                }
            }
        }

        private boolean isSafeForQueen(boolean[][] board, int x, int y, int n) {
            // Check row & column
            for(int k = 0; k < n; k++) {
                if(board[x][k] || board[k][y]) {
                    return false;
                }
            }

            // Check diagonal #1
            for(int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
                if(board[i][j]) {
                    return false;
                }
            }

            // Check diagonal #2
            for(int i = x, j = y; i >= 0 && j < n; i--, j++) {
                if(board[i][j]) {
                    return false;
                }
            }

            return true;
        }

        private void addBoardToSolution(boolean[][] board, int n, List<List<String>> solutions) {
            List<String> solution = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                StringBuilder rowString = new StringBuilder();

                for(int j = 0; j < n; j++) {
                    if(board[i][j]) {
                        rowString.append("Q");
                    } else {
                        rowString.append(".");
                    }
                }

                solution.add(rowString.toString());
            }

            solutions.add(solution);
        }
    }

    class Solution_A2 {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> solution = new ArrayList<>();
            if(n < 1) {
                return solution;
            }

            boolean[][] board = new boolean[n][n];
            solveNQueens(board, n, 0, solution);
            return solution;
        }

        private void solveNQueens(boolean[][] board, int n, int r, List<List<String>> solution) {
            if(r == n) {
                // Solved !
                solution.add(getStringRepresentation(board));
                return;
            }

            for(int c = 0; c < n; c++) {
                if(isSafeForQueen(board, n, r, c)) {
                    board[r][c] = true;
                    solveNQueens(board, n, r + 1, solution);

                    board[r][c] = false;
                }
            }
        }

        private List<String> getStringRepresentation(boolean[][] board) {
            List<String> result = new ArrayList<>();
            for(var row : board) {
                StringBuilder rowBuilder = new StringBuilder();
                for(var cell : row) {
                    if(cell) {
                        rowBuilder.append('Q');
                    } else {
                        rowBuilder.append('.');
                    }
                }

                result.add(rowBuilder.toString());
            }

            return result;
        }

        private boolean isSafeForQueen(boolean[][] board, int n, int r, int c) {
            // Row
            for(int j = 0; j < c; j++) {
                if(board[r][j]) {
                    return false;
                }
            }

            // Column
            for(int i = 0; i < r; i++) {
                if(board[i][c]) {
                    return false;
                }
            }

            // Left Diagonal
            for(int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
                if(board[i][j]) {
                    return false;
                }
            }

            // Right Diagonal
            for(int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
                if(board[i][j]) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    51. N-Queens
//    Hard
//    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
//    Given an integer n, return all distinct solutions to the n-queens puzzle.
//
//    Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
//
//
//
//    Example 1:
//
//
//    Input: n = 4
//    Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
//    Example 2:
//
//    Input: n = 1
//    Output: [["Q"]]
//
//
//    Constraints:
//
//    1 <= n <= 9