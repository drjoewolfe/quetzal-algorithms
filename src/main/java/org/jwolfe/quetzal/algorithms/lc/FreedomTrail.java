package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class FreedomTrail {
    class Solution {
        public int findRotateSteps(String ring, String key) {
            if (key == null || key.length() == 0) {
                return 0;
            }

            if (ring == null || ring.length() == 0) {
                return -1;
            }

            int m = ring.length();
            int n = key.length();

            int[][] dp = new int[m][n + 1];
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            for (int i = 0; i < m; i++) {
                dp[i][n] = 0;
            }

            for (int keyIndex = n - 1; keyIndex >= 0; keyIndex--) {
                int keyChar = key.charAt(keyIndex);

                for (int startRingIndex = 0; startRingIndex < m; startRingIndex++) {

                    for (int endRingIndex = 0; endRingIndex < m; endRingIndex++) {
                        char ringChar = ring.charAt(endRingIndex);

                        if (ringChar == keyChar) {
                            int stepsToChar = getSteps(ring, startRingIndex, endRingIndex) + 1;
                            int remainingSteps = dp[endRingIndex][keyIndex + 1];
                            int totalSteps = stepsToChar + remainingSteps;

                            dp[startRingIndex][keyIndex] = Math.min(dp[startRingIndex][keyIndex], totalSteps);
                        }
                    }
                }
            }

            return dp[0][0];
        }

        private int getSteps(String ring, int startIndex, int endIndex) {
            int stepsBetween = Math.abs(startIndex - endIndex);
            int stepsAround = ring.length() - stepsBetween;

            return Math.min(stepsBetween, stepsAround);
        }
    }

    class Solution_Memoized {
        public int findRotateSteps(String ring, String key) {
            if (key == null || key.length() == 0) {
                return 0;
            }

            if (ring == null || ring.length() == 0) {
                return -1;
            }

            int m = ring.length();
            int n = key.length();

            int[][] memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return findRotateSteps(ring, 0, key, 0, memo);
        }

        private int findRotateSteps(String ring, int ringIndex, String key, int keyIndex, int[][] memo) {
            if (keyIndex == key.length()) {
                return 0;
            }

            if (memo[ringIndex][keyIndex] != -1) {
                return memo[ringIndex][keyIndex];
            }

            char k = key.charAt(keyIndex);
            int minSteps = Integer.MAX_VALUE;

            for (int i = 0; i < ring.length(); i++) {
                char r = ring.charAt(i);

                if (k == r) {
                    int stepsToR = getSteps(ring, ringIndex, i) + 1;
                    int remainingSteps = findRotateSteps(ring, i, key, keyIndex + 1, memo);
                    int totalSteps = stepsToR + remainingSteps;

                    minSteps = Math.min(minSteps, totalSteps);
                }
            }

            memo[ringIndex][keyIndex] = minSteps;
            return minSteps;
        }

        private int getSteps(String ring, int startIndex, int endIndex) {
            int stepsBetween = Math.abs(startIndex - endIndex);
            int stepsAround = ring.length() - stepsBetween;

            return Math.min(stepsBetween, stepsAround);
        }
    }

    class Solution_Recursive_TLE {
        public int findRotateSteps(String ring, String key) {
            if (key == null || key.length() == 0) {
                return 0;
            }

            if (ring == null || ring.length() == 0) {
                return -1;
            }

            return findRotateSteps(ring, 0, key, 0);
        }

        private int findRotateSteps(String ring, int ringIndex, String key, int keyIndex) {
            if (keyIndex == key.length()) {
                return 0;
            }

            char k = key.charAt(keyIndex);
            int minSteps = Integer.MAX_VALUE;

            for (int i = 0; i < ring.length(); i++) {
                char r = ring.charAt(i);

                if (k == r) {
                    int stepsToR = getSteps(ring, ringIndex, i) + 1;
                    int remainingSteps = findRotateSteps(ring, i, key, keyIndex + 1);
                    int totalSteps = stepsToR + remainingSteps;

                    minSteps = Math.min(minSteps, totalSteps);
                }
            }

            return minSteps;
        }

        private int getSteps(String ring, int startIndex, int endIndex) {
            int stepsBetween = Math.abs(startIndex - endIndex);
            int stepsAround = ring.length() - stepsBetween;

            return Math.min(stepsBetween, stepsAround);
        }
    }

// "godding"
// "gd"

// "caotmcaataijjxi"
// "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"
}

//    514. Freedom Trail
//    Hard
//    In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
//
//    Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.
//
//    Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.
//
//    At the stage of rotating the ring to spell the key character key[i]:
//
//    You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
//    If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.
//
//
//    Example 1:
//
//
//    Input: ring = "godding", key = "gd"
//    Output: 4
//    Explanation:
//    For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
//    For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
//    Also, we need 1 more step for spelling.
//    So the final output is 4.
//    Example 2:
//
//    Input: ring = "godding", key = "godding"
//    Output: 13
//
//
//    Constraints:
//
//    1 <= ring.length, key.length <= 100
//    ring and key consist of only lower case English letters.
//    It is guaranteed that key could always be spelled by rotating ring.