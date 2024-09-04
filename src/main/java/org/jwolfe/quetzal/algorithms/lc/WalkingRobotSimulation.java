package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation {
    class Solution {
        public int robotSim(int[] commands, int[][] obstacles) {
            if (commands == null || commands.length == 0) {
                return 0;
            }

            int maxDistanceSquared = 0;
            Set<Integer> traps = new HashSet<>();
            for (int[] point : obstacles) {
                traps.add(hash(point));
            }


            int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            int x = 0;
            int y = 0;
            int currentDirection = 0;

            for (int command : commands) {
                if (command == -2) {
                    currentDirection = (currentDirection + 3) % 4;
                    continue;
                } else if (command == -1) {
                    currentDirection = (currentDirection + 1) % 4;
                    continue;
                } else {
                    int[] direction = directions[currentDirection];
                    for (int step = 0; step < command; step++) {
                        int nextX = x + direction[0];
                        int nextY = y + direction[1];

                        if (traps.contains(hash(nextX, nextY))) {
                            break;
                        }

                        x = nextX;
                        y = nextY;
                    }
                }

                int distanceSquared = x * x + y * y;
                maxDistanceSquared = Math.max(maxDistanceSquared, distanceSquared);
            }


            return maxDistanceSquared;
        }

        private int HASH_MULTIPLIER = 60001;

        private int hash(int[] point) {
            return point[0] * HASH_MULTIPLIER + point[1];
        }

        private int hash(int x, int y) {
            return x * HASH_MULTIPLIER + y;
        }
    }

    class Solution_Correct_2 {
        public int robotSim(int[] commands, int[][] obstacles) {
            if (commands == null || commands.length == 0) {
                return 0;
            }

            int x = 0;
            int y = 0;
            int maxDistance = 0;

            Set<Point> trapSet = new HashSet<>();
            for (int[] trap : obstacles) {
                trapSet.add(new Point(trap[0], trap[1]));
            }

            char d = 'N';
            for (int c : commands) {
                switch (c) {
                    case -2:
                        d = turnLeft(d);
                        break;
                    case -1:
                        d = turnRight(d);
                        break;
                    default:
                        for (int s = 1; s <= c; s++) {
                            int cx = x;
                            int cy = y;

                            switch (d) {
                                case 'N':
                                    cy += 1;
                                    break;
                                case 'S':
                                    cy -= 1;
                                    break;
                                case 'E':
                                    cx -= 1;
                                    break;
                                case 'W':
                                    cx += 1;
                                    break;
                            }

                            if (trapSet.contains(new Point(cx, cy))) {
                                break;
                            }

                            x = cx;
                            y = cy;
                        }

                        int distance = (x * x) + (y * y);
                        maxDistance = Math.max(maxDistance, distance);

                        break;

                }
            }

            return maxDistance;
        }

        private char turnLeft(char d) {
            switch (d) {
                case 'N':
                    d = 'E';
                    break;
                case 'S':
                    d = 'W';
                    break;
                case 'E':
                    d = 'S';
                    break;
                case 'W':
                    d = 'N';
                    break;
            }

            return d;
        }

        private char turnRight(char d) {
            switch (d) {
                case 'N':
                    d = 'W';
                    break;
                case 'S':
                    d = 'E';
                    break;
                case 'E':
                    d = 'N';
                    break;
                case 'W':
                    d = 'S';
                    break;
            }

            return d;
        }

        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object other) {
                if (other instanceof Point) {
                    Point p = (Point) other;
                    return this.x == p.x && this.y == p.y;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }
        }
    }

    class Solution_Class {
        public int robotSim(int[] commands, int[][] obstacles) {
            if (commands == null || commands.length == 0) {
                return 0;
            }

            int x = 0;
            int y = 0;

            int maxD = 0;

            char d = 'N';
            for (int c : commands) {
                switch (c) {
                    case -2:
                        d = turnLeft(d);
                        break;
                    case -1:
                        d = turnRight(d);
                        break;
                    default:
                        for (int s = 1; s <= c; s++) {
                            int cx = x;
                            int cy = y;

                            switch (d) {
                                case 'N':
                                    cy += 1;
                                    break;
                                case 'S':
                                    cy -= 1;
                                    break;
                                case 'E':
                                    cx -= 1;
                                    break;
                                case 'W':
                                    cx += 1;
                                    break;
                            }

                            boolean blocked = false;
                            for (int[] trap : obstacles) {
                                if (trap[0] == cx && trap[1] == cy) {
                                    blocked = true;
                                }
                            }

                            if (blocked) {
                                break;
                            }

                            x = cx;
                            y = cy;
                        }

                        int distance = (x * x) + (y * y);
                        maxD = Math.max(maxD, distance);

                        break;

                }
            }

            return maxD;
        }

        private char turnLeft(char d) {
            switch (d) {
                case 'N':
                    d = 'E';
                    break;
                case 'S':
                    d = 'W';
                    break;
                case 'E':
                    d = 'S';
                    break;
                case 'W':
                    d = 'N';
                    break;
            }

            return d;
        }

        private char turnRight(char d) {
            switch (d) {
                case 'N':
                    d = 'W';
                    break;
                case 'S':
                    d = 'E';
                    break;
                case 'E':
                    d = 'N';
                    break;
                case 'W':
                    d = 'S';
                    break;
            }

            return d;
        }
    }

// [4,-1,3]
// []
}

//    874. Walking Robot Simulation
//    Medium
//    A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:
//
//    -2: Turn left 90 degrees.
//    -1: Turn right 90 degrees.
//    1 <= k <= 9: Move forward k units, one unit at a time.
//    Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.
//
//    Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).
//
//    Note:
//
//    North means +Y direction.
//    East means +X direction.
//    South means -Y direction.
//    West means -X direction.
//    There can be obstacle in [0,0].
//
//
//    Example 1:
//
//    Input: commands = [4,-1,3], obstacles = []
//    Output: 25
//    Explanation: The robot starts at (0, 0):
//    1. Move north 4 units to (0, 4).
//    2. Turn right.
//    3. Move east 3 units to (3, 4).
//    The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
//    Example 2:
//
//    Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
//    Output: 65
//    Explanation: The robot starts at (0, 0):
//    1. Move north 4 units to (0, 4).
//    2. Turn right.
//    3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
//    4. Turn left.
//    5. Move north 4 units to (1, 8).
//    The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
//    Example 3:
//
//    Input: commands = [6,-1,-1,6], obstacles = []
//    Output: 36
//    Explanation: The robot starts at (0, 0):
//    1. Move north 6 units to (0, 6).
//    2. Turn right.
//    3. Turn right.
//    4. Move south 6 units to (0, 0).
//    The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
//
//
//    Constraints:
//
//    1 <= commands.length <= 104
//    commands[i] is either -2, -1, or an integer in the range [1, 9].
//    0 <= obstacles.length <= 104
//    -3 * 104 <= xi, yi <= 3 * 104
//    The answer is guaranteed to be less than 231.