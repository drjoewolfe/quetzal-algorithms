package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountCompleteSubarraysInAnArray {
    class Solution {
        public int countCompleteSubarrays(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(nums[i]);
            }

            int completeSubArrayCount = 0;
            int distinctCount = set.size();

            Map<Integer, Integer> counts = new HashMap<>();

            int right = 0;
            for (int left = 0; left < n; left++) {
                if (left > 0) {
                    int prevElement = nums[left - 1];
                    if (counts.get(prevElement) == 1) {
                        counts.remove(prevElement);
                    } else {
                        counts.put(prevElement, counts.get(prevElement) - 1);
                    }
                }

                while (right < n && counts.size() < distinctCount) {
                    int nextElement = nums[right];
                    counts.put(nextElement, counts.getOrDefault(nextElement, 0) + 1);
                    right++;
                }

                if (counts.size() == distinctCount) {
                    completeSubArrayCount += (n - right + 1);
                }
            }

            return completeSubArrayCount;
        }
    }
}

//    2799. Count Complete Subarrays in an Array
//    Medium
//    You are given an array nums consisting of positive integers.
//
//    We call a subarray of an array complete if the following condition is satisfied:
//
//    The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
//    Return the number of complete subarrays.
//
//    A subarray is a contiguous non-empty part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,1,2,2]
//    Output: 4
//    Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].
//    Example 2:
//
//    Input: nums = [5,5,5,5]
//    Output: 10
//    Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that we can choose is 10.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 2000