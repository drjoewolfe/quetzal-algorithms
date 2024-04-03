package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {
    class Solution {
        public boolean exist(char[][] board, String word) {
            if(board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
                return false;
            }

            int m = board.length;
            int n = board[0].length;

            if(m * n < word.length()) {
                return false;
            }

            boolean[][] visited = new boolean[m][n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(search(board, i, j, word, 0, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean search(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
            if(index == word.length()) {
                return true;
            }

            if(row < 0 || row == board.length || col < 0 || col == board[0].length || visited[row][col]) {
                return false;
            }

            if(word.charAt(index) != board[row][col]) {
                return false;
            }

            visited[row][col] = true;
            boolean found = search(board, row - 1, col, word, index + 1, visited)
                    || search(board, row + 1, col, word, index + 1, visited)
                    || search(board, row, col - 1, word, index + 1, visited)
                    || search(board, row, col + 1, word, index + 1, visited);

            visited[row][col] = false;

            return found;
        }
    }

    class Solution_Correct_2 {
        public boolean exist(char[][] board, String word) {
            if(board == null || board.length == 0) {
                return false;
            }

            int m = board.length;
            int n = board[0].length;

            for(char[] row : board) {
                if(row.length == 0) {
                    return false;
                }
            }

            if(word.length() > m * n) {
                return false;
            }

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(search(board, r, c, word, 0, new HashSet<>())) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean search(char[][] board, int row, int col, String word, int index, Set<Integer> visited) {
            if(index == word.length()) {
                return true;
            }

            if(row < 0 || row == board.length || col < 0 || col == board[0].length) {
                return false;
            }

            int memento = row * 10 + col;
            if(visited.contains(memento)) {
                return false;
            }

            boolean found = false;

            visited.add(memento);
            if(board[row][col] == word.charAt(index)) {
                if(search(board, row - 1, col, word, index + 1, visited)
                        || search(board, row + 1, col, word, index + 1, visited)
                        || search(board, row, col - 1, word, index + 1, visited)
                        || search(board, row, col + 1, word, index + 1, visited)) {
                    found = true;
                }
            }

            visited.remove(memento);
            return found;
        }
    }

    class Solution_Correct_1 {
        public boolean exist(char[][] board, String word) {
            if(board == null || board.length == 0 || word == null || word.length() == 0) {
                return false;
            }

            int m = board.length;
            int n = board[0].length;

            for(char[] row : board) {
                if(row.length != n) {
                    return false;
                }
            }

            boolean[][] visited = new boolean[m][n];
            for(int r  = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    boolean rv = search(board, r, c, m, n, word, 0, visited);
                    if(rv) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean search(char[][] board, int r, int c, int m, int n, String word, int index, boolean[][] visited) {
            if(r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) {
                return false;
            }

            visited[r][c] = true;
            boolean rv = false;

            if(board[r][c] != word.charAt(index)) {
                rv = false;
            } else if(index == word.length() - 1) {
                rv = true;
            } else {
                rv = search(board, r + 1, c, m, n, word, index + 1, visited)
                        || search(board, r - 1, c, m, n, word, index + 1, visited)
                        || search(board, r, c + 1, m, n, word, index + 1, visited)
                        || search(board, r, c - 1, m, n, word, index + 1, visited);
            }

            visited[r][c] = false;
            return rv;
        }
    }

// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
// "ABCCED"
}

//    79. Word Search
//    Medium
//    Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//
//    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//
//
//    Example 1:
//
//
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
//    Output: true
//    Example 2:
//
//
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
//    Output: true
//    Example 3:
//
//
//    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
//    Output: false
//
//
//    Constraints:
//
//    m == board.length
//    n = board[i].length
//    1 <= m, n <= 6
//    1 <= word.length <= 15
//    board and word consists of only lowercase and uppercase English letters.
//
//
//    Follow up: Could you use search pruning to make your solution faster with a larger board?