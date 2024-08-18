package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeSet;

public class UglyNumberII {
    class Solution {
        public int nthUglyNumber(int n) {
            if (n < 1) {
                return 0;
            }

            TreeSet<Long> set = new TreeSet<>();
            set.add(1L);

            Long current = 1L;
            for (int i = 1; i <= n; i++) {
                current = set.pollFirst();

                set.add(current * 2);
                set.add(current * 3);
                set.add(current * 5);
            }

            return current.intValue();
        }
    }

    class Solution_Correct_1 {
        public int nthUglyNumber(int n) {
            if (n <= 0) {
                return 0;
            }

            int[] ugly = new int[n + 1];
            ugly[1] = 1;

            int twoIndex = 1;
            int threeIndex = 1;
            int fiveIndex = 1;

            for (int count = 2; count <= n; count++) {
                int twoMultiple = ugly[twoIndex] * 2;
                int threeMultiple = ugly[threeIndex] * 3;
                int fiveMultiple = ugly[fiveIndex] * 5;

                int min = Math.min(twoMultiple,
                        Math.min(threeMultiple, fiveMultiple));
                ugly[count] = min;

                if (min == twoMultiple) {
                    twoIndex++;
                }

                if (min == threeMultiple) {
                    threeIndex++;
                }

                if (min == fiveMultiple) {
                    fiveIndex++;
                }
            }

            return ugly[n];
        }
    }
}

//    264. Ugly Number II
//    Medium
//    An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
//
//    Given an integer n, return the nth ugly number.
//
//
//
//    Example 1:
//
//    Input: n = 10
//    Output: 12
//    Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
//    Example 2:
//
//    Input: n = 1
//    Output: 1
//    Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
//
//
//    Constraints:
//
//    1 <= n <= 1690
