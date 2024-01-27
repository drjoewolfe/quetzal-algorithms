package org.jwolfe.quetzal.algorithms.lc;

public class KInversePairsArray {
    class Solution {
        private int MOD = 1_000_000_007;

        public int kInversePairs(int n, int k) {
            if(n < 1 || k < 0) {
                return 0;
            }

            int[][] dp = new int[n + 1][k + 1];
            dp[0][0] = 1;

            for(int len = 1; len <= n; len++) {
                for(int inversePairs = 0; inversePairs <= k; inversePairs++) {
                    for(int i = 0; i <= Math.min(inversePairs, len - 1); i++) {
                        dp[len][inversePairs] = (dp[len][inversePairs] + dp[len - 1][inversePairs - i]) % MOD;
                    }
                }
            }

            return dp[n][k];
        }
    }

    class Solution_Incorrect {
        int MOD = 1_000_000_007;

        public int kInversePairs(int n, int k) {
            if(n < 1 || k < 0 || n > 1000 || k > 1000) {
                return 0;
            }

            Integer[][] memo = new Integer[1001][1001];
            return computeKInversePairs(n, k, memo);
        }

        private int computeKInversePairs(int n, int k, Integer[][] memo) {
            if(n == 0) {
                return 0;
            }

            if(k == 0) {
                return 1;
            }

            if(memo[n][k] != null) {
                return memo[n][k];
            }

            long inversions = 0;
            for(int i = 0; i <= Math.min(k, n - 1); i++) {
                inversions +=  computeKInversePairs(n - 1, k - i, memo);
                inversions %= MOD;
            }

            memo[n][k] = (int) inversions;
            return memo[n][k];
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }


    class Solution_Brute {
        long arrayCount;
        int MOD = 1_000_000_007;

        public int kInversePairs(int n, int k) {
            if(n < 1 || k < 0) {
                return 0;
            }

            if(k == 0) {
                return 1;
            }

            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = i;
            }

            arrayCount = 0;
            kInversePairs(arr, n, k, 0);

            return (int) (arrayCount % MOD);
        }

        private void kInversePairs(int[] arr, int n, int k, int index) {
            if(index == n) {
                if(hasKInversePairs(arr, k)) {
                    arrayCount++;
                }

                return;
            }

            for(int i = index; i < n; i++) {
                swap(arr, index, i);
                kInversePairs(arr, n, k, index + 1);

                swap(arr, index, i);
            }
        }

        private boolean hasKInversePairs(int[] arr, int k) {
            int count = 0;

            for(int i = 0; i < arr.length - 1; i++) {
                for(int j = i + 1; j < arr.length; j++) {
                    if(arr[i] > arr[j]) {
                        count++;
                    }

                    if(count > k) {
                        return false;
                    }
                }
            }

            return count == k;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 3
// 0

// 3
// 1

// 10
// 55

// 2
// 1
}

//    629. K Inverse Pairs Array
//    Hard
//    For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
//
//    Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 3, k = 0
//    Output: 1
//    Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
//    Example 2:
//
//    Input: n = 3, k = 1
//    Output: 2
//    Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
//
//
//    Constraints:
//
//    1 <= n <= 1000
//    0 <= k <= 1000