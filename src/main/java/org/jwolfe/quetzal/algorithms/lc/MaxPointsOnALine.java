package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    class Solution {
        public int maxPoints(int[][] points) {
            if(points == null || points.length == 0) {
                return 0;
            }

            int maxCount = 1;
            for(int i = 0; i < points.length - 1; i++) {
                maxCount = Math.max(maxCount,
                        maxPoints(points, i));
            }

            return maxCount;
        }

        private int maxPoints(int[][] points, int index) {
            int[] point1 = points[index];

            int maxPoints = 1;

            Map<String, Integer> slopeCounts = new HashMap<>();
            for(int i = index + 1; i < points.length; i++) {
                int[] point2 = points[i];

                String slopeMemento = getSlope(point1, point2);
                slopeCounts.put(slopeMemento, slopeCounts.getOrDefault(slopeMemento, 1) + 1);
                maxPoints = Math.max(maxPoints, slopeCounts.get(slopeMemento));
            }

            return maxPoints;
        }

        private String getSlope(int[] point1, int[] point2) {
            int x1 = point1[0];
            int y1 = point1[1];

            int x2 = point2[0];
            int y2 = point2[1];

            int numerator = y2 - y1;
            int denominator = x2 - x1;

            int gcd = getGcd(numerator, denominator);

            numerator /= gcd;
            denominator /= gcd;

            return Integer.toString(numerator) + ":" + Integer.toString(denominator);
        }

        private int getGcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return getGcd(y, x % y);
        }
    }

// [[1,1],[2,2],[3,3]]
// [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
}

//    149. Max Points on a Line
//    Hard
//    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
//
//
//
//    Example 1:
//
//
//    Input: points = [[1,1],[2,2],[3,3]]
//    Output: 3
//    Example 2:
//
//
//    Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//    Output: 4
//
//
//    Constraints:
//
//    1 <= points.length <= 300
//    points[i].length == 2
//    -104 <= xi, yi <= 104
//    All the points are unique.