package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class Finding3DigitEvenNumbers {
    class Solution {
        public int[] findEvenNumbers(int[] digits) {
            if(digits == null || digits.length < 3) {
                return new int[0];
            }

            int[] counts = new int[10];
            for(int i = 0; i < digits.length; i++) {
                int val = digits[i];
                counts[val]++;
            }

            List<Integer> list = new ArrayList<>();
            for(int val = 100; val < 999; val += 2) {
                int num = val;
                int digit3 = num % 10;
                num /= 10;

                int digit2 = num % 10;
                num /= 10;

                int digit1 = num;

                int[] tempCounts = counts.clone();
                if(tempCounts[digit1] > 0) {
                    tempCounts[digit1]--;
                } else {
                    continue;
                }

                if(tempCounts[digit2] > 0) {
                    tempCounts[digit2]--;
                } else {
                    continue;
                }

                if(tempCounts[digit3] == 0) {
                    continue;
                }

                list.add(val);
            }

            int[] results = new int[list.size()];
            for(int i = 0; i < list.size(); i++) {
                results[i] = list.get(i);
            }

            return results;
        }
    }

// [2,1,3,0]
// [0,7,1]
}

//    2094. Finding 3-Digit Even Numbers
//    Easy
//    You are given an integer array digits, where each element is a digit. The array may contain duplicates.
//
//    You need to find all the unique integers that follow the given requirements:
//
//    The integer consists of the concatenation of three elements from digits in any arbitrary order.
//    The integer does not have leading zeros.
//    The integer is even.
//    For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
//
//    Return a sorted array of the unique integers.
//
//
//
//    Example 1:
//
//    Input: digits = [2,1,3,0]
//    Output: [102,120,130,132,210,230,302,310,312,320]
//    Explanation: All the possible integers that follow the requirements are in the output array.
//    Notice that there are no odd integers or integers with leading zeros.
//    Example 2:
//
//    Input: digits = [2,2,8,8,2]
//    Output: [222,228,282,288,822,828,882]
//    Explanation: The same digit can be used as many times as it appears in digits.
//    In this example, the digit 8 is used twice each time in 288, 828, and 882.
//    Example 3:
//
//    Input: digits = [3,7,5]
//    Output: []
//    Explanation: No even integers can be formed using the given digits.
//
//
//    Constraints:
//
//    3 <= digits.length <= 100
//    0 <= digits[i] <= 9
