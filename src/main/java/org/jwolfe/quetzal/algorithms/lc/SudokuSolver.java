package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SudokuSolver {
    class Solution {
        public void solveSudoku(char[][] board) {
            if(board == null || board.length != 9 || board[0].length != 9) {
                return;
            }

            solveSoduku(board, 0);
        }

        private boolean solveSoduku(char[][] board, int cell) {
            int row = 0;
            int column = 0;

            for(; cell < 81; cell++) {
                row = cell / 9;
                column = cell % 9;

                if(board[row][column] == '.') {
                    break;
                }
            }

            if(cell == 81) {
                return true;
            }

            for(char cn = '1'; cn <= '9'; cn++) {
                if(isValid(board, row, column, cn)) {
                    board[row][column] = cn;
                    boolean rv = solveSoduku(board, cell + 1);
                    if(rv) {
                        return true;
                    }

                    board[row][column] = '.';
                }
            }

            return false;
        }

        private void print(char[][] board) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 9; c++) {
                    System.out.print(board[r][c] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }

        private boolean isValid(char[][] board, int row, int column, char cn) {
            int rowSum = 0;
            int columnSum = 0;

            for(int i = 0; i < 9; i++) {
                if(board[row][i] == cn) {
                    return false;
                }

                if(board[i][column] == cn) {
                    return false;
                }

                if(board[row][i] != '.') {
                    rowSum += (board[row][i] - '0');
                }

                if(board[i][column] != '.') {
                    columnSum += (board[i][column] - '0');
                }
            }

            if(rowSum > 45 || columnSum > 45) {
                return false;
            }

            int blockSum = 0;
            int blockRow = (row / 3) * 3;
            int blockColumn = (column / 3) * 3;
            for(int i = 0; i < 3; i++) {
                int r = blockRow + i;
                for(int j = 0; j < 3; j++) {
                    int c = blockColumn + j;
                    if(board[r][c] == cn) {
                        return false;
                    }

                    if(board[r][c] != '.') {
                        blockSum += (board[r][c] - '0');
                    }
                }
            }

            if(blockSum > 45) {
                return false;
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public void solveSudoku(char[][] board) {
            if(board == null || board.length != 9 || board[0].length != 9) {
                return;
            }

            Map<Integer, Set<Integer>> rowMap = constructMap();
            Map<Integer, Set<Integer>> colMap = constructMap();
            Map<Integer, Set<Integer>> boxMap = constructMap();

            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(board[i][j] == '.') {
                        continue;
                    }

                    int val = board[i][j] - '0';
                    int box = getBox(i, j);

                    rowMap.get(i).add(val);
                    colMap.get(j).add(val);
                    boxMap.get(box).add(val);
                }
            }

            solveSoduku(board, 0, rowMap, colMap, boxMap);
        }

        private boolean solveSoduku(char[][] board, int cell, Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Map<Integer, Set<Integer>> boxMap) {
            if(cell == 81) {
                // Solved
                return true;
            }

            int row = cell / 9;
            int col = cell % 9;
            int box = getBox(row, col);

            if(board[row][col] == '.') {
                for(int val = 1; val <= 9; val++) {
                    if(rowMap.get(row).contains(val)
                            || colMap.get(col).contains(val)
                            || boxMap.get(box).contains(val)) {
                        continue;
                    }

                    // Try with val at row & col
                    board[row][col] = (char) (val + '0');
                    rowMap.get(row).add(val);
                    colMap.get(col).add(val);
                    boxMap.get(box).add(val);

                    if(solveSoduku(board, cell + 1, rowMap, colMap, boxMap)) {
                        return true;
                    }

                    // Backtrack
                    board[row][col] = '.';
                    rowMap.get(row).remove(val);
                    colMap.get(col).remove(val);
                    boxMap.get(box).remove(val);
                }

            } else {
                return solveSoduku(board, cell + 1, rowMap, colMap, boxMap);
            }

            return false;
        }

        private Map<Integer, Set<Integer>> constructMap() {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for(int i = 0; i < 9; i++) {
                map.put(i, new HashSet<>());
            }

            return map;
        }

        private int getBox(int row, int col) {
            return (row / 3) * 3 + (col / 3);
        }

        private void print(char[][] board) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}

//    37. Sudoku Solver
//    Hard
//    Write a program to solve a Sudoku puzzle by filling the empty cells.
//
//    A sudoku solution must satisfy all of the following rules:
//
//    Each of the digits 1-9 must occur exactly once in each row.
//    Each of the digits 1-9 must occur exactly once in each column.
//    Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
//    The '.' character indicates empty cells.
//
//
//
//    Example 1:
//
//
//    Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
//    Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
//    Explanation: The input board is shown above and the only valid solution is shown below:
//
//
//
//
//    Constraints:
//
//    board.length == 9
//    board[i].length == 9
//    board[i][j] is a digit or '.'.
//    It is guaranteed that the input board has only one solution.
