package org.jwolfe.quetzal.algorithms.lc;

public class LargestTriangleArea {
    class Solution {
        public double largestTriangleArea(int[][] points) {
            if (points == null || points.length < 3) {
                return 0d;
            }

            double maxArea = 0;
            int n = points.length;
            for (int i = 0; i < n - 2; i++) {
                int[] p1 = points[i];

                for (int j = i + 1; j < n - 1; j++) {
                    int[] p2 = points[j];

                    for (int k = j + 1; k < n; k++) {
                        int[] p3 = points[k];

                        double area = findArea(p1, p2, p3);
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }

            return maxArea;
        }

        private double findArea(int[] p1, int[] p2, int[] p3) {
            int x1 = p1[0];
            int y1 = p1[1];

            int x2 = p2[0];
            int y2 = p2[1];

            int x3 = p3[0];
            int y3 = p3[1];

            return Math.abs(1d * (x1 * y2) + (x2 * y3) + (x3 * y1) - (x2 * y1) - (x3 * y2) - (x1 * y3)) / 2;
        }
    }
}

//    812. Largest Triangle Area
//    Easy
//    Given an array of points on the X-Y plane points where points[i] = [xi, yi], return the area of the largest triangle that can be formed by any three different points. Answers within 10-5 of the actual answer will be accepted.
//
//
//
//    Example 1:
//
//
//    Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
//    Output: 2.00000
//    Explanation: The five points are shown in the above figure. The red triangle is the largest.
//    Example 2:
//
//    Input: points = [[1,0],[0,0],[0,1]]
//    Output: 0.50000
//
//
//    Constraints:
//
//    3 <= points.length <= 50
//    -50 <= xi, yi <= 50
//    All the given points are unique.
