package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CheckIfNumberHasEqualDigitCountAndDigitValue {
    class Solution {
        public boolean digitCount(String num) {
            if(num == null || num.length() == 0) {
                return true;
            }

            Map<Integer, Integer> frequencies = new HashMap<>();
            for(int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                int digit = c - '0';

                frequencies.put(digit, frequencies.getOrDefault(digit, 0) + 1);
            }

            for(int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                int digit = c - '0';

                if(!frequencies.containsKey(i)) {
                    if(digit > 0) {
                        return false;
                    }
                } else {
                    int count = frequencies.get(i);
                    if(count != digit) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}

//    2283. Check if Number Has Equal Digit Count and Digit Value
//    Easy
//    You are given a 0-indexed string num of length n consisting of digits.
//
//    Return true if for every index i in the range 0 <= i < n, the digit i occurs num[i] times in num, otherwise return false.
//
//
//
//    Example 1:
//
//    Input: num = "1210"
//    Output: true
//    Explanation:
//    num[0] = '1'. The digit 0 occurs once in num.
//    num[1] = '2'. The digit 1 occurs twice in num.
//    num[2] = '1'. The digit 2 occurs once in num.
//    num[3] = '0'. The digit 3 occurs zero times in num.
//    The condition holds true for every index in "1210", so return true.
//    Example 2:
//
//    Input: num = "030"
//    Output: false
//    Explanation:
//    num[0] = '0'. The digit 0 should occur zero times, but actually occurs twice in num.
//    num[1] = '3'. The digit 1 should occur three times, but actually occurs zero times in num.
//    num[2] = '0'. The digit 2 occurs zero times in num.
//    The indices 0 and 1 both violate the condition, so return false.
//
//
//    Constraints:
//
//    n == num.length
//    1 <= n <= 10
//    num consists of digits.