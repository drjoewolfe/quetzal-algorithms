package org.jwolfe.quetzal.algorithms.lc;

public class SmallestNumberWithAllSetBits {
    class Solution {
        public int smallestNumber(int n) {
            int x = 1;
            while (x < n) {
                x = (x * 2) + 1;
            }

            return x;
        }
    }
}

//    3370. Smallest Number With All Set Bits
//    Easy
//    You are given a positive number n.
//
//    Return the smallest number x greater than or equal to n, such that the binary representation of x contains only set bits
//
//
//
//    Example 1:
//
//    Input: n = 5
//
//    Output: 7
//
//    Explanation:
//
//    The binary representation of 7 is "111".
//
//    Example 2:
//
//    Input: n = 10
//
//    Output: 15
//
//    Explanation:
//
//    The binary representation of 15 is "1111".
//
//    Example 3:
//
//    Input: n = 3
//
//    Output: 3
//
//    Explanation:
//
//    The binary representation of 3 is "11".
//
//
//
//    Constraints:
//
//    1 <= n <= 1000