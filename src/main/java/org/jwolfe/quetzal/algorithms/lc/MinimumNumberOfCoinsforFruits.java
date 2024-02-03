package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumNumberOfCoinsforFruits {
    class Solution {
        public int minimumCoins(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }

            int n = prices.length;

            // dp[i] => min coins such that we bought ith fruit & acquired all fruits in the range [i...];
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[n - 1] = prices[n - 1];

            for(int i = n - 1; i >= 0; i--) {
                dp[i] = prices[i] + ((i + i + 2) < n ? dp[i + i + 2] : 0);
                for(int j = i + 1; j <= i + (i + 1) && j < n; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + prices[i]);
                }
            }

            return dp[0];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public int minimumCoins(int[] prices) {
            if(prices == null || prices.length == 0) {
                return 0;
            }

            return minimumCoins(prices, 0, 0, 0);
        }

        private int minimumCoins(int[] prices, int index, int freeCounter, int currentCoins) {
            if(index == prices.length) {
                return currentCoins;
            }

            int price = prices[index];
            int minCoins = minimumCoins(prices, index + 1, freeCounter + index + 1, currentCoins + price);

            if(freeCounter > 0) {
                // Try without Taking this
                minCoins = Math.min(minCoins,
                        minimumCoins(prices, index + 1, freeCounter - 1, currentCoins)
                );
            }

            return minCoins;
        }
    }

// [3,1,2]
// [1,10,1,1]
// [38,23,27,32,47,45,48,24,39,26,37,42,24,45,27,26,15,16,26,6]
}

//    2944. Minimum Number of Coins for Fruits
//    Medium
//    You are at a fruit market with different types of exotic fruits on display.
//
//    You are given a 1-indexed array prices, where prices[i] denotes the number of coins needed to purchase the ith fruit.
//
//    The fruit market has the following offer:
//
//    If you purchase the ith fruit at prices[i] coins, you can get the next i fruits for free.
//    Note that even if you can take fruit j for free, you can still purchase it for prices[j] coins to receive a new offer.
//
//    Return the minimum number of coins needed to acquire all the fruits.
//
//
//
//    Example 1:
//
//    Input: prices = [3,1,2]
//    Output: 4
//    Explanation: You can acquire the fruits as follows:
//    - Purchase the 1st fruit with 3 coins, you are allowed to take the 2nd fruit for free.
//    - Purchase the 2nd fruit with 1 coin, you are allowed to take the 3rd fruit for free.
//    - Take the 3rd fruit for free.
//    Note that even though you were allowed to take the 2nd fruit for free, you purchased it because it is more optimal.
//    It can be proven that 4 is the minimum number of coins needed to acquire all the fruits.
//    Example 2:
//
//    Input: prices = [1,10,1,1]
//    Output: 2
//    Explanation: You can acquire the fruits as follows:
//    - Purchase the 1st fruit with 1 coin, you are allowed to take the 2nd fruit for free.
//    - Take the 2nd fruit for free.
//    - Purchase the 3rd fruit for 1 coin, you are allowed to take the 4th fruit for free.
//    - Take the 4th fruit for free.
//    It can be proven that 2 is the minimum number of coins needed to acquire all the fruits.
//
//
//    Constraints:
//
//    1 <= prices.length <= 1000
//    1 <= prices[i] <= 105