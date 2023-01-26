package org.jwolfe.quetzal.algorithms.lc;

public class AlternatingDigitSum {
    class Solution {
        public int alternateDigitSum(int n) {
            if(n < 1) {
                return 0;
            }

            int nr = reverse(n);

            int sum = 0;
            int sign = 1;
            while(nr > 0) {
                sum += sign * (nr % 10);
                nr /= 10;
                sign *= -1;
            }

            return sum;
        }

        private int reverse(int n) {
            int nr = 0;
            while(n > 0) {
                nr *= 10;
                nr += n % 10;

                n /= 10;
            }

            return nr;
        }
    }
}

//    2544. Alternating Digit Sum
//    Easy
//    You are given a positive integer n. Each digit of n has a sign according to the following rules:
//
//    The most significant digit is assigned a positive sign.
//    Each other digit has an opposite sign to its adjacent digits.
//    Return the sum of all digits with their corresponding sign.
//
//
//
//    Example 1:
//
//    Input: n = 521
//    Output: 4
//    Explanation: (+5) + (-2) + (+1) = 4.
//    Example 2:
//
//    Input: n = 111
//    Output: 1
//    Explanation: (+1) + (-1) + (+1) = 1.
//    Example 3:
//
//    Input: n = 886996
//    Output: 0
//    Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
//
//
//    Constraints:
//
//    1 <= n <= 109