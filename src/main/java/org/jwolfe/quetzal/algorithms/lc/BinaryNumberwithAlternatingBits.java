package org.jwolfe.quetzal.algorithms.lc;

public class BinaryNumberwithAlternatingBits {
    class Solution {
        public boolean hasAlternatingBits(int n) {
            int prevBit = n & 1;
            n >>= 1;

            while (n > 0) {
                int currBit = n & 1;
                if (prevBit == currBit) {
                    return false;
                }

                prevBit = currBit;
                n >>= 1;
            }

            return true;
        }
    }

    class Solution_Incorrect_1 {
        public boolean hasAlternatingBits(int n) {
            boolean prevBitSet = ((n & 1) > 0);
            for (int i = 1; i < 32; i++) {
                boolean currBitSet = ((n & (1 << i)) > 0);
                if (prevBitSet == currBitSet) {
                    return false;
                }

                prevBitSet = currBitSet;
            }

            return true;
        }
    }
}

//    693. Binary Number with Alternating Bits
//    Easy
//    Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
//
//
//
//    Example 1:
//
//    Input: n = 5
//    Output: true
//    Explanation: The binary representation of 5 is: 101
//    Example 2:
//
//    Input: n = 7
//    Output: false
//    Explanation: The binary representation of 7 is: 111.
//    Example 3:
//
//    Input: n = 11
//    Output: false
//    Explanation: The binary representation of 11 is: 1011.
//
//
//    Constraints:
//
//    1 <= n <= 231 - 1