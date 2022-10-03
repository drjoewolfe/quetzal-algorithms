package org.jwolfe.quetzal.algorithms.lc;

public class MinimumTimeToMakeRopeColorful {
    class Solution {
        public int minCost(String s, int[] cost) {
            if(s == null || s.length() == 0 || cost == null || cost.length != s.length()) {
                return 0;
            }

            int minCost = 0;

            char currBalloon = s.charAt(0);
            int currBalloonMaxCost = cost[0];

            minCost += currBalloonMaxCost;

            for(int i = 1; i < s.length(); i++) {
                char nextBalloon = s.charAt(i);

                if(currBalloon == nextBalloon) {
                    minCost += cost[i];
                    currBalloonMaxCost = Math.max(currBalloonMaxCost, cost[i]);
                } else {
                    minCost -= currBalloonMaxCost;
                    minCost += cost[i];

                    currBalloon = nextBalloon;
                    currBalloonMaxCost = cost[i];
                }
            }

            minCost -= currBalloonMaxCost;

            return minCost;
        }
    }

    class Solution_Correct_1 {
        public int minCost(String s, int[] cost) {
            if(s == null || s.length() == 0 || cost == null || cost.length != s.length()) {
                return 0;
            }

            int n = s.length();

            int minCost = cost[0];

            char last = s.charAt(0);
            int maxItemCost = cost[0];
            for(int i = 1; i < n; i++) {
                char curr = s.charAt(i);

                if(curr != last) {
                    minCost -= maxItemCost;

                    last = curr;
                    minCost += cost[i];
                    maxItemCost = cost[i];
                } else {
                    minCost += cost[i];
                    maxItemCost = Math.max(maxItemCost, cost[i]);
                }
            }

            minCost -= maxItemCost;

            return minCost;
        }
    }
}

//    1578. Minimum Time to Make Rope Colorful
//    Medium
//    Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
//
//    Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
//
//    Return the minimum time Bob needs to make the rope colorful.
//
//
//
//    Example 1:
//
//
//    Input: colors = "abaac", neededTime = [1,2,3,4,5]
//    Output: 3
//    Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
//    Bob can remove the blue balloon at index 2. This takes 3 seconds.
//    There are no longer two consecutive balloons of the same color. Total time = 3.
//    Example 2:
//
//
//    Input: colors = "abc", neededTime = [1,2,3]
//    Output: 0
//    Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.
//    Example 3:
//
//
//    Input: colors = "aabaa", neededTime = [1,2,3,4,1]
//    Output: 2
//    Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
//    There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
//
//
//    Constraints:
//
//    n == colors.length == neededTime.length
//    1 <= n <= 105
//    1 <= neededTime[i] <= 104
//    colors contains only lowercase English letters.