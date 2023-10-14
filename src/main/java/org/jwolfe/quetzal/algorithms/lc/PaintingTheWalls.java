package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class PaintingTheWalls {
    class Solution {
        public int paintWalls(int[] cost, int[] time) {
            if(cost == null || cost.length == 0 || time == null || cost.length != time.length) {
                return 0;
            }

            int n = cost.length;
            int[][] memo = new int[n][n + 1];

            return dp(cost, time, 0, n, memo);
        }

        private int dp(int[] cost, int[] time, int i, int remain, int[][] memo) {
            if(remain <= 0) {
                return 0;
            }

            if(i == cost.length) {
                return (int) 1e9;
            }

            if(memo[i][remain] != 0) {
                return memo[i][remain];
            }

            // Hire the paid painter
            int paintCost = cost[i] + dp(cost, time, i + 1, remain - 1 - time[i], memo);

            // Dont hire the paid painter
            int dontPaintCost = dp(cost, time, i + 1, remain, memo);

            memo[i][remain] = Math.min(paintCost, dontPaintCost);
            return memo[i][remain];
        }
    }

    class Solution_Recursive_TLE {
        public int paintWalls(int[] cost, int[] time) {
            if(cost == null || cost.length == 0 || time == null || cost.length != time.length) {
                return 0;
            }

            return dp(cost, time, 0, cost.length);
        }

        private int dp(int[] cost, int[] time, int i, int remain) {
            if(remain <= 0) {
                return 0;
            }

            if(i == cost.length) {
                return (int) 1e9;
            }

            // Hire the paid painter
            int paintCost = cost[i] + dp(cost, time, i + 1, remain - 1 - time[i]);

            // Dont hire the paid painter
            int dontPaintCost = dp(cost, time, i + 1, remain);

            return Math.min(paintCost, dontPaintCost);
        }
    }

    class Solution_Incorrect {
        public int paintWalls(int[] cost, int[] time) {
            if(cost == null || cost.length == 0 || time == null || cost.length != time.length) {
                return 0;
            }

            int n = cost.length;
            WallInfo[] wallInfos = new WallInfo[n];
            for(int i = 0; i < n; i++) {
                wallInfos[i] = new WallInfo(cost[i], time[i]);
            }

            Arrays.sort(wallInfos, (a, b) -> {
                if(a.dailyCost < b.dailyCost) {
                    return -1;
                } else if(a.dailyCost == b.dailyCost) {
                    return 0;
                }

                return 1;
            });

            int left = 0;
            int right = n - 1;
            int minCost = 0;
            while(left <= right) {
                // Give to paid painter
                int paintingCost = wallInfos[left].cost;
                int timeTaken = wallInfos[left].time;

                minCost += paintingCost;
                System.out.println(minCost + ", " + timeTaken + " : " + left + "," + right);
                // Give enough work for free painter
                for(int i = 1; i <= timeTaken && left < right; i++) {
                    right--;
                }

                left++;

                System.out.println(left + " - " + right);
            }

            return minCost;
        }

        class WallInfo {
            int cost;
            int time;
            double dailyCost;

            public WallInfo(int cost, int time) {
                this.cost = cost;
                this.time = time;
                this.dailyCost = 1d * cost / time;
            }
        }
    }


// [1,2,3,2]
// [1,2,3,2]

// [26,53,10,24,25,20,63,51]
// [1,1,1,1,2,2,2,1]

// [42,8,28,35,21,13,21,35]
// [2,1,1,1,2,1,1,2]

// [607,77,1307,214,948,727,1029,397,298,1196,681,1097,281,1543,264,866,544,582,474,877,1353,604,158,1144,666,816,373,320,755,1478,1453,512,1128,1037,1587,1450,961,509,354,523,1548]
// [1,5,3,4,1,6,6,5,3,1,6,4,5,2,1,6,3,5,5,2,6,5,5,6,1,3,4,6,5,4,6,2,4,6,2,1,4,4,2,2,6]
}

//    2742. Painting the Walls
//    Hard
//    You are given two 0-indexed integer arrays, cost and time, of size n representing the costs and the time taken to paint n different walls respectively. There are two painters available:
//
//    A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
//    A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can only be used if the paid painter is already occupied.
//    Return the minimum amount of money required to paint the n walls.
//
//
//
//    Example 1:
//
//    Input: cost = [1,2,3,2], time = [1,2,3,2]
//    Output: 3
//    Explanation: The walls at index 0 and 1 will be painted by the paid painter, and it will take 3 units of time; meanwhile, the free painter will paint the walls at index 2 and 3, free of cost in 2 units of time. Thus, the total cost is 1 + 2 = 3.
//    Example 2:
//
//    Input: cost = [2,3,4,2], time = [1,1,1,1]
//    Output: 4
//    Explanation: The walls at index 0 and 3 will be painted by the paid painter, and it will take 2 units of time; meanwhile, the free painter will paint the walls at index 1 and 2, free of cost in 2 units of time. Thus, the total cost is 2 + 2 = 4.
//
//
//    Constraints:
//
//    1 <= cost.length <= 500
//    cost.length == time.length
//    1 <= cost[i] <= 106
//    1 <= time[i] <= 500