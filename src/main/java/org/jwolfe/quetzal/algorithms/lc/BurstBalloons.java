package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;
import java.util.stream.Collectors;

public class BurstBalloons {
    class Solution {
        public int maxCoins(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[][] dp = new int[n][n];

            // Length 1
            for(int i = 0; i < n; i++) {
                int b = nums[i];

                int a = (i == 0) ? 1 : nums[i - 1];
                int c = (i == n - 1) ? 1 : nums[i + 1];

                dp[i][i] = a * b * c;
            }

            // Length > 1
            for(int l = 2; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    dp[i][j] = Integer.MIN_VALUE;
                    for(int k = i; k <= j; k++) {
                        // Consider bursting as [i...k - 1], [k], [k + 1... j], with k burst last
                        int fromLeft = (k == i) ? 0 : dp[i][k - 1];
                        int fromRight = (k == j) ? 0 : dp[k + 1][j];
                        int last = nums[k]
                                * ((i == 0) ? 1 : nums[i - 1])
                                * ((j == n - 1) ? 1 : nums[j + 1]);

                        int coins = fromLeft + fromRight + last;

                        dp[i][j] = Math.max(dp[i][j], coins);
                    }
                }
            }

            return dp[0][n - 1];
        }
    }

    class Solution_Memoized_TLE_2 {
        public int maxCoins(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            List<Integer> numbers = new ArrayList<>();
            for(int a : nums) {
                numbers.add(a);
            }

            return maxCoins(numbers, new HashMap<>());
        }

        private int maxCoins(List<Integer> nums, Map<String, Integer> memo) {
            if(nums.size() == 1) {
                return nums.get(0);
            }

            String key = getKey(nums);
            if(memo.containsKey(key)) {
                return memo.get(key);
            }

            int maxCoins = 1;

            int n = nums.size();
            for(int i = 0; i < n; i++) {
                int b = nums.get(i);

                int a = (i == 0) ? 1 : nums.get(i - 1);
                int c = (i == n - 1) ? 1 : nums.get(i + 1);

                int coins = a * b * c;
                nums.remove(i);
                coins += maxCoins(nums, memo);
                nums.add(i, b);

                maxCoins = Math.max(maxCoins, coins);
            }

            memo.put(key, maxCoins);
            return maxCoins;
        }

        private String getKey(List<Integer> nums) {
            StringBuilder builder = new StringBuilder();
            for(Integer a : nums) {
                builder.append(a + " - ");
            }

            return builder.toString();
        }
    }


    class Solution_Correct_1 {
        public int maxCoins(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[][] dp = new int[n][n];

            // For bursting one balloon
            for(int i = 0; i < n; i++) {
                dp[i][i] = ((i == 0 ? 1 : nums[i - 1])
                        * nums[i]
                        * (i == n - 1 ? 1 : nums[i + 1]));
            }

            // For bursting more than one balloon
            for(int l = 2; l <= n; l++) {
                for(int i = 0; i + l <= n; i ++) {
                    int j = i + l - 1;
                    // In the window(i, j)

                    dp[i][j] = Integer.MIN_VALUE;
                    for(int k = i; k <= j; k ++) {
                        // Assume we burst k last. (i ... k - 1) on the left is burst first, & (k + 1 ... j) on the right then burst, finally, k.

                        int fromLeftSide = (k == i ? 0 : dp[i][k - 1]);
                        int fromRightSide = (k == j ? 0 : dp[k + 1][j]);
                        int lastBurstCoins = ((i == 0 ? 1 : nums[i - 1])
                                * nums[k]
                                * (j == n - 1 ? 1 : nums[j + 1]));

                        int coins = fromLeftSide + fromRightSide + lastBurstCoins;

                        dp[i][j] = Math.max(dp[i][j], coins);
                    }
                }
            }

            return dp[0][n - 1];
        }

        public int maxCoinsWithOrder(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            BurstMetrics[][] dp = new BurstMetrics[n][n];

            // For arrays of length 1
            for(int i = 0; i < n; i++) {
                int coins = ((i == 0 ? 1 : nums[i - 1])
                        * nums[i]
                        * (i == n - 1 ? 1 : nums[i + 1]));

                BurstMetrics metrics = new BurstMetrics(coins, i);
                dp[i][i] = metrics;
            }

            for(int l = 2; l <= n; l++) {
                for(int i = 0; i + l <= n; i ++) {
                    int j = i + l - 1;

                    int maxCoins = Integer.MIN_VALUE;
                    int lastBurstIndex = -1;
                    for(int k = i; k <= j; k ++) {
                        int fromLeftSide = (k == i ? 0 : dp[i][k - 1].coins);
                        int fromRightSide = (k == j ? 0 : dp[k + 1][j].coins);
                        int lastBurstCoins = ((i == 0 ? 1 : nums[i - 1])
                                * nums[k]
                                * (j == n - 1 ? 1 : nums[j + 1]));

                        int coins = fromLeftSide + fromRightSide + lastBurstCoins;
                        // System.out.println("[" + i + ", " + j + ": " + k + "] -> L:" + fromLeftSide + "; R:" + fromRightSide + "; LB: " + lastBurstCoins + "; T:" + coins + ";");

                        if(maxCoins < coins) {
                            maxCoins = coins;
                            lastBurstIndex = k;
                        }
                    }

                    dp[i][j] = new BurstMetrics(maxCoins, lastBurstIndex);
                }
            }

            return dp[0][n - 1].coins;
        }

        class BurstMetrics {
            int coins;
            int lastBurstIndex;

            public BurstMetrics(int coins, int lastBurstIndex) {
                this.coins = coins;
                this.lastBurstIndex = lastBurstIndex;
            }

            @Override
            public String toString() {
                return "(" + coins + ", " + lastBurstIndex + ")";
            }
        }

        private void printMatrix(BurstMetrics[][] matrix) {
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    BurstMetrics metrics = matrix[i][j];

                    System.out.print((metrics == null ? "()" : metrics.toString()) + "\t");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Memo {
        public int maxCoins(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            Map<String, Integer> memo = new HashMap<>();
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            return maxCoins(list, memo);
        }

        private int maxCoins(List<Integer> nums, Map<String, Integer> memo) {
            int n = nums.size();

            if(n == 0) {
                return 0;
            }

            if(n == 1) {
                return nums.get(0);
            }

            String key = getKey(nums);
            if(memo.containsKey(key)) {
                return memo.get(key);
            }

            int maxValue = 0;

            for(int i = 0; i < n; i++) {
                int a = (i > 0) ? nums.get(i - 1) : 1;
                int b = nums.get(i);
                int c = (i < n- 1) ? nums.get(i + 1) : 1;

                nums.remove(i);
                int value = (a * b * c) + maxCoins(nums, memo);
                nums.add(i, b);

                maxValue = Math.max(value, maxValue);
            }

            memo.put(key, maxValue);
            return maxValue;
        }

        private String getKey(List<Integer> nums) {
            StringBuilder keyBuilder = new StringBuilder();
            for(var a : nums) {
                keyBuilder.append(a + "-");
            }

            return keyBuilder.toString();
        }
    }

    class Solution_Brute {
        public int maxCoins(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            return maxCoins(list);
        }

        private int maxCoins(List<Integer> nums) {
            int n = nums.size();

            if(n == 0) {
                return 0;
            }

            if(n == 1) {
                return nums.get(0);
            }

            int maxValue = 0;

            for(int i = 0; i < n; i++) {
                int a = (i > 0) ? nums.get(i - 1) : 1;
                int b = nums.get(i);
                int c = (i < n- 1) ? nums.get(i + 1) : 1;

                nums.remove(i);
                int value = (a * b * c) + maxCoins(nums);
                nums.add(i, b);

                maxValue = Math.max(value, maxValue);
            }

            return maxValue;
        }
    }

// [3, 1, 5, 8]
// [7,9,8,0,7,1,3,5,5,2,3,3]
// [8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,5]
// [8,9,5,1,1,7,7,3,2,1,6,4,9,7,2,0,0,7,9,4,0,7,8,5,1,3,6,9]

}

//    312. Burst Balloons
//    Hard
//    You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
//
//    If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
//
//    Return the maximum coins you can collect by bursting the balloons wisely.
//
//
//
//    Example 1:
//
//    Input: nums = [3,1,5,8]
//    Output: 167
//    Explanation:
//    nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//    coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
//    Example 2:
//
//    Input: nums = [1,5]
//    Output: 10
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 500
//    0 <= nums[i] <= 100