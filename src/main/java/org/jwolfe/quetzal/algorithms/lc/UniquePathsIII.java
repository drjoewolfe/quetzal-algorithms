package org.jwolfe.quetzal.algorithms.lc;

public class UniquePathsIII {
    class Solution {
        public int uniquePathsIII(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int startRow = 0;
            int startColumn = 0;
            int emptyCellCount = 0;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == 1) {
                        startRow = i;
                        startColumn = j;

                        emptyCellCount++;
                    } else if(grid[i][j] == 0) {
                        emptyCellCount++;
                    }
                }
            }

            boolean[][] visited = new boolean[m][n];
            return uniquePaths(grid, startRow, startColumn, visited, emptyCellCount, 0);
        }

        private int uniquePaths(int[][] grid, int row, int col, boolean[][] visited, int emptyCellCount, int visitedCellCount) {
            if(row < 0 || row == grid.length || col < 0 || col == grid[0].length
                    || grid[row][col] == -1
                    || visited[row][col]) {
                return 0;
            }

            if(grid[row][col] == 2) {
                if(emptyCellCount == visitedCellCount) {
                    return 1;
                }

                return 0;
            }

            visited[row][col] = true;

            int count = 0;
            count += uniquePaths(grid, row + 1, col, visited, emptyCellCount, visitedCellCount + 1);
            count += uniquePaths(grid, row - 1, col, visited, emptyCellCount, visitedCellCount + 1);
            count += uniquePaths(grid, row, col + 1, visited, emptyCellCount, visitedCellCount + 1);
            count += uniquePaths(grid, row, col - 1, visited, emptyCellCount, visitedCellCount + 1);
            visited[row][col] = false;

            return count;
        }
    }

    class Solution_Correct_1 {
        int uniquePathCount;

        public int uniquePathsIII(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            uniquePathCount = 0;

            GridMetadata metadata = computeMetadata(grid);
            boolean[][] visited = new boolean[metadata.rowCount][metadata.colCount];

            computeUniquePathCount(grid, metadata.startRow, metadata.startCol, metadata, visited, 0);

            return uniquePathCount;
        }

        private GridMetadata computeMetadata(int[][] grid) {
            GridMetadata metadata = new GridMetadata();
            metadata.rowCount = grid.length;
            metadata.colCount = grid[0].length;

            for(int r = 0; r < grid.length; r++) {
                for(int c = 0; c < grid[0].length; c++) {
                    int val = grid[r][c];

                    if(val == 1) {
                        metadata.startRow = r;
                        metadata.startCol = c;
                    } else if(val == 2) {
                        metadata.endRow = r;
                        metadata.endCol = c;
                    } else if(val == 0) {
                        metadata.emptyCellCount++;
                    }
                }
            }

            return metadata;
        }

        private void computeUniquePathCount(int[][] grid, int row, int col, GridMetadata metadata, boolean[][] visited, int currentCount) {
            if(row < 0 || row >= metadata.rowCount || col < 0 || col >= metadata.colCount || visited[row][col]
                    || grid[row][col] == -1) {
                return;
            }

            if(grid[row][col] == 2) {
                if(currentCount == metadata.emptyCellCount) {
                    uniquePathCount++;
                }

                return;
            }

            currentCount = grid[row][col] != 1 ? currentCount + 1 : currentCount;
            visited[row][col] = true;

            computeUniquePathCount(grid, row + 1, col, metadata, visited, currentCount);
            computeUniquePathCount(grid, row - 1, col, metadata, visited, currentCount);
            computeUniquePathCount(grid, row, col + 1, metadata, visited, currentCount);
            computeUniquePathCount(grid, row, col - 1, metadata, visited, currentCount);

            visited[row][col] = false;
        }

        private class GridMetadata {
            int rowCount;
            int colCount;

            int startRow;
            int startCol;

            int endRow;
            int endCol;

            int emptyCellCount;
        }
    }

// [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
// [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
}

//    980. Unique Paths III
//    Hard
//    You are given an m x n integer array grid where grid[i][j] could be:
//
//    1 representing the starting square. There is exactly one starting square.
//    2 representing the ending square. There is exactly one ending square.
//    0 representing empty squares we can walk over.
//    -1 representing obstacles that we cannot walk over.
//    Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//    Output: 2
//    Explanation: We have the following two paths:
//    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
//    2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
//    Example 2:
//
//
//    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
//    Output: 4
//    Explanation: We have the following four paths:
//    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
//    2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
//    3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
//    4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
//    Example 3:
//
//
//    Input: grid = [[0,1],[2,0]]
//    Output: 0
//    Explanation: There is no path that walks over every empty square exactly once.
//    Note that the starting and ending square can be anywhere in the grid.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 20
//    1 <= m * n <= 20
//    -1 <= grid[i][j] <= 2
//    There is exactly one starting cell and one ending cell.