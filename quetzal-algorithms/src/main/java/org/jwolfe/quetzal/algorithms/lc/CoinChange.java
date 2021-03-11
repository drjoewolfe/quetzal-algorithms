package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CoinChange {
    class Solution {
        public int coinChange(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 1) {
                return 0;
            }

            Arrays.sort(coins);
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;

            for(int i = 1; i <= amount; i++) {
                for(int j = 0; j < coins.length; j++) {
                    if(coins[j] <= i ) {
                        dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                    } else {
                        break;
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }

        private void print(Integer[] arr) {
            for(Integer n : arr) {
                System.out.print(n + " ");
            }

            System.out.println();
        }
    }

    class Solution_Memoized {
        public int coinChange(int[] coins, int amount) {
            if(coins == null || coins.length == 0 || amount < 1) {
                return 0;
            }

            Integer[] memo = new Integer[amount + 1];
            Arrays.fill(memo, null);

            return coinChange(coins, amount, memo);
        }

        public int coinChange(int[] coins, int amount, Integer[] memo) {
            if(memo[amount] != null) {
                return memo[amount];
            }

            if(amount == 0) {
                memo[amount] = 0;
            } else {
                int minCoins = Integer.MAX_VALUE;
                for(int coin : coins) {
                    if(amount < coin) {
                        continue;
                    }

                    int w = coinChange(coins, amount - coin, memo);

                    if(w != -1) {
                        minCoins = Math.min(minCoins, w + 1);
                    }
                }

                if(minCoins == Integer.MAX_VALUE) {
                    minCoins = -1;
                }

                memo[amount] = minCoins;
            }

            return memo[amount];
        }

        private void print(Integer[] arr) {
            for(Integer n : arr) {
                System.out.print(n + " ");
            }

            System.out.println();
        }
    }

    class Solution_Classic {
        public int coinChange(int[] coins, int amount) {
            if(coins == null || coins.length == 0) {
                return -1;
            }

            if(amount == 0) {
                return 0;
            }

            int minCoins = Integer.MAX_VALUE;
            for(int coin : coins) {
                if(amount < coin) {
                    continue;
                }

                int w = coinChange(coins, amount - coin);

                if(w != -1) {
                    minCoins = Math.min(minCoins, w + 1);
                }
            }

            if(minCoins == Integer.MAX_VALUE) {
                return -1;
            }

            return minCoins;
        }
    }
}

//    322. Coin Change
//    Medium
//    You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//
//    You may assume that you have an infinite number of each kind of coin.
//
//
//
//    Example 1:
//
//    Input: coins = [1,2,5], amount = 11
//    Output: 3
//    Explanation: 11 = 5 + 5 + 1
//    Example 2:
//
//    Input: coins = [2], amount = 3
//    Output: -1
//    Example 3:
//
//    Input: coins = [1], amount = 0
//    Output: 0
//    Example 4:
//
//    Input: coins = [1], amount = 1
//    Output: 1
//    Example 5:
//
//    Input: coins = [1], amount = 2
//    Output: 2
//
//
//    Constraints:
//
//    1 <= coins.length <= 12
//    1 <= coins[i] <= 231 - 1
//    0 <= amount <= 104