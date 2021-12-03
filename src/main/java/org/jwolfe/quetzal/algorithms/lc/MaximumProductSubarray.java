package org.jwolfe.quetzal.algorithms.lc;

public class MaximumProductSubarray {
    class Solution {
        public int maxProduct(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int maxSoFar = nums[0];

            int maxEndingHere = nums[0];
            int minEndingHere = nums[0];

            for(int i = 1; i < nums.length; i++) {
                int val = nums[i];

                int tempMaxEndingHere = maxEndingHere;

                maxEndingHere = Math.max(val,
                        Math.max(maxEndingHere * val,
                                minEndingHere * val));

                minEndingHere = Math.min(val,
                        Math.min(minEndingHere * val,
                                tempMaxEndingHere * val));

                maxSoFar = Math.max(maxSoFar, maxEndingHere);
            }


            return maxSoFar;
        }
    }

    class Solution_Correct_1 {
        public int maxProduct(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            // If everything is (+)ve, return the product
            // Account for (-)ves & 0
            int maxOverall = nums[0];
            int maxEndingHere = nums[0];
            int minEndingHere = nums[0];

            for(int i = 1; i < nums.length; i++) {
                int tempMaxEndingHere = maxEndingHere;

                maxEndingHere = Math.max(nums[i],
                        Math.max(maxEndingHere * nums[i],
                                minEndingHere * nums[i]));

                minEndingHere = Math.min(nums[i],
                        Math.min(tempMaxEndingHere * nums[i],
                                minEndingHere * nums[i]));

                maxOverall = Math.max(maxOverall, maxEndingHere);
            }

            return maxOverall;
        }
    }

// [2,3,-2,4]
}

//    152. Maximum Product Subarray
//    Medium
//    Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
//
//    It is guaranteed that the answer will fit in a 32-bit integer.
//
//    A subarray is a contiguous subsequence of the array.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,-2,4]
//    Output: 6
//    Explanation: [2,3] has the largest product 6.
//    Example 2:
//
//    Input: nums = [-2,0,-1]
//    Output: 0
//    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 104
//    -10 <= nums[i] <= 10
//    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.