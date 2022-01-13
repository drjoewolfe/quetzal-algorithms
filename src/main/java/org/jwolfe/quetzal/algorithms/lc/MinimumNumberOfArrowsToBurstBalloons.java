package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinimumNumberOfArrowsToBurstBalloons {
    class Solution {
        public int findMinArrowShots(int[][] points) {
            if(points == null || points.length == 0) {
                return 0;
            }

            Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

            int arrows = 1;
            int[] window = new int[] {points[0][0], points[0][1]};
            for(int i = 1; i < points.length; i++) {
                int[] balloon = points[i];

                if(window[1] < balloon[0]) {
                    arrows++;
                    window[0] = balloon[0];
                    window[1] = balloon[1];
                } else {
                    // Overlap
                    window[0] = Math.max(window[0], balloon[0]);
                    window[1] = Math.min(window[1], balloon[1]);
                }
            }

            return arrows;
        }
    }

    class Solution_Correct_1 {
        public int findMinArrowShots(int[][] points) {
            if(points == null || points.length == 0) {
                return 0;
            }

            Arrays.sort(points, (b1, b2) -> Integer.compare(b1[0], b2[0]));
            int arrows = 1;
            int end = points[0][1];
            for(int i = 1; i < points.length; i++) {
                if(end < points[i][0]) {
                    arrows++;
                    end = points[i][1];
                } else {
                    end = Math.min(end, points[i][1]);
                }
            }

            return arrows;
        }
    }

// [[10,16],[2,8],[1,6],[7,12]]
// [[1,2],[2,3],[3,4],[4,5]]

    class Solution_Approach1 {
        public int findMinArrowShots(int[][] points) {
            if(points == null || points.length == 0) {
                return 0;
            }

            PriorityQueue<BalloonEnd> minHeap = new PriorityQueue<BalloonEnd>((b1, b2) -> {
                if(b1.x == b2.x) {
                    if(b1.isEnd && b2.isEnd) {
                        return 0;
                    } else if(b1.isEnd) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

                return Integer.compare(b1.x, b2.x);
            });
            for(int i = 0; i < points.length; i++) {
                minHeap.offer(new BalloonEnd(i, points[i][0], false));
                minHeap.offer(new BalloonEnd(i, points[i][1], true));
            }

            int arrows = 0;
            Set<BalloonEnd> currentBalloons = new HashSet<>();
            while(!minHeap.isEmpty()) {
                BalloonEnd end = minHeap.poll();
                if(!end.isEnd) {
                    currentBalloons.add(end);
                } else {
                    if(currentBalloons.contains(end)) {
                        arrows ++;
                        currentBalloons.clear();
                    }
                }
            }

            return arrows;
        }

        class BalloonEnd {
            int id;
            int x;
            boolean isEnd;

            public BalloonEnd(int id, int x, boolean isEnd) {
                this.id = id;
                this.x = x;
                this.isEnd = isEnd;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof BalloonEnd) {
                    BalloonEnd other = (BalloonEnd) o;
                    return other.id == this.id;
                }

                return false;
            }

            @Override
            public int hashCode() {
                return id;
            }

            @Override
            public String toString() {
                return "(" + id + ", " + x + ", " + isEnd + ")";
            }
        }
    }

// [[10,16],[2,8],[1,6],[7,12]]
// [[1,2],[2,3],[3,4],[4,5]]
}

//    452. Minimum Number of Arrows to Burst Balloons
//    Medium
//    There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
//
//    Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
//
//    Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
//
//
//
//    Example 1:
//
//    Input: points = [[10,16],[2,8],[1,6],[7,12]]
//    Output: 2
//    Explanation: The balloons can be burst by 2 arrows:
//    - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//    - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
//    Example 2:
//
//    Input: points = [[1,2],[3,4],[5,6],[7,8]]
//    Output: 4
//    Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
//    Example 3:
//
//    Input: points = [[1,2],[2,3],[3,4],[4,5]]
//    Output: 2
//    Explanation: The balloons can be burst by 2 arrows:
//    - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//    - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//
//
//    Constraints:
//
//    1 <= points.length <= 105
//    points[i].length == 2
//    -231 <= xstart < xend <= 231 - 1
