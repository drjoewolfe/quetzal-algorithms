package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfSubarraysWithBoundedMaximum {
    class Solution {
        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int subArrayCount = 0;

            int start = -1;
            int end = -1;
            for(int i = 0; i < n; i++) {
                int val = nums[i];

                if(val < left) {
                    int count = end - start;
                    subArrayCount += count;
                } else if(val > right) {
                    start = i;
                    end = i;
                } else {
                    end = i;
                    int count = end - start;
                    subArrayCount += count;
                }
            }

            return subArrayCount;
        }
    }

    class Solution_Brute {
        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int subArrayCount = 0;
            for(int i = 0; i < n; i++) {
                int max = nums[i];

                for(int j = i; j < n; j++) {
                    max = Math.max(max, nums[j]);

                    if(max >= left && max <= right) {
                        subArrayCount++;
                    }

                    if(max > right) {
                        break;
                    }
                }
            }

            return subArrayCount;
        }
    }

    class Solution_Incorrect {
        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int subarrayCount = 0;

            int max = Integer.MIN_VALUE;
            int start = -1;
            int end = -1;

            for(int i = 0; i < nums.length; i++) {
                max = Math.max(max, nums[i]);

                if(max >= left && max <= right) {
                    if(start == -1) {
                        start = i;
                        end = i;
                    } else {
                        end++;
                    }
                } else {
                    if(start != -1) {
                        int count = end - start + 1;
                        subarrayCount += (count * (count + 1)) / 2;
                        System.out.println("[1] " + start + ", " + end + ", " + max + ", " + count + ", " + subarrayCount);
                    }

                    max = Integer.MIN_VALUE;
                    start = -1;
                    end = -1;
                }
            }

            if(start != -1 && max >= left && max <= right) {
                int count = end - start + 1;
                subarrayCount += (count * (count + 1)) / 2;
                System.out.println("[2] " + start + ", " + end + ", " + max + ", " + count + ", " + subarrayCount);
            }

            return subarrayCount;
        }
    }

// [2,1,4,3]
// 2
// 3

// [2,9,2,5,6]
// 2
// 8

// [73,55,36,5,55,14,9,7,72,52]
// 32
// 69
}

//    795. Number of Subarrays with Bounded Maximum
//    Medium
//    We are given an array nums of positive integers, and two positive integers left and right (left <= right).
//
//    Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least left and at most right.
//
//    Example:
//    Input:
//    nums = [2, 1, 4, 3]
//    left = 2
//    right = 3
//    Output: 3
//    Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
//    Note:
//
//    left, right, and nums[i] will be an integer in the range [0, 109].
//    The length of nums will be in the range of [1, 50000].
