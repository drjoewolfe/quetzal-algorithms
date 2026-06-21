package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumIceCreamBars {
    class Solution {
        public int maxIceCream(int[] costs, int coins) {
            if (costs == null || costs.length == 0 || coins < 1) {
                return 0;
            }

            int n = costs.length;
            costs = countingSort(costs);

            int count = 0;
            int index = 0;
            while (index < n && costs[index] <= coins) {
                count++;
                coins -= costs[index++];
            }

            return count;
        }

        private int[] countingSort(int[] arr) {
            int n = arr.length;

            int max = arr[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(max, arr[i]);
            }

            int[] count = new int[max + 1];
            for (int i = 0; i < n; i++) {
                int val = arr[i];
                count[val]++;
            }

            int[] prefixSum = new int[max + 1];
            prefixSum[0] = count[0];
            for (int i = 1; i <= max; i++) {
                prefixSum[i] = prefixSum[i - 1] + count[i];
            }

            int[] sortedArray = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                int val = arr[i];
                sortedArray[prefixSum[val] - 1] = val;
                prefixSum[val]--;
            }

            return sortedArray;
        }
    }

    class Solution_Correct_2 {
        public int maxIceCream(int[] costs, int coins) {
            if (costs == null || costs.length == 0 || coins < 1) {
                return 0;
            }

            int n = costs.length;
            Arrays.sort(costs);

            int count = 0;
            int index = 0;
            while (index < n && costs[index] <= coins) {
                count++;
                coins -= costs[index++];
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int maxIceCream(int[] costs, int coins) {
            if (costs == null || costs.length == 0) {
                return 0;
            }

            Arrays.sort(costs);
            int count = 0;

            int i = 0;
            int n = costs.length;
            while (coins > 0
                    && i < n) {
                coins -= costs[i++];
                if (coins >= 0) {
                    count++;
                }
            }

            return count;
        }
    }

// [1,3,2,4,1]
// 7
}

//    1833. Maximum Ice Cream Bars
//    Medium
//    It is a sweltering summer day, and a boy wants to buy some ice cream bars.
//
//    At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible.
//
//    Return the maximum number of ice cream bars the boy can buy with coins coins.
//
//    Note: The boy can buy the ice cream bars in any order.
//
//
//
//    Example 1:
//
//    Input: costs = [1,3,2,4,1], coins = 7
//    Output: 4
//    Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
//    Example 2:
//
//    Input: costs = [10,6,8,7,7,8], coins = 5
//    Output: 0
//    Explanation: The boy cannot afford any of the ice cream bars.
//    Example 3:
//
//    Input: costs = [1,6,3,1,2,5], coins = 20
//    Output: 6
//    Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
//
//
//    Constraints:
//
//    costs.length == n
//    1 <= n <= 105
//    1 <= costs[i] <= 105
//    1 <= coins <= 108