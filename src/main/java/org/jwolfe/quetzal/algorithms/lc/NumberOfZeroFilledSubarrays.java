package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfZeroFilledSubarrays {
    class Solution {
        public long zeroFilledSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0L;
            }

            long count = 0;
            long streak = 0;
            for (int val : nums) {
                if (val == 0) {
                    streak++;
                } else {
                    streak = 0;
                }

                count += streak;
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public long zeroFilledSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            long count = 0;

            int zeroPrefix = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    count += zeroPrefix + 1;
                    zeroPrefix++;
                } else {
                    zeroPrefix = 0;
                }
            }

            return count;
        }
    }

    class Solution_Brute {
        public long zeroFilledSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            long count = 0;
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    boolean zeroArray = true;
                    for (int k = i; k <= j; k++) {
                        if (nums[k] != 0) {
                            zeroArray = false;
                        }
                    }

                    if (zeroArray) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

// [1,3,0,0,2,0,0,4]
}

//    2348. Number of Zero-Filled Subarrays
//    Medium
//    Given an integer array nums, return the number of subarrays filled with 0.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,0,0,2,0,0,4]
//    Output: 6
//    Explanation:
//    There are 4 occurrences of [0] as a subarray.
//    There are 2 occurrences of [0,0] as a subarray.
//    There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
//    Example 2:
//
//    Input: nums = [0,0,0,2,0,0]
//    Output: 9
//    Explanation:
//    There are 5 occurrences of [0] as a subarray.
//    There are 3 occurrences of [0,0] as a subarray.
//    There is 1 occurrence of [0,0,0] as a subarray.
//    There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
//    Example 3:
//
//    Input: nums = [2,10,2019]
//    Output: 0
//    Explanation: There is no subarray filled with 0. Therefore, we return 0.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -109 <= nums[i] <= 109
