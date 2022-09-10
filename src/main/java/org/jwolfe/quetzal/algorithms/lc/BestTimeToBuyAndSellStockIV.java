package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV {
    class Solution {
        public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            int n = prices.length;
            int[][] dp = new int[n + 1][k + 1];

            for(int txnCount = 1; txnCount <= k; txnCount++) {

                for(int i = 1; i < n; i++) {
                    // Consider Txn with sell at i
                    int profitWithTxn = 0;
                    int sellRate = prices[i];

                    // Scan for optimal buy for sell at i
                    for(int j = i - 1; j >= 0; j--) {
                        int buyRate = prices[j];
                        int txnProfit = sellRate - buyRate;

                        if(txnProfit < 0) {
                            continue;
                        }

                        profitWithTxn = Math.max(profitWithTxn,
                                txnProfit + dp[j][txnCount - 1]);
                    }

                    // No Txn with sell at i
                    int profitWithoutTxn = dp[i][txnCount];

                    dp[i + 1][txnCount] = Math.max(profitWithTxn, profitWithoutTxn);
                }
            }

            return dp[n][k];
        }

        private void print(int[][] dp) {
            for(int[] row : dp) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memoized {
        public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            int n = prices.length;
            int[][] dp = new int[n][k + 1];
            for(int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }

            return maxProfit(prices, 0, k, dp);
        }

        private int maxProfit(int[] prices, int index, int remainingTxnCount, int[][] dp) {
            if(remainingTxnCount == 0 || index == prices.length) {
                return 0;
            }

            if(dp[index][remainingTxnCount] != -1) {
                return dp[index][remainingTxnCount];
            }

            // Buy
            int buyProfit = 0;
            int buyRate = prices[index];
            for(int i = index + 1; i < prices.length; i++) {
                int sellRate = prices[i];
                int profit = sellRate - buyRate;

                if(profit < 0) {
                    continue;
                }

                // Try selling at this price
                buyProfit = Math.max(buyProfit,
                        profit + maxProfit(prices, i + 1, remainingTxnCount - 1, dp));
            }

            // No Buy
            int noBuyProfit = maxProfit(prices, index + 1, remainingTxnCount, dp);

            dp[index][remainingTxnCount] = Math.max(buyProfit, noBuyProfit);
            return dp[index][remainingTxnCount];
        }

        private void print(int[][] dp) {
            for(int[] row : dp) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Recursive {
        public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            return maxProfit(prices, 0, k);
        }

        private int maxProfit(int[] prices, int index, int remainingTxnCount) {
            if(remainingTxnCount == 0 || index == prices.length) {
                return 0;
            }

            // Buy
            int buyProfit = 0;
            int buyRate = prices[index];
            for(int i = index + 1; i < prices.length; i++) {
                int sellRate = prices[i];
                int profit = sellRate - buyRate;

                if(profit < 0) {
                    continue;
                }

                // Try selling at this price
                buyProfit = Math.max(buyProfit,
                        profit + maxProfit(prices, i + 1, remainingTxnCount - 1));
            }

            // No Buy
            int noBuyProfit = maxProfit(prices, index + 1, remainingTxnCount);

            return Math.max(buyProfit, noBuyProfit);
        }
    }

    class Solution_Brute_1 {
        int largestProfit;

        public int maxProfit(int k, int[] prices) {
            if(prices == null || prices.length < 2) {
                return 0;
            }

            largestProfit = 0;
            maxProfit(prices, 0, false, k, 0, 0, 0);

            return largestProfit;
        }

        private void maxProfit(int[] prices, int index, boolean sellMode, int k, int txnCount, int currentProfit, int buyPrice) {
            if(txnCount == k) {
                largestProfit = Math.max(largestProfit, currentProfit);
                return;
            }

            if(index == prices.length) {
                if(!sellMode) {
                    largestProfit = Math.max(largestProfit, currentProfit);
                }

                return;
            }

            if(!sellMode) {
                // Buy mode

                // Buy
                maxProfit(prices, index + 1, true, k, txnCount, currentProfit, prices[index]);

                // No Buy
                maxProfit(prices, index + 1, false, k, txnCount, currentProfit, 0);
            } else {
                // Sell mode

                // Sell
                int profit = prices[index] - buyPrice;
                maxProfit(prices, index + 1, false, k, txnCount + 1, currentProfit + profit, 0);

                // No Sell
                maxProfit(prices, index + 1, true, k, txnCount, currentProfit, buyPrice);
            }
        }
    }

// 2
// [2,4,1]

// 2
// [3,3,5,0,0,3,1,4]

// 7
// [48,12,60,93,97,42,25,64,17,56,85,93,9,48,52,42,58,85,81,84,69,36,1,54,23,15,72,15,11,94]
}

//    188. Best Time to Buy and Sell Stock IV
//    Hard
//    You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
//
//    Find the maximum profit you can achieve. You may complete at most k transactions.
//
//    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//
//
//    Example 1:
//
//    Input: k = 2, prices = [2,4,1]
//    Output: 2
//    Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//    Example 2:
//
//    Input: k = 2, prices = [3,2,6,5,0,3]
//    Output: 7
//    Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
//
//    Constraints:
//
//    0 <= k <= 100
//    0 <= prices.length <= 1000
//    0 <= prices[i] <= 1000