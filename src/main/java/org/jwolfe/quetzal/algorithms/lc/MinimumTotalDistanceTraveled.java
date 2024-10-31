package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTotalDistanceTraveled {
    class Solution {
        public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
            if (robot == null || robot.size() == 0 || factory == null || factory.length == 0) {
                return 0;
            }

            Collections.sort(robot);
            Arrays.sort(factory, (a, b) -> a[0] - b[0]);

            List<Integer> flattenedFactories = new ArrayList<>();
            for (int[] f : factory) {
                int position = f[0];
                int capacity = f[1];
                for (int i = 0; i < capacity; i++) {
                    flattenedFactories.add(position);
                }
            }

            int m = robot.size();
            int n = flattenedFactories.size();

            Long[][] memo = new Long[m][n];
            return minimumTotalDistance(robot, flattenedFactories, m, n, 0, 0, memo);
        }

        private long minimumTotalDistance(List<Integer> robots, List<Integer> factories, int m, int n, int ri, int fi, Long[][] memo) {
            if (ri == m) {
                return 0L;
            }

            if (fi == n) {
                return (long) 1e12;
            }

            if (memo[ri][fi] != null) {
                return memo[ri][fi];
            }

            // assign
            long distance = Math.abs(robots.get(ri) - factories.get(fi));
            long assign = minimumTotalDistance(robots, factories, m, n, ri + 1, fi + 1, memo) + distance;

            // skip
            long skip = minimumTotalDistance(robots, factories, m, n, ri, fi + 1, memo);

            return memo[ri][fi] = Math.min(assign, skip);
        }
    }

// [0,4,6]
// [[2,2],[6,2]]
}

//    2463. Minimum Total Distance Traveled
//    Hard
//    There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
//
//    The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.
//
//    All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
//
//    At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.
//
//    Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.
//
//    Note that
//
//    All robots move at the same speed.
//    If two robots move in the same direction, they will never collide.
//    If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
//    If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
//    If the robot moved from a position x to a position y, the distance it moved is |y - x|.
//
//
//    Example 1:
//
//
//    Input: robot = [0,4,6], factory = [[2,2],[6,2]]
//    Output: 4
//    Explanation: As shown in the figure:
//    - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
//    - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
//    - The third robot at position 6 will be repaired at the second factory. It does not need to move.
//    The limit of the first factory is 2, and it fixed 2 robots.
//    The limit of the second factory is 2, and it fixed 1 robot.
//    The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
//    Example 2:
//
//
//    Input: robot = [1,-1], factory = [[-2,1],[2,1]]
//    Output: 2
//    Explanation: As shown in the figure:
//    - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
//    - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
//    The limit of the first factory is 1, and it fixed 1 robot.
//    The limit of the second factory is 1, and it fixed 1 robot.
//    The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
//
//
//    Constraints:
//
//    1 <= robot.length, factory.length <= 100
//    factory[j].length == 2
//    -109 <= robot[i], positionj <= 109
//    0 <= limitj <= robot.length
//    The input will be generated such that it is always possible to repair every robot.