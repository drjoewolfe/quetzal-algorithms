package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfSubArraysWithOddSum {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numOfSubarrays(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int count = 0;

            int runningSum = 0;
            int odd = 0;
            int even = 0;

            for (int i = 0; i < n; i++) {
                runningSum += arr[i];

                if (runningSum % 2 == 0) {
                    count += odd;
                    even++;
                } else {
                    count++;
                    count += even;

                    odd++;
                }

                count %= MOD;
            }


            return count;
        }
    }

    class Solution_Correct_1 {
        public int numOfSubarrays(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int mod = 1000000007;
            int n = arr.length;
            int even = 1;
            int odd = 0;
            int sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i];
                if (sum % 2 == 0) {
                    count += odd;
                    even++;
                } else {
                    count += even;
                    odd++;
                }

                count %= mod;
            }

            return count;
        }
    }

    class Solution_n2 {
        public int numOfSubarrays(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int count = 0;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += arr[j];

                    if (sum % 2 != 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_Brute {
        public int numOfSubarrays(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;
            int count = 0;
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    int sum = 0;
                    for (int u = i; u <= j; u++) {
                        sum += arr[u];
                    }

                    if (sum % 2 != 0) {
                        count++;
                    }

                    // System.out.println(i + ", " + j + ", " + sum + ", " + count);
                }
            }

            return count;
        }
    }
}

//    1524. Number of Sub-arrays With Odd Sum
//    Medium
//    Given an array of integers arr, return the number of subarrays with an odd sum.
//
//    Since the answer can be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: arr = [1,3,5]
//    Output: 4
//    Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
//    All sub-arrays sum are [1,4,9,3,8,5].
//    Odd sums are [1,9,3,5] so the answer is 4.
//    Example 2:
//
//    Input: arr = [2,4,6]
//    Output: 0
//    Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
//    All sub-arrays sum are [2,6,12,4,10,6].
//    All sub-arrays have even sum and the answer is 0.
//    Example 3:
//
//    Input: arr = [1,2,3,4,5,6,7]
//    Output: 16
//
//
//    Constraints:
//
//    1 <= arr.length <= 105
//    1 <= arr[i] <= 100