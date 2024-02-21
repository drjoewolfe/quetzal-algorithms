package org.jwolfe.quetzal.algorithms.lc;

public class BitwiseANDOfNumbersRange {
    class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            int counter = 0;

            while(m != n) {
                m >>= 1;
                n >>= 1;

                counter++;
            }

            return m << counter;
        }
    }

    class Solution_Correct_1 {
        public int rangeBitwiseAnd(int m, int n) {
            int counter = 0;

            while(m != n) {
                m >>= 1;
                n >>= 1;

                counter++;
            }

            return m << counter;
        }
    }

    class Solution_Brute {
        public int rangeBitwiseAnd(int m, int n) {
            int result = m;
            for(int i = m + 1; i <= n; i++) {
                result &= i;
            }

            return result;
        }
    }


// 5 ->  1 0 1
// 6 ->  1 1 0
// 7 ->  1 1 1

// A ->  1 0 0
}

//    201. Bitwise AND of Numbers Range
//    Medium
//    Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
//
//
//
//    Example 1:
//
//    Input: left = 5, right = 7
//    Output: 4
//    Example 2:
//
//    Input: left = 0, right = 0
//    Output: 0
//    Example 3:
//
//    Input: left = 1, right = 2147483647
//    Output: 0
//
//
//    Constraints:
//
//    0 <= left <= right <= 231 - 1