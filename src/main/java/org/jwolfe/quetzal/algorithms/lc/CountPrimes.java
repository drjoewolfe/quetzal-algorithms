package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CountPrimes {
    class Solution {
        public int countPrimes(int n) {
            if(n <= 2) {
                return 0;
            }

            boolean[] primes = new boolean[n];
            Arrays.fill(primes, true);
            primes[0] = primes[1] = false;


            for(int i = 2; i * i < n; i++) {
                if(primes[i]) {
                    for(int j = i; j * i < n; j++) {
                        primes[j * i] = false;
                    }
                }
            }

            int count = 0;
            for(int i = 1; i < primes.length; i++) {
                if(primes[i]) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_Brute {
        public int countPrimes(int n) {
            if(n < 0) {
                return 0;
            }

            int count = 0;
            for(int i = 2; i < n; i++) {
                if(isPrime(i)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isPrime(int n) {
            for(int i = 2; i <= n / 2; i++) {
                if(n % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    204. Count Primes
//    Easy
//    Count the number of prime numbers less than a non-negative number, n.
//
//
//
//    Example 1:
//
//    Input: n = 10
//    Output: 4
//    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
//    Example 2:
//
//    Input: n = 0
//    Output: 0
//    Example 3:
//
//    Input: n = 1
//    Output: 0
//
//
//    Constraints:
//
//    0 <= n <= 5 * 106
