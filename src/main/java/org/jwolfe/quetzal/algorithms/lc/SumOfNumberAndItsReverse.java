package org.jwolfe.quetzal.algorithms.lc;

public class SumOfNumberAndItsReverse {
    class Solution {
        public boolean sumOfNumberAndReverse(int num) {
            if(num < 0) {
                return false;
            }

            for(int a = 0; a <= num; a++) {
                int r = reverse(a);
                if(a + r == num) {
                    return true;
                }
            }

            return false;
        }

        private int reverse(int n) {
            int r = 0;
            while(n > 0) {
                r *= 10;
                r += n % 10;

                n /= 10;
            }

            return r;
        }
    }
}

//    2443. Sum of Number and Its Reverse
//    Medium
//    Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer and its reverse, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: num = 443
//    Output: true
//    Explanation: 172 + 271 = 443 so we return true.
//    Example 2:
//
//    Input: num = 63
//    Output: false
//    Explanation: 63 cannot be expressed as the sum of a non-negative integer and its reverse so we return false.
//    Example 3:
//
//    Input: num = 181
//    Output: true
//    Explanation: 140 + 041 = 181 so we return true. Note that when a number is reversed, there may be leading zeros.
//
//
//    Constraints:
//
//    0 <= num <= 105