package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CountTheNumberOfFairPairs {
    class Solution {
        public long countFairPairs(int[] nums, int lower, int upper) {
            if(nums == null || nums.length < 2 || lower > upper) {
                return 0;
            }

            long count = 0;

            Arrays.sort(nums);
            for(int i = 0; i < nums.length - 1; i++) {
                int lowerBound = getLowerBound(nums, i, lower, upper);
                int upperBound = getUpperBound(nums, i, lower, upper);

                if(lowerBound == -1) {
                    continue;
                }


                count += (upperBound - lowerBound + 1);
            }

            return count;
        }

        private int getLowerBound(int[] nums, int start, int lower, int upper) {
            int left = start + 1;
            int right = nums.length - 1;
            int index = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int val = nums[start] + nums[mid];

                if(val >= lower && val <= upper) {
                    index = mid;
                    right = mid - 1;
                } else if(val < lower) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }

        private int getUpperBound(int[] nums, int start, int lower, int upper) {
            int left = start + 1;
            int right = nums.length - 1;
            int index = -1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int val = nums[start] + nums[mid];

                if(val >= lower && val <= upper) {
                    index = mid;
                    left = mid + 1;
                } else if(val < lower) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [0,1,7,4,4,5]
// 3
// 6

// [1,7,9,2,5]
// 11
// 11

// [6,9,4,2,7,5,10,3]
// 13
// 13
}

//    2563. Count the Number of Fair Pairs
//    Medium
//    Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
//
//    A pair (i, j) is fair if:
//
//    0 <= i < j < n, and
//    lower <= nums[i] + nums[j] <= upper
//
//
//    Example 1:
//
//    Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
//    Output: 6
//    Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
//    Example 2:
//
//    Input: nums = [1,7,9,2,5], lower = 11, upper = 11
//    Output: 1
//    Explanation: There is a single fair pair: (2,3).
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    nums.length == n
//    -109 <= nums[i] <= 109
//    -109 <= lower <= upper <= 109
