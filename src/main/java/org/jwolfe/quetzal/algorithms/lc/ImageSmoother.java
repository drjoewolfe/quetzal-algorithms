package org.jwolfe.quetzal.algorithms.lc;

public class ImageSmoother {
    class Solution {
        public int[][] imageSmoother(int[][] M) {
            if(M == null || M.length == 0 || M[0].length == 0) {
                return M;
            }

            int m = M.length;
            int n = M[0].length;

            int[][] smoothened = new int[m][n];

            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    int sum = 0;
                    int count = 0;

                    for(int i = r - 1; i <= r + 1; i++) {
                        for(int j = c - 1; j <= c + 1; j++) {
                            if(i < 0 || j < 0 || i == m || j == n) {
                                continue;
                            }

                            sum += M[i][j];
                            count++;
                        }
                    }


                    smoothened[r][c] = sum / count;
                }
            }

            return smoothened;
        }
    }

    class Solution_Correct_1 {
        public int[][] imageSmoother(int[][] M) {
            if(M == null || M.length == 0 || M[0].length == 0) {
                return M;
            }

            int rowCount = M.length;
            int columnCount = M[0].length;

            int[][] smooth = new int[rowCount][columnCount];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    double total = 0;
                    double set = 0;

                    for(int nr = r - 1; nr <= r + 1; nr++) {
                        for(int nc = c - 1; nc <= c + 1; nc++) {
                            if(nr >= 0 && nr < rowCount
                                    && nc >= 0 && nc < columnCount) {
                                total++;
                                set += M[nr][nc];
                            }
                        }
                    }

                    smooth[r][c] = (int) Math.floor(set / total);
                }
            }

            return smooth;
        }
    }

    class Solution_Brute {
        public int[][] imageSmoother(int[][] M) {
            if(M == null || M.length == 0 || M[0].length == 0) {
                return M;
            }

            int rowCount = M.length;
            int columnCount = M[0].length;

            int[][] smooth = new int[rowCount][columnCount];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    double total = 0;
                    double set = 0;

                    if(r > 0 && c > 0) {
                        total++;
                        set += M[r - 1][c - 1];
                    }

                    if(r > 0) {
                        total++;
                        set += M[r - 1][c];
                    }

                    if(c > 0) {
                        total++;
                        set += M[r][c - 1];
                    }

                    if(r < rowCount - 1 && c < columnCount - 1) {
                        total++;
                        set += M[r + 1][c + 1];
                    }

                    if(r < rowCount - 1) {
                        total++;
                        set += M[r + 1][c];

                        if(c > 0) {
                            total++;
                            set += M[r + 1][c - 1];
                        }
                    }

                    if(c < columnCount - 1) {
                        total++;
                        set += M[r][c + 1];

                        if( r > 0) {
                            total++;
                            set += M[r - 1][c + 1];
                        }
                    }

                    smooth[r][c] = (int) Math.floor(set / total);
                }
            }

            return smooth;
        }
    }
}

//    661. Image Smoother
//    Easy
//    An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).
//
//
//    Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.
//
//
//
//    Example 1:
//
//
//    Input: img = [[1,1,1],[1,0,1],[1,1,1]]
//    Output: [[0,0,0],[0,0,0],[0,0,0]]
//    Explanation:
//    For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
//    For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
//    For the point (1,1): floor(8/9) = floor(0.88888889) = 0
//    Example 2:
//
//
//    Input: img = [[100,200,100],[200,50,200],[100,200,100]]
//    Output: [[137,141,137],[141,138,141],[137,141,137]]
//    Explanation:
//    For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
//    For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
//    For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
//
//
//    Constraints:
//
//    m == img.length
//    n == img[i].length
//    1 <= m, n <= 200
//    0 <= img[i][j] <= 255
