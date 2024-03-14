package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSum {
    class Solution {
        public int numSubarraysWithSum(int[] nums, int goal) {
            if(nums == null || nums.length == 0 || goal < 0) {
                return 0;
            }

            int count = 0;
            int prefixSum = 0;
            Map<Integer, Integer> prefixSumCounts = new HashMap<>();

            for(int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];

                if(prefixSum == goal) {
                    count++;
                }

                int diff = prefixSum - goal;
                if(prefixSumCounts.containsKey(diff)) {
                    count += prefixSumCounts.get(diff);
                }

                prefixSumCounts.put(prefixSum, prefixSumCounts.getOrDefault(prefixSum, 0) + 1);
            }

            return count;
        }
    }

// [1,0,1,0,1]
// 2

// [0,0,0,0,1]
// 2

// [1,0,1,0,1]
// 2
}

//    930. Binary Subarrays With Sum
//    Medium
//    Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
//
//    A subarray is a contiguous part of the array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,0,1,0,1], goal = 2
//    Output: 4
//    Explanation: The 4 subarrays are bolded and underlined below:
//    [1,0,1,0,1]
//    [1,0,1,0,1]
//    [1,0,1,0,1]
//    [1,0,1,0,1]
//    Example 2:
//
//    Input: nums = [0,0,0,0,0], goal = 0
//    Output: 15
//
//
//    Constraints:
//
//    1 <= nums.length <= 3 * 104
//    nums[i] is either 0 or 1.
//    0 <= goal <= nums.length