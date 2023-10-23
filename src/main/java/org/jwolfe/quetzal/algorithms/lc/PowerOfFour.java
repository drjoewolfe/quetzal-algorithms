package org.jwolfe.quetzal.algorithms.lc;

public class PowerOfFour {
    class Solution {
        public boolean isPowerOfFour(int num) {
            return num > 0
                    && ((num & (num - 1)) == 0)
                    && ((num - 1) % 3 == 0);
        }
    }

    class Solution_Correct_2 {
        public boolean isPowerOfFour(int num) {
            return (num > 0)
                    && ((num & (num - 1)) == 0)
                    && ((num - 1) % 3 == 0);
        }
    }

    class Solution_Correct_1 {
        public boolean isPowerOfFour(int num) {
            int evenSetBits = 0;
            for(int i = 0; i < 32; i++) {
                if( ((num >> i) & 1) == 1) {
                    if(i % 2 == 0) {
                        // Even bit set
                        evenSetBits++;
                    } else {
                        return false;
                    }
                }
            }

            if(evenSetBits == 1) {
                return true;
            }

            return false;
        }
    }

    class Solution_Try01 {
        public boolean isPowerOfFour(int num) {
            if(num % 4 > 0) {
                return false;
            }

            if(num == Integer.MIN_VALUE) {
                return false;
            }

            num = Math.abs(num);
            while(num > 0) {
                num = num / 4;
                System.out.println(num);
                if(num > 1 && num % 4 > 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    342. Power of Four
//    Easy
//    Given an integer n, return true if it is a power of four. Otherwise, return false.
//
//    An integer n is a power of four, if there exists an integer x such that n == 4x.
//
//
//
//    Example 1:
//
//    Input: n = 16
//    Output: true
//    Example 2:
//
//    Input: n = 5
//    Output: false
//    Example 3:
//
//    Input: n = 1
//    Output: true
//
//
//    Constraints:
//
//    -231 <= n <= 231 - 1
//
//
//    Follow up: Could you solve it without loops/recursion?