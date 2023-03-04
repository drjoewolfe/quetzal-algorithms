package org.jwolfe.quetzal.algorithms.lc;

public class CountSubarraysWithFixedBounds {
    class Solution {
        public long countSubarrays(int[] nums, int minK, int maxK) {
            if(nums == null || nums.length < 1 || minK > maxK) {
                return 0;
            }

            int leftBound = -1;
            int minIndex = -1;
            int maxIndex = -1;

            long count = 0;
            for(int i = 0; i < nums.length; i++) {
                int val = nums[i];

                if(val < minK || val > maxK) {
                    leftBound = i;
                } else {
                    if(val == minK) {
                        minIndex = i;
                    }

                    if(val == maxK) {
                        maxIndex = i;
                    }
                }

                int subArrayCount = Math.max(0, Math.min(minIndex, maxIndex) - leftBound);
                count += subArrayCount;
            }

            return count;
        }
    }
}

//    2444. Count Subarrays With Fixed Bounds
//    Hard
//    You are given an integer array nums and two integers minK and maxK.
//
//    A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
//
//    The minimum value in the subarray is equal to minK.
//    The maximum value in the subarray is equal to maxK.
//    Return the number of fixed-bound subarrays.
//
//    A subarray is a contiguous part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
//    Output: 2
//    Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
//    Example 2:
//
//    Input: nums = [1,1,1,1], minK = 1, maxK = 1
//    Output: 10
//    Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
//
//
//    Constraints:
//
//    2 <= nums.length <= 105
//    1 <= nums[i], minK, maxK <= 106
