package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach {
    class Solution {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            if(heights == null || heights.length == 0) {
                return -1;
            }

            int n = heights.length;

            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            int i = 0;
            for(; i < n - 1; i++) {
                int delta = heights[i + 1] - heights[i];
                if(delta <= 0) {
                    continue;
                }

                if(bricks >= delta) {
                    bricks -= delta;
                    queue.offer(delta);
                } else if(ladders > 0) {
                    if(!queue.isEmpty()) {
                        int earlierBricksUsed = queue.peek();
                        if(earlierBricksUsed >= delta) {
                            bricks += earlierBricksUsed;
                            queue.poll();
                            queue.offer(delta);

                            bricks -= delta;
                        }
                    }

                    ladders--;
                } else {
                    break;
                }
            }

            return i;
        }
    }

    class Solution_Recursive {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            if(heights == null || heights.length == 0) {
                return -1;
            }

            return furthestBuilding(heights, bricks, ladders, 0);
        }

        public int furthestBuilding(int[] heights, int bricks, int ladders, int current) {
            int n = heights.length;
            while(current < n - 1 && heights[current] >= heights[current + 1]) {
                current++;
            }

            if(current == n - 1) {
                return current;
            }

            // Taller building
            int delta = heights[current + 1] - heights[current];
            int withBricks = Integer.MIN_VALUE;
            if(delta <= bricks) {
                withBricks = furthestBuilding(heights, bricks - delta, ladders, current + 1);
            }

            int withLadder = Integer.MIN_VALUE;
            if(ladders > 0) {
                withLadder = furthestBuilding(heights, bricks, ladders - 1, current + 1);
            }

            if(withBricks != Integer.MIN_VALUE || withLadder != Integer.MIN_VALUE) {
                return Math.max(withBricks, withLadder);
            }

            return current;
        }
    }

// [4,2,7,6,9,14,12]
// 5
// 1

// [7,5,13]
// 0
// 0
}

//    1642. Furthest Building You Can Reach
//    Medium
//    You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
//
//    You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
//
//    While moving from building i to building i+1 (0-indexed),
//
//    If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
//    If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
//    Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
//
//
//
//    Example 1:
//
//
//    Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
//    Output: 4
//    Explanation: Starting at building 0, you can follow these steps:
//    - Go to building 1 without using ladders nor bricks since 4 >= 2.
//    - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
//    - Go to building 3 without using ladders nor bricks since 7 >= 6.
//    - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
//    It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
//    Example 2:
//
//    Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
//    Output: 7
//    Example 3:
//
//    Input: heights = [14,3,19,3], bricks = 17, ladders = 0
//    Output: 3
//
//
//    Constraints:
//
//    1 <= heights.length <= 105
//    1 <= heights[i] <= 106
//    0 <= bricks <= 109
//    0 <= ladders <= heights.length