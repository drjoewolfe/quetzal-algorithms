package org.jwolfe.quetzal.algorithms.lc;

public class CountNumberOfPairsWithAbsoluteDifferenceK {
    class Solution {
        public int countKDifference(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            int n = nums.length;

            int[] buckets = new int[101];
            for(int val : nums) {
                buckets[val]++;
            }

            int pairCount = 0;
            for(int i = 1; i <= (100 - k); i++) {
                pairCount += (buckets[i] * buckets[k + i]);
            }

            return pairCount;
        }
    }

    class Solution_Brute {
        public int countKDifference(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            int n = nums.length;
            int pairCount = 0;
            for(int i = 0; i < n - 1; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n; j++) {
                    int b = nums[j];

                    if(Math.abs(a - b) == k) {
                        pairCount++;
                    }
                }
            }

            return pairCount;
        }
    }
}

//    2006. Count Number of Pairs With Absolute Difference K
//    Easy
//    Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.
//
//    The value of |x| is defined as:
//
//    x if x >= 0.
//    -x if x < 0.
//
//
//    Example 1:
//
//    Input: nums = [1,2,2,1], k = 1
//    Output: 4
//    Explanation: The pairs with an absolute difference of 1 are:
//    - [1,2,2,1]
//    - [1,2,2,1]
//    - [1,2,2,1]
//    - [1,2,2,1]
//    Example 2:
//
//    Input: nums = [1,3], k = 3
//    Output: 0
//    Explanation: There are no pairs with an absolute difference of 3.
//    Example 3:
//
//    Input: nums = [3,2,1,5,4], k = 2
//    Output: 3
//    Explanation: The pairs with an absolute difference of 2 are:
//    - [3,2,1,5,4]
//    - [3,2,1,5,4]
//    - [3,2,1,5,4]
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    1 <= nums[i] <= 100
//    1 <= k <= 99