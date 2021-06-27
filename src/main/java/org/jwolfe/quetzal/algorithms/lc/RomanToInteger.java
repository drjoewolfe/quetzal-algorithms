package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    class Solution {
        private Map<Character, Integer> romanMappings;

        public int romanToInt(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            initializeMappings();

            int n = s.length();
            int sum = romanMappings.get(s.charAt(n - 1));
            for (int i = n - 2; i >= 0; i--) {
                char c = s.charAt(i);
                char pc = s.charAt(i + 1);

                int cv = romanMappings.get(c);
                int pcv = romanMappings.get(pc);

                if (cv < pcv) {
                    sum -= cv;
                } else {
                    sum += cv;
                }
            }

            return sum;
        }

        private void initializeMappings() {
            romanMappings = new HashMap<>();
            romanMappings.put('I', 1);
            romanMappings.put('V', 5);
            romanMappings.put('X', 10);
            romanMappings.put('L', 50);
            romanMappings.put('C', 100);
            romanMappings.put('D', 500);
            romanMappings.put('M', 1000);
        }
    }

    class Solution_Classic {
        public int romanToInt(String s) {
            int sum = 0;

            if (s == null) {
                return 0;
            }

            int index = 0;
            int length = s.length();
            while (index < length) {
                char c1 = s.charAt(index);

                if (c1 == 'I') {
                    if (index < length - 1) {
                        char c2 = s.charAt(index + 1);
                        if (c2 == 'V') {
                            sum += 4;
                            index++;
                        } else if (c2 == 'X') {
                            sum += 9;
                            index++;
                        } else {
                            sum += 1;
                        }
                    } else {
                        sum += 1;
                    }

                } else if (c1 == 'V') {
                    sum += 5;
                } else if (c1 == 'X') {
                    if (index < length - 1) {
                        char c2 = s.charAt(index + 1);
                        if (c2 == 'L') {
                            sum += 40;
                            index++;
                        } else if (c2 == 'C') {
                            sum += 90;
                            index++;
                        } else {
                            sum += 10;
                        }
                    } else {
                        sum += 10;
                    }
                } else if (c1 == 'L') {
                    sum += 50;
                } else if (c1 == 'C') {
                    if (index < length - 1) {
                        char c2 = s.charAt(index + 1);
                        if (c2 == 'D') {
                            sum += 400;
                            index++;
                        } else if (c2 == 'M') {
                            sum += 900;
                            index++;
                        } else {
                            sum += 100;
                        }
                    } else {
                        sum += 100;
                    }
                } else if (c1 == 'D') {
                    sum += 500;
                } else if (c1 == 'M') {
                    sum += 1000;
                }

                index++;
            }

            return sum;
        }
    }
}

//    13. Roman to Integer
//    Easy
//    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//    Symbol       Value
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000
//    For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
//
//    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//    I can be placed before V (5) and X (10) to make 4 and 9.
//    X can be placed before L (50) and C (100) to make 40 and 90.
//    C can be placed before D (500) and M (1000) to make 400 and 900.
//    Given a roman numeral, convert it to an integer.
//
//
//
//    Example 1:
//
//    Input: s = "III"
//    Output: 3
//    Example 2:
//
//    Input: s = "IV"
//    Output: 4
//    Example 3:
//
//    Input: s = "IX"
//    Output: 9
//    Example 4:
//
//    Input: s = "LVIII"
//    Output: 58
//    Explanation: L = 50, V= 5, III = 3.
//    Example 5:
//
//    Input: s = "MCMXCIV"
//    Output: 1994
//    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
//
//
//    Constraints:
//
//    1 <= s.length <= 15
//    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
//    It is guaranteed that s is a valid roman numeral in the range [1, 3999].