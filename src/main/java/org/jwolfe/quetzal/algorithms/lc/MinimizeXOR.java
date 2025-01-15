package org.jwolfe.quetzal.algorithms.lc;

public class MinimizeXOR {
    class Solution {
        public int minimizeXor(int num1, int num2) {
            int answer = num1;

            int target = countBits(num2);
            int current = countBits(num1);
            int position = 0;

            while (current < target) {
                if (!isSet(answer, position)) {
                    answer = setBit(answer, position);
                    current++;
                }

                position++;
            }

            while (current > target) {
                if (isSet(answer, position)) {
                    answer = unSetBit(answer, position);
                    current--;
                }

                position++;
            }

            return answer;
        }

        private int countBits(int num) {
            return Integer.bitCount(num);
        }

        private boolean isSet(int num, int pos) {
            return (num & (1 << pos)) > 0;
        }

        private int setBit(int num, int pos) {
            return num | (1 << pos);
        }

        private int unSetBit(int num, int pos) {
            return num & ~(1 << pos);
        }
    }

    class Solution_Correct_1 {
        public int minimizeXor(int num1, int num2) {
            int numBits2 = getNumSetBits(num2);
            int numBits1 = getNumSetBits(num1);

            if (numBits1 < numBits2) {
                int delta = numBits2 - numBits1;

                // set the delta least important bits of nums1
                for (int i = 0; i < 32; i++) {
                    if ((num1 & (1 << i)) == 0) {
                        num1 |= (1 << i);
                        delta--;

                        if (delta == 0) {
                            break;
                        }
                    }
                }

            } else if (numBits1 > numBits2) {
                int delta = numBits1 - numBits2;

                // unset the delta least important bits of nums1
                for (int i = 0; i < 32; i++) {
                    if ((num1 & (1 << i)) > 0) {
                        num1 ^= (1 << i);
                        delta--;

                        if (delta == 0) {
                            break;
                        }
                    }
                }
            }

            return num1;
        }

        private int getNumSetBits(int num) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) > 0) {
                    count++;
                }
            }

            return count;
        }
    }

// 3
// 5

// 1
// 12

// 25
// 72
}

//    2429. Minimize XOR
//    Medium
//    Given two positive integers num1 and num2, find the positive integer x such that:
//
//    x has the same number of set bits as num2, and
//    The value x XOR num1 is minimal.
//    Note that XOR is the bitwise XOR operation.
//
//    Return the integer x. The test cases are generated such that x is uniquely determined.
//
//    The number of set bits of an integer is the number of 1's in its binary representation.
//
//
//
//    Example 1:
//
//    Input: num1 = 3, num2 = 5
//    Output: 3
//    Explanation:
//    The binary representations of num1 and num2 are 0011 and 0101, respectively.
//    The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
//    Example 2:
//
//    Input: num1 = 1, num2 = 12
//    Output: 3
//    Explanation:
//    The binary representations of num1 and num2 are 0001 and 1100, respectively.
//    The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
//
//
//    Constraints:
//
//    1 <= num1, num2 <= 109