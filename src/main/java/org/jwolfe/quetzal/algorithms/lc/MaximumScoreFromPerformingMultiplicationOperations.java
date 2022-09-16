package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MaximumScoreFromPerformingMultiplicationOperations {
    class Solution {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            int m = multipliers.length;
            int n = nums.length;

            int[] dp = new int[m + 1];
            for(int mIndex = m - 1; mIndex >= 0; mIndex--) {
                int[] dpp = dp.clone();

                int multiplier = multipliers[mIndex];

                for(int left = mIndex; left >= 0; left--) {
                    int right = (n - 1) - (mIndex - left);

                    int leftScore = nums[left] * multiplier + dpp[left + 1];
                    int rightScore = nums[right] * multiplier + dpp[left];

                    int score = Math.max(leftScore, rightScore);
                    dp[left] = score;
                }
            }

            return dp[0];
        }
    }

    class Solution_Correct {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            int m = multipliers.length;
            int n = nums.length;

            int[][] dp = new int[m + 1][n + 1];
            for(int mIndex = m - 1; mIndex >= 0; mIndex--) {
                int multiplier = multipliers[mIndex];

                for(int left = mIndex; left >= 0; left--) {
                    int right = (n - 1) - (mIndex - left);

                    int leftScore = nums[left] * multiplier + dp[mIndex + 1][left + 1];
                    int rightScore = nums[right] * multiplier + dp[mIndex + 1][left];

                    int score = Math.max(leftScore, rightScore);
                    dp[mIndex][left] = score;
                }
            }

            return dp[0][0];
        }
    }

    class Solution_Memoized_Again_TLE {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            return maximumScore(nums, multipliers, 0, 0, nums.length - 1, new HashMap<>());
        }

        private int maximumScore(int[] nums, int[] multipliers, int mIndex, int left, int right, Map<String, Integer> cache) {
            if(mIndex == multipliers.length) {
                return 0;
            }

            String memento = mIndex + "-" + left;
            if(cache.containsKey(memento)) {
                return cache.get(memento);
            }

            // Try left
            int leftScore = multipliers[mIndex] * nums[left]
                    + maximumScore(nums, multipliers, mIndex + 1, left + 1, right, cache);

            // Try right;
            int rightScore = multipliers[mIndex] * nums[right]
                    + maximumScore(nums, multipliers, mIndex + 1, left, right - 1, cache);

            int score = Math.max(leftScore, rightScore);
            cache.put(memento, score);

            return score;
        }
    }


    class Solution_Memoized_MLE {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            Integer[][] cache = new Integer[multipliers.length][nums.length];
            return maximumScore(nums, multipliers, 0, 0, nums.length - 1, cache);
        }

        private int maximumScore(int[] nums, int[] multipliers, int mIndex, int left, int right, Integer[][] cache) {
            if(mIndex == multipliers.length) {
                return 0;
            }

            if(cache[mIndex][left] != null) {
                return cache[mIndex][left];
            }

            // Try left
            int leftScore = multipliers[mIndex] * nums[left]
                    + maximumScore(nums, multipliers, mIndex + 1, left + 1, right, cache);

            // Try right;
            int rightScore = multipliers[mIndex] * nums[right]
                    + maximumScore(nums, multipliers, mIndex + 1, left, right - 1, cache);

            int score = Math.max(leftScore, rightScore);
            cache[mIndex][left] = score;

            return score;
        }
    }

    class Solution_Memoized_TLE {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            return maximumScore(nums, multipliers, 0, 0, nums.length - 1, new HashMap<>());
        }

        private int maximumScore(int[] nums, int[] multipliers, int mIndex, int left, int right, Map<String, Integer> cache) {
            if(mIndex == multipliers.length) {
                return 0;
            }

            String memento = mIndex + "-" + left + "-" + right;
            if(cache.containsKey(memento)) {
                return cache.get(memento);
            }

            // Try left
            int leftScore = multipliers[mIndex] * nums[left]
                    + maximumScore(nums, multipliers, mIndex + 1, left + 1, right, cache);

            // Try right;
            int rightScore = multipliers[mIndex] * nums[right]
                    + maximumScore(nums, multipliers, mIndex + 1, left, right - 1, cache);

            int score = Math.max(leftScore, rightScore);
            cache.put(memento, score);

            return score;
        }
    }

    class Solution_MLE {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            int m = multipliers.length;
            int n = nums.length;
            Integer[][][] cache = new Integer[m][n][n];
            return maximumScore(nums, multipliers, 0, 0, nums.length - 1, cache);
        }

        private int maximumScore(int[] nums, int[] multipliers, int mIndex, int left, int right, Integer[][][] cache) {
            if(mIndex == multipliers.length) {
                return 0;
            }

            if(cache[mIndex][left][right] != null) {
                return cache[mIndex][left][right];
            }

            // Try left
            int leftScore = multipliers[mIndex] * nums[left]
                    + maximumScore(nums, multipliers, mIndex + 1, left + 1, right, cache);

            // Try right;
            int rightScore = multipliers[mIndex] * nums[right]
                    + maximumScore(nums, multipliers, mIndex + 1, left, right - 1, cache);

            int score = Math.max(leftScore, rightScore);
            cache[mIndex][left][right] = score;

            return score;
        }
    }

    class Solution_Recursive {
        public int maximumScore(int[] nums, int[] multipliers) {
            if(nums == null || nums.length == 0 || multipliers == null || multipliers.length == 0 || nums.length < multipliers.length) {
                return 0;
            }

            return maximumScore(nums, multipliers, 0, 0, nums.length - 1);
        }

        private int maximumScore(int[] nums, int[] multipliers, int mIndex, int left, int right) {
            if(mIndex == multipliers.length) {
                return 0;
            }

            // Try left
            int leftScore = multipliers[mIndex] * nums[left]
                    + maximumScore(nums, multipliers, mIndex + 1, left + 1, right);

            // Try right;
            int rightScore = multipliers[mIndex] * nums[right]
                    + maximumScore(nums, multipliers, mIndex + 1, left, right - 1);

            // System.out.println(left + ", " + right + " : " + mIndex + " = " + leftScore + ", " + rightScore);
            return Math.max(leftScore, rightScore);
        }
    }

// [1,2,3]
// [3,2,1]
}

//    1770. Maximum Score from Performing Multiplication Operations
//    Medium
//    You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
//
//    You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
//
//    Choose one integer x from either the start or the end of the array nums.
//    Add multipliers[i] * x to your score.
//    Remove x from the array nums.
//    Return the maximum score after performing m operations.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3], multipliers = [3,2,1]
//    Output: 14
//    Explanation: An optimal solution is as follows:
//    - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
//    - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
//    - Choose from the end, [1], adding 1 * 1 = 1 to the score.
//    The total score is 9 + 4 + 1 = 14.
//    Example 2:
//
//    Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
//    Output: 102
//    Explanation: An optimal solution is as follows:
//    - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
//    - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
//    - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
//    - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
//    - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
//    The total score is 50 + 15 - 9 + 4 + 42 = 102.
//
//
//    Constraints:
//
//    n == nums.length
//    m == multipliers.length
//    1 <= m <= 103
//    m <= n <= 105
//    -1000 <= nums[i], multipliers[i] <= 1000