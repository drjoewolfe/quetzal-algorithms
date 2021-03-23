package org.jwolfe.quetzal.algorithms.lc;

public class IntegerToRoman {
    public String intToRoman(int num) {
        if(num <= 0){
            return "";
        }

        StringBuilder builder = new StringBuilder();

        int n = num / 1000;
        num %= 1000;
        while(n > 0) {
            builder.append("M");
            n--;
        }

        if(num >= 900) {
            builder.append("CM");
            num -= 900;
        }

        n = num / 500;
        num %= 500;
        while(n > 0) {
            builder.append("D");
            n--;
        }

        if(num >= 400) {
            builder.append("CD");
            num -= 400;
        }

        n = num / 100;
        num %= 100;
        while(n > 0) {
            builder.append("C");
            n--;
        }

        if(num >= 90) {
            builder.append("XC");
            num -= 90;
        }

        n = num / 50;
        num %= 50;
        while(n > 0) {
            builder.append("L");
            n--;
        }

        if(num >= 40) {
            builder.append("XL");
            num -= 40;
        }

        n = num / 10;
        num %= 10;
        while(n > 0) {
            builder.append("X");
            n--;
        }

        if(num == 9) {
            builder.append("IX");
            num -= 9;
        }

        n = num / 5;
        num %= 5;
        while(n > 0) {
            builder.append("V");
            n--;
        }

        if(num == 4) {
            builder.append("IV");
            num -= 4;
        }

        while(num > 0) {
            builder.append("I");
            num--;
        }

        return builder.toString();
    }
}

//    12. Integer to Roman
//    Medium
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
//    Given an integer, convert it to a roman numeral.
//
//
//
//    Example 1:
//
//    Input: num = 3
//    Output: "III"
//    Example 2:
//
//    Input: num = 4
//    Output: "IV"
//    Example 3:
//
//    Input: num = 9
//    Output: "IX"
//    Example 4:
//
//    Input: num = 58
//    Output: "LVIII"
//    Explanation: L = 50, V = 5, III = 3.
//    Example 5:
//
//    Input: num = 1994
//    Output: "MCMXCIV"
//    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
//
//
//    Constraints:
//
//    1 <= num <= 3999
