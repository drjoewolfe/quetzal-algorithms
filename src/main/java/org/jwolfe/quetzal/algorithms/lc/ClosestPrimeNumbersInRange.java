package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPrimeNumbersInRange {
    class Solution {
        public int[] closestPrimes(int left, int right) {
            int[] sieve = generateSieve(right);

            List<Integer> primes = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                if (sieve[i] == 1) {
                    primes.add(i);
                }
            }

            int[] result = new int[2];
            Arrays.fill(result, -1);

            if (primes.size() < 2) {
                return result;
            }

            int minDifference = Integer.MAX_VALUE;
            for (int i = 1; i < primes.size(); i++) {
                int b = primes.get(i);
                int a = primes.get(i - 1);
                int diff = b - a;

                if (diff < minDifference) {
                    minDifference = diff;
                    result[0] = a;
                    result[1] = b;
                }
            }

            return result;
        }

        private int[] generateSieve(int n) {
            int[] sieve = new int[n + 1];

            // 1 = prime, 0 = non-prime
            // Assume all are prime
            Arrays.fill(sieve, 1);

            // 0 & 1 are not prime
            sieve[0] = 0;
            sieve[1] = 0;

            for (int i = 2; i * i <= n; i++) {
                if (sieve[i] == 1) {
                    // multiples of a prime are non-prime
                    for (int j = i * i; j <= n; j += i) {
                        sieve[j] = 0;
                    }
                }
            }

            return sieve;
        }
    }
}

//    2523. Closest Prime Numbers in Range
//    Medium
//    Given two positive integers left and right, find the two integers num1 and num2 such that:
//
//    left <= num1 < num2 <= right .
//    Both num1 and num2 are prime numbers.
//    num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
//    Return the positive integer array ans = [num1, num2]. If there are multiple pairs satisfying these conditions, return the one with the smallest num1 value. If no such numbers exist, return [-1, -1].
//
//
//
//    Example 1:
//
//    Input: left = 10, right = 19
//    Output: [11,13]
//    Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
//    The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
//    Since 11 is smaller than 17, we return the first pair.
//    Example 2:
//
//    Input: left = 4, right = 6
//    Output: [-1,-1]
//    Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
//
//
//    Constraints:
//
//    1 <= left <= right <= 106