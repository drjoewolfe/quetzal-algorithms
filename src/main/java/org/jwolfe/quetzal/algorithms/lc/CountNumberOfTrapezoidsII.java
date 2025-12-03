package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumberOfTrapezoidsII {
    class Solution {
        public int countTrapezoids(int[][] points) {
            if (points == null || points.length < 4) {
                return 0;
            }

            int count = 0;
            int n = points.length;

            double inf = 1_000_000_007;

            Map<Double, List<Double>> slopeToIntercepts = new HashMap<>();
            Map<Integer, List<Double>> midToSlopes = new HashMap<>();

            for (int i = 0; i < n - 1; i++) {
                int[] point1 = points[i];
                int x1 = point1[0];
                int y1 = point1[1];

                for (int j = i + 1; j < n; j++) {
                    int[] point2 = points[j];
                    int x2 = point2[0];
                    int y2 = point2[1];

                    // Line is defined as y = mx + c, m = slope, c = y-intercept
                    double m;
                    double c;

                    if (x1 == x2) {
                        // Vertifcal line
                        m = inf;
                        c = x1;
                    } else {
                        m = 1.0 * (y2 - y1) / (x2 - x1);

                        // (y - y1) = m(x - x1)
                        int dx = x2 - x1;
                        int dy = y2 - y1;
                        c = 1.0 * (y1 * dx - x1 * dy) / dx;
                    }

                    if (m == -0.0) {
                        m = 0.0;
                    }
                    if (c == -0.0) {
                        c = 0.0;
                    }

                    int mid = (x1 + x2) * 10000 + (y1 + y2);
                    if (!slopeToIntercepts.containsKey(m)) {
                        slopeToIntercepts.put(m, new ArrayList<>());
                    }

                    slopeToIntercepts.get(m).add(c);

                    if (!midToSlopes.containsKey(mid)) {
                        midToSlopes.put(mid, new ArrayList<>());
                    }

                    midToSlopes.get(mid).add(m);
                }
            }

            for (var entry : slopeToIntercepts.entrySet()) {
                List<Double> intercepts = entry.getValue();
                if (intercepts.size() == 1) {
                    // Only one line with this slopw
                    continue;
                }

                Map<Double, Integer> interceptCounts = new HashMap<>();
                for (double c : intercepts) {
                    interceptCounts.put(c, interceptCounts.getOrDefault(c, 0) + 1);
                }

                int sum = 0;
                for (int interceptCount : interceptCounts.values()) {
                    count += sum * interceptCount;
                    sum += interceptCount;
                }
            }

            for (var entry : midToSlopes.entrySet()) {
                List<Double> slopes = entry.getValue();
                if (slopes.size() == 0) {
                    // Only one slope for this mid point
                    continue;
                }

                Map<Double, Integer> slopeCounts = new HashMap<>();
                for (double m : slopes) {
                    slopeCounts.put(m, slopeCounts.getOrDefault(m, 0) + 1);
                }

                int sum = 0;
                for (int slopeCount : slopeCounts.values()) {
                    count -= sum * slopeCount;
                    sum += slopeCount;
                }
            }

            return count;
        }
    }
}

//    3625. Count Number of Trapezoids II
//    Hard
//    You are given a 2D integer array points where points[i] = [xi, yi] represents the coordinates of the ith point on the Cartesian plane.
//
//    Return the number of unique trapezoids that can be formed by choosing any four distinct points from points.
//
//    A trapezoid is a convex quadrilateral with at least one pair of parallel sides. Two lines are parallel if and only if they have the same slope.
//
//
//
//    Example 1:
//
//    Input: points = [[-3,2],[3,0],[2,3],[3,2],[2,-3]]
//
//    Output: 2
//
//    Explanation:
//
//
//
//    There are two distinct ways to pick four points that form a trapezoid:
//
//    The points [-3,2], [2,3], [3,2], [2,-3] form one trapezoid.
//    The points [2,3], [3,2], [3,0], [2,-3] form another trapezoid.
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
//    There is only one trapezoid which can be formed.
//
//
//
//    Constraints:
//
//    4 <= points.length <= 500
//    –1000 <= xi, yi <= 1000
//    All points are pairwise distinct.