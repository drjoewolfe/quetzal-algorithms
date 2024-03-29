package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    class Solution {
        public int maxWidthOfVerticalArea(int[][] points) {
            if(points == null || points.length < 2) {
                return 0;
            }

            Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);
            int maxDistance = Integer.MIN_VALUE;
            for(int i = 1; i < points.length; i++) {
                int[] p1 = points[i - 1];
                int[] p2 = points[i];

                int x1 = p1[0];
                int x2 = p2[0];

                if(x1 == x2) {
                    continue;
                }

                maxDistance = Math.max(maxDistance, x2 - x1);
            }

            return maxDistance == Integer.MIN_VALUE ? 0 : maxDistance;
        }
    }

    class Solution_Correct_1 {
        public int maxWidthOfVerticalArea(int[][] points) {
            if(points == null || points.length < 2) {
                return 0;
            }

            Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);
            int maxDistance = Integer.MIN_VALUE;
            for(int i = 1; i < points.length; i++) {
                if(points[i][0] == points[i - 1][0]) {
                    continue;
                }

                maxDistance = Math.max(maxDistance, points[i][0] - points[i - 1][0]);
            }

            return maxDistance == Integer.MIN_VALUE ? 0 : maxDistance;
        }

        private void print(int[][] points) {
            for(int[] p : points) {
                System.out.print(" [" + p[0] + ", " + p[1] + "] ");
            }

            System.out.println();
        }
    }

// [[8,7],[9,9],[7,4],[9,7]]
// [[1,1],[1,2],[1,3]]
}

//    1637. Widest Vertical Area Between Two Points Containing No Points
//    Medium
//    Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
//
//    A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.
//
//    Note that points on the edge of a vertical area are not considered included in the area.
//
//
//
//    Example 1:
//
//    ​
//    Input: points = [[8,7],[9,9],[7,4],[9,7]]
//    Output: 1
//    Explanation: Both the red and the blue area are optimal.
//    Example 2:
//
//    Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
//    Output: 3
//
//
//    Constraints:
//
//    n == points.length
//    2 <= n <= 105
//    points[i].length == 2
//    0 <= xi, yi <= 109