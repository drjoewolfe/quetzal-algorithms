package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {
    class Solution {
        public int mincostTickets(int[] days, int[] costs) {
            if (days == null || days.length == 0 || costs == null || costs.length != 3) {
                return 0;
            }

            Set<Integer> daySet = new HashSet<>();
            for (int d : days) {
                daySet.add(d);
            }

            int[] dp = new int[400];
            for (int d = 365; d >= 0; d--) {
                if (daySet.contains(d)) {
                    int c1 = costs[0] + dp[d + 1];
                    int c2 = costs[1] + dp[d + 7];
                    int c3 = costs[2] + dp[d + 30];

                    dp[d] = Math.min(c1,
                            Math.min(c2, c3));
                } else {
                    dp[d] = dp[d + 1];
                }
            }

            return dp[1];
        }
    }

    class Solution_Correct_DP {
        public int mincostTickets(int[] days, int[] costs) {
            if (days == null || days.length == 0 || costs == null || costs.length != 3) {
                return 0;
            }

            Set<Integer> daySet = new HashSet<>();
            for (int d : days) {
                daySet.add(d);
            }

            int[] dp = new int[400];

            for (int d = 365; d > 0; d--) {
                if (daySet.contains(d)) {
                    dp[d] = Math.min(
                            costs[0] + dp[d + 1],
                            Math.min(
                                    costs[1] + dp[d + 7],
                                    costs[2] + dp[d + 30]
                            )
                    );
                } else {
                    dp[d] = dp[d + 1];
                }
            }

            return dp[1];
        }
    }

    class Solution_Memo {
        public int mincostTickets(int[] days, int[] costs) {
            if (days == null || days.length == 0 || costs == null || costs.length != 3) {
                return 0;
            }

            int n = days.length;
            int[] memo = new int[n];
            Arrays.fill(memo, -1);

            return mincostTickets(days, costs, days.length, 0, 0, memo);
        }

        private int mincostTickets(int[] days, int[] costs, int n, int dayIndex, int validTill, int[] memo) {
            while (dayIndex < n && days[dayIndex] <= validTill) {
                dayIndex++;
            }

            if (dayIndex == n) {
                return 0;
            }

            if (memo[dayIndex] != -1) {
                return memo[dayIndex];
            }

            int c1 = mincostTickets(days, costs, n, dayIndex, days[dayIndex], memo) + costs[0];
            int c2 = mincostTickets(days, costs, n, dayIndex, days[dayIndex] + 6, memo) + costs[1];
            int c3 = mincostTickets(days, costs, n, dayIndex, days[dayIndex] + 29, memo) + costs[2];

            int c = Math.min(c1,
                    Math.min(c2, c3));

            memo[dayIndex] = c;
            return c;
        }
    }

    class Solution_Brute {
        public int mincostTickets(int[] days, int[] costs) {
            if (days == null || days.length == 0 || costs == null || costs.length != 3) {
                return 0;
            }

            return mincostTickets(days, costs, days.length, 0, 0, 0);
        }

        private int mincostTickets(int[] days, int[] costs, int n, int dayIndex, int validTill, int runningCost) {
            while (dayIndex < n && days[dayIndex] <= validTill) {
                dayIndex++;
            }

            if (dayIndex == n) {
                return runningCost;
            }

            int c1 = mincostTickets(days, costs, n, dayIndex, days[dayIndex], runningCost + costs[0]);
            int c2 = mincostTickets(days, costs, n, dayIndex, days[dayIndex] + 6, runningCost + costs[1]);
            int c3 = mincostTickets(days, costs, n, dayIndex, days[dayIndex] + 29, runningCost + costs[2]);

            return Math.min(c1,
                    Math.min(c2, c3));
        }
    }
}

//    983. Minimum Cost For Tickets
//    Medium
//    You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
//
//    Train tickets are sold in three different ways:
//
//    a 1-day pass is sold for costs[0] dollars,
//    a 7-day pass is sold for costs[1] dollars, and
//    a 30-day pass is sold for costs[2] dollars.
//    The passes allow that many days of consecutive travel.
//
//    For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
//    Return the minimum number of dollars you need to travel every day in the given list of days.
//
//
//
//    Example 1:
//
//    Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//    Output: 11
//    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//    On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//    On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
//    On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//    In total, you spent $11 and covered all the days of your travel.
//    Example 2:
//
//    Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
//    Output: 17
//    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//    On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
//    On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
//    In total, you spent $17 and covered all the days of your travel.
//
//
//    Constraints:
//
//    1 <= days.length <= 365
//    1 <= days[i] <= 365
//    days is in strictly increasing order.
//    costs.length == 3
//    1 <= costs[i] <= 1000