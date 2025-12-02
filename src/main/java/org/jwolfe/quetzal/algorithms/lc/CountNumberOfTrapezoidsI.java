package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfTrapezoidsI {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countTrapezoids(int[][] points) {
            if (points == null || points.length < 4) {
                return 0;
            }

            Map<Integer, Integer> pointsByY = new HashMap<>();
            for (var point : points) {
                int y = point[1];
                pointsByY.put(y, pointsByY.getOrDefault(y, 0) + 1);
            }

            long count = 0;
            long prevEdges = 0;

            for (var entry : pointsByY.entrySet()) {
                int pointCount = entry.getValue();
                long edgeCount = 1L * pointCount * (pointCount - 1) / 2;
                edgeCount %= MOD;

                count += (edgeCount * prevEdges);
                count %= MOD;

                prevEdges += edgeCount;
                prevEdges %= MOD;
            }

            return (int) count;
        }
    }
}

//    3623. Count Number of Trapezoids I
//    Medium
//    You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point on the Cartesian plane.
//
//    A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal sides (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.
//
//    Return the number of unique horizontal trapezoids that can be formed by choosing any four distinct points from points.
//
//    Since the answer may be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
//
//    Output: 3
//
//    Explanation:
//
//
//
//    There are three distinct ways to pick four points that form a horizontal trapezoid:
//
//    Using points [1,0], [2,0], [3,2], and [2,2].
//    Using points [2,0], [3,0], [3,2], and [2,2].
//    Using points [1,0], [3,0], [3,2], and [2,2].
//    Example 2:
//
//    Input: points = [[0,0],[1,0],[0,1],[2,1]]
//
//    Output: 1
//
//    Explanation:
//
//
//
//    There is only one horizontal trapezoid that can be formed.
//
//
//
//    Constraints:
//
//    4 <= points.length <= 105
//    –108 <= xi, yi <= 108
//    All points are pairwise distinct.