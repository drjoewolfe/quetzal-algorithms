package org.jwolfe.quetzal.algorithms.lc;

public class RomanToInteger {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int val = 0;
        int i = 0;
        int n = s.length();
        while(i < n) {
            char c = s.charAt(i++);
            if(i < n) {
                char d = s.charAt(i);
                if(c == 'I') {
                    if(d == 'V') {
                        val+=4;
                        i++;
                    } else if(d == 'X') {
                        val+=9;
                        i++;
                    } else {
                        val++;
                    }
                } else if(c == 'X') {
                    if(d == 'L') {
                        val+=40;
                        i++;
                    } else if(d == 'C') {
                        val+=90;
                        i++;
                    } else {
                        val+=10;
                    }
                } else if(c == 'C') {
                    if(d == 'D') {
                        val+=400;
                        i++;
                    } else if(d == 'M') {
                        val+=900;
                        i++;
                    } else {
                        val+=100;
                    }
                } else {
                    val += getValue(c);
                }
            } else {
                val += getValue(c);
            }
        }

        return val;
    }

    private int getValue(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
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