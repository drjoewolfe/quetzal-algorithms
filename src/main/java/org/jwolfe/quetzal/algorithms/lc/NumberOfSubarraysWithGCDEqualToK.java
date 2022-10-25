package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfSubarraysWithGCDEqualToK {
    class Solution {
        public int subarrayGCD(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for(int i = 0; i < n; i++) {
                int result = nums[i];
                for(int j = i; j < n && (nums[j] % k == 0); j++) {
                    result = gcd(result, nums[j]);

                    if(result == k) {
                        count++;
                    }
                }
            }

            return count;
        }

        private int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

    class Solution_N3_TLE {
        public int subarrayGCD(int[] nums, int k) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for(int l = 1; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if(gcd(nums, i, j) == k) {
                        count++;
                    }
                }
            }

            return count;
        }

        private int gcd(int[] nums, int start, int end) {
            int result = nums[start];
            for(int i = start + 1; i <= end; i++) {
                result = gcd(result, nums[i]);
            }

            return result;
        }

        private int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

// [9,3,1,2,6,3]
// 3

// [1]
// 1
}

//    2447. Number of Subarrays With GCD Equal to K
//    Medium
//    Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//    The greatest common divisor of an array is the largest integer that evenly divides all the array elements.
//
//
//
//    Example 1:
//
//    Input: nums = [9,3,1,2,6,3], k = 3
//    Output: 4
//    Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
//    - [9,3,1,2,6,3]
//    - [9,3,1,2,6,3]
//    - [9,3,1,2,6,3]
//    - [9,3,1,2,6,3]
//    Example 2:
//
//    Input: nums = [4], k = 7
//    Output: 0
//    Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i], k <= 109