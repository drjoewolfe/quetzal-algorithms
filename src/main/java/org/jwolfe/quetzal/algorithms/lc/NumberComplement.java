package org.jwolfe.quetzal.algorithms.lc;

public class NumberComplement {
    class Solution {
        public int findComplement(int num) {
            int complement = 0;
            int position = 1;

            while(num != 0) {
                if((num & 1) == 0) {
                    complement |= position;
                }

                num >>= 1;
                position <<= 1;
            }

            return complement;
        }
    }

    class Solution_Correct_1 {
        public int findComplement(int num) {
            int complement = 0;
            int bitPosition = 1;

            while(num != 0) {
                if((num & 1) == 0) {
                    complement |= bitPosition;
                }

                bitPosition <<= 1;
                num >>= 1;
            }

            return complement;
        }
    }
}

//    476. Number Complement
//    Easy
//    The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
//
//    For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
//    Given an integer num, return its complement.
//
//
//
//    Example 1:
//
//    Input: num = 5
//    Output: 2
//    Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
//    Example 2:
//
//    Input: num = 1
//    Output: 0
//    Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
//
//
//    Constraints:
//
//    1 <= num < 231
//
//
//    Note: This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/