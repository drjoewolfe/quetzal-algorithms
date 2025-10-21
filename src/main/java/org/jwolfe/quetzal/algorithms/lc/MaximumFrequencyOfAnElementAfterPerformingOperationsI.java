package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyOfAnElementAfterPerformingOperationsI {
    class Solution {
        public int maxFrequency(int[] nums, int k, int numOperations) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            Arrays.sort(nums);

            Map<Integer, Integer> frequencies = new HashMap<>();
            int ans = 0;

            int fi = 0;
            for (int ci = 0; ci < n; ci++) {
                int curr = nums[ci];
                int first = nums[fi];

                if (curr != first) {
                    int length = ci - fi;
                    frequencies.put(first, length);
                    ans = Math.max(ans, length);
                    fi = ci;
                }
            }

            int lastStreakLength = n - fi;
            frequencies.put(nums[fi], lastStreakLength);
            ans = Math.max(ans, lastStreakLength);

            int start = nums[0];
            int end = nums[n - 1];
            for (int curr = start; curr <= end; curr++) {
                int left = lowerBound(nums, curr - k);
                int right = upperBound(nums, curr + k);

                int length = right - left + 1;

                if (frequencies.containsKey(curr)) {
                    int frequency = Math.min(length, frequencies.get(curr) + numOperations);
                    ans = Math.max(ans, frequency);
                } else {
                    int frequency = Math.min(length, numOperations);
                    ans = Math.max(ans, frequency);
                }
            }

            return ans;
        }

        private int lowerBound(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

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

//    3346. Maximum Frequency of an Element After Performing Operations I
//    Medium
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
//    Adding 0 to nums[1]. nums becomes [1, 4, 5].
//    Adding -1 to nums[2]. nums becomes [1, 4, 4].
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
//    1 <= nums[i] <= 105
//    0 <= k <= 105
//    0 <= numOperations <= nums.length