package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            if (board == null || board.length != 9) {
                return false;
            }

            for (char[] row : board) {
                if (row.length != 9) {
                    return false;
                }
            }

            for (int i = 0; i < 9; i++) {
                if (!isRowValid(board, i)
                        || !isColumnValid(board, i)
                        || !isBoxValid(board, i)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isRowValid(char[][] board, int row) {
            Set<Character> set = new HashSet<>();
            for (int c = 0; c < 9; c++) {
                char val = board[row][c];
                if (val != '.'
                        && set.contains(val)) {
                    return false;
                }

                set.add(val);
            }

            return true;
        }

        private boolean isColumnValid(char[][] board, int column) {
            Set<Character> set = new HashSet<>();
            for (int r = 0; r < 9; r++) {
                char val = board[r][column];
                if (val != '.'
                        && set.contains(val)) {
                    return false;
                }

                set.add(val);
            }

            return true;
        }

        private boolean isBoxValid(char[][] board, int box) {
            int row = (box / 3) * 3;
            int column = (box % 3) * 3;

            Set<Character> set = new HashSet<>();
            for (int r = row; r < row + 3; r++) {
                for (int c = column; c < column + 3; c++) {
                    char val = board[r][c];
                    if (val != '.'
                            && set.contains(val)) {
                        return false;
                    }

                    set.add(val);
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean isValidSudoku(char[][] board) {
            if (board == null || board.length != 9) {
                return false;
            }

            for (char[] row : board) {
                if (row.length != 9) {
                    return false;
                }
            }

            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (board[r][c] == '.') {
                        continue;
                    }

                    if (!isRowValid(board, r, c)
                            || !isColumnValid(board, r, c)
                            || !isBoxValid(board, r, c)
                    ) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isRowValid(char[][] board, int row, int column) {
            for (int c = 0; c < 9; c++) {
                if (c == column) {
                    continue;
                }

                if (board[row][c] == board[row][column]) {
                    return false;
                }
            }

            return true;
        }

        private boolean isColumnValid(char[][] board, int row, int column) {
            for (int r = 0; r < 9; r++) {
                if (r == row) {
                    continue;
                }

                if (board[r][column] == board[row][column]) {
                    return false;
                }
            }

            return true;
        }

        private boolean isBoxValid(char[][] board, int row, int column) {
            int top = (row / 3) * 3;
            int left = (column / 3) * 3;

            for (int r = top; r < top + 3; r++) {
                for (int c = left; c < left + 3; c++) {
                    if (r == row && c == column) {
                        continue;
                    }

                    if (board[r][c] == board[row][column]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public boolean isValidSudoku(char[][] board) {
            if (board == null || board.length != 9 || board[0].length != 9) {
                return false;
            }

            boolean[] markers = new boolean[10];

            // Check rows
            for (int r = 0; r < 9; r++) {
                Arrays.fill(markers, false);

                for (int c = 0; c < 9; c++) {
                    if (board[r][c] == '.') {
                        continue;
                    }

                    int val = board[r][c] - '0';
                    if (markers[val]) {
                        return false;
                    }

                    markers[val] = true;
                }
            }

            // Check columns
            for (int c = 0; c < 9; c++) {
                Arrays.fill(markers, false);

                for (int r = 0; r < 9; r++) {
                    if (board[r][c] == '.') {
                        continue;
                    }

                    int val = board[r][c] - '0';
                    if (markers[val]) {
                        return false;
                    }

                    markers[val] = true;
                }
            }


            // Check boxes
            for (int box = 0; box < 9; box++) {
                Arrays.fill(markers, false);

                int br = (box / 3) * 3;
                int bc = (box % 3) * 3;

                for (int i = 0; i < 3; i++) {
                    int r = br + i;

                    for (int j = 0; j < 3; j++) {
                        int c = bc + j;

                        if (board[r][c] == '.') {
                            continue;
                        }

                        int val = board[r][c] - '0';
                        if (markers[val]) {
                            return false;
                        }

                        markers[val] = true;
                    }
                }
            }

            return true;
        }
    }
}

//    36. Valid Sudoku
//    Medium
//    Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//    Each row must contain the digits 1-9 without repetition.
//    Each column must contain the digits 1-9 without repetition.
//    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//    Note:
//
//    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
//    Only the filled cells need to be validated according to the mentioned rules.
//
//
//    Example 1:
//
//
//    Input: board =
//    [["5","3",".",".","7",".",".",".","."]
//    ,["6",".",".","1","9","5",".",".","."]
//    ,[".","9","8",".",".",".",".","6","."]
//    ,["8",".",".",".","6",".",".",".","3"]
//    ,["4",".",".","8",".","3",".",".","1"]
//    ,["7",".",".",".","2",".",".",".","6"]
//    ,[".","6",".",".",".",".","2","8","."]
//    ,[".",".",".","4","1","9",".",".","5"]
//    ,[".",".",".",".","8",".",".","7","9"]]
//    Output: true
//    Example 2:
//
//    Input: board =
//    [["8","3",".",".","7",".",".",".","."]
//    ,["6",".",".","1","9","5",".",".","."]
//    ,[".","9","8",".",".",".",".","6","."]
//    ,["8",".",".",".","6",".",".",".","3"]
//    ,["4",".",".","8",".","3",".",".","1"]
//    ,["7",".",".",".","2",".",".",".","6"]
//    ,[".","6",".",".",".",".","2","8","."]
//    ,[".",".",".","4","1","9",".",".","5"]
//    ,[".",".",".",".","8",".",".","7","9"]]
//    Output: false
//    Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
//
//
//    Constraints:
//
//    board.length == 9
//    board[i].length == 9
//    board[i][j] is a digit or '.'.