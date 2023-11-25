package org.jwolfe.quetzal.algorithms.lc;

public class SumOfAbsoluteDifferencesInASortedArray {
    class Solution {
        public int[] getSumAbsoluteDifferences(int[] nums) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] prefixSums = new int[n];
            int[] suffixSums = new int[n];

            for(int i = 1; i < n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
            }

            for(int i = n - 2; i >= 0; i--) {
                suffixSums[i] = suffixSums[i + 1] + nums[i + 1];
            }


            int[] results = new int[n];
            for(int i = 0; i < n; i++) {
                results[i] = nums[i] * i - nums[i] * (n - i - 1) - prefixSums[i] + suffixSums[i];
            }

            return results;
        }
    }

    class Solution_Brute_TLE {
        public int[] getSumAbsoluteDifferences(int[] nums) {
            if(nums == null || nums.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int[] results = new int[n];
            for(int i = 0; i < n; i++) {
                int a = nums[i];

                int sum = 0;
                for(int j = 0; j < n; j++) {
                    int b = nums[j];

                    sum += Math.abs(a - b);
                }

                results[i] = sum;
            }

            return results;
        }
    }
}

//    1685. Sum of Absolute Differences in a Sorted Array
//    Medium
//    You are given an integer array nums sorted in non-decreasing order.
//
//    Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
//
//    In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,5]
//    Output: [4,3,5]
//    Explanation: Assuming the arrays are 0-indexed, then
//    result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
//    result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
//    result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
//    Example 2:
//
//    Input: nums = [1,4,6,8,10]
//    Output: [24,15,13,15,21]
//
//
//    Constraints:
//
//    2 <= nums.length <= 105
//    1 <= nums[i] <= nums[i + 1] <= 104
//
//    Absolute difference is the same as max(a, b) - min(a, b). How can you use this fact with the fact that the array is sorted?
//    For nums[i], the answer is (nums[i] - nums[0]) + (nums[i] - nums[1]) + ... + (nums[i] - nums[i-1]) + (nums[i+1] - nums[i]) + (nums[i+2] - nums[i]) + ... + (nums[n-1] - nums[i]).
//    It can be simplified to (nums[i] * i - (nums[0] + nums[1] + ... + nums[i-1])) + ((nums[i+1] + nums[i+2] + ... + nums[n-1]) - nums[i] * (n-i-1)). One can build prefix and suffix sums to compute this quickly.