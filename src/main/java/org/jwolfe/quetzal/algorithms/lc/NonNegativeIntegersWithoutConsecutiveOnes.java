package org.jwolfe.quetzal.algorithms.lc;

public class NonNegativeIntegersWithoutConsecutiveOnes {
    class Solution {
        public int findIntegers(int n) {
            if(n < 0) {
                return 0;
            }

            int[] fib = getFibonacci(32);

            int count = 0;
            int prevBit = 0;
            for(int i = 31; i >= 0; i--) {
                if((n & (1 << i)) > 0) {
                    // MSB at i-th position
                    count += fib[i];
                    if(prevBit == 1) {
                        return count;
                    }

                    prevBit = 1;
                } else {
                    prevBit = 0;
                }
            }

            return count + 1;
        }

        private int[] getFibonacci(int n) {
            int[] fib = new int[n];
            fib[0] = 1;
            fib[1] = 2;

            for(int i = 2; i < n; i++) {
                fib[i] = fib[i - 1] + fib[i - 2];
            }

            return fib;
        }

        private void print(int[] arr) {
            for(var a : arr) {
                System.out.print(a + " ");
            }
        }
    }

    class Solution_Brute {
        public int findIntegers(int n) {
            if(n < 0) {
                return 0;
            }

            int count = 1;
            for(int i = 1; i <= n; i++) {
                if(!hasConsecutiveOnes(i)) {
                    count++;
                }
            }

            return count;
        }

        private boolean hasConsecutiveOnes(int x) {
            int prev = x & 1;
            x >>= 1;

            while(x > 0) {
                int curr = x & 1;
                if(prev == 1 && curr == 1) {
                    return true;
                }

                prev = curr;
                x >>= 1;
            }

            return false;
        }
    }

// 1000000000
// 5
}

//    600. Non-negative Integers without Consecutive Ones
//    Hard
//    Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.
//
//
//
//    Example 1:
//
//    Input: n = 5
//    Output: 5
//    Explanation:
//    Here are the non-negative integers <= 5 with their corresponding binary representations:
//    0 : 0
//    1 : 1
//    2 : 10
//    3 : 11
//    4 : 100
//    5 : 101
//    Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
//    Example 2:
//
//    Input: n = 1
//    Output: 2
//    Example 3:
//
//    Input: n = 2
//    Output: 3
//
//
//    Constraints:
//
//    1 <= n <= 109