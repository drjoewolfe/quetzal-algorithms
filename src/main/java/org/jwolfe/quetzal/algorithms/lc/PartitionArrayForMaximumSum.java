package org.jwolfe.quetzal.algorithms.lc;

public class PartitionArrayForMaximumSum {
    class Solution {
        public int maxSumAfterPartitioning(int[] arr, int k) {
            if(arr == null || arr.length == 0 || k < 1) {
                return 0;
            }

            int n = arr.length;
            int[] dp = new int[n + 1];

            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= k && i - j >= 0; j++) {
                    for(int pos = 1; pos <= j; pos++) {
                        dp[i] = Math.max(dp[i],
                                dp[i - j] + arr[i - pos] * j
                        );
                    }
                }
            }

            return dp[n];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    1043. Partition Array for Maximum Sum
//    Medium
//    Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
//
//    Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.
//
//
//
//    Example 1:
//
//    Input: arr = [1,15,7,9,2,5,10], k = 3
//    Output: 84
//    Explanation: arr becomes [15,15,15,9,10,10,10]
//    Example 2:
//
//    Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//    Output: 83
//    Example 3:
//
//    Input: arr = [1], k = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= arr.length <= 500
//    0 <= arr[i] <= 109
//    1 <= k <= arr.length