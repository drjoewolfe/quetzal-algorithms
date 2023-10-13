package org.jwolfe.quetzal.algorithms.lc;

public class MinCostClimbingStairs {
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            if (cost == null || cost.length == 0) {
                return 0;
            }

            if (cost.length == 1) {
                return 0;
            }

            int n = cost.length;
            int[] minCosts = new int[n];
            minCosts[0] = cost[0];
            minCosts[1] = cost[1];
            for (int i = 2; i < n; i++) {
                minCosts[i] = cost[i] + Math.min(minCosts[i - 2], minCosts[i - 1]);
            }

            return Math.min(minCosts[n - 2], minCosts[n - 1]);
        }
    }

    class Solution_Correct_1 {
        public int minCostClimbingStairs(int[] cost) {
            if (cost == null || cost.length == 0) {
                return 0;
            }

            if (cost.length == 1) {
                return cost[0];
            }

            int n = cost.length;
            int[] minCosts = new int[n];
            minCosts[0] = cost[0];
            minCosts[1] = cost[1];

            for (int i = 2; i < n; i++) {
                minCosts[i] = Math.min(minCosts[i - 1], minCosts[i - 2]) + cost[i];
            }

            return Math.min(minCosts[n - 1], minCosts[n - 2]);
        }
    }
}

//    746. Min Cost Climbing Stairs
//    Easy
//
//    3337
//
//    714
//
//    Add to List
//
//    Share
//    You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
//
//    You can either start from the step with index 0, or the step with index 1.
//
//    Return the minimum cost to reach the top of the floor.
//
//
//
//    Example 1:
//
//    Input: cost = [10,15,20]
//    Output: 15
//    Explanation: Cheapest is: start on cost[1], pay that cost, and go to the top.
//    Example 2:
//
//    Input: cost = [1,100,1,1,1,100,1,1,100,1]
//    Output: 6
//    Explanation: Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
//
//
//    Constraints:
//
//    2 <= cost.length <= 1000
//    0 <= cost[i] <= 999