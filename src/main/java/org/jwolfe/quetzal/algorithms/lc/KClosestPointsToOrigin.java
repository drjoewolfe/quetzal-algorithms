package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    class Solution {
        public int[][] kClosest(int[][] points, int K) {
            if(points == null || points.length == 0 || K < 1) {
                return new int[0][0];
            }

            PriorityQueue<DistanceMemento> heap = new PriorityQueue<>();
            for(int i = 0; i < points.length; i++) {
                int[] point = points[i];
                int x = point[0];
                int y = point[1];

                double d = Math.sqrt(x*x + y*y);
                heap.offer(new DistanceMemento(i, d));
            }

            int[][] results = new int[K][2];
            int index = 0;
            while(K > 0) {
                var memento = heap.poll();
                int i = memento.index;

                results[index++] = points[i];
                K--;
            }

            return results;
        }

        private class DistanceMemento implements Comparable<DistanceMemento> {
            int index;
            double distance;

            public DistanceMemento(int index, double distance) {
                this.index = index;
                this.distance = distance;
            }

            @Override
            public int compareTo(DistanceMemento other) {
                if(this.distance == other.distance) {
                    return 0;
                } else if(this.distance > other.distance) {
                    return 1;
                } else {
                    return -1;
                }
            }

            @Override
            public String toString() {
                return "[" + index + ", " + distance + "]";
            }
        }
    }

    class Solution_Correct_1 {
        public int[][] kClosest(int[][] points, int K) {
            if(points == null || points.length == 0 || K <= 0) {
                return new int[0][0];
            }

            PriorityQueue<Point> minHeap = new PriorityQueue<>();

            for(int i = 0; i < points.length; i++) {
                int[] p = points[i];
                if(p.length != 2) {
                    return new int[0][0];
                }

                minHeap.offer(new Point(p[0], p[1]));
            }

            int[][] kClosestPoints = new int[K][2];
            for(int i = 0; i < K; i++) {
                Point p = minHeap.poll();
                kClosestPoints[i][0] = p.x;
                kClosestPoints[i][1] = p.y;
            }

            return kClosestPoints;
        }

        class Point implements Comparable<Point> {
            int x;
            int y;

            double d;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;

                this.d = Math.sqrt(x*x + y*y);
            }

            @Override
            public int compareTo(Point p) {
                if(this.d == p.d) {
                    return 0;
                }

                if(this.d > p.d) {
                    return 1;
                }

                return -1;
            }
        }
    }
}

//    973. K Closest Points to Origin
//    Medium
//    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
//
//    The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
//
//    You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
//
//
//
//    Example 1:
//
//
//    Input: points = [[1,3],[-2,2]], k = 1
//    Output: [[-2,2]]
//    Explanation:
//    The distance between (1, 3) and the origin is sqrt(10).
//    The distance between (-2, 2) and the origin is sqrt(8).
//    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//    We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
//    Example 2:
//
//    Input: points = [[3,3],[5,-1],[-2,4]], k = 2
//    Output: [[3,3],[-2,4]]
//    Explanation: The answer [[-2,4],[3,3]] would also be accepted.
//
//
//    Constraints:
//
//    1 <= k <= points.length <= 104
//    -104 < xi, yi < 104