package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MaximumFrequencyOfAnElementAfterPerformingOperationsII {
    class Solution {
        public int maxFrequency(int[] nums, int k, int numOperations) {
            if (nums == null) {
                return 0;
            }

            int maxFrequency = 0;

            Map<Integer, Integer> frequencies = new HashMap<>();
            TreeMap<Integer, Integer> differences = new TreeMap<>();
            for (int val : nums) {
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);

                int left = val - k;
                int right = val + k;
                differences.put(left, differences.getOrDefault(left, 0) + 1);
                differences.put(right + 1, differences.getOrDefault(right + 1, 0) - 1);
                differences.put(val, differences.getOrDefault(val, 0));
            }

            int cumulativeSum = 0;
            for (var entry : differences.entrySet()) {
                int target = entry.getKey();
                int targetFrequency = frequencies.getOrDefault(target, 0);

                int difference = entry.getValue();
                int window = difference + cumulativeSum;

                int needsConversion = window - targetFrequency;
                int maxPossibleFrequency = Math.min(needsConversion, numOperations);
                maxFrequency = Math.max(maxFrequency, targetFrequency + maxPossibleFrequency);

                cumulativeSum = window;
            }

            return maxFrequency;
        }

        private int lowerBound(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int upperBound(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left - 1;
        }
    }

    class Solution_TLE {
        public int maxFrequency(int[] nums, int k, int numOperations) {
            if (nums == null) {
                return 0;
            }

            int maxFrequency = 0;

            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int val : nums) {
                frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
                maxFrequency = Math.max(maxFrequency, frequencies.get(val));
            }

            Arrays.sort(nums);
            int n = nums.length;
            int first = nums[0];
            int last = nums[n - 1];

            for (int curr = first; curr <= last; curr++) {
                int left = curr - k;
                int right = curr + k;

                int leftIndex = lowerBound(nums, left);
                int rightIndex = upperBound(nums, right);

                int streakLength = rightIndex - leftIndex + 1;
                int currFrequency = frequencies.getOrDefault(curr, 0);

                int frequency = Math.min(streakLength, numOperations + currFrequency);
                maxFrequency = Math.max(maxFrequency, frequency);
            }

            return maxFrequency;
        }

        private int lowerBound(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int upperBound(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                int val = nums[mid];

                if (val <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left - 1;
        }
    }
}

//    3347. Maximum Frequency of an Element After Performing Operations II
//    Hard
//    You are given an integer array nums and two integers k and numOperations.
//
//    You must perform an operation numOperations times on nums, where in each operation you:
//
//    Select an index i that was not selected in any previous operations.
//    Add an integer in the range [-k, k] to nums[i].
//    Return the maximum possible frequency of any element in nums after performing the operations.
//
//
//
//    Example 1:
//
//    Input: nums = [1,4,5], k = 1, numOperations = 2
//
//    Output: 2
//
//    Explanation:
//
//    We can achieve a maximum frequency of two by:
//
//    Adding 0 to nums[1], after which nums becomes [1, 4, 5].
//    Adding -1 to nums[2], after which nums becomes [1, 4, 4].
//    Example 2:
//
//    Input: nums = [5,11,20,20], k = 5, numOperations = 1
//
//    Output: 2
//
//    Explanation:
//
//    We can achieve a maximum frequency of two by:
//
//    Adding 0 to nums[1].
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    0 <= k <= 109
//    0 <= numOperations <= nums.length