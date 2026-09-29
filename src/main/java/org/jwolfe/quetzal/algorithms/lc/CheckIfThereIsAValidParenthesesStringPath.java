package org.jwolfe.quetzal.algorithms.lc;

public class  CheckIfThereIsAValidParenthesesStringPath {
    class Solution {
        public boolean hasValidPath(char[][] grid) {
            if (grid == null) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            int pathLength = m + n - 1;

            if (pathLength % 2 != 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
                return false;
            }

            Boolean[][][] memo = new Boolean[m][n][pathLength + 1];
            return hasValidPath(grid, m, n, 0, 0, 0, memo);
        }

        private boolean hasValidPath(char[][] grid, int m, int n, int r, int c, int openCount, Boolean[][][] memo) {
            if (r == m || c == n) {
                return false;
            }

            openCount += (grid[r][c] == '(') ? 1 : -1;
            if (openCount < 0) {
                return false;
            }

            if (r == m - 1 && c == n - 1) {
                return openCount == 0;
            }

            if (memo[r][c][openCount] != null) {
                return memo[r][c][openCount];
            }

            if (hasValidPath(grid, m, n, r + 1, c, openCount, memo)) {
                return memo[r][c][openCount] = true;
            }

            return memo[r][c][openCount] = hasValidPath(grid, m, n, r, c + 1, openCount, memo);
        }
    }

    class Solution_TLE {
        public boolean hasValidPath(char[][] grid) {
            if (grid == null) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            int pathLength = m + n - 1;

            if (pathLength % 2 != 0 || grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
                return false;
            }

            return hasValidPath(grid, m, n, 0, 0, 0);
        }

        private boolean hasValidPath(char[][] grid, int m, int n, int r, int c, int openCount) {
            if (r == m || c == n) {
                return false;
            }

            openCount += (grid[r][c] == '(') ? 1 : -1;
            if (openCount < 0) {
                return false;
            }

            if (r == m - 1 && c == n - 1) {
                return openCount == 0;
            }

            if (hasValidPath(grid, m, n, r + 1, c, openCount)) {
                return true;
            }

            return hasValidPath(grid, m, n, r, c + 1, openCount);
        }
    }
}

//    2267. Check if There Is a Valid Parentheses String Path
//    Hard
//    A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
//
//    It is ().
//    It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
//    It can be written as (A), where A is a valid parentheses string.
//    You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:
//
//    The path starts from the upper left cell (0, 0).
//    The path ends at the bottom-right cell (m - 1, n - 1).
//    The path only ever moves down or right.
//    The resulting parentheses string formed by the path is valid.
//    Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.
//
//
//
//    Example 1:
//
//
//    Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
//    Output: true
//    Explanation: The above diagram shows two possible paths that form valid parentheses strings.
//    The first path shown results in the valid parentheses string "()(())".
//    The second path shown results in the valid parentheses string "((()))".
//    Note that there may be other valid parentheses string paths.
//    Example 2:
//
//
//    Input: grid = [[")",")"],["(","("]]
//    Output: false
//    Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 100
//    grid[i][j] is either '(' or ')'.