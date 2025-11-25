package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class SmallestIntegerDivisibleByK {
    class Solution {
        public int smallestRepunitDivByK(int k) {
            int remainder = 0;
            for (int length = 1; length <= k; length++) {
                remainder = (remainder * 10 + 1) % k;
                if (remainder == 0) {
                    return length;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int smallestRepunitDivByK(int K) {
            if (K < 1) {
                return 0;
            }

            if (K % 2 == 0) {
                // sequence of 1-s not divisible by even number
                return -1;
            }

            int remainder = 1;
            int count = 1;
            Set<Integer> seen = new HashSet<>();

            while (remainder % K != 0) {
                int number = remainder * 10;
                number += 1;

                remainder = number % K;
                if (seen.contains(remainder)) {
                    return -1;
                } else {
                    seen.add(remainder);
                }

                count++;
            }

            return count;
        }
    }
}

//    1015. Smallest Integer Divisible by K
//    Medium
//    Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.
//
//    Return the length of n. If there is no such n, return -1.
//
//    Note: n may not fit in a 64-bit signed integer.
//
//
//
//    Example 1:
//
//    Input: k = 1
//    Output: 1
//    Explanation: The smallest answer is n = 1, which has length 1.
//    Example 2:
//
//    Input: k = 2
//    Output: -1
//    Explanation: There is no such positive integer n divisible by 2.
//    Example 3:
//
//    Input: k = 3
//    Output: 3
//    Explanation: The smallest answer is n = 111, which has length 3.
//
//
//    Constraints:
//
//    1 <= k <= 105
