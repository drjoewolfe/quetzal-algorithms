package org.jwolfe.quetzal.algorithms.lc;

public class BestTimeToBuyAndSellStockWithCooldown {
    class Solution {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            int n = prices.length;

            // buy[i] => max profit when the last action is buy
            int[] buy = new int[n];

            // sell[i] => max profit when the last action is sell
            int[] sell = new int[n];

            buy[0] = (-1) * prices[0];
            sell[0] = 0;

            buy[1] = Math.max((-1) * prices[1], buy[0]);
            sell[1] = Math.max(0, buy[0] + prices[1]);

            for(int i = 2; i < n; i++) {
                buy[i] = Math.max(buy[i - 1],  sell[i - 2] - prices[i]);
                sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            }


            return sell[n - 1];
        }
    }

    class Solution_Correct_1 {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            int n = prices.length;
            int[][] dp = new int[n][2];

            dp[0][0] = 0;
            dp[0][1] = (-1) * prices[0];

            dp[1][0] = Math.max(dp[0][1] + prices[1], dp[0][0]);
            dp[1][1] = Math.max(dp[0][0] - prices[1], dp[0][1]);

            for(int i = 2; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1]);
            }

            return dp[n - 1][0];
        }
    }
}

//    309. Best Time to Buy and Sell Stock with Cooldown
//    Medium
//    You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//    Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
//
//    After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
//    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//
//
//    Example 1:
//
//    Input: prices = [1,2,3,0,2]
//    Output: 3
//    Explanation: transactions = [buy, sell, cooldown, buy, sell]
//    Example 2:
//
//    Input: prices = [1]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= prices.length <= 5000
//    0 <= prices[i] <= 1000