package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageOverlap {
    class Solution {
        public int largestOverlap(int[][] A, int[][] B) {
            if(A == null || B == null || A.length != A[0].length || A.length == 0 || A.length != B.length || A[0].length != B[0].length) {
                return 0;
            }

            int n = A.length;
            var aCoordinates = getOneCoordinates(A);
            var bCoordinates = getOneCoordinates(B);

            int maxOverlap = 0;
            Map<Coordinate, Integer> map = new HashMap<>();
            for(var aOrdinate : aCoordinates) {
                for(var bOrdinate: bCoordinates) {
                    int dx = aOrdinate.x - bOrdinate.x;
                    int dy = aOrdinate.y - bOrdinate.y;

                    var vector = new Coordinate(dx, dy);
                    map.put(vector, map.getOrDefault(vector, 0) + 1);
                    maxOverlap = Math.max(maxOverlap, map.get(vector));
                }
            }

            return maxOverlap;
        }

        private List<Coordinate> getOneCoordinates(int[][] matrix) {
            List<Coordinate> coordinates = new ArrayList<>();

            int n = matrix.length;
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    int val = matrix[r][c];
                    if(val == 1) {
                        coordinates.add(new Coordinate(r, c));
                    }
                }
            }

            return coordinates;
        }

        private class Coordinate {
            int x;
            int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if(o == null) {
                    return false;
                }

                if(this == o) {
                    return true;
                }

                if(o instanceof Coordinate) {
                    Coordinate other = (Coordinate) o;
                    return this.x == other.x && this.y == other.y;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }
        }
    }

    class Solution_Correct_1 {
        public int largestOverlap(int[][] A, int[][] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length;

            List<Coordinates> aCoordinates = getCoordinatesOfOnes(A);
            List<Coordinates> bCoordinates = getCoordinatesOfOnes(B);

            int maxOverlap = 0;
            Map<Coordinates, Integer> transformationCounts = new HashMap<>();
            for(Coordinates ac : aCoordinates) {
                for(Coordinates bc : bCoordinates) {
                    int dx = ac.x - bc.x;
                    int dy = ac.y - bc.y;

                    Coordinates transformationVector = new Coordinates(dx, dy);
                    transformationCounts.put(transformationVector, transformationCounts.getOrDefault(transformationVector, 0) + 1);
                    maxOverlap = Math.max(maxOverlap, transformationCounts.get(transformationVector));
                }
            }

            return maxOverlap;
        }

        private List<Coordinates> getCoordinatesOfOnes(int[][] A) {
            List<Coordinates> coordinates = new ArrayList<>();
            for(int i = 0; i < A.length; i++) {
                for(int j = 0; j < A[i].length; j++) {
                    if(A[i][j] == 1) {
                        coordinates.add(new Coordinates(i, j));
                    }
                }
            }

            return coordinates;
        }

        class Coordinates {
            int x;
            int y;

            public Coordinates(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if(this == o) {
                    return true;
                }

                if(o == null) {
                    return false;
                }


                if(o instanceof Coordinates) {
                    Coordinates oc = (Coordinates) o;
                    return (this.x == oc.x) && (this.y == oc.y);
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }
        }
    }

    class Solution_Counting {
        public int largestOverlap(int[][] A, int[][] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length;

            int maxOverlap = 0;
            int overlap = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    overlap = getOverlap(A, B, m, n, i, j);
                    maxOverlap = Math.max(maxOverlap, overlap);

                    overlap = getOverlap(B, A, m, n, i, j);
                    maxOverlap = Math.max(maxOverlap, overlap);
                }
            }

            return maxOverlap;
        }

        private int getOverlap(int[][] A, int[][] B, int m, int n, int x, int y) {
            int overlap = 0;
            for(int i = 0; i < m - x; i++) {
                for(int j = 0; j < n - y; j++) {
                    int a = A[i][j];
                    int b = B[x + i][y + j];

                    if(a == 1 && b == 1) {
                        overlap++;
                    }
                }
            }

            return overlap;
        }
    }

    class Solution_Attempt1 {
        public int largestOverlap(int[][] A, int[][] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length;

            int maxOverlap = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int overlap = getOverlap(A, B, m, n, i, j);
                    maxOverlap = Math.max(maxOverlap, overlap);
                }
            }

            return maxOverlap;
        }

        private int[][] clone(int A[][], int m, int n) {
            int[][] AC = new int[m][];
            for(int i = 0; i < n; i++) {
                AC[i] = A[i].clone();
            }

            return AC;
        }

        private int getOverlap(int[][] A, int[][] B, int m, int n, int x, int y) {
            int overlap = 0;
            for(int i = 0; i < m - x; i++) {
                for(int j = 0; j < n - y; j++) {
                    int a = A[i][j];
                    int b = B[x + i][y + j];

                    if(a == 1 && b == 1) {
                        overlap++;
                    }
                }
            }

            return overlap;
        }
    }
}

//    835. Image Overlap
//    Medium
//    You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
//
//    We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
//
//    Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
//
//    Return the largest possible overlap.
//
//
//
//    Example 1:
//
//
//    Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
//    Output: 3
//    Explanation: We translate img1 to right by 1 unit and down by 1 unit.
//
//    The number of positions that have a 1 in both images is 3 (shown in red).
//
//    Example 2:
//
//    Input: img1 = [[1]], img2 = [[1]]
//    Output: 1
//    Example 3:
//
//    Input: img1 = [[0]], img2 = [[0]]
//    Output: 0
//
//
//    Constraints:
//
//    n == img1.length == img1[i].length
//    n == img2.length == img2[i].length
//    1 <= n <= 30
//    img1[i][j] is either 0 or 1.
//    img2[i][j] is either 0 or 1.