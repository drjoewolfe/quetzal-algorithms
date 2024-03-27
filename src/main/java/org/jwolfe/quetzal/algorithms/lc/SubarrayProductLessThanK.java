package org.jwolfe.quetzal.algorithms.lc;

public class SubarrayProductLessThanK {
    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k <= 1) {
                return 0;
            }

            int count = 0;
            int n = nums.length;

            int left = 0;
            int right = 0;

            int product = 1;

            for(; right < n; right++) {
                int a = nums[right];
                product *= a;

                while(product >= k) {
                    product /= nums[left];
                    left++;
                }

                count += (right - left + 1);
            }


            return count;
        }
    }

// [10,5,2,6]
// 100

// [1,1,1]
// 1
}

//    713. Subarray Product Less Than K
//    Medium
//    Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
//
//
//
//    Example 1:
//
//    Input: nums = [10,5,2,6], k = 100
//    Output: 8
//    Explanation: The 8 subarrays that have product less than 100 are:
//    [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
//    Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
//    Example 2:
//
//    Input: nums = [1,2,3], k = 0
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 3 * 104
//    1 <= nums[i] <= 1000
//    0 <= k <= 106