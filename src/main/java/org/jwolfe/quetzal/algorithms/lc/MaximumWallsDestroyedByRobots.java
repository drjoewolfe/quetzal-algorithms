package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumWallsDestroyedByRobots {
    class Solution {
        public int maxWalls(int[] robots, int[] distance, int[] walls) {
            if (robots == null || robots.length == 0 || distance == null || distance.length != robots.length || walls == null || walls.length == 0) {
                return 0;
            }

            int n = robots.length;
            List<RobotInfo> robotInfos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                RobotInfo robotInfo = new RobotInfo(robots[i], distance[i]);
                robotInfos.add(robotInfo);
            }

            robotInfos.sort((a, b) -> a.position - b.position);
            Arrays.sort(walls);

            for (int i = 0; i < n; i++) {
                int leftLimit = (i == 0) ? 1 : robotInfos.get(i - 1).position + 1;
                int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : robotInfos.get(i + 1).position - 1;

                RobotInfo robotInfo = robotInfos.get(i);
                robotInfo.leftRange = Math.max(robotInfo.position - robotInfo.maxDistance, leftLimit);
                robotInfo.rightRange = Math.min(robotInfo.position + robotInfo.maxDistance, rightLimit);
            }

            Integer[][] memo = new Integer[n][2];
            return maxWalls(robotInfos, walls, 0, 0, memo);
        }

        private int maxWalls(List<RobotInfo> robotInfos, int[] walls, int robotIndex, int prevDirection, Integer[][] memo) {
            if (robotIndex == robotInfos.size()) {
                return 0;
            }

            if (memo[robotIndex][prevDirection] != null) {
                return memo[robotIndex][prevDirection];
            }

            RobotInfo robotInfo = robotInfos.get(robotIndex);
            int leftStart = robotInfo.leftRange;

            if (prevDirection == 1) {
                // Previous robot fired to the right
                RobotInfo prevRobotInfo = robotInfos.get(robotIndex - 1);
                leftStart = Math.max(leftStart, prevRobotInfo.rightRange + 1);
            }

            int leftWalls = countWalls(walls, leftStart, robotInfo.position)
                    + maxWalls(robotInfos, walls, robotIndex + 1, 0, memo);

            int rightWalls = countWalls(walls, robotInfo.position, robotInfo.rightRange)
                    + maxWalls(robotInfos, walls, robotIndex + 1, 1, memo);

            return memo[robotIndex][prevDirection] = Math.max(leftWalls, rightWalls);
        }

        private int countWalls(int[] walls, int left, int right) {
            int leftBound = lowerBound(walls, left);
            int rightBound = upperBound(walls, right);

            return rightBound - leftBound;
        }

        private int lowerBound(int[] arr, int target) {
            int low = 0, high = arr.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] >= target) high = mid;
                else low = mid + 1;
            }
            return low; // Smallest index i such that arr[i] >= target
        }

        private int upperBound(int[] arr, int key) {
            int low = 0, high = arr.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] <= key) low = mid + 1;
                else high = mid;
            }
            return low; // Smallest index where arr[index] > key
        }

        private class RobotInfo {
            private int position;
            private int maxDistance;
            private int leftRange;
            private int rightRange;

            public RobotInfo(int position, int maxDistance) {
                this.position = position;
                this.maxDistance = maxDistance;
            }
        }
    }

    class Solution_TLE {
        public int maxWalls(int[] robots, int[] distance, int[] walls) {
            if (robots == null || robots.length == 0 || distance == null || distance.length != robots.length || walls == null || walls.length == 0) {
                return 0;
            }

            int n = robots.length;
            List<RobotInfo> robotInfos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                RobotInfo robotInfo = new RobotInfo(robots[i], distance[i]);
                robotInfos.add(robotInfo);
            }

            robotInfos.sort((a, b) -> a.position - b.position);
            Arrays.sort(walls);

            for (int i = 0; i < n; i++) {
                int leftLimit = (i == 0) ? 1 : robotInfos.get(i - 1).position + 1;
                int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : robotInfos.get(i + 1).position - 1;

                RobotInfo robotInfo = robotInfos.get(i);
                robotInfo.leftRange = Math.max(robotInfo.position - robotInfo.maxDistance, leftLimit);
                robotInfo.rightRange = Math.min(robotInfo.position + robotInfo.maxDistance, rightLimit);
            }

            return maxWalls(robotInfos, walls, 0, 0);
        }

        private int maxWalls(List<RobotInfo> robotInfos, int[] walls, int robotIndex, int prevDirection) {
            if (robotIndex == robotInfos.size()) {
                return 0;
            }

            RobotInfo robotInfo = robotInfos.get(robotIndex);
            int leftStart = robotInfo.leftRange;

            if (prevDirection == 1) {
                // Previous robot fired to the right
                RobotInfo prevRobotInfo = robotInfos.get(robotIndex - 1);
                leftStart = Math.max(leftStart, prevRobotInfo.rightRange + 1);
            }

            int leftWalls = countWalls(walls, leftStart, robotInfo.position)
                    + maxWalls(robotInfos, walls, robotIndex + 1, 0);

            int rightWalls = countWalls(walls, robotInfo.position, robotInfo.rightRange)
                    + maxWalls(robotInfos, walls, robotIndex + 1, 1);

            return Math.max(leftWalls, rightWalls);
        }

        private int countWalls(int[] walls, int left, int right) {
            int leftBound = lowerBound(walls, left);
            int rightBound = upperBound(walls, right);

            return rightBound - leftBound;
        }

        private int lowerBound(int[] arr, int target) {
            int low = 0, high = arr.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] >= target) high = mid;
                else low = mid + 1;
            }
            return low; // Smallest index i such that arr[i] >= target
        }

        private int upperBound(int[] arr, int key) {
            int low = 0, high = arr.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] <= key) low = mid + 1;
                else high = mid;
            }
            return low; // Smallest index where arr[index] > key
        }

        private class RobotInfo {
            private int position;
            private int maxDistance;
            private int leftRange;
            private int rightRange;

            public RobotInfo(int position, int maxDistance) {
                this.position = position;
                this.maxDistance = maxDistance;
            }
        }
    }
}

//    3661. Maximum Walls Destroyed by Robots
//    Hard
//    There is an endless straight line populated with some robots and walls. You are given integer arrays robots, distance, and walls:
//    robots[i] is the position of the ith robot.
//    distance[i] is the maximum distance the ith robot's bullet can travel.
//    walls[j] is the position of the jth wall.
//    Every robot has one bullet that can either fire to the left or the right at most distance[i] meters.
//
//    A bullet destroys every wall in its path that lies within its range. Robots are fixed obstacles: if a bullet hits another robot before reaching a wall, it immediately stops at that robot and cannot continue.
//
//    Return the maximum number of unique walls that can be destroyed by the robots.
//
//    Notes:
//
//    A wall and a robot may share the same position; the wall can be destroyed by the robot at that position.
//    Robots are not destroyed by bullets.
//
//
//    Example 1:
//
//    Input: robots = [4], distance = [3], walls = [1,10]
//
//    Output: 1
//
//    Explanation:
//
//    robots[0] = 4 fires left with distance[0] = 3, covering [1, 4] and destroys walls[0] = 1.
//    Thus, the answer is 1.
//    Example 2:
//
//    Input: robots = [10,2], distance = [5,1], walls = [5,2,7]
//
//    Output: 3
//
//    Explanation:
//
//    robots[0] = 10 fires left with distance[0] = 5, covering [5, 10] and destroys walls[0] = 5 and walls[2] = 7.
//    robots[1] = 2 fires left with distance[1] = 1, covering [1, 2] and destroys walls[1] = 2.
//    Thus, the answer is 3.
//    Example 3:
//    Input: robots = [1,2], distance = [100,1], walls = [10]
//
//    Output: 0
//
//    Explanation:
//
//    In this example, only robots[0] can reach the wall, but its shot to the right is blocked by robots[1]; thus the answer is 0.
//
//
//
//    Constraints:
//
//    1 <= robots.length == distance.length <= 105
//    1 <= walls.length <= 105
//    1 <= robots[i], walls[j] <= 109
//    1 <= distance[i] <= 105
//    All values in robots are unique
//    All values in walls are unique