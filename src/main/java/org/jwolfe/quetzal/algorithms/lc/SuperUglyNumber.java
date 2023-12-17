package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SuperUglyNumber {
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            if(n < 1 || primes == null || primes.length == 0) {
                return 0;
            }

            long[] dp = new long[n + 1];
            dp[1] = 1;

            int pn = primes.length;
            int[] pointers = new int[pn];
            Arrays.fill(pointers, 1);

            int lastMinPointerValue = -1;
            for(int i = 2; i <= n; i++) {
                long min = Integer.MAX_VALUE;
                for(int j = 0; j < pn; j++) {
                    long candidate = primes[j] * dp[pointers[j]];
                    min = Math.min(min, candidate);
                }

                dp[i] = min;

                for(int j = 0; j < pn; j++) {
                    long candidate = primes[j] * dp[pointers[j]];
                    if(candidate == min) {
                        pointers[j]++;
                    }
                }
            }

            // print(dp);
            return (int) dp[n];
        }

        private void print(long[] arr) {
            for(long a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 12
// [2,7,13,19]

// 15
// [3,5,7,11,19,23,29,41,43,47]

// 5911
// [2,3,5,7]
}

//    313. Super Ugly Number
//    Medium
//    A super ugly number is a positive integer whose prime factors are in the array primes.
//
//    Given an integer n and an array of integers primes, return the nth super ugly number.
//
//    The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
//
//
//
//    Example 1:
//
//    Input: n = 12, primes = [2,7,13,19]
//    Output: 32
//    Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
//    Example 2:
//
//    Input: n = 1, primes = [2,3,5]
//    Output: 1
//    Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
//
//
//    Constraints:
//
//    1 <= n <= 105
//    1 <= primes.length <= 100
//    2 <= primes[i] <= 1000
//    primes[i] is guaranteed to be a prime number.
//    All the values of primes are unique and sorted in ascending order.