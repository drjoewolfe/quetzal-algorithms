package org.jwolfe.quetzal.algorithms.lc;

public class CapacityToShipPackagesWithinDDays {
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            if(weights == null || weights.length == 0 || days < 1) {
                return 0;
            }

            int sum = 0;
            int min = Integer.MAX_VALUE;
            for(int w : weights) {
                sum += w;
                min = Math.min(min, w);
            }

            int left = min;
            int right = sum;
            int minCapacity = sum;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(isValidCapacity(weights, days, mid)) {
                    minCapacity = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minCapacity;
        }

        private boolean isValidCapacity(int[] weights, int days, int capacity) {
            int i = -1;
            int n = weights.length;
            int current = 0;

            for(int d = 1; d <= days; d++) {
                while(i < n - 1
                        && current + weights[i + 1] <= capacity) {
                    i++;
                    current += weights[i];
                }

                current = 0;
            }

            if(i != n - 1) {
                return false;
            }

            return true;
        }
    }

    class Solution_TLE {
        int minCapacity;

        public int shipWithinDays(int[] weights, int days) {
            if(weights == null || weights.length == 0 || days < 1) {
                return 0;
            }

            int n = weights.length;
            if(days > n) {
                // More days than weights. Return the max weight
                int max = weights[0];
                for(int i = 1; i < n; i++) {
                    max = Math.max(max, weights[i]);
                }

                return max;
            }

            minCapacity = Integer.MAX_VALUE;
            computeShipWithinDays(weights, n, 0, 0, days, 0);

            return minCapacity;
        }

        private void computeShipWithinDays(int[] weights, int n, int index, int day, int totalDays, int maxDayWeight) {
            if(day == totalDays - 1) {
                // Last day
                int sum = 0;
                for(int i = index; i < n; i++) {
                    sum += weights[i];
                }

                maxDayWeight = Math.max(maxDayWeight, sum);
                minCapacity = Math.min(minCapacity, maxDayWeight);
                return;
            }

            int toIndex = n - (totalDays - day);
            int currentWeight = 0;

            for(int i = index; i <= toIndex; i++) {
                currentWeight += weights[i];
                computeShipWithinDays(weights, n, i + 1, day + 1, totalDays, Math.max(maxDayWeight, currentWeight));
            }
        }
    }

// [1,2,3,4,5,6,7,8,9,10]
// 5

// [474,465,91,171,362,15,187,270,29,279,283,207,210,246,131,346,500,140,142,420,244,326,99,51,464,241,307,313,98,52,140,296]
// 16
}

//    1011. Capacity To Ship Packages Within D Days
//    Medium
//    A conveyor belt has packages that must be shipped from one port to another within days days.
//
//    The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
//
//    Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
//
//
//
//    Example 1:
//
//    Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//    Output: 15
//    Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
//    1st day: 1, 2, 3, 4, 5
//    2nd day: 6, 7
//    3rd day: 8
//    4th day: 9
//    5th day: 10
//
//    Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
//    Example 2:
//
//    Input: weights = [3,2,2,4,1,4], days = 3
//    Output: 6
//    Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
//    1st day: 3, 2
//    2nd day: 2, 4
//    3rd day: 1, 4
//    Example 3:
//
//    Input: weights = [1,2,3,1,1], days = 4
//    Output: 3
//    Explanation:
//    1st day: 1
//    2nd day: 2
//    3rd day: 3
//    4th day: 1, 1
//
//
//    Constraints:
//
//    1 <= days <= weights.length <= 5 * 104
//    1 <= weights[i] <= 500