package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {
    class Solution {
        public int numberOfBoomerangs(int[][] points) {
            if(points == null || points.length < 3) {
                return 0;
            }

            int n = points.length;
            Map<Integer, Map<Integer, Integer>> pointMap = new HashMap<>();
            for(int i = 0; i < n; i++) {
                pointMap.put(i, new HashMap<>());
            }

            for(int i = 0; i < n - 1; i++) {
                int[] a = points[i];

                for(int j = i + 1; j < n; j++) {
                    int[] b = points[j];

                    int d = getDistance(a, b);
                    pointMap.get(i).put(d, pointMap.get(i).getOrDefault(d, 0) + 1);
                    pointMap.get(j).put(d, pointMap.get(j).getOrDefault(d, 0) + 1);
                }
            }

            int count = 0;
            for(var entry : pointMap.entrySet()) {
                for(var distanceEntry : entry.getValue().entrySet()) {
                    int pointCount = distanceEntry.getValue();
                    count += pointCount * (pointCount - 1);
                }
            }

            return count;
        }

        private int getDistance(int[] a, int[] b) {
            int ax = a[0];
            int ay = a[1];

            int bx = b[0];
            int by = b[1];

            int dx = ax - bx;
            int dy = ay - by;

            return dx * dx + dy * dy;
        }
    }
}

//    447. Number of Boomerangs
//    Medium
//    You are given n points in the plane that are all distinct, where points[i] = [xi, yi]. A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
//
//    Return the number of boomerangs.
//
//
//
//    Example 1:
//
//    Input: points = [[0,0],[1,0],[2,0]]
//    Output: 2
//    Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
//    Example 2:
//
//    Input: points = [[1,1],[2,2],[3,3]]
//    Output: 2
//    Example 3:
//
//    Input: points = [[1,1]]
//    Output: 0
//
//
//    Constraints:
//
//    n == points.length
//    1 <= n <= 500
//    points[i].length == 2
//    -104 <= xi, yi <= 104
//    All the points are unique.