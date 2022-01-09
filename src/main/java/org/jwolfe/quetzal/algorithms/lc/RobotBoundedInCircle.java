package org.jwolfe.quetzal.algorithms.lc;

public class RobotBoundedInCircle {
    class Solution {
        public boolean isRobotBounded(String instructions) {
            if(instructions == null || instructions.length() == 0) {
                return true;
            }

            int x = 0;
            int y = 0;
            char direction = 'N';

            for(int i = 0; i < instructions.length(); i++) {
                char ins = instructions.charAt(i);
                switch(ins) {
                    case 'G':
                        if(direction == 'N') {
                            y--;
                        } else if(direction == 'W') {
                            x--;
                        } else if(direction == 'S') {
                            y++;
                        } else {
                            x++;
                        }
                        break;
                    case 'L':
                        if(direction == 'N') {
                            direction = 'W';
                        } else if(direction == 'W') {
                            direction = 'S';
                        } else if(direction == 'S') {
                            direction = 'E';
                        } else {
                            direction = 'N';
                        }
                        break;
                    case 'R':
                        if(direction == 'N') {
                            direction = 'E';
                        } else if(direction == 'E') {
                            direction = 'S';
                        } else if(direction == 'S') {
                            direction = 'W';
                        } else {
                            direction = 'N';
                        }
                        break;
                }
            }

            return (x == 0 && y == 0) || direction != 'N';
        }
    }
}

//    1041. Robot Bounded In Circle
//    Medium
//    On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
//
//    "G": go straight 1 unit;
//    "L": turn 90 degrees to the left;
//    "R": turn 90 degrees to the right.
//    The robot performs the instructions given in order, and repeats them forever.
//
//    Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
//
//
//
//    Example 1:
//
//    Input: instructions = "GGLLGG"
//    Output: true
//    Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
//    When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
//    Example 2:
//
//    Input: instructions = "GG"
//    Output: false
//    Explanation: The robot moves north indefinitely.
//    Example 3:
//
//    Input: instructions = "GL"
//    Output: true
//    Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
//
//
//    Constraints:
//
//    1 <= instructions.length <= 100
//    instructions[i] is 'G', 'L' or, 'R'.
