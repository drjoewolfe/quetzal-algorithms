package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ErectTheFence {
    class Solution {
        public int[][] outerTrees(int[][] trees) {
            if(trees == null || trees.length < 4) {
                return trees;
            }

            Arrays.sort(trees, (a, b) -> {
                if(a[0] == b[0]) {
                    return b[1] - a[1];
                }

                return a[0] - b[0];
            });

            List<int[]> upper = new ArrayList<>();
            List<int[]> lower = new ArrayList<>();

            for(int[] point : trees) {
                int upperSize = upper.size();
                while(upperSize > 1
                        && getOrientation(upper.get(upperSize - 2), upper.get(upperSize - 1), point) > 0) {
                    upper.remove(upperSize - 1);
                    upperSize--;
                }

                int lowerSize = lower.size();
                while(lowerSize > 1
                        && getOrientation(lower.get(lowerSize - 2), lower.get(lowerSize - 1), point) < 0) {
                    lower.remove(lowerSize - 1);
                    lowerSize--;
                }

                upper.add(point);
                lower.add(point);
            }

            Set<int[]> set = new HashSet<>();
            for(int[] point : upper) {
                set.add(point);
            }

            for(int[] point : lower) {
                set.add(point);
            }

            int size = set.size();
            int[][] results = new int[size][2];
            int i = 0;
            for(int[] point : set) {
                results[i++] = point;
            }

            return results;
        }

        private void print(List<int[]> points) {
            for(int[] point : points) {
                System.out.print("(" + point[0] + ", " + point[1] + ") ");
            }

            System.out.println();
        }

        private int getOrientation(int[] p1, int[] p2, int[] p3) {
            int x1 = p1[0];
            int y1 = p1[1];

            int x2 = p2[0];
            int y2 = p2[1];

            int x3 = p3[0];
            int y3 = p3[1];

            // m32 - m21 < 0
            //  => (y3 - y2) / (x3 - x2) - (y2 - y1) / (x2 - x1) < 0
            //  => (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) < 0;
            return (((y3 - y2) * (x2 - x1)) - ((y2 - y1) * (x3 - x2)));
        }

        private boolean isClockwise(int[] p1, int[] p2, int[] p3) {
            int x1 = p1[0];
            int y1 = p1[1];

            int x2 = p2[0];
            int y2 = p2[1];

            int x3 = p3[0];
            int y3 = p3[1];

            // m32 - m21 < 0
            //  => (y3 - y2) / (x3 - x2) - (y2 - y1) / (x2 - x1) < 0
            //  => (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) < 0;
            return (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) < 0;
        }

        private boolean isCounterClockwise(int[] p1, int[] p2, int[] p3) {
            int x1 = p1[0];
            int y1 = p1[1];

            int x2 = p2[0];
            int y2 = p2[1];

            int x3 = p3[0];
            int y3 = p3[1];

            // m32 - m21 > 0
            //  => (y3 - y2) / (x3 - x2) - (y2 - y1) / (x2 - x1) > 0
            //  => (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) > 0;
            return (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) > 0;
        }
    }
}

//    587. Erect the Fence
//    Hard
//    You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
//
//    You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
//
//    Return the coordinates of trees that are exactly located on the fence perimeter.
//
//
//
//    Example 1:
//
//
//    Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
//    Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
//    Example 2:
//
//
//    Input: points = [[1,2],[2,2],[4,2]]
//    Output: [[4,2],[2,2],[1,2]]
//
//
//    Constraints:
//
//    1 <= points.length <= 3000
//    points[i].length == 2
//    0 <= xi, yi <= 100
//    All the given points are unique.
