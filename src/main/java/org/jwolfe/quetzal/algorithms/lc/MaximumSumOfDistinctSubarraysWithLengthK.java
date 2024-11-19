package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumSumOfDistinctSubarraysWithLengthK {
    class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0 || nums.length < k) {
                return 0;
            }

            int n = nums.length;
            int left = 0;
            int right = 0;

            long maxSum = 0;
            long sum = 0;
            Map<Integer, Integer> numToIndex = new HashMap<>();

            while (right < n) {
                int val = nums[right];
                int lastOccurance = numToIndex.getOrDefault(val, -1);

                while (left <= lastOccurance
                        || right - left + 1 > k) {
                    sum -= nums[left];
                    left++;
                }

                numToIndex.put(val, right);
                sum += val;

                if (right - left + 1 == k) {
                    maxSum = Math.max(maxSum, sum);
                }

                right++;
            }

            return maxSum;
        }
    }

    class Solution_Correct_1 {
        public long maximumSubarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0 || nums.length < k) {
                return 0;
            }

            long maxSum = 0;
            long sum = 0;
            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int i = 0; i < k - 1; i++) {
                int a = nums[i];
                sum += a;

                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);
            }

            for (int i = k - 1; i < nums.length; i++) {
                int a = nums[i];
                sum += a;
                frequencies.put(a, frequencies.getOrDefault(a, 0) + 1);

                if (frequencies.size() == k) {
                    maxSum = Math.max(maxSum, sum);
                }

                int b = nums[i - k + 1];
                sum -= b;
                if (frequencies.get(b) == 1) {
                    frequencies.remove(b);
                } else {
                    frequencies.put(b, frequencies.get(b) - 1);
                }
            }

            return maxSum;
        }
    }

    class Solution_Brute {
        public long maximumSubarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0 || nums.length < k) {
                return 0;
            }

            long maxSum = 0;
            int left = 0;
            for (int right = k - 1; right < nums.length; right++) {
                boolean valid = true;
                long sum = 0;
                Set<Integer> set = new HashSet<>();
                for (int i = left; i <= right; i++) {
                    int a = nums[i];
                    if (set.contains(a)) {
                        valid = false;
                        break;
                    }

                    set.add(a);
                    sum += a;
                }

                if (valid) {
                    maxSum = Math.max(maxSum, sum);
                }

                left++;
            }

            return maxSum;
        }
    }

// [1,5,4,2,9,9,9]
// 3
}

//    2461. Maximum Sum of Distinct Subarrays With Length K
//    Medium
//    You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
//
//    The length of the subarray is k, and
//    All the elements of the subarray are distinct.
//    Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,5,4,2,9,9,9], k = 3
//    Output: 15
//    Explanation: The subarrays of nums with length 3 are:
//    - [1,5,4] which meets the requirements and has a sum of 10.
//    - [5,4,2] which meets the requirements and has a sum of 11.
//    - [4,2,9] which meets the requirements and has a sum of 15.
//    - [2,9,9] which does not meet the requirements because the element 9 is repeated.
//    - [9,9,9] which does not meet the requirements because the element 9 is repeated.
//    We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
//    Example 2:
//
//    Input: nums = [4,4,4], k = 3
//    Output: 0
//    Explanation: The subarrays of nums with length 3 are:
//    - [4,4,4] which does not meet the requirements because the element 4 is repeated.
//    We return 0 because no subarrays meet the conditions.
//
//
//    Constraints:
//
//    1 <= k <= nums.length <= 105
//    1 <= nums[i] <= 105