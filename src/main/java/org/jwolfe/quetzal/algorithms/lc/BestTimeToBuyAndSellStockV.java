package org.jwolfe.quetzal.algorithms.lc;

public class BestTimeToBuyAndSellStockV {
    class Solution {
        public long maximumProfit(int[] prices, int k) {
            if (prices == null || prices.length == 0 || k == 0) {
                return 0L;
            }

            int n = prices.length;
            Long[][][] memo = new Long[n][k + 1][3];
            return dfs(prices, n - 1, k, 0, memo);
        }

        private long dfs(int[] prices, int i, int k, int state, Long[][][] memo) {
            if (k == 0) {
                // No more transactions allowed
                return 0L;
            }

            if (memo[i][k][state] != null) {
                return memo[i][k][state];
            }

            if (i == 0) {
                if (state == 0) {
                    return 0;
                } else if (state == 1) {
                    return -prices[0];
                } else {
                    return prices[0];
                }
            }

            int price = prices[i];
            long profit;
            if (state == 0) {
                profit = Math.max(
                        dfs(prices, i - 1, k, 0, memo),
                        Math.max(
                                dfs(prices, i - 1, k, 1, memo) + price,
                                dfs(prices, i - 1, k, 2, memo) - price
                        )
                );
            } else if (state == 1) {
                profit = Math.max(
                        dfs(prices, i - 1, k, 1, memo),
                        dfs(prices, i - 1, k - 1, 0, memo) - price
                );
            } else {
                profit = Math.max(
                        dfs(prices, i - 1, k, 2, memo),
                        dfs(prices, i - 1, k - 1, 0, memo) + price
                );
            }

            return memo[i][k][state] = profit;
        }
    }

    class Solution_TLE {
        public long maximumProfit(int[] prices, int k) {
            if (prices == null || prices.length == 0 || k == 0) {
                return 0L;
            }

            int n = prices.length;
            return dfs(prices, n - 1, k, 0);
        }

        private long dfs(int[] prices, int i, int k, int state) {
            if (k == 0) {
                // No more transactions allowed
                return 0L;
            }

            if (i == 0) {
                if (state == 0) {
                    return 0;
                } else if (state == 1) {
                    return -prices[0];
                } else {
                    return prices[0];
                }
            }

            int price = prices[i];
            long profit;
            if (state == 0) {
                profit = Math.max(
                        dfs(prices, i - 1, k, 0),
                        Math.max(
                                dfs(prices, i - 1, k, 1) + price,
                                dfs(prices, i - 1, k, 2) - price
                        )
                );
            } else if (state == 1) {
                profit = Math.max(
                        dfs(prices, i - 1, k, 1),
                        dfs(prices, i - 1, k - 1, 0) - price
                );
            } else {
                profit = Math.max(
                        dfs(prices, i - 1, k, 2),
                        dfs(prices, i - 1, k - 1, 0) + price
                );
            }

            return profit;
        }
    }
}

//    3573. Best Time to Buy and Sell Stock V
//    Medium
//    You are given an integer array prices where prices[i] is the price of a stock in dollars on the ith day, and an integer k.
//
//    You are allowed to make at most k transactions, where each transaction can be either of the following:
//
//    Normal transaction: Buy on day i, then sell on a later day j where i < j. You profit prices[j] - prices[i].
//
//    Short selling transaction: Sell on day i, then buy back on a later day j where i < j. You profit prices[i] - prices[j].
//
//    Note that you must complete each transaction before starting another. Additionally, you can't buy or sell on the same day you are selling or buying back as part of a previous transaction.
//
//    Return the maximum total profit you can earn by making at most k transactions.
//
//
//
//    Example 1:
//
//    Input: prices = [1,7,9,8,2], k = 2
//
//    Output: 14
//
//    Explanation:
//
//    We can make $14 of profit through 2 transactions:
//    A normal transaction: buy the stock on day 0 for $1 then sell it on day 2 for $9.
//    A short selling transaction: sell the stock on day 3 for $8 then buy back on day 4 for $2.
//    Example 2:
//
//    Input: prices = [12,16,19,19,8,1,19,13,9], k = 3
//
//    Output: 36
//
//    Explanation:
//
//    We can make $36 of profit through 3 transactions:
//    A normal transaction: buy the stock on day 0 for $12 then sell it on day 2 for $19.
//    A short selling transaction: sell the stock on day 3 for $19 then buy back on day 4 for $8.
//    A normal transaction: buy the stock on day 5 for $1 then sell it on day 6 for $19.
//
//
//    Constraints:
//
//    2 <= prices.length <= 103
//    1 <= prices[i] <= 109
//    1 <= k <= prices.length / 2