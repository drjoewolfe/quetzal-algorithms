package org.jwolfe.quetzal.algorithms.lc;

public class MaximumProductOfThreeNumbers {
    class Solution {
        public int maximumProduct(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            int largest = Integer.MIN_VALUE;
            int secondLargest = Integer.MIN_VALUE;
            int thirdLargest = Integer.MIN_VALUE;

            int smallest = Integer.MAX_VALUE;
            int secondSmallest = Integer.MAX_VALUE;

            for (int val : nums) {
                int pLargest = largest;
                int pSecondLargest = secondLargest;
                int pSmallest = smallest;

                largest = Math.max(largest, val);
                secondLargest = Math.max(secondLargest, Math.min(pLargest, val));
                thirdLargest = Math.max(thirdLargest, Math.min(pSecondLargest, val));

                smallest = Math.min(smallest, val);
                secondSmallest = Math.min(secondSmallest, Math.max(pSmallest, val));
            }

            int option1 = largest * secondLargest * thirdLargest;
            int option2 = smallest * secondSmallest * largest;

            return Math.max(option1, option2);
        }
    }
}

//    628. Maximum Product of Three Numbers
//    Easy
//    Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: 6
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: 24
//    Example 3:
//
//    Input: nums = [-1,-2,-3]
//    Output: -6
//
//
//    Constraints:
//
//    3 <= nums.length <= 104
//    -1000 <= nums[i] <= 1000