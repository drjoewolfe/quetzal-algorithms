package org.jwolfe.quetzal.algorithms.lc;

public class SubarraySumsDivisibleByK {
    class Solution {
        public int subarraysDivByK(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return 0;
            }

            int n = nums.length;
            int[] prefixSums = new int[n];
            prefixSums[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i];
            }

            int[] remainderCounts = new int[k];
            remainderCounts[0] = 1;

            int count = 0;
            for (int i = 0; i < n; i++) {
                int remainder = ((prefixSums[i] % k) + k) % k;
                count += remainderCounts[remainder];

                remainderCounts[remainder]++;
            }

            return count;
        }
    }

    class Solution_Correct_2 {
        public int subarraysDivByK(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 2) {
                return 0;
            }

            int n = nums.length;
            int prefixSum = 0;
            int count = 0;

            int[] remainderCounts = new int[k];
            remainderCounts[0] = 1;
            for (int i = 0; i < n; i++) {
                prefixSum += nums[i];
                int remainder = (prefixSum % k + k) % k;
                count += remainderCounts[remainder];

                remainderCounts[remainder]++;
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int subarraysDivByK(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 2) {
                return 0;
            }

            int n = nums.length;
            int[] prefixSums = new int[n];
            prefixSums[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefixSums[i] = nums[i] + prefixSums[i - 1];
            }

            int count = 0;
            int[] remainderCounts = new int[k];
            remainderCounts[0] = 1;
            for (int i = 0; i < n; i++) {
                int remainder = (prefixSums[i] % k + k) % k;
                count += remainderCounts[remainder];

                remainderCounts[remainder]++;
            }

            return count;
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public int subarraysDivByK(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 2) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for (int i = 0; i < n; i++) {
                int sum = 0;

                for (int j = i; j < n; j++) {
                    sum += nums[j];
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

// [4,5,0,-2,-3,1]
// 5

// [5]
// 9
}

//    974. Subarray Sums Divisible by K
//    Medium
//    Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
//
//    A subarray is a contiguous part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [4,5,0,-2,-3,1], k = 5
//    Output: 7
//    Explanation: There are 7 subarrays with a sum divisible by k = 5:
//    [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
//    Example 2:
//
//    Input: nums = [5], k = 9
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 3 * 104
//    -104 <= nums[i] <= 104
//    2 <= k <= 104