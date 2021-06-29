package org.jwolfe.quetzal.algorithms.lc;

public class MaxConsecutiveOnesIII {
    class Solution {
        public int longestOnes(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            int left = 0;
            int right = 0;

            int oneCount = 0;
            int maxOnes = 0;
            int windowZeros = 0;

            int n = nums.length;
            while(right < n) {
                oneCount++;
                if(nums[right] == 0) {
                    windowZeros++;
                }

                while(left <= right && windowZeros > k) {
                    oneCount--;
                    if(nums[left] == 0) {
                        windowZeros--;
                    }

                    left++;
                }

                maxOnes = Math.max(maxOnes, oneCount);
                right++;
            }

            return maxOnes;
        }
    }

// [1,1,1,0,0,0,1,1,1,1,0]
// 2

// [0,0,0,0]
// 0
}

//    1004. Max Consecutive Ones III
//    Medium
//    Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//    Output: 6
//    Explanation: [1,1,1,0,0,1,1,1,1,1,1]
//    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//    Example 2:
//
//    Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
//    Output: 10
//    Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//    Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    nums[i] is either 0 or 1.
//    0 <= k <= nums.length