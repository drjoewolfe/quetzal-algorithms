package org.jwolfe.quetzal.algorithms.lc;

public class ThreeDivisors {
    class Solution {
        public boolean isThree(int n) {
            int divisors = 2;
            for(int x = 2; x <= n / 2; x++) {
                if(n % x == 0) {
                    divisors++;
                }

                if(divisors > 3) {
                    break;
                }
            }

            return divisors == 3;
        }
    }
}

//    1952. Three Divisors
//    Easy
//    Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
//
//    An integer m is a divisor of n if there exists an integer k such that n = k * m.
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: false
//    Explantion: 2 has only two divisors: 1 and 2.
//    Example 2:
//
//    Input: n = 4
//    Output: true
//    Explantion: 4 has three divisors: 1, 2, and 4.
//
//
//    Constraints:
//
//    1 <= n <= 104
