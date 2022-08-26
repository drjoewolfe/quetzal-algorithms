package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReorderedPowerOf2 {
    class Solution {
        public boolean reorderedPowerOf2(int N) {
            if(N <= 0) {
                return false;
            }

            int[] digitCounts = getDigitCounts(N);
            for(int i = 0; i < 32; i++) {
                int[] powerOfTwoDigitCount = getDigitCounts(1 << i);
                if(Arrays.equals(digitCounts, powerOfTwoDigitCount)) {
                    return true;
                }
            }

            return false;
        }

        private int[] getDigitCounts(int n) {
            int[] digitCounts = new int[10];
            while(n > 0) {
                digitCounts[n % 10]++;
                n /= 10;
            }

            return digitCounts;
        }
    }

    class Solution_Permutation {
        public boolean reorderedPowerOf2(int N) {
            if(N <= 0) {
                return false;
            }

            List<Integer> digits = getDigits(N);
            return permuteForPowerOf2(digits, 0);
        }

        private boolean permuteForPowerOf2(List<Integer> digits, int index) {
            if(index == digits.size()) {
                return isPowerOf2(digits);
            }

            int a = digits.get(index);
            for(int i = index; i < digits.size(); i++) {
                int b = digits.get(i);
                if(index == 0 && b == 0) {
                    continue;
                }

                swap(digits, index, i);
                if(permuteForPowerOf2(digits, index + 1)) {
                    return true;
                }

                swap(digits, i, index);
            }

            return false;
        }

        private List<Integer> getDigits(int N) {
            List<Integer> digits = new ArrayList<>();

            while(N > 0) {
                digits.add(N % 10);
                N /= 10;
            }

            return digits;
        }

        private void swap(List<Integer> list, int i, int j) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        private boolean isPowerOf2(List<Integer> digits) {
            int x = 0;
            for(int i = 0; i < digits.size(); i++) {
                x *= 10;
                x += digits.get(i);
            }

            while(x > 1) {
                if(x % 2 != 0) {
                    return false;
                }

                x /= 2;
            }

            return true;
        }
    }
}

//    869. Reordered Power of 2
//    Medium
//    You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
//
//    Return true if and only if we can do this so that the resulting number is a power of two.
//
//
//
//    Example 1:
//
//    Input: n = 1
//    Output: true
//    Example 2:
//
//    Input: n = 10
//    Output: false
//
//
//    Constraints:
//
//    1 <= n <= 109