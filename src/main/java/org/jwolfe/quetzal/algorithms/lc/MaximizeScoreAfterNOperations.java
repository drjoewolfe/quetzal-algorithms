package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaximizeScoreAfterNOperations {
    class Solution {
        public int maxScore(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return 0;
            }

            int n = nums.length;
            int size = 1 << n; // 2 ^ n;
            int[] memo = new int[size];
            Arrays.fill(memo, -1);

            return maxScore(nums, 0, 1, memo);
        }

        private int maxScore(int[] nums, int mask, int iteration, int[] memo) {
            if(iteration * 2 > nums.length) {
                return 0;
            }

            if(memo[mask] != -1) {
                return memo[mask];
            }

            int max = 0;

            for(int firstIndex = 0; firstIndex < nums.length - 1; firstIndex++) {
                if(((mask >> firstIndex) & 1) == 1) {
                    continue;
                }

                int first = nums[firstIndex];
                for(int secondIndex = firstIndex + 1; secondIndex < nums.length; secondIndex++) {
                    if(((mask >> secondIndex) & 1) == 1) {
                        continue;
                    }

                    int newMask = mask | (1 << firstIndex) | (1 << secondIndex);

                    int second = nums[secondIndex];
                    int score = (iteration * gcd(first, second))
                            + maxScore(nums, newMask, iteration + 1, memo);

                    max = Math.max(max, score);
                }
            }

            memo[mask] = max;
            return max;
        }

        private int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

    class Solution_TLE_2 {
        public int maxScore(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return 0;
            }

            return maxScore(nums, 0, 1);
        }

        private int maxScore(int[] nums, int mask, int iteration) {
            if(iteration * 2 > nums.length) {
                return 0;
            }

            int max = 0;

            for(int firstIndex = 0; firstIndex < nums.length - 1; firstIndex++) {
                if(((mask >> firstIndex) & 1) == 1) {
                    continue;
                }

                int first = nums[firstIndex];
                for(int secondIndex = firstIndex + 1; secondIndex < nums.length; secondIndex++) {
                    if(((mask >> secondIndex) & 1) == 1) {
                        continue;
                    }

                    int newMask = mask | (1 << firstIndex) | (1 << secondIndex);

                    int second = nums[secondIndex];
                    int score = (iteration * gcd(first, second))
                            + maxScore(nums, newMask, iteration + 1);

                    max = Math.max(max, score);
                }
            }

            return max;
        }

        private int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

    class Solution_TLE {
        public int maxScore(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return 0;
            }

            return maxScore(nums, new HashSet<>(), 1);
        }

        private int maxScore(int[] nums, Set<Integer> selectedIndexes, int iteration) {
            if(selectedIndexes.size() == nums.length) {
                return 0;
            }

            int max = 0;

            for(int firstIndex = 0; firstIndex < nums.length - 1; firstIndex++) {
                if(selectedIndexes.contains(firstIndex)) {
                    continue;
                }

                selectedIndexes.add(firstIndex);
                int first = nums[firstIndex];
                for(int secondIndex = firstIndex + 1; secondIndex < nums.length; secondIndex++) {
                    if(selectedIndexes.contains(secondIndex)) {
                        continue;
                    }

                    selectedIndexes.add(secondIndex);
                    int second = nums[secondIndex];
                    int score = (iteration * gcd(first, second))
                            + maxScore(nums, selectedIndexes, iteration + 1);

                    max = Math.max(max, score);
                    selectedIndexes.remove(secondIndex);
                }

                selectedIndexes.remove(firstIndex);
            }

            return max;
        }

        private int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

// [1,2]
// [773274,313112,131789,222437,918065,49745,321270,74163,900218,80160,325440,961730]
}

//    1799. Maximize Score After N Operations
//    Hard
//    You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
//
//    In the ith operation (1-indexed), you will:
//
//    Choose two elements, x and y.
//    Receive a score of i * gcd(x, y).
//    Remove x and y from nums.
//    Return the maximum score you can receive after performing n operations.
//
//    The function gcd(x, y) is the greatest common divisor of x and y.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2]
//    Output: 1
//    Explanation: The optimal choice of operations is:
//    (1 * gcd(1, 2)) = 1
//    Example 2:
//
//    Input: nums = [3,4,6,8]
//    Output: 11
//    Explanation: The optimal choice of operations is:
//    (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
//    Example 3:
//
//    Input: nums = [1,2,3,4,5,6]
//    Output: 14
//    Explanation: The optimal choice of operations is:
//    (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
//
//
//    Constraints:
//
//    1 <= n <= 7
//    nums.length == 2 * n
//    1 <= nums[i] <= 106