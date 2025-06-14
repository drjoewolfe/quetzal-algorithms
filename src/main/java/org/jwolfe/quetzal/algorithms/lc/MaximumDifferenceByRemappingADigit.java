package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumDifferenceByRemappingADigit {
    class Solution {
        public int minMaxDifference(int num) {
            String s = Integer.toString(num);

            int index = 0;
            int n = s.length();
            while (index < n && s.charAt(index) == '9') {
                index++;
            }

            String maxString = s;
            if (index < n) {
                char mc = s.charAt(index);
                maxString = s.replace(mc, '9');
            }

            char sc = s.charAt(0);
            String minString = s.replace(sc, '0');

            int maxNum = Integer.parseInt(maxString);
            int minNum = Integer.parseInt(minString);

            return maxNum - minNum;
        }
    }

    class Solution_Correct_1 {
        public int minMaxDifference(int num) {
            List<Integer> digitList = getList(num);

            // Find first non-9 number
            int digitForMax = -1;
            for (int i = 0; i < digitList.size(); i++) {
                int digit = digitList.get(i);
                if (digit != 9) {
                    digitForMax = digit;
                    break;
                }
            }

            int maxNumber = (digitForMax != -1) ? replaceAndGenerateNumber(digitList, digitForMax, 9) : num;

            int digitForMin = digitList.get(0);
            int minNumber = replaceAndGenerateNumber(digitList, digitForMin, 0);

            return maxNumber - minNumber;
        }

        private List<Integer> getList(int num) {
            List<Integer> list = new ArrayList<>();

            while (num > 0) {
                int digit = num % 10;
                list.add(digit);

                num /= 10;
            }

            Collections.reverse(list);
            return list;
        }

        private int replaceAndGenerateNumber(List<Integer> list, int from, int to) {
            int number = 0;

            for (int i = 0; i < list.size(); i++) {
                int digit = list.get(i);

                if (digit == from) {
                    digit = to;
                }

                number *= 10;
                number += digit;
            }

            return number;
        }
    }
}

//    2566. Maximum Difference by Remapping a Digit
//    Easy
//    You are given an integer num. You know that Danny Mittal will sneakily remap one of the 10 possible digits (0 to 9) to another digit.
//
//    Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.
//
//    Notes:
//
//    When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
//    Danny can remap a digit to itself, in which case num does not change.
//    Danny can remap different digits for obtaining minimum and maximum values respectively.
//    The resulting number after remapping can contain leading zeroes.
//    We mentioned "Danny Mittal" to congratulate him on being in the top 10 in Weekly Contest 326.
//
//
//    Example 1:
//
//    Input: num = 11891
//    Output: 99009
//    Explanation:
//    To achieve the maximum value, Danny can remap the digit 1 to the digit 9 to yield 99899.
//    To achieve the minimum value, Danny can remap the digit 1 to the digit 0, yielding 890.
//    The difference between these two numbers is 99009.
//    Example 2:
//
//    Input: num = 90
//    Output: 99
//    Explanation:
//    The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
//    Thus, we return 99.
//
//
//    Constraints:
//
//    1 <= num <= 108