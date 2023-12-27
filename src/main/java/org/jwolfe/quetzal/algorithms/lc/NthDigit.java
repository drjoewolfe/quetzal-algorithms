package org.jwolfe.quetzal.algorithms.lc;

public class NthDigit {
    class Solution {
        public int findNthDigit(int n) {
            int length = 1;
            long count = 9;

            int start = 1;

            while(n > (length * count)) {
                n -= (length * count);

                length++;
                count *= 10;
                start *= 10;
            }

            // [start..] is the start of the range with "length" digits
            // "n" count of digits to be counted in this range

            int number = start + (n - 1) / length;
            String str = Integer.toString(number);
            return str.charAt((n - 1) % length) - '0';
        }
    }

// 3
// 11
// 1000000000
}

//    400. Nth Digit
//    Medium
//    Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
//
//
//
//    Example 1:
//
//    Input: n = 3
//    Output: 3
//    Example 2:
//
//    Input: n = 11
//    Output: 0
//    Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
//
//
//    Constraints:
//
//    1 <= n <= 231 - 1